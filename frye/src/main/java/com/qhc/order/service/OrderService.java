package com.qhc.order.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import com.qhc.order.entity.ItemAttachment;
import com.qhc.order.entity.Order;
import com.qhc.order.entity.OrderInfo;
import com.qhc.order.mapper.AttachmentMapper;
import com.qhc.order.mapper.BillingPlanMapper;
import com.qhc.order.mapper.BpmDicisionMapper;
import com.qhc.order.mapper.CharacteristicsMapper;
import com.qhc.order.mapper.DeliveryAddressMapper;
import com.qhc.order.mapper.ItemAttachmentMapper;
import com.qhc.order.mapper.ItemMapper;
import com.qhc.order.mapper.OrderInfoMapper;
import com.qhc.order.mapper.OrderMapper;
import com.qhc.sap.dao.PaymentTermRepository;
import com.qhc.sap.dao.SalesTypeRepository;
import com.qhc.sap.dao.SapMaterialGroupsRepository;
import com.qhc.sap.dao.TerminalIndustryCodeRepository;
import com.qhc.sap.domain.Characteristic;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.sap.entity.PaymentTerm;
import com.qhc.sap.entity.SalesType;
import com.qhc.sap.entity.TermianlIndustryCode;
import com.qhc.sap.mapper.SapViewMapper;
import com.qhc.sap.service.MaterialService;
import com.qhc.system.dao.AreaRepository;
import com.qhc.system.dao.CityRepository;
import com.qhc.system.dao.ProvinceRepository;
import com.qhc.system.dao.SettingsRepository;
import com.qhc.system.entity.Area;
import com.qhc.system.entity.City;
import com.qhc.system.entity.Province;
import com.qhc.system.entity.Settings;
import com.qhc.system.entity.User;
import com.qhc.system.service.UserService;
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
	ItemAttachmentMapper itemAttachmentMapper;

	@Autowired
	CharacteristicsMapper characteristicsMapper;

	@Autowired
	SapViewMapper sapViewMapper;

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
	private SettingsRepository settingsRepository;

	@Autowired
	private SapMaterialGroupsRepository materialGroupsRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private BpmService bpmService;

	public OrderDto save(String user, final OrderDto orderDto) throws Exception {
		Order order = new Order();
		OrderInfo orderInfo = new OrderInfo();
		
		if (StringUtils.isEmpty(orderDto.getStatus())) {
			orderDto.setStatus(OrderDto.ORDER_STATUS_DRAFT);
		}
		
		// 报价单状态
		if (orderDto.getStOrderType().equals("3")) {
			if (StringUtils.isEmpty(orderDto.getQuoteStatus())) {
				orderDto.setQuoteStatus("00");
			}
		}

		// calculate gross profit margin
		List<MaterialGroups> margin = calculateGrossProfit(orderDto);
		orderDto.setGrossProfitMargin(new ObjectMapper().writeValueAsString(margin));

		// is b2c
		List<ItemDto> items = orderDto.getItems();
		if (items != null) {
			items.forEach(i -> {
				if (StringUtils.isNotEmpty(i.getB2cComments())) {
					orderDto.setIsB2c(1);
				}
			});
		}

		BeanUtils.copyProperties(order, orderDto);
		BeanUtils.copyProperties(orderInfo, orderDto);

		if (orderDto.getId() == null || orderDto.getId() == 0) {
			// new version
			String version = new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date());
			orderInfo.setVersion(version.toUpperCase());
			orderInfo.setIsActive(1);
			orderInfo.setVersionNum(1);
			orderInfo.setCreater(user);
			orderInfo.setUpdater(user);

			if (orderDto.getOrderId() == null || orderDto.getOrderId() == 0) {
				String sequenceNumber = "QHC" + version;
				order.setSequenceNumber(sequenceNumber.toUpperCase());
				order.setSalesCode(user);

				orderMapper.insert(order);

				orderInfo.setOrderId(order.getId());
			}

			orderInfoMapper.insert(orderInfo);
		} else {
			orderInfo.setUpdater(user);
			orderInfoMapper.update(orderInfo);
			orderMapper.update(order);
		}
		orderDto.setId(orderInfo.getId());

		saveAttachments(orderDto);
		saveBillingPlans(orderDto);
		saveDeliveryAddresses(orderDto);

		saveItems(orderDto);

		OrderDto dto = this.findOrder(orderInfo.getId());

		return dto;
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
		String status = order.getStatus();
		
		if (!(OrderDto.ORDER_STATUS_APPROVED.equals(status) || OrderDto.ORDER_STATUS_APPROVED_UPDATE.equals(status) || OrderDto.ORDER_STATUS_SAP.equals(status))) {
			throw new RuntimeException("订单当前状态不允许变更");
		}

		order.setId(null);
		order.setCreater(user);
		// new version
		String version = new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date());
		order.setVersion(version);
		order.setStatus(OrderDto.ORDER_STATUS_DRAFT);
		order.setSubmitBpmTime(null);
		order.setSubmitTime(null);
		order.setIsActive(1);
		order.setVersionNum(order.getVersionNum() + 1);

		// 将其他版本的active设置为0
		orderInfoMapper.inactive(order.getSequenceNumber());

		order = this.save(user, order);

		return order;
	}
	
	public OrderDto copy(String user, Integer orderInfoId) throws Exception {
		OrderDto order = this.findOrder(orderInfoId);
		
		order.setId(null);
		order.setCreater(user);
		// new version
		String version = new SimpleDateFormat("yyyyMMddHHssmmSSS").format(new Date());
		order.setVersion(version);
		order.setStatus(OrderDto.ORDER_STATUS_DRAFT);
		order.setSubmitBpmTime(null);
		order.setSubmitTime(null);
		order.setIsActive(1);
		order.setVersionNum(1);

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

	private void saveDeliveryAddresses(OrderDto orderDto) throws Exception {
		Integer orderInfoId = orderDto.getId();
		deliveryAddressMapper.deleteByOrderInfoId(orderInfoId);
		List<DeliveryAddressDto> addresses = orderDto.getDeliveryAddress();
		for (DeliveryAddressDto addressDto : addresses) {
			DeliveryAddress address = new DeliveryAddress();

			BeanUtils.copyProperties(address, addressDto);

			address.setId(null);
			address.setOrderInfoId(orderInfoId);

			deliveryAddressMapper.insert(address);
		}
	}

	private void saveItems(OrderDto orderDto) throws Exception {
		Integer orderInfoId = orderDto.getId();
		deleteItems(orderInfoId);
		List<ItemDto> items = orderDto.getItems();
		for (ItemDto itemDto : items) {
			Item item = new Item();

			BeanUtils.copyProperties(item, itemDto);

			item.setId(null);
			item.setOrderInfoId(orderInfoId);

			itemMapper.insert(item);

			List<CharacteristicDto> configs = itemDto.getConfigs();
			if (configs != null && configs.size() > 0) {
				for (CharacteristicDto dto : configs) {
					Characteristics c = new Characteristics();

					BeanUtils.copyProperties(c, dto);
					c.setIsConfigurable(dto.isConfigurable() ? 1 : 0);

					c.setId(null);
					c.setItemId(item.getId());

					characteristicsMapper.insert(c);
				}
			} else if (itemDto.getIsConfigurable()) {
				// 可配置物料但没有配置调研表，取默认值
				List<Characteristic> characs = materialService.getCharactersByClazzCode(itemDto.getMaterialCode(),
						orderDto.getCustomerIndustry());
				for (Characteristic c : characs) {
					boolean configurable = c.getConfigs() != null && c.getConfigs().size() > 0;
					Characteristics cs = new Characteristics();
					cs.setItemId(item.getId());
					cs.setIsConfigurable(configurable ? 1 : 0);
					cs.setKeyCode(c.getCode());
					if (configurable) {
						c.getConfigs().forEach(e -> {
							if (e.isDefault()) {
								cs.setValueCode(e.getCode());
							}
						});
					}
				}
			}
			
			// 保存调研表附件
			List<ItemAttachment> attachments = itemDto.getAttachments();
			if (attachments != null && attachments.size() > 0) {
				for (ItemAttachment itemAttachment : attachments) {
					itemAttachment.setItemId(item.getId());
					itemAttachment.setOrderInfoId(item.getOrderInfoId());
					itemAttachmentMapper.insert(itemAttachment);
				}
			}
		}
	}

	private void deleteItems(Integer orderInfoId) {
		// delete k_characteristics
		this.characteristicsMapper.deleteByOrderInfoId(orderInfoId);
		
		// delete item attachemtn
		itemAttachmentMapper.deleteByOrderInfoId(orderInfoId);

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
		order = save(user, order);
		String status = order.getStatus();
		switch (status) {
		case OrderDto.ORDER_STATUS_DRAFT:
		case OrderDto.ORDER_STATUS_REJECT:
			if (order.getIsB2c() == 1) {
				order.setStatus(OrderDto.ORDER_STATUS_B2C);
			} else if (order.getCustomerClazz().equals(OrderDto.ORDER_CUSTOMER_KEY_ACCOUNT_CODE)) {
				// 大客户
				order.setStatus(OrderDto.ORDER_STATUS_ENGINER);
			} else {
				order.setStatus(OrderDto.ORDER_STATUS_MANAGER);
			}
			break;
		case OrderDto.ORDER_STATUS_B2C:
			if (order.getCustomerClazz().equals(OrderDto.ORDER_CUSTOMER_KEY_ACCOUNT_CODE)) {
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
		
		status = order.getStatus();
		orderInfoMapper.updateStatus(order.getId(), user, status, new Date(), null, null, null);
	}

	/**
	 * 
	 * @return sales type
	 */
	public List<SalesType> getSalesTypes() {
		return salesTypeRepo.findAll();
	}

	// sap_material_group分组
	public List<MaterialGroups> calculateGrossProfit(OrderDto order) {
		// 查询所有物料类型sap_material_group isenable != 0
		List<MaterialGroups> groups = materialGroupsRepository.findByIsenableNotOrderByCode(0);
		List<ItemDto> items = order.getItems();

		if (items == null || items.size() == 0) {
			return groups;
		}

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
				BigDecimal wtwcost = BigDecimal.ZERO;// wtw（生产）成本
				BigDecimal cost = BigDecimal.ZERO;// 销售成本
				BigDecimal wtwgrossProfit = BigDecimal.ZERO;// wtw毛利
				BigDecimal grossProfit = BigDecimal.ZERO;// 毛利
				Double wtwgrossProfitMargin = 0D;// wtw毛利率
				Double grossProfitMargin = 0D;// 毛利率

				for (ItemDto item : items) {
					if (item.getMaterialGroupCode().equals(entity.getCode())) {
						// 1.	金额= sum（实卖金额合计），实卖金额=实卖价*数量，实卖价=零售价*折扣
						BigDecimal saleAmount = BigDecimal
								.valueOf((item.getActualPrice() + item.getOptionalActualPrica()) * item.getQuantity());
						amount = amount.add(saleAmount);
						// 成本（销售）
						cost = cost.add(BigDecimal.valueOf(item.getTransationPrice() * item.getQuantity()));
						// 成本（生产）
						wtwcost = wtwcost.add(BigDecimal.valueOf(item.getStandardPrice() * item.getQuantity()));
					}
				}
				// 2.	不含税金额=金额/(1+税率)
				excludingTaxAmount = BigDecimal.valueOf(amount.doubleValue()/(1 + taxRate));
				// 毛利=不含税金额-成本
				grossProfit = excludingTaxAmount.subtract(cost);
				// 毛利（生产）=不含税金额-成本（生产）
				wtwgrossProfit = excludingTaxAmount.subtract(wtwcost);
				// 毛利率
				grossProfitMargin = this.calculateGrossProfit(excludingTaxAmount, cost);
				// WTW（生产）毛利率
				wtwgrossProfitMargin = this.calculateGrossProfit(excludingTaxAmount, wtwcost);

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

	private Double calculateGrossProfit(BigDecimal afterTaxAmount, BigDecimal cost) {
		Double v = 0D;
		BigDecimal profit = afterTaxAmount.subtract(cost);
		if (profit.compareTo(BigDecimal.ZERO) == 0) {
			return 0d;
		}
		try {
			v = profit.divide(afterTaxAmount, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
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
		Settings p = settingsRepository.findEnabledInfo("std_discount");
		if (p != null) {
			oo.setStandardDiscount(p.getsValue());
		}

		// 税率，Code：1c20b7ffba1a59faa081324eb34844a5
		p = settingsRepository.findEnabledInfo("tax_rate");
		if (p != null) {
			Map<String, Double> taxRate = new HashMap<String, Double>();
			taxRate.put("10", Double.valueOf(p.getsValue()));
			taxRate.put("30", Double.valueOf(p.getsValue()));

			oo.setTaxRate(taxRate);
		}

		// 经销商结算方式
		oo.setDealerPaymentTerms(constService.findDealerPaymentTerms());

		return oo;
	}

	/**
	 * 根据流水号组装数据并同步SAP
	 * 
	 * @param sequenceNumber
	 * @param version
	 * @return
	 */
	public String sendToSap(String user, Integer orderInfoId) {
		try {
			OrderDto orderDto = this.findOrder(orderInfoId);
			this.save(user, orderDto);

			// 1. 根据sequenceNumber组装数据
			SapOrder sapOrder = assembleSapOrder(orderDto);

			sendToSap(sapOrder);
//		  	logger.info("SAP同步开单结果==>"+sapRes);
		} catch (Exception e) {
			e.printStackTrace();
			return "推送订单到SAP失败，错误信息：" + e.getMessage();
		}

		return "success";

	}

	/**
	 * 下发订单到SAP
	 * 
	 * @param orderDto
	 * @return
	 * @throws JsonProcessingException
	 */
	public String sendToSap(SapOrder sapOrder) {
		String res = null;
		try {
			// 1.ͬ同步SAP开单 没有数据 先注释
			String sapStr = new ObjectMapper().writeValueAsString(sapOrder);
			logger.info("Order Data: {}", sapStr);
			// 没有数据先注释
			res = HttpUtil.postbody(orderCreationUrl, sapStr);
		} catch (Exception e) {
			logger.error("ͬ同步SAP异常==>", e);
			throw new RuntimeException("ͬ同步SAP异常");
		}

		// 2. 处理返回结果
		logger.info("SAP返回结果==>" + res);

		return res;
	}

	/**
	 * 根据流水号组装数据
	 * 
	 * @param sequenceNumber
	 * @return //
	 */
	private SapOrder assembleSapOrder(OrderDto order) {
		Integer orderInfoId = order.getId();
		String orderType = order.getOrderType();

		// assemble sap order header
		SapOrderHeader header = new SapOrderHeader();
		List<SapOrderItem> sapItems = new ArrayList<SapOrderItem>();
		List<SapOrderCharacteristics> sapCharacs = new ArrayList<SapOrderCharacteristics>();
		List<SapOrderPrice> sapPrices = new ArrayList<SapOrderPrice>();
		List<SapOrderPlan> sapPlans = new ArrayList<SapOrderPlan>();

		// Header input
		header.setAuart(toString(order.getOrderType())); // Sales order type/订单类型
		header.setVkorg("0841"); // Sales org./销售组织 -- Fixed value/固定为 0841
		header.setVtweg(order.getCustomerClazz()); // DC/分销渠道 -- 客户
		header.setName2(order.getShopName()); // Store name/店名
		header.setSpart(order.getSaleType()); // Division/产品组
		header.setVkbur(toString(order.getOfficeCode())); // Sales office/销售办公室 -- 大区
		header.setVkgrp(toString(order.getGroupCode())); // Sales group/销售组 -- 中心
		header.setVbeln(toString(order.getContractNumber()).toUpperCase()); // SO number/销售订单编号 -- 合同号
		header.setKvgr1(toString(order.getIsConvenientStore())); // Customer grp.1/客户组1 -- 是否便利店
		header.setKvgr2(toString(order.getIsNew())); // Customer grp.2/客户组2 -- 是否新客户
		header.setKvgr3(toString(order.getIsReformed())); // Customer grp.3/客户组3 -- 是否改造店
		header.setBstzd(toString(order.getWarranty())); // Additional/附加的 -- 保修年限
		header.setBstkdE(toString(order.getContractNumber()).toUpperCase()); // Ship-to PO/送达方-采购订单编号 -- 项目报备编号 - 合同号
		header.setVsart(toString(order.getTransferType())); // Shipping type/装运类型 -- 运输类型
		header.setZterm(order.getPaymentType()); // Payment terms/付款条款 -- 结算方式 大客户为空，dealer取billing_plan的第一条code（唯一一条）
		header.setKunnr(toString(order.getCustomerCode())); // Sold-to party/售达方 -- 签约单位
		header.setWaerk(toString(order.getCurrency())); // Currency/币别 -- 币别
		header.setInco1(toString(order.getIncoterm())); // Incoterms/国际贸易条款 -- 国际贸易条件 code
		header.setInco1(toString(order.getIncotermName())); // Incoterms2/国际贸易条款2 -- 国际贸易条件2 name
//		// 折扣
		header.setVbbkz120(String.valueOf(order.getContractRmbValue())); // Contract amount/合同金额 -- 合同金额
		header.setVbbkz121(order.getSalesCode()); // Sale rep./签约人 -- 客户经理
		header.setVbbkz109(order.getContractManager()); // Order clerk/合同管理员 -- 合同管理员
		String contactorInfo = toString(order.getContactor1Id()) + "/" + toString(order.getContactor1Tel())
				+ toString(order.getContactor2Id()) + "/" + toString(order.getContactor2Tel())
				+ toString(order.getContactor3Id()) + "/" + toString(order.getContactor3Tel());
		header.setVbbkz108(contactorInfo); // Contact info./授权人信息 -- 授权人信息6个字段 3个联系人id+tel / 分隔
		String vbbkz122 = toString(order.getIsTerm1()) + "/" + toString(order.getIsTerm2())
				+ toString(order.getIsTerm3());
		header.setVbbkz122(vbbkz122); // Survey info. for header /调研表相关内容 -- 调研表相关内容3个字段
		header.setVbbkz106(order.getReceiveType()); // Receiving method /收货方式 -- 收货方式

//		ItemService itemService;
		List<ItemDto> items = order.getItems(); // itemMapper.findByOrderInfoId(order.getId());
		for (ItemDto item : items) {
			int rowNumber = item.getRowNum();
			SapOrderItem sapItem = new SapOrderItem();
			// Ship-to PO item/送达方-采购订单编号项目
			sapItem.setPosnr(rowNumber);
			// Material Number/物料编码
			sapItem.setMatnr(item.getMaterialCode());
			// Target quantity/数量
			sapItem.setZmeng((int) item.getQuantity());
			// Req.dlv.date/请求发货日期 yyyyMMdd
			String deliveryDate = item.getDeliveryDate() == null ? ""
					: new SimpleDateFormat("yyyyMMdd").format(item.getDeliveryDate());
			sapItem.setEdatu(deliveryDate);
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
			List<CharacteristicDto> characList = characteristicsMapper.findByItemId(item.getId());
			for (CharacteristicDto charac : characList) {
				SapOrderCharacteristics c = new SapOrderCharacteristics();

				if (charac.isConfigurable()) {
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
		// header的付款条款为billing plan 的 code
		List<BillingPlan> planList = billingPlanMapper.findByOrderInfoId(orderInfoId);
		for (BillingPlan kBiddingPlan : planList) {
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
//		boolean includeDetail = orderQuery.isIncludeDetail();

		PageInfo<OrderDto> page = queryOrderView(orderQuery);

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
		List<Attachment> attachments = attachmentMapper.findByOrderInfoId(orderInfoId);
		order.setAttachments(attachments);

		// 收货地址
		List<DeliveryAddressDto> addresses = deliveryAddressMapper.findByOrderInfoId(orderInfoId);
		order.setDeliveryAddress(addresses);

		// billing plan
		List<BillingPlan> billingPlanList = billingPlanMapper.findByOrderInfoId(orderInfoId);
		order.setPayments(billingPlanList);

		// items
		assembleItems(order);
	}

	private void assembleItems(OrderDto order) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderInfoId", order.getId());
		List<ItemDto> items = itemMapper.findByParams(params);
		order.setItems(items);
		Map<String, String> unitMap = this.constService.findMeasurementUnits();
		for (ItemDto item : items) {
			Integer itemId = item.getId();

			MaterialDto m = sapViewMapper.findMaterialInfo(item.getMaterialCode(), null).get(0);

			item.setMaterialName(m.getDescription());
			item.setMaterialGroupCode(m.getGroupCode());
			item.setMaterialGroupName(m.getGroupName());
			item.setStMaterialGroupCode(m.getStGroupCode());
			item.setStMaterialGroupName(m.getStGroupName());
			item.setUnitCode(m.getUnitCode());
			item.setUnitName(m.getUnitName());
			item.setIsConfigurable(m.isConfigurable());
			item.setIsPurchased(m.isPurchased());

			Integer deliveryAddressSeq = item.getDeliveryAddressSeq();
			if (deliveryAddressSeq != null) {
				order.getDeliveryAddress().forEach(e -> {
					if (e.getSeq() != null && e.getSeq().equals(deliveryAddressSeq)) {
						item.setProvinceCode(e.getProvinceCode());
						item.setProvinceName(e.getProvinceName());
						item.setCityCode(e.getCityCode());
						item.setCityName(e.getCityName());
						item.setDistrictCode(e.getDistrictCode());
						item.setDistrictName(e.getDistrictName());
					}
				});
			}

			// characteristics
			List<CharacteristicDto> configs = characteristicsMapper.findByItemId(itemId);
			item.setConfigs(configs);
			
			// item attachment
			List<ItemAttachment> attachments = itemAttachmentMapper.findByItemId(itemId);
			item.setAttachments(attachments);
		}
	}

	/**
	 * 根据条件查询订单视图
	 * 
	 * @param orderQuery
	 * @return
	 * @throws Exception
	 */
	private PageInfo<OrderDto> queryOrderView(OrderQuery orderQuery) throws Exception {
		boolean includeDetail = orderQuery.isIncludeDetail();
		// 设置分页信息
		int pageNo = orderQuery.getPageNo() == null ? 0 : orderQuery.getPageNo().intValue();
		int pageSize = orderQuery.getPageSize() == null ? 10000 : orderQuery.getPageSize();

		com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
		List<OrderDto> orders = orderInfoMapper.findOrderViewByParams(orderQuery);
		for (OrderDto order : orders) {
			// user name
			List<User> list = userService.findByIdentitys(order.getCreater(), order.getUpdater(), order.getSalesCode(),
					order.getContractManager());
			list.forEach(u -> {
				if (u.getUserIdentity().equals(order.getCreater())) {
					order.setCreaterName(u.getName());
				}
				if (u.getUserIdentity().equals(order.getUpdater())) {
					order.setUpdaterName(u.getName());
				}
				if (u.getUserIdentity().equals(order.getSalesCode())) {
					order.setSalesName(u.getName());
					order.setUserOfficeCode(u.getOfficeCode());
				}
//					if (u.getUserIdentity().equals(order.getContractManager())) {
//						order.setContractManager(u.getName());
//					}
			});

			// customerclazz
			order.setCustomerClazzName(constService.findCustomerClazzByCode(order.getCustomerClazz()));

			if (includeDetail) {
				assembleOrderDetail(order);
			}
		}

		return new PageInfo<>(orders);
	}

	/**
	 * 
	 * 发送订单到BPM审批
	 * 
	 * @param order
	 * @throws Exception 
	 */
	public void submitBpm(String user, OrderDto order) throws Exception {
		order = save(user, order);
		List<ItemDto> items = order.getItems();
		if (items == null) {
			items = new ArrayList<ItemDto>();
		}
		List<MaterialGroups> grossProfitMargins = this.calculateGrossProfit(order);
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
		List<DeliveryAddressDto> addresses = order.getDeliveryAddress();
		if (addresses != null && addresses.size() > 0) {
			StringBuilder strAddress = new StringBuilder(512);
			for (DeliveryAddressDto deliveryAddressDto : addresses) {
				strAddress.append(",").append(deliveryAddressDto.getAddress());
			}
			bpmHeader.setAddress(strAddress.substring(1));
		}
		bpmHeader.setApprovalDiscount(order.getDiscount());
		bpmHeader.setB2c(String.valueOf(order.getIsB2c()));
		bpmHeader.setBodyDiscount(order.getBodyDiscount());
		bpmHeader.setComments(StringUtils.trimToEmpty(order.getComments()));
		bpmHeader.setContractAmount(order.getContractValue());
		bpmHeader.setContractNumber(StringUtils.trimToEmpty(order.getContractNumber()));
		bpmHeader.setContractRmbAmount(order.getContractRmbValue());
		bpmHeader.setCreateTime(order.getCreateTime());
		bpmHeader.setCurrencyName(StringUtils.trimToEmpty(order.getCurrencyName()));
		bpmHeader.setCustomerName(StringUtils.trimToEmpty(order.getCustomerName()));
		bpmHeader.setDealer(order.getOrderType().equals(OrderDto.ORDER_TYPE_DEALER) ? "1" : "0");
		bpmHeader.setDiscount(order.getDiscount());
		bpmHeader.setEarliestDeliveryDate(order.getEarliestDeliveryDate());
		bpmHeader.setElectricalFee(ObjectUtils.defaultIfNull(order.getElectricalFee(), 0d));
		bpmHeader.setExchange(ObjectUtils.defaultIfNull(order.getCurrencyExchange(), 0d));
		bpmHeader.setInstallFee(ObjectUtils.defaultIfNull(order.getInstallFee(), 0d));
		bpmHeader.setMaintenanceFee(ObjectUtils.defaultIfNull(order.getMaintenanceFee(), 0d));
		bpmHeader.setMargin(ObjectUtils.defaultIfNull(sumMargin.getGrossProfitMargin(), 0d));
		bpmHeader.setMaterialFee(ObjectUtils.defaultIfNull(order.getMaterialFee(), 0d));

		bpmHeader.setMergeDiscount(ObjectUtils.defaultIfNull(bpmHeader.getDiscount(), 0d));
		bpmHeader.setOrderType(StringUtils.trimToEmpty(order.getOrderType()));
		bpmHeader.setPaymentTypeName(StringUtils.trimToEmpty(order.getPaymentType()));
		bpmHeader.setReceiveTypeName(StringUtils.trimToEmpty(order.getReceiveTypeName()));
		bpmHeader.setRefrigeratoryFee(ObjectUtils.defaultIfNull(order.getRefrigeratoryFee(), 0d));
		bpmHeader.setSalesCode(StringUtils.trimToEmpty(order.getSalesCode()));
		bpmHeader.setSalesTel(StringUtils.trimToEmpty(order.getSalesTel()));
		bpmHeader.setSaleType(StringUtils.trimToEmpty(order.getSaleType()));
		bpmHeader.setSapOffice(StringUtils.trimToEmpty(order.getOfficeName()));
		bpmHeader.setSequenceNumber(StringUtils.trimToEmpty(order.getSequenceNumber()));
		bpmHeader.setShopName(StringUtils.trimToEmpty(order.getShopName()));
		bpmHeader.setSpecialDiscount(String.valueOf(order.getIsSpecial()));
		bpmHeader.setStatus(StringUtils.trimToEmpty(order.getStatus()));
		bpmHeader.setTaxRate(ObjectUtils.defaultIfNull(order.getTaxRate(), 0d));
		bpmHeader.setUnitDiscount(ObjectUtils.defaultIfNull(order.getMainDiscount(), 0d));
		bpmHeader.setWtwMargin(ObjectUtils.defaultIfNull(sumMargin.getWtwGrossProfitMargin(), 0d));

		// set bpm order item
		if (items.size() > 0) {
			StringBuilder strGroupName = new StringBuilder(256);
			for (ItemDto itemDto : items) {
				strGroupName.append(",").append(itemDto.getMaterialGroupName());
				OrderItem bpmItem = new OrderItem();
				bpmItems.add(bpmItem);

				bpmItem.setActuralAmount(itemDto.getActualPrice() * itemDto.getQuantity());
				bpmItem.setActuralAmountOfOptional(itemDto.getOptionalActualPrica() * itemDto.getQuantity());
				bpmItem.setActuralPrice(itemDto.getActualPrice());
				bpmItem.setActuralPriceOfOptional(itemDto.getOptionalActualPrica());
				bpmItem.setAddress(StringUtils.trimToEmpty(itemDto.getAddress()));
				bpmItem.setB2cAmountEstimated(itemDto.getB2cEstimatedPrice() * itemDto.getQuantity());
				bpmItem.setB2cComments(StringUtils.trimToEmpty(itemDto.getB2cComments()));
				bpmItem.setB2cCostOfEstimated(itemDto.getB2cEstimatedCost());
				bpmItem.setB2cPriceEstimated(itemDto.getB2cEstimatedPrice());
				bpmItem.setColorComments(StringUtils.trimToEmpty(itemDto.getColorComments()));
				bpmItem.setDeliveryDate(itemDto.getDeliveryDate());
				bpmItem.setDiscount(itemDto.getDiscount());
				bpmItem.setItemCategoryName(StringUtils.trimToEmpty(itemDto.getItemCategory()));
				bpmItem.setItemRequirementPlanName(StringUtils.trimToEmpty(itemDto.getItemRequirementPlan()));
				bpmItem.setMaterialCode(StringUtils.trimToEmpty(itemDto.getMaterialCode()));
				bpmItem.setMaterialAttribute(itemDto.getIsPurchased() ? "采购" : "生产");
				bpmItem.setMaterialGroupName(StringUtils.trimToEmpty(itemDto.getMaterialGroupName()));
				bpmItem.setMaterialName(StringUtils.trimToEmpty(itemDto.getMaterialName()));
				String unitName = this.constService.findMeasurementUnits().get(itemDto.getUnitCode());
				bpmItem.setMeasureUnitName(StringUtils.trimToEmpty(unitName));
				bpmItem.setOnStoreDate(itemDto.getOnStoreDate());
				bpmItem.setPeriod(ObjectUtils.defaultIfNull(itemDto.getPeriod(), 0));
				bpmItem.setProduceDate(itemDto.getProduceDate());
				bpmItem.setQuantity(itemDto.getQuantity());
				bpmItem.setRetailAmount(itemDto.getRetailPrice() * itemDto.getQuantity());
				bpmItem.setRetailPrice(itemDto.getRetailPrice());
				bpmItem.setRowNumber(itemDto.getRowNum());
				bpmItem.setShippDate(itemDto.getShippDate());
				bpmItem.setSpecialComments(StringUtils.trimToEmpty(itemDto.getSpecialComments()));
				bpmItem.setTranscationPriceOfOptional(itemDto.getOptionalTransationPrice());
				bpmItem.setTransfterPrice(itemDto.getTransationPrice());
			}
			bpmHeader.setMaterialGroupNames(strGroupName.length() > 0 ? strGroupName.substring(1) : "");
		}

		// set bpm order margins and wtw margins
		for (MaterialGroups grossProfitMargin : grossProfitMargins) {
			OrderMargin margin = new OrderMargin();
			OrderMargin wtwMargin = new OrderMargin();

			bpmMargins.add(margin);
			bpmWtwMargins.add(wtwMargin);

			margin.setAmount(ObjectUtils.defaultIfNull(grossProfitMargin.getAmount(), BigDecimal.ZERO));
			margin.setCode(StringUtils.trimToEmpty(grossProfitMargin.getCode()));
			margin.setName(StringUtils.trimToEmpty(grossProfitMargin.getName()));
			margin.setCost(ObjectUtils.defaultIfNull(grossProfitMargin.getCost(), BigDecimal.ZERO));
			margin.setExcludingTaxAmount(ObjectUtils.defaultIfNull(grossProfitMargin.getExcludingTaxAmount(), BigDecimal.ZERO));
			margin.setGrossProfit(ObjectUtils.defaultIfNull(grossProfitMargin.getGrossProfit(), BigDecimal.ZERO));
			margin.setMargin(ObjectUtils.defaultIfNull(grossProfitMargin.getGrossProfitMargin(), 0d));
			margin.setStatus("1");
			// 最后修改人和修改时间
//			margin.setUpdateBy(order.get);
//			margin.setUpdateTime(updateTime);

			wtwMargin.setAmount(grossProfitMargin.getAmount());
			wtwMargin.setCode(StringUtils.trimToEmpty(grossProfitMargin.getCode()));
			wtwMargin.setName(StringUtils.trimToEmpty(grossProfitMargin.getName()));
			wtwMargin.setCost(ObjectUtils.defaultIfNull(grossProfitMargin.getWtwCost(), BigDecimal.ZERO));
			wtwMargin.setExcludingTaxAmount(ObjectUtils.defaultIfNull(grossProfitMargin.getExcludingTaxAmount(), BigDecimal.ZERO));
			wtwMargin.setGrossProfit(ObjectUtils.defaultIfNull(grossProfitMargin.getWtwGrossProfit(), BigDecimal.ZERO));
			wtwMargin.setMargin(ObjectUtils.defaultIfNull(grossProfitMargin.getWtwGrossProfitMargin(), 0d));
			wtwMargin.setStatus("1");
			// 最后修改人和修改时间
//			wtwMargin.setUpdateBy(order.get);
//			wtwMargin.setUpdateTime(updateTime);
		}

		// Call the bpm interface to start the order approval process
		try {
			String json = new ObjectMapper().writeValueAsString(bpmOrder);
			bpmService.callSendProcess(json);
			orderInfoMapper.updateStatus(order.getId(), user, OrderDto.ORDER_STATUS_BPM, null, new Date(), null, null);
		} catch (Exception e) {
			logger.error("Submit order to BPM is failed.", e);
			throw new RuntimeException("Submit order to BPM is failed." + e.getMessage());
		}
	}

	/**
	 * 更新BPM审批状态和折扣
	 * 
	 * @throws Exception
	 */
	public void updateBpmStatus(String user, String sequenceNumber, String status, Double bodyDiscount,
			Double unitDiscount) throws Exception {
		OrderInfo orderInfo = orderInfoMapper.findByParams(null, sequenceNumber, null, "1").get(0);
		Order order = orderMapper.findById(orderInfo.getOrderId());

		BpmDicision bpmDicision = new BpmDicision();
		bpmDicision.setOrderInfoId(orderInfo.getId());
		bpmDicision.setBodyDiscount(orderInfo.getBodyDiscount());
		bpmDicision.setMainDiccount(orderInfo.getMainDiscount());
		if (status.equals("1")) {
			// 审批通过
			if (orderInfo.getVersionNum() > 1) {
				status = OrderDto.ORDER_STATUS_APPROVED_UPDATE;
			} else {
				status = OrderDto.ORDER_STATUS_APPROVED;
			}
			bpmDicision.setIsPassed(1);
			bpmDicision.setApprovedBodyDiscount(bodyDiscount);
			bpmDicision.setApprovedMainDiscount(unitDiscount);

			// 经销商非标准折扣订单，并且柜体折扣或机组折扣在bpm审批时被修改
			if (order.getStOrderType().equals("2")
					&& (orderInfo.getBodyDiscount() != bodyDiscount || orderInfo.getMainDiscount() != unitDiscount)) {
				// 修改订单行项目折扣，重新计算订单毛利率
				orderInfo.setStatus(status);
				orderInfo.setUpdater(user);
				orderInfo.setApprovedBodyDiscount(bodyDiscount);
				orderInfo.setApprovedMainDiscount(unitDiscount);

				OrderDto orderDto = this.findOrder(orderInfo.getId());
				List<ItemDto> items = orderDto.getItems();
				// 更新行项目的discount
				updateBpmDicount(items, bodyDiscount, unitDiscount);
				double itemsAmount = 0; // 行项目金额合计
				for (ItemDto itemDto : items) {
					double discount = itemDto.getDiscount();
					itemsAmount += itemDto.getActualPrice() * itemDto.getQuantity() * discount;
				}
				// merge discount
				orderInfo.setItemsAmount(itemsAmount);
				orderInfo.setDiscount(BigDecimal.valueOf(itemsAmount / orderInfo.getContractRmbValue())
						.setScale(4, RoundingMode.HALF_UP).doubleValue());

				// calculate gross profit margin
				List<MaterialGroups> margin = calculateGrossProfit(orderDto);
				orderInfo.setGrossProfitMargin(new ObjectMapper().writeValueAsString(margin));

				orderInfoMapper.update(orderInfo);
			} else {
				orderInfoMapper.updateStatus(orderInfo.getId(), user, status, null, null, bodyDiscount, unitDiscount);
			}
		} else {
			// 审批拒绝
			status = OrderDto.ORDER_STATUS_REJECT_BPM;
			bpmDicision.setIsPassed(0);
			orderInfoMapper.updateStatus(orderInfo.getId(), user, status, null, null, null, null);
		}
		dicisionMapper.insert(bpmDicision);
	}

	/**
	 * 根据BPM审批结果更新行项目的折扣
	 * 
	 * @param items
	 * @param bodyDiscount
	 * @param unitDiscount
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void updateBpmDicount(List<ItemDto> items, Double bodyDiscount, Double unitDiscount)
			throws IllegalAccessException, InvocationTargetException {
		for (ItemDto itemDto : items) {
			String stGroup = itemDto.getStMaterialGroupCode();
			if (stGroup.equals("T101") || stGroup.equals("T102")) {
				// 机柜
				if (stGroup.equals("T101")) {
					itemDto.setDiscount(bodyDiscount);
				}
				// 机组
				if (itemDto.getStMaterialGroupCode().equals("T102")) {
					itemDto.setDiscount(unitDiscount);
				}

				Item item = new Item();
				BeanUtils.copyProperties(item, itemDto);
				itemMapper.update(item);
			}
		}
	}

	private boolean isEmpty(String v) {
		return v == null || v.trim().length() == 0;
	}

	private String toString(Object v) {
		if (v == null) {
			return "";
		}
		return v.toString();
	}

	/**
	 * 修改报价单报价状态
	 * 
	 * @param user
	 * @param orderInfoId
	 * @param quoteStatus
	 * @return
	 */
	public String updateQuoteStatus(String user, Integer orderInfoId, String quoteStatus) {
		OrderInfo orderInfo = orderInfoMapper.findById(orderInfoId);
		Order order = orderMapper.findById(orderInfo.getOrderId());
		order.setQuoteStatus(quoteStatus);
		orderMapper.update(order);
		return null;
	}

}
