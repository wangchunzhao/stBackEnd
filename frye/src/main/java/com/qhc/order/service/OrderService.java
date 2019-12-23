package com.qhc.order.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.qhc.order.domain.CharacteristicDto;
import com.qhc.order.domain.DeliveryAddressDto;
import com.qhc.order.domain.ItemDto;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderOption;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.OrderVersion;
import com.qhc.order.domain.PaymentPlan;
import com.qhc.order.domain.bpm.BpmOrder;
import com.qhc.order.domain.bpm.OrderHeader;
import com.qhc.order.domain.bpm.OrderItem;
import com.qhc.order.domain.bpm.OrderMargin;
import com.qhc.order.domain.sap.SapOrder;
import com.qhc.order.domain.sap.SapOrderCharacteristics;
import com.qhc.order.domain.sap.SapOrderHeader;
import com.qhc.order.domain.sap.SapOrderItem;
import com.qhc.order.domain.sap.SapOrderPlan;
import com.qhc.order.domain.sap.SapOrderPrice;
import com.qhc.order.entity.Attachment;
import com.qhc.order.entity.BillingPlan;
import com.qhc.order.entity.BpmDicision;
import com.qhc.order.entity.Characteristics;
import com.qhc.order.entity.DeliveryAddress;
import com.qhc.order.entity.Item;
import com.qhc.order.entity.Order;
import com.qhc.order.entity.OrderInfo;
import com.qhc.order.mapper.AttachmentMapper;
import com.qhc.order.mapper.BillingPlanMapper;
import com.qhc.order.mapper.BpmDicisionMapper;
import com.qhc.order.mapper.CharacteristicsMapper;
import com.qhc.order.mapper.DeliveryAddressMapper;
import com.qhc.order.mapper.ItemMapper;
import com.qhc.order.mapper.OrderInfoMapper;
import com.qhc.order.mapper.OrderMapper;
import com.qhc.sap.dao.CurrencyRepository;
import com.qhc.sap.dao.PaymentTermRepository;
import com.qhc.sap.dao.SalesGroupRepository;
import com.qhc.sap.dao.SalesOfficeRepository;
import com.qhc.sap.dao.SalesTypeRepository;
import com.qhc.sap.dao.SapMaterialGroupsRepository;
import com.qhc.sap.dao.TerminalIndustryCodeRepository;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.sap.entity.PaymentTerm;
import com.qhc.sap.entity.SalesType;
import com.qhc.sap.entity.TermianlIndustryCode;
import com.qhc.sap.mapper.SapViewMapper;
import com.qhc.system.dao.AreaRepository;
import com.qhc.system.dao.CityRepository;
import com.qhc.system.dao.ProvinceRepository;
import com.qhc.system.dao.SettingsRepository;
import com.qhc.system.entity.Area;
import com.qhc.system.entity.City;
import com.qhc.system.entity.Province;
import com.qhc.system.entity.Settings;
import com.qhc.utils.HttpUtil;

@Service
public class OrderService {
	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Value("${sap.paymentplan.addr}")
	String paymentplanUrlStr;

	@Value("${sap.sapCreateOrder.addr}")
	private String orderCreationUrl;
	
	@Value("${sap.sapChangeOrder.addr}")
	private String orderChangeUrl;

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private BpmDicisionMapper dicisionMapper;

	@Autowired
	BillingPlanMapper billingPlanMapper;

	@Autowired
	AttachmentMapper attachmentMapper;

	@Autowired
	DeliveryAddressMapper deliveryAddressMapper;

	@Autowired
	ItemMapper itemMapper;

	@Autowired
	CharacteristicsMapper characteristicsMapper;

	@Autowired
	private SalesTypeRepository salesTypeRepo;

	@Autowired
	private AreaRepository distinctRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private ProvinceRepository provinceRepo;

	@Autowired
	private TerminalIndustryCodeRepository industryCodeRepo;

	@Autowired
	private SalesTypeRepository saleTypeRepo;

	@Autowired
	private PaymentTermRepository paymentRepo;

	@Autowired
	private ConstantService constService;

	@Autowired
	private BayernService<SapOrder> bayernService;

	@Autowired
	private SettingsRepository settingsRepository;

	@Autowired
	private SapMaterialGroupsRepository materialGroupsRepository;

	@Autowired
	private SapViewMapper sapViewMapper;

	private final static String ORDER_CREATION_SAP = "order/create/sapOrder";

	private final static String COST_INSTALLATION = "BG1GDA00000-X";
	private final static String COST_MATERIALS = "BG1GDB00000-X";
	private final static String COST_ELETRICAL = "BG1R8J00000-X";
	private final static String COST_COOLROOM = "BG1R8R00000-X";
	private final static String COST_MAINTANANCE = "BG1R8K00000-X";

	public OrderDto save(String user, OrderDto orderDto) throws Exception {
		OrderInfo orderInfo = new OrderInfo();
		
		// calculate gross profit margin
		List<MaterialGroups> margin = calcGrossProfit(orderDto);
		orderDto.setGrossProfitMargin(new ObjectMapper().writeValueAsString(margin));
		
		BeanUtils.copyProperties(orderInfo, orderDto);
		
		if (orderDto.getId() == null) {
			if (orderDto.getOrderId() == null) {
				Order order = new Order();
				BeanUtils.copyProperties(order, orderDto);
				orderMapper.insert(order);

				orderInfo.setOrderId(order.getId());
			}

			orderInfo.setCreater(user);
			orderInfo.setUpdater(user);
			orderInfoMapper.insert(orderInfo);
			
			orderInfo.setId(orderInfo.getId());
		} else {
			orderInfo.setUpdater(user);
			orderInfoMapper.update(orderInfo);
		}
		orderDto.setId(orderInfo.getId());

		saveAttachments(orderDto);
		saveBillingPlans(orderDto);
		saveDeliveryAddresses(orderDto);

		saveItems(orderDto);
		
		orderDto = this.findOrder(orderInfo.getId());

		return orderDto;
	}
	
	/**
	 * 订单变更，BPM审批通过后的订单修改，产生新的版本
	 * 
	 * @param user
	 * @param orderInfoId
	 * @return
	 * @throws Exception 
	 */
	public OrderDto upgrade(String user, Integer orderInfoId) throws Exception {
		OrderDto order = this.findOrder(orderInfoId);
		
		order.setId(null);
		// TODO new version
		order.setVersion("" + System.currentTimeMillis());
		order.setStatus(OrderDto.ORDER_STATUS_DRAFT);
		order.setSubmitBpmTime(null);
		order.setSubmitTime(null);
		
		order = this.save(user, order);
		
		return order;
	}

	/**
	 * 保存订单附件信息
	 * 
	 * @param order
	 */
	private void saveAttachments(OrderDto orderDto) {
		Integer orderInfoId = orderDto.getId();
		attachmentMapper.deleteByOrderInfoId(orderInfoId);
		List<Attachment> attachments = orderDto.getAttachments();
		for (Attachment attachment : attachments) {
			attachment.setId(null);
			attachment.setOrderInfoId(orderInfoId);
			
			attachmentMapper.insert(attachment);
		}
	}

	private void saveBillingPlans(OrderDto orderDto) {
		Integer orderInfoId = orderDto.getId();
		billingPlanMapper.deleteByOrderInfoId(orderInfoId);
		List<BillingPlan> payments = orderDto.getPayments();
		for (BillingPlan billingPlan : payments) {
			billingPlan.setId(null);
			billingPlan.setOrderInfoId(orderInfoId);
			
			billingPlanMapper.insert(billingPlan);
		}
	}

	private void saveDeliveryAddresses(OrderDto orderDto) {
		Integer orderInfoId = orderDto.getId();
		deliveryAddressMapper.deleteByOrderInfoId(orderInfoId);
		List<BillingPlan> payments = orderDto.getPayments();
		for (BillingPlan billingPlan : payments) {
			billingPlan.setId(null);
			billingPlan.setOrderInfoId(orderInfoId);
			
			billingPlanMapper.insert(billingPlan);
		}
	}

	private void saveItems(OrderDto orderDto) throws Exception {
		Integer orderInfoId = orderDto.getId();
		deleteItems(orderInfoId);
		List<ItemDto> items = orderDto.getItems();
		for (ItemDto itemDto : items) {
			Item item = new Item();
			
			BeanUtils.copyProperties(item, orderDto);
			item.setOrderInfoId(orderInfoId);
			item.setId(null);
			
			itemMapper.insert(item);
			
			List<CharacteristicDto> configs = itemDto.getConfigs();
			for (CharacteristicDto dto : configs) {
				Characteristics c = new Characteristics();
				
				BeanUtils.copyProperties(c, dto);
				c.setId(null);
				
				characteristicsMapper.insert(c);
			}
		} 
	}
	
	private void deleteItems(Integer orderInfoId) {
		// delete k_characteristics
		this.characteristicsMapper.deleteByOrderInfoId(orderInfoId);
		
		// delete order item
		itemMapper.deleteByOrderInfoId(orderInfoId);
	}

	/**
	 * 提交订单
	 * 
	 * @param order
	 * @throws Exception 
	 */
	public void submit(String user, OrderDto order) throws Exception {
		String status = order.getStatus();
		switch (status) {
			case OrderDto.ORDER_STATUS_DRAFT: 
			case OrderDto.ORDER_STATUS_REJECT: 
				if (order.getIsB2c() == 1) {
					order.setStatus(OrderDto.ORDER_STATUS_B2C);
				} else if (order.getCustomerClazz().equals(OrderDto.ORDER_CUSTOMER_DEALER_CODE)) { 
					// 大客户
					order.setStatus(OrderDto.ORDER_STATUS_ENGINER);
				} else {
					order.setStatus(OrderDto.ORDER_STATUS_MANAGER);
				}
				break;
			case OrderDto.ORDER_STATUS_B2C: 
				if (order.getCustomerClazz().equals(OrderDto.ORDER_CUSTOMER_DEALER_CODE)) { 
					// 大客户
					order.setStatus(OrderDto.ORDER_STATUS_ENGINER);
				} else {
					order.setStatus(OrderDto.ORDER_STATUS_MANAGER);
				}
				break;
			case OrderDto.ORDER_STATUS_ENGINER: 
				order.setStatus(OrderDto.ORDER_STATUS_MANAGER);
				break;
			default:
				throw new RuntimeException("Unknown status for submit order.");
		}
		order = save(user, order);
	}

	/**
	 * 
	 * @return sales type
	 */
	public List<SalesType> getSalesTypes() {
		return salesTypeRepo.findAll();
	}

	public List<MaterialGroups> calcGrossProfit(String sequenceNumber, String version) throws Exception {
		OrderDto order = this.findOrder(sequenceNumber, version);
		String json = order.getGrossProfitMargin();
		
		ObjectMapper mapper = new ObjectMapper();
		JavaType type = mapper.getTypeFactory().constructCollectionLikeType(List.class, MaterialGroups.class);
		return new ObjectMapper().readValue(json, type);
	}

	// sap_material_group分组
	public List<MaterialGroups> calcGrossProfit(OrderDto order) {
		// 查询所有物料类型sap_material_group isenable != 0
		List<MaterialGroups> groups = materialGroupsRepository.findByIsenableNotOrderByCode(0);
		List<ItemDto> items = new ArrayList<ItemDto>();

		items = order.getItems();

		// 税率
		Double taxRate = order.getTaxRate();

		// 毛利表
		BigDecimal sumAmount = BigDecimal.ZERO;// 金额
		BigDecimal sumExcludingTaxAmount = BigDecimal.ZERO;// 不含税金额
		BigDecimal sumWtwCost = BigDecimal.ZERO;// wtw成本
		BigDecimal sumCost = BigDecimal.ZERO;// 成本
		BigDecimal sumWtwGrossProfit = BigDecimal.ZERO;// 毛利
		BigDecimal sumGrossProfit = BigDecimal.ZERO;// 毛利
		Double sumWtwGrossProfitMargin = 0D;// 毛利率
		Double sumGrossProfitMargin = 0D;// 毛利率
		if (items != null && items.size() > 0) {
			for (MaterialGroups entity : groups) {
				BigDecimal amount = BigDecimal.ZERO;// 金额
				BigDecimal excludingTaxAmount = BigDecimal.ZERO;// 不含税金额
				BigDecimal wtwcost = BigDecimal.ZERO;// wtw成本
				BigDecimal cost = BigDecimal.ZERO;// 成本
				BigDecimal wtwgrossProfit = BigDecimal.ZERO;// wtw毛利
				BigDecimal grossProfit = BigDecimal.ZERO;// 毛利
				Double wtwgrossProfitMargin = 0D;// wtw毛利率
				Double grossProfitMargin = 0D;// 毛利率

				for (ItemDto item : items) {
					if (item.getMaterialGroupCode().equals(entity.getCode())) {
						// 总金额
						BigDecimal saleAmount = BigDecimal
								.valueOf((item.getActualPrice() + item.getOptionalActualPrica()) * item.getQuantity());
						amount = amount.add(saleAmount);
						// 总金额减去税金 = 不含税金额
						BigDecimal taxAmount = saleAmount.multiply(BigDecimal.valueOf(taxRate));
						excludingTaxAmount = excludingTaxAmount.add(saleAmount.subtract(taxAmount));
						// wtw成本
						wtwcost = wtwcost.add(BigDecimal.valueOf(item.getStandardPrice() * item.getQuantity()));
						// 毛利
						wtwgrossProfit = excludingTaxAmount.subtract(wtwcost);
						cost = cost.add(BigDecimal.valueOf(item.getTransationPrice() * item.getQuantity()));
						// 毛利
						grossProfit = excludingTaxAmount.subtract(cost);

					}
				}
				// WTW毛利率
				wtwgrossProfitMargin = this.calculateGrossProfit(excludingTaxAmount, wtwcost);
				// 毛利率
				grossProfitMargin = this.calculateGrossProfit(excludingTaxAmount, cost);

				sumAmount = sumAmount.add(amount);
				sumExcludingTaxAmount = sumExcludingTaxAmount.add(excludingTaxAmount);
				sumWtwCost = sumWtwCost.add(wtwcost);
				sumCost = sumCost.add(cost);
				sumWtwGrossProfit = sumWtwGrossProfit.add(wtwgrossProfit);
				sumGrossProfit = sumGrossProfit.add(grossProfit);

				entity.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
				entity.setExcludingTaxAmount(excludingTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
				entity.setWtwCost(wtwcost.setScale(2, BigDecimal.ROUND_HALF_UP));
				entity.setCost(cost.setScale(2, BigDecimal.ROUND_HALF_UP));
				entity.setWtwGrossProfit(wtwgrossProfit.setScale(2, BigDecimal.ROUND_HALF_UP));
				entity.setGrossProfit(grossProfit.setScale(2, BigDecimal.ROUND_HALF_UP));
				entity.setWtwGrossProfitMargin(
						BigDecimal.valueOf(wtwgrossProfitMargin).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
				entity.setGrossProfitMargin(
						BigDecimal.valueOf(grossProfitMargin).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}

		} else {
			throw new RuntimeException("订单行项目为空，无法计算毛利率！");
		}

		MaterialGroups sumssg = new MaterialGroups();
		sumssg.setAmount(sumAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setExcludingTaxAmount(sumExcludingTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setWtwCost(sumWtwCost.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setCost(sumCost.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setWtwGrossProfit(sumWtwGrossProfit.setScale(4, BigDecimal.ROUND_HALF_UP));
		sumssg.setGrossProfit(sumGrossProfit.setScale(4, BigDecimal.ROUND_HALF_UP));
		// WTW毛利率
		sumWtwGrossProfitMargin = this.calculateGrossProfit(sumExcludingTaxAmount, sumWtwCost);
		// 毛利率
		sumGrossProfitMargin = this.calculateGrossProfit(sumExcludingTaxAmount, sumCost);
		sumssg.setWtwGrossProfitMargin(sumWtwGrossProfitMargin);
		sumssg.setGrossProfitMargin(sumGrossProfitMargin);
		sumssg.setCode("sum");
		sumssg.setName("合计");

		groups.add(sumssg);

		return groups;
	}

	public Double calculateGrossProfit(BigDecimal afterTaxAmount, BigDecimal cost) {
		Double v = 0D;
		try {
			v = (afterTaxAmount.subtract(cost)).divide(afterTaxAmount, 4, BigDecimal.ROUND_HALF_UP)
					.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		}

		return v;
	}

	/**
	 * 
	 * @return
	 */
	public OrderOption getOrderOption() {
		OrderOption oo = new OrderOption();
		//
		List<Province> bps = provinceRepo.findAll();
		Map<String, String> provinces = oo.getProvinces();
		for (Province bp : bps) {
			provinces.put(bp.getCode(), bp.getName());
		}
		//
		List<City> bcs = cityRepo.findAll();
		Map<String, Map<String, String>> citys = oo.getCitys();
		for (City bc : bcs) {
			String pcode = bc.getProvinceCode();
			Map<String, String> vals = new HashMap<String, String>();
			vals.put(bc.getCode(), bc.getName());
			if (citys.putIfAbsent(pcode, vals) != null) {
				citys.get(pcode).put(bc.getCode(), bc.getName());
			}

		}
		//
		List<Area> bas = distinctRepo.findAll();
		Set<Area> distincts = oo.getDistricts();
		distincts.addAll(bas);
		//
		Map<String, String> incs = oo.getTermialClass();
		List<TermianlIndustryCode> tics = industryCodeRepo.findAll();
		for (TermianlIndustryCode tic : tics) {
			incs.put(tic.getCode(), tic.getName());
		}
		//
		Map<String, String> st = oo.getSaleTypes();
		List<SalesType> dsts = saleTypeRepo.findAll();
		for (SalesType dst : dsts) {
			st.put(dst.getCode(), dst.getName());
		}
		//
//		Map<String, Map<String, String>> offices = oo.getOffices();
//		List<SapSalesOffice> ssos = officeRepo.findAll();
//		for (SapSalesOffice so : ssos) {
//			String tcode = so.getTypeCode();
//			Map<String, String> vals = new HashMap<String, String>();
//			vals.put(so.getCode(), so.getName());
//			if (offices.putIfAbsent(tcode, vals) != null) {
//				offices.get(tcode).put(so.getCode(), so.getName());
//			}
//		}
//		//
//		Map<String, Map<String, String>> groups = oo.getGroups();
//		List<SapSalesGroup> ssgs = groupsRepo.findAll();
//		for (SapSalesGroup ssg : ssgs) {
//			String ocode = ssg.getOfficeCode();
//			Map<String, String> vals = new HashMap<String, String>();
//			vals.put(ssg.getCode(), ssg.getName());
//			if (groups.putIfAbsent(ocode, vals) != null) {
//				groups.get(ocode).put(ssg.getCode(), ssg.getName());
//			}
//		}
		//
		Map<String, Double> taxRate = oo.getTaxRate();
//		taxRate.put("10", 0.13);
//		taxRate.put("20", 0.0);
//		taxRate.put("30", 0.13);

		//
		Map<String, String> payments = oo.getPaymentType();
		Map<String, String> bidding = oo.getBiddingPlan();
		List<PaymentTerm> pts = paymentRepo.findAll();
		for (PaymentTerm pt : pts) {
			if (pt.getIsPaymentTerm()) {
				payments.put(pt.getCode(), pt.getName());
			} else {
				bidding.put(pt.getCode(), pt.getName());
			}
		}
		//
		oo.setOrderTypes(constService.getOrderTypes());

		// 终端客户性质选择 Industry Codes For dealer
		oo.setDealerIndustryCodes(constService.findFordealerIndustryCodes());

		// 物料评估类MaterialGroups
		oo.setMaterialGroups(constService.findMaterialGroups());

		// 物料在订单上的分类MaterialGroupOrders
		oo.setMaterialGroupOrders(constService.findMaterialGroupOrders());

		// 物料评估类 与 物料在订单上的分类映射
		oo.setMaterialGroupMapGroupOrder(constService.findMaterialGroupMapGroupOrders());

		// 销售区域Sales Offices
		oo.setOffices(constService.findSalesOffices());

		// 销售区域Sales Goups
		oo.setGroups(constService.findSalesGroups());

		// 运输方式ShippingTypes
		oo.setShippingTypes(constService.findShippingTypes());

		// 收货方式ReceiveTerms
		oo.setReceiveTerms(constService.findReceiveTerms());

		// 国际贸易条款
		oo.setIntercoms(constService.findIncoterms());

		// 币种
		oo.setExchangeRate(constService.findCurrencies());

		// 安装方式
		oo.setInstallationTerms(constService.findInstallationTerms());

		// 标准折扣，Code：0d5d7ea6b2605e38b4f3dbd394168b3b
		Settings p = settingsRepository.findEnabledInfo("0d5d7ea6b2605e38b4f3dbd394168b3b");
		if (p != null) {
			oo.setStandardDiscount(p.getsValue());
		}

		// 税率，Code：1c20b7ffba1a59faa081324eb34844a5
		p = settingsRepository.findEnabledInfo("1c20b7ffba1a59faa081324eb34844a5");
		if (p != null) {
			taxRate.put("10", Double.valueOf(p.getsValue()));
			taxRate.put("30", Double.valueOf(p.getsValue()));
		}

		return oo;
	}

	/**
	 * 根据流水号组装数据并同步SAP
	 * 
	 * @param sequenceNumber
	 * @param version
	 * @return
	 */
	public String sendToSap(String sequenceNumber, String version) {
		try {
			// 1. 根据sequenceNumber组装数据
			SapOrder sapOrder = assembleSapOrder(sequenceNumber, version);

			// 2. 调用bayernService同步SAP
//			bayernService.postJason(ORDER_CREATION_SAP, sapOrder);
		    // 获取流水号: sequenceNumber
			//1.ͬ同步SAP开单 没有数据 先注释
			String sapStr = new ObjectMapper().writeValueAsString(sapOrder);
			logger.info("Order Data: {}", sapStr);
			String res = null;
			try {
				//没有数据先注释
				res = HttpUtil.postbody(orderCreationUrl, sapStr);
			} catch (Exception e) {
				logger.error("ͬ同步SAP异常==>",e);
				throw new RuntimeException("ͬ同步SAP异常");
			}
			
			//2. 处理返回结果
			logger.info("SAP返回结果==>"+res);
//		  	logger.info("SAP同步开单结果==>"+sapRes);
		} catch (Exception e) {
			e.printStackTrace();
			return "推送订单到SAP失败，错误信息：" + e.getMessage();
		}

		return "success";

	}

	/**
	 * 根据流水号组装数据
	 * 
	 * @param sequenceNumber
	 * @return //
	 */
	private SapOrder assembleSapOrder(String sequenceNumber, String version) {
		OrderQuery query = new OrderQuery();
		query.setLast(true);
		query.setSequenceNumber(sequenceNumber);
		query.setVersion(version);
		OrderDto orderView = orderInfoMapper.findOrderViewByParams(query).get(0);
		Integer orderInfoId = orderView.getId();
		String orderType = orderView.getOrderType();

		// assemble sap order header
		SapOrderHeader header = new SapOrderHeader();
		List<SapOrderItem> sapItems = new ArrayList<SapOrderItem>();
		List<SapOrderCharacteristics> sapCharacs = new ArrayList<SapOrderCharacteristics>();
		List<SapOrderPrice> sapPrices = new ArrayList<SapOrderPrice>();
		List<SapOrderPlan> sapPlans = new ArrayList<SapOrderPlan>();

		// Header input
		header.setAuart(toString(orderView.getOrderType())); // Sales order type/订单类型
		header.setVkorg("0841"); // Sales org./销售组织 -- Fixed value/固定为 0841
		header.setVtweg(orderView.getCustomerClazz()); // DC/分销渠道 -- 客户
		header.setName2(orderView.getCustomerName()); // Store name/店名
		header.setSpart(orderView.getSaleType()); // Division/产品组
		header.setVkbur(toString(orderView.getOfficeCode())); // Sales office/销售办公室 -- 大区
		header.setVkgrp(toString(orderView.getGroupCode())); // Sales group/销售组 -- 中心
		header.setVbeln(toString(orderView.getContractNumber())); // SO number/销售订单编号 -- 合同号
		header.setKvgr1(toString(orderView.getIsConvenientStore())); // Customer grp.1/客户组1 -- 是否便利店
		header.setKvgr2(toString(orderView.getIsNew())); // Customer grp.2/客户组2 -- 是否新客户
		header.setKvgr3(toString(orderView.getIsReformed())); // Customer grp.3/客户组3 -- 是否改造店
		header.setBstzd(toString(orderView.getWarranty())); // Additional/附加的 -- 保修年限
		header.setBstkdE(toString(orderView.getContractNumber())); // Ship-to PO/送达方-采购订单编号 -- 项目报备编号 - 合同号
		header.setVsart(toString(orderView.getTransferType())); // Shipping type/装运类型 -- 运输类型
//		header.setZterm();	// Payment terms/付款条款 -- 结算方式  大客户为空，dealer取billing_plan的第一条code（唯一一条） 
		header.setKunnr(toString(orderView.getCustomerCode())); // Sold-to party/售达方 -- 签约单位
		header.setWaerk(toString(orderView.getCurrency())); // Currency/币别 -- 币别
		header.setInco1(toString(orderView.getIncoterm())); // Incoterms/国际贸易条款 -- 国际贸易条件 code
		header.setInco1(toString(orderView.getIncotermName())); // Incoterms2/国际贸易条款2 -- 国际贸易条件2 name
//		// 折扣
		header.setVbbkz120(String.valueOf(orderView.getContractRmbValue())); // Contract amount/合同金额 -- 合同金额
		header.setVbbkz121(orderView.getSalesCode()); // Sale rep./签约人 -- 客户经理
		header.setVbbkz109(orderView.getContractManager()); // Order clerk/合同管理员 -- 合同管理员
		String contactorInfo = toString(orderView.getContactor1Id()) + "/" + toString(orderView.getContactor1Tel())
				+ toString(orderView.getContactor2Id()) + "/" + toString(orderView.getContactor2Tel())
				+ toString(orderView.getContactor3Id()) + "/" + toString(orderView.getContactor3Tel());
		header.setVbbkz108(contactorInfo); // Contact info./授权人信息 -- 授权人信息6个字段 3个联系人id+tel / 分隔
		String vbbkz122 = toString(orderView.getIsTerm1()) + "/" + toString(orderView.getIsTerm2())
				+ toString(orderView.getIsTerm3());
		header.setVbbkz122(vbbkz122); // Survey info. for header /调研表相关内容 -- 调研表相关内容3个字段
		header.setVbbkz106(orderView.getReceiveType()); // Receiving method /收货方式 -- 收货方式

//		ItemService itemService;
		List<ItemDto> items = itemMapper.findByOrderInfoId(orderView.getId());
		for (ItemDto item : items) {
			int rowNumber = item.getRowNum();
			SapOrderItem sapItem = new SapOrderItem();
			// Ship-to PO item/送达方-采购订单编号项目
			sapItem.setPosnr(rowNumber);
			// Material Number/物料编码
			sapItem.setMatnr(item.getMaterialCode());
			// Target quantity/数量
			sapItem.setZmeng((int) item.getQuantity());
			// Req.dlv.date/请求发货日期
//			item.setEdatu(itemDetail.getDeliveryDate());
			// Item category/行项目类别 -- 项目类别
			sapItem.setPstyv(item.getItemCategory());
			// Item usage/项目用途 -- 项目需求计划
			sapItem.setVkaus(item.getItemRequirementPlan());

			// Ship-to address/送达方地址
			// 街道名称
			sapItem.setStreet(item.getAddress());
//			// Province/省 -- 省code
			sapItem.setRegion(item.getProvinceCode());
//			// City/市 -- 市名称
			sapItem.setCity1(item.getCityCode());
//			// District/区 -- 区名称
			sapItem.setCity2(item.getDistrictCode());

			// B2C note/B2C备注
			sapItem.setVbbp0006(item.getB2cComments());
			// Survey info. for item /调研表基本信息
			StringBuilder sb = new StringBuilder(128);
			if (!isEmpty(item.getRequestBrand())) {
				sb.append(",").append(item.getRequestBrand());
			}
			if (!isEmpty(item.getRequestPackage())) {
				sb.append(",").append(item.getRequestPackage());
			}
			if (!isEmpty(item.getRequestNameplate())) {
				sb.append(",").append(item.getRequestNameplate());
			}
			if (!isEmpty(item.getRequestCircult())) {
				sb.append(",").append(item.getRequestCircult());
			}
			sapItem.setVbbpz121(sb.length() > 0 ? sb.substring(1) : "");
			// Special note/特殊备注
			sapItem.setVbbpz117(item.getSpecialComments());
			// Color option/颜色可选项 -- Characteristics中处理
			sapItem.setVbbpz120(item.getColorComments());
			// Survey info. Note/调研表备注
			sapItem.setVbbp0007(item.getComments());
			// Color Note/颜色备注
			sapItem.setVbbpz118(item.getColorComments());

			sapItems.add(sapItem);

			// Price/condition record input
			// ZH05：实卖价合计
			SapOrderPrice price1 = new SapOrderPrice();
			price1.setPosnr(rowNumber);
			price1.setKschl("ZH05");
			price1.setKbetr(BigDecimal.valueOf(item.getActualPrice() * item.getQuantity()));
			sapPrices.add(price1);
			// ZH08：转移价合计/成本合计
			SapOrderPrice price2 = new SapOrderPrice();
			price2.setPosnr(rowNumber);
			price2.setKschl("ZH08");
			price2.setKbetr(BigDecimal.valueOf(item.getTransationPrice() * item.getQuantity()));
			sapPrices.add(price2);

			// Characteristics value input
			List<Characteristics> characList = characteristicsMapper.findByItemId(item.getId());
			for (Characteristics charac : characList) {
				SapOrderCharacteristics c = new SapOrderCharacteristics();

				if (charac.getIsConfigurable() == 1) {
					// 设置 Item 的 Color option/颜色可选项
					String vbbpz120 = sapItem.getVbbpz120();
					String tmp = charac.getKeyCode() + ":" + charac.getValueCode();
					vbbpz120 = (vbbpz120 == null || vbbpz120.length() == 0) ? tmp : "," + tmp;
					sapItem.setVbbpz120(vbbpz120);
					continue;
				}

				// Item/行项目编号
				c.setPosnr(rowNumber);
				// Characteristic/特性
				c.setAtnam(charac.getKeyCode());
				// Char. value/特性值
				c.setAtwrt(charac.getValueCode());
				sapCharacs.add(c);
			}
		}

		// Billing plan
		// 当订单为经销商订单，billing plan只有一条数据，金额为空或0，不向sap发送billing plan数据，sap order
		// header的付款条款为billing plan 的 code
		List<BillingPlan> planList = billingPlanMapper.findByOrderInfoId(orderInfoId);
		for (BillingPlan kBiddingPlan : planList) {
			if (orderType.equals(OrderDto.ORDER_TYPE_DEALER)) {
				header.setZterm(kBiddingPlan.getCode());
				break;
			}
//			if (kBiddingPlan.getAmount() == null || kBiddingPlan.getAmount().doubleValue() == 0 || orderType.equals(ORDER_TYPE_DEALER)) {
//				header.setZterm(kBiddingPlan.getCode());
//				break;
//			}

			SapOrderPlan plan = new SapOrderPlan();
			// Value to be billed/金额
			plan.setFakwr(BigDecimal.valueOf(kBiddingPlan.getAmount()));
			// Settlement date/结算日期
			plan.setFkdat(new SimpleDateFormat("yyyyMMdd").format(kBiddingPlan.getPayDate()));
			// Date category/日期原因
			plan.setTetxt(kBiddingPlan.getReason());
			// Payment terms/付款条款
			plan.setZterm(kBiddingPlan.getCode());
		}

		SapOrder sapOrder = new SapOrder();
		sapOrder.setIsZhdr(header);
		sapOrder.setItZitem(sapItems);
		sapOrder.setItZcharc(sapCharacs);
		sapOrder.setItZcond(sapPrices);
		sapOrder.setItZplan(sapPlans);

		return sapOrder;

	}

	/**
	 * 查询订单版本历史
	 * 
	 * @param sequenceNubmer
	 * @return
	 */
	public List<OrderVersion> findOrderVersions(String sequenceNumber) {
		List<OrderVersion> versions = orderInfoMapper.findOrderVersions(sequenceNumber);

		return versions;
	}

	/**
	 * 查询订单详情
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public OrderDto findOrder(Integer id) throws Exception {
		OrderDto order = null;
		OrderQuery orderQuery = new OrderQuery();
		orderQuery.setId(id);
		orderQuery.setIncludeDetail(true);

		PageInfo<OrderDto> page = queryOrderView(orderQuery);
		List<OrderDto> orderViews = page.getList();

		if (orderViews.size() > 0) {
			order = orderViews.get(0);
			assembleOrderDetail(order);
		}

		return order;
	}

	/**
	 * 查询订单详情
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public OrderDto findOrder(String sequenceNumber, String version) throws Exception {
		OrderDto order = null;
		OrderQuery orderQuery = new OrderQuery();
		orderQuery.setSequenceNumber(sequenceNumber);
		orderQuery.setVersion(version);
		orderQuery.setIncludeDetail(true);

		PageInfo<OrderDto> page = queryOrderView(orderQuery);
		List<OrderDto> orderViews = page.getList();

		if (orderViews.size() > 0) {
			order = orderViews.get(0);
			assembleOrderDetail(order);
		}

		return order;
	}

	/**
	 * 查询订单
	 * 
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public PageInfo<OrderDto> findOrders(OrderQuery orderQuery) throws Exception {
		boolean includeDetail = orderQuery.isIncludeDetail();

		PageInfo<OrderDto> page = queryOrderView(orderQuery);
		List<OrderDto> orders = page.getList();
		if (includeDetail) {
			for (OrderDto order : orders) {
				assembleOrderDetail(order);
			}
		}

		return page;
	}

	/**
	 * 组装订单详情
	 * 
	 * @param order
	 * @param orderId
	 * @param orderInfoId
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private void assembleOrderDetail(OrderDto order) throws Exception {
		Integer orderInfoId = order.getId();
		// Attached File
		order.setAttachments(new ArrayList<Attachment>());
		List<Attachment> attachments = attachmentMapper.findByOrderInfo(orderInfoId);
		order.setAttachments(attachments);

		// 收货地址
		List<DeliveryAddress> addressList = deliveryAddressMapper.findByOrderInfoId(orderInfoId);
		List<DeliveryAddressDto> addresses = new ArrayList<DeliveryAddressDto>(addressList.size());
		for (DeliveryAddress deliveryAddress : addressList) {
			DeliveryAddressDto address = new DeliveryAddressDto();
			BeanUtils.copyProperties(address, deliveryAddress);
			addresses.add(address);
		}
		order.setDeliveryAddress(addresses);

		// billing plan
		List<BillingPlan> billingPlanList = billingPlanMapper.findByOrderInfoId(orderInfoId);
		order.setPayments(billingPlanList);

		// items
		assembleItems(order);
	}

	private void assembleItems(OrderDto order) throws Exception {
		Integer orderInfoId = order.getId();
		List<ItemDto> items = itemMapper.findByOrderInfoId(orderInfoId);
		order.setItems(items);
		Map<String, String> unitMap = this.constService.findMeasurementUnits();
		Map<String, String> cityMap = new HashMap(); // this.constService..findMeasurementUnits();
		for (ItemDto itemDetail : items) {
			Integer itemId = itemDetail.getId();
			ItemDto item = new ItemDto();
			BeanUtils.copyProperties(item, itemDetail);
			
			item.setUnitName(unitMap.get(item.getUnitCode()));
			// TODO
//			item.setProvinceName(provinceName);
//			item.setCityName(cityMap.get(item.getCityCode()));
//			item.setDistrictName(districtName);
//			item.setMaterialgroupName(materialgroupName);

			// characteristics
			List<Characteristics> characs = characteristicsMapper.findByItemId(itemId);
			List<CharacteristicDto> configs = new ArrayList<CharacteristicDto>();
			for (Characteristics charac : characs) {
				CharacteristicDto c = new CharacteristicDto();
				BeanUtils.copyProperties(c, charac);
				configs.add(c);
			}
			item.setConfigs(configs);
		}
	}

	/**
	 * 根据条件查询订单视图
	 * 
	 * @param orderQuery
	 * @return
	 */
	private PageInfo<OrderDto> queryOrderView(OrderQuery orderQuery) {
		// 设置分页信息
		int pageNo = orderQuery.getPageNo() == null ? 0 : orderQuery.getPageNo().intValue();
		int pageSize = orderQuery.getPageSize() == null ? 10000 : orderQuery.getPageSize();

		com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
		List<OrderDto> list = orderInfoMapper.findOrderViewByParams(orderQuery);

		return new PageInfo<>(list);
	}

	/**
	 * 根据条件查询订单视图
	 * 
	 * @param orderQuery
	 * @return
	 */
//	private PageHelper<OrderDto> queryOrderView(OrderQuery orderQuery) {
//		StringBuilder querySql = new StringBuilder();
//		Map<String, Object> params = new HashMap<>();
//		querySql.append("select * from k_order_view where 1=1 ");
//		if (orderQuery.getOrderId() != null) {
//			querySql.append(" and order_id = :orderId");
//			params.put("orderId", orderQuery.getOrderId());
//		}
//		if (!isEmpty(orderQuery.getSequenceNumber())) {
//			querySql.append(" and UPPER(sequence_number) like :sequenceNumber");// .append(query.getSequenceNumber());
//			params.put("sequenceNumber", "%" + orderQuery.getSequenceNumber().toUpperCase() + "%");
//		}
//		if (!isEmpty(orderQuery.getVersionId())) {
//			querySql.append(" and version_id = :versionId");// .append(query.getVersionId());
//			params.put("versionId", orderQuery.getVersionId());
//		}
//		if (!isEmpty(orderQuery.getVersion())) {
//			querySql.append(" and version = :version"); // .append(query.getVersion());
//			params.put("version", orderQuery.getVersion());
//		}
//		if (!isEmpty(orderQuery.getStatus())) {
//			querySql.append(" and status = :status"); // .append(query.getStatus());
//			params.put("status", orderQuery.getStatus());
//		}
//		if (!isEmpty(orderQuery.getSalesCode())) {
//			querySql.append(" and owner_domain_id = :ownerId");
//			params.put("ownerId", orderQuery.getSalesCode());
//		}
//		if (!isEmpty(orderQuery.getSalesName())) {
//			querySql.append(" and UPPER(owner_name) like :ownerName");
//			params.put("ownerName", "%" + orderQuery.getSalesName().toUpperCase() + "%");
//		}
//		if (!isEmpty(orderQuery.getCreateTime())) {
//			// 2019-04-07 - 2019-11-07
//			String strtime = orderQuery.getCreateTime();
//			String[] strtimes = strtime.split(" - ");
//			String start = strtimes[0].trim();
//			String end = strtimes[1].trim();
//			querySql.append(
//					" and DATE_FORMAT(create_time, '%Y-%m-%d') >= :start and DATE_FORMAT(create_time, '%Y-%m-%d') <= :end");
//			params.put("start", start);
//			params.put("end", end);
//		}
//		if (!isEmpty(orderQuery.getSubmitTime())) {
//			// 2019-04-07 - 2019-11-07
//			String strtime = orderQuery.getSubmitTime();
//			String[] strtimes = strtime.split(" - ");
//			String start = strtimes[0].trim();
//			String end = strtimes[1].trim();
//			querySql.append(
//					" and DATE_FORMAT(submit_date, '%Y-%m-%d') >= :start and DATE_FORMAT(submit_date, '%Y-%m-%d') <= :end");
//			params.put("start", start);
//			params.put("end", end);
//		}
//		if (!isEmpty(orderQuery.getOfficeCode())) {
//			querySql.append(" and office_code = :officeCode"); // .append(query.getStatus());
//			params.put("officeCode", orderQuery.getOfficeCode());
//		}
//		if (!isEmpty(orderQuery.getOrderType())) {
//			querySql.append(" and order_type_code = :orderType"); // .append(query.getStatus());
//			params.put("orderType", orderQuery.getOrderType());
//		}
//		if (!isEmpty(orderQuery.getContractNumber())) {
//			querySql.append(" and UPPER(contract_number) like :contractNumber"); // .append(query.getStatus());
//			params.put("contractNumber", "%" + orderQuery.getContractNumber().toUpperCase() + "%");
//		}
//		if (!isEmpty(orderQuery.getContracterName())) {
//			querySql.append(" and UPPER(contractor_name) like :contractorName"); // .append(query.getStatus());
//			params.put("contractorName", "%" + orderQuery.getContracterName().toUpperCase() + "%");
//		}
//		if (orderQuery.getStatusList() != null && orderQuery.getStatusList().size() > 0) {
//			if (orderQuery.getDominStatusList() != null && orderQuery.getDominStatusList().size() > 0) {
//				querySql.append(" and (status in (:statuslist)").append(" or (owner_domain_id = :ownerId and status in (:domainStatuslist)))");
//				params.put("statuslist", orderQuery.getStatusList());
//				params.put("ownerId", orderQuery.getDominSalesCode());
//				params.put("domainStatuslist", orderQuery.getDominStatusList());
//			} else {
//				querySql.append(" and status in (:statuslist)");
//				params.put("statuslist", orderQuery.getStatusList());
//			}
//		}
//		if (!isEmpty(orderQuery.getSpecialDiscount())) {
//			boolean special = orderQuery.getSpecialDiscount().equals("1");
//			if (special) {
//				querySql.append(" and (body_discount + main_discount) != 96");
//			} else {
//				querySql.append(" and (body_discount + main_discount) = 96");
//			}
//		}
//		if (orderQuery.isLast()) {
//			querySql.append(
//					" and version_create_time = (select max(create_time) from k_order_version where k_order_version.k_orders_id = k_order_view.order_id)"); // .append(query.getStatus());
//		}
//		if (!isEmpty(orderQuery.getB2c())) {
//			boolean has = orderQuery.getB2c().equals("1");
//			querySql.append(
//					" and exists (select 1 from k_item_details where k_item_details.k_forms_id = k_order_view.form_id");
//			if (has) {
//				querySql.append(" and b2c_comments is not null and LENGTH(TRIM(b2c_comments)) > 0");
//			} else {
//				querySql.append(" and (b2c_comments is null or LENGTH(TRIM(b2c_comments)) = 0)");
//			}
//			querySql.append(")");
//		}
//
//		Query query = entityManager.createNativeQuery(querySql.toString(), OrderDto.class);
//		for (Map.Entry<String, Object> entry : params.entrySet()) {
//			query.setParameter(entry.getKey(), entry.getValue());
//		}
//
//		// 设置分页信息
//		int pageNo = orderQuery.getPageNo() == null ? 0 : orderQuery.getPageNo().intValue();
//		int pageSize = orderQuery.getPageSize() == null ? 10000 : orderQuery.getPageSize();
//		query.setFirstResult(pageNo * pageSize);
//		query.setMaxResults(pageSize);
//
//		List<OrderDto> orderViews = query.getResultList();
//
//		// 统计总记录数
//		String countSql = querySql.toString();
//		countSql = "select count(1) " + countSql.substring(countSql.indexOf("from"));
//		Query countQuery = entityManager.createNativeQuery(countSql);
//		for (Map.Entry<String, Object> entry : params.entrySet()) {
//			countQuery.setParameter(entry.getKey(), entry.getValue());
//		}
//		BigInteger totalCount = (BigInteger) countQuery.getSingleResult();
//		PageHelper page = new PageHelper<OrderDto>();
//		page.setRows(orderViews);
//		page.setTotal(totalCount.intValue());
//
//		return page;
//	}

	/**
	 * 
	 * 发送订单到BPM审批
	 * 
	 * @param order
	 */
	public void submitBpm(String user, OrderDto order) {
		List<ItemDto> items = order.getItems();
		if (items == null) {
			items = new ArrayList<ItemDto>();
		}
		List<MaterialGroups> grossProfitMargins = this.calcGrossProfit(order);
		MaterialGroups sumMargin = grossProfitMargins.get(grossProfitMargins.size() - 1);

		BpmOrder bpmOrder = new BpmOrder();
		OrderHeader bpmHeader = new OrderHeader();
		List<OrderItem> bpmItems = new ArrayList<OrderItem>(items.size());
		List<OrderMargin> bpmMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());
		List<OrderMargin> bpmWtwMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());

		bpmOrder.setOrder(bpmHeader);
		bpmOrder.setItems(bpmItems);
		bpmOrder.setMargin(bpmMargins);
		bpmOrder.setWtwMargin(bpmWtwMargins);

		// set bpm order
//		bpmHeader.setAddress(order.geta);
//		bpmHeader.setApprovalDiscount(order.getApprovedDiscount());
		bpmHeader.setB2c("0");
		List<ItemDto> l = order.getItems();
		for (ItemDto ItemDto : l) {
			String c = ItemDto.getB2cComments();
			if (c != null && c.trim().length() > 0) {
				bpmHeader.setB2c("1");
				break;
			}
		}
		bpmHeader.setBodyDiscount(order.getBodyDiscount());
		bpmHeader.setComments(order.getComments());
		bpmHeader.setContractAmount(order.getContractValue());
		bpmHeader.setContractNumber(order.getContractNumber());
		bpmHeader.setContractRmbAmount(order.getContractRmbValue());
		bpmHeader.setCreateTime(order.getCreateTime());
		bpmHeader.setCurrencyName(order.getCurrencyName());
		bpmHeader.setCustomerName(order.getCustomerName());
		bpmHeader.setDealer(order.getOrderType().equals(OrderDto.ORDER_TYPE_DEALER) ? "1" : "0");
		bpmHeader.setDiscount(order.getDiscount());
		bpmHeader.setEarliestDeliveryDate(order.getEarliestDeliveryDate());
		bpmHeader.setElectricalFee(order.getElectricalFee());
		bpmHeader.setExchange(order.getCurrencyExchange());
		bpmHeader.setInstallFee(order.getInstallFee());
		bpmHeader.setMaintenanceFee(order.getMaintenanceFee());
		bpmHeader.setMargin(sumMargin.getGrossProfitMargin());
		bpmHeader.setMaterialFee(order.getMaterialFee());
		// TODO
//		bpmHeader.setMaterialGroupNames(order.get);
		bpmHeader.setMergeDiscount(bpmHeader.getDiscount());
		bpmHeader.setOrderType(order.getOrderType());
		bpmHeader.setPaymentTypeName(order.getPaymentType());
		bpmHeader.setReceiveTypeName(order.getReceiveTypeName());
		bpmHeader.setRefrigeratoryFee(order.getRefrigeratoryFee());
		bpmHeader.setSalesCode(order.getSalesCode());
		bpmHeader.setSalesTel(order.getSalesTel());
		bpmHeader.setSaleType(order.getSaleType());
		bpmHeader.setSapOffice(order.getOfficeName());
		bpmHeader.setSequenceNumber(order.getSequenceNumber());
		bpmHeader.setShopName(order.getCustomerName());
		bpmHeader.setSpecialDiscount(bpmHeader.getDiscount() == 0.48 ? "1" : "0");
		bpmHeader.setStatus(order.getStatus());
		bpmHeader.setTaxRate(order.getTaxRate());
		bpmHeader.setUnitDiscount(order.getMainDiscount());
		bpmHeader.setWtwMargin(sumMargin.getWtwGrossProfitMargin());

		// set bpm order item
		for (ItemDto ItemDto : items) {
			OrderItem bpmItem = new OrderItem();
			bpmItems.add(bpmItem);

			bpmItem.setActuralAmount(ItemDto.getActualPrice() * ItemDto.getQuantity());
			bpmItem.setActuralAmountOfOptional(ItemDto.getOptionalActualPrica() * ItemDto.getQuantity());
			bpmItem.setActuralPrice(ItemDto.getActualPrice());
			bpmItem.setActuralPriceOfOptional(ItemDto.getOptionalActualPrica());
			bpmItem.setAddress(ItemDto.getAddress());
			bpmItem.setB2cAmountEstimated(ItemDto.getB2cEstimatedPrice() * ItemDto.getQuantity());
			bpmItem.setB2cComments(ItemDto.getB2cComments());
			bpmItem.setB2cCostOfEstimated(ItemDto.getB2cEstimatedCost());
			bpmItem.setB2cPriceEstimated(ItemDto.getB2cEstimatedPrice());
			bpmItem.setColorComments(ItemDto.getColorComments());
			bpmItem.setDeliveryDate(ItemDto.getDeliveryDate());
			bpmItem.setDiscount(ItemDto.getDiscount());
			bpmItem.setItemCategoryName(ItemDto.getItemCategory());
			bpmItem.setItemRequirementPlanName(ItemDto.getItemRequirementPlan());
			bpmItem.setMaterialCode(ItemDto.getMaterialCode());
			bpmItem.setMaterialAttribute(ItemDto.getIsPurchased() == 1 ? "采购" : "生产");
			bpmItem.setMaterialGroupName(ItemDto.getMaterialgroupName());
			bpmItem.setMaterialName(ItemDto.getMaterialName());
			bpmItem.setMeasureUnitName(ItemDto.getMaterialName());
			bpmItem.setOnStoreDate(ItemDto.getOnStoreDate());
			bpmItem.setPeriod(ItemDto.getPeriod());
			bpmItem.setProduceDate(ItemDto.getProduceDate());
			bpmItem.setQuantity(ItemDto.getQuantity());
			bpmItem.setRetailAmount(ItemDto.getRetailPrice() * ItemDto.getQuantity());
			bpmItem.setRetailPrice(ItemDto.getRetailPrice());
			bpmItem.setRowNumber(ItemDto.getRowNum());
			bpmItem.setShippDate(ItemDto.getShippDate());
			bpmItem.setSpecialComments(ItemDto.getSpecialComments());
			bpmItem.setTranscationPriceOfOptional(ItemDto.getOptionalTransationPrice());
			bpmItem.setTransfterPrice(ItemDto.getTransationPrice());
		}

		// set bpm order margins and wtw margins
		for (MaterialGroups grossProfitMargin : grossProfitMargins) {
			OrderMargin margin = new OrderMargin();
			OrderMargin wtwMargin = new OrderMargin();

			bpmMargins.add(margin);
			bpmWtwMargins.add(wtwMargin);

			margin.setAmount(grossProfitMargin.getAmount());
			margin.setCode(grossProfitMargin.getCode());
			margin.setName(grossProfitMargin.getName());
			margin.setCost(grossProfitMargin.getCost());
			margin.setExcludingTaxAmount(grossProfitMargin.getExcludingTaxAmount());
			margin.setGrossProfit(grossProfitMargin.getGrossProfit());
			margin.setMargin(grossProfitMargin.getGrossProfitMargin());
			margin.setStatus("1");
			// 最后修改人和修改时间
//			margin.setUpdateBy(order.get);
//			margin.setUpdateTime(updateTime);

			wtwMargin.setAmount(grossProfitMargin.getAmount());
			wtwMargin.setCode(grossProfitMargin.getCode());
			wtwMargin.setName(grossProfitMargin.getName());
			wtwMargin.setCost(grossProfitMargin.getWtwCost());
			wtwMargin.setExcludingTaxAmount(grossProfitMargin.getExcludingTaxAmount());
			wtwMargin.setGrossProfit(grossProfitMargin.getWtwGrossProfit());
			wtwMargin.setMargin(grossProfitMargin.getWtwGrossProfitMargin());
			wtwMargin.setStatus("1");
			// 最后修改人和修改时间
//			wtwMargin.setUpdateBy(order.get);
//			wtwMargin.setUpdateTime(updateTime);
		}

		// TODO Call the bpm interface to start the order approval process
		// TODO For test 直接改状态
		updateBpmStatus(user, order.getSequenceNumber(), "1", order.getBodyDiscount(), order.getMainDiscount());
	}

	/**
	 * 更新BPM审批状态和折扣
	 */
	public void updateBpmStatus(String user, String sequenceNumber, String status, Double bodyDiscount,
			Double unitDiscount) {
		OrderInfo orderInfo = orderInfoMapper.findByParams(null, sequenceNumber, null, "1").get(0);
		BpmDicision bpmDicision = new BpmDicision();
		bpmDicision.setOrderInfoId(orderInfo.getId());
		bpmDicision.setBodyDiscount(orderInfo.getBodyDiscount());
		bpmDicision.setMainDiccount(orderInfo.getMainDiscount());
		if (status.equals("1")) {
			// 审批通过
			status = OrderDto.ORDER_STATUS_APPROVED;
			bpmDicision.setIsPassed(1);
		} else {
			// 审批拒绝
			status = OrderDto.ORDER_STATUS_REJECT_BPM;
			bpmDicision.setApprovedBodyDiscount(bodyDiscount);
			bpmDicision.setApprovedMainDiscount(unitDiscount);
			bpmDicision.setIsPassed(0);
		}
		dicisionMapper.insert(bpmDicision);
		orderInfoMapper.updateStatus(orderInfo.getId(), user, status, null, null, bodyDiscount, unitDiscount);
	}

	private boolean isEmpty(String v) {
		return v == null || v.trim().length() == 0;
	}

	private String toString(Object v) {
		return toString(v, "");
	}

	private String toString(Object v, String defaultValue) {
		if (v == null) {
			return defaultValue;
		}
		return v.toString();
	}

	private double toDouble(Number n) {
		return n == null ? 0 : n.doubleValue();
	}

}
