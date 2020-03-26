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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
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
import com.qhc.order.domain.bpm.OrderAttachment;
import com.qhc.order.domain.bpm.OrderHeader;
import com.qhc.order.domain.bpm.OrderItem;
import com.qhc.order.domain.bpm.OrderMargin;
import com.qhc.order.domain.sap.SapOrder;
import com.qhc.order.entity.Attachment;
import com.qhc.order.entity.BillingPlan;
import com.qhc.order.entity.BpmDicision;
import com.qhc.order.entity.Characteristics;
import com.qhc.order.entity.DeliveryAddress;
import com.qhc.order.entity.Item;
import com.qhc.order.entity.ItemAttachment;
import com.qhc.order.entity.ItemColor;
import com.qhc.order.entity.Order;
import com.qhc.order.entity.OrderInfo;
import com.qhc.order.entity.SpecialOrderApplication;
import com.qhc.order.mapper.AttachmentMapper;
import com.qhc.order.mapper.BillingPlanMapper;
import com.qhc.order.mapper.BpmDicisionMapper;
import com.qhc.order.mapper.CharacteristicsMapper;
import com.qhc.order.mapper.DeliveryAddressMapper;
import com.qhc.order.mapper.ItemAttachmentMapper;
import com.qhc.order.mapper.ItemColorMapper;
import com.qhc.order.mapper.ItemMapper;
import com.qhc.order.mapper.OrderInfoMapper;
import com.qhc.order.mapper.OrderMapper;
import com.qhc.order.mapper.SpecialOrderApplicationMapper;
import com.qhc.sap.dao.PaymentTermRepository;
import com.qhc.sap.dao.SalesTypeRepository;
import com.qhc.sap.dao.TerminalIndustryCodeRepository;
import com.qhc.sap.domain.Characteristic;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.sap.entity.PaymentTerm;
import com.qhc.sap.entity.ProductClass;
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
import com.qhc.system.service.SettingsService;
import com.qhc.system.service.UserService;

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
	private BillingPlanMapper billingPlanMapper;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemAttachmentMapper itemAttachmentMapper;

	@Autowired
	private CharacteristicsMapper characteristicsMapper;

	@Autowired
	private ItemColorMapper itemColorMapper;

	@Autowired
	private SapViewMapper sapViewMapper;

	@Autowired
	private SalesTypeRepository salesTypeRepo;

	@Autowired
	private AreaRepository districtRepo;

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
	private UserService userService;

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private BpmService bpmService;
	
	@Autowired
	private GrossProfitMarginService grossProfitMarginService;
	
	@Autowired
	private SpecialOrderApplicationMapper specialOrderApplicationMapper;
	
	@Autowired
	private SettingsService settingsService;
	
	@Autowired
	private SapOrderService sapOrderService;
	
	@Autowired
	private com.qhc.sap.mapper.SapOrderMapper sapOrderMapper;

	@Transactional
	public OrderDto save(String user, final OrderDto orderDto) throws Exception {
		List<ItemDto> items = orderDto.getItems();
		Order order = new Order();
		OrderInfo orderInfo = new OrderInfo();
		
		if (StringUtils.isEmpty(orderDto.getStatus())) {
			orderDto.setStatus(OrderDto.ORDER_STATUS_DRAFT);
		}
		
		if (orderDto.getStatus().equals(OrderDto.ORDER_STATUS_MANAGER)) {
			orderDto.setContractManager(user);
		}
		
		// 报价单状态
		if (orderDto.getStOrderType().equals("3")) {
			if (StringUtils.isEmpty(orderDto.getQuoteStatus())) {
				orderDto.setQuoteStatus("00");
			}
		}
		
		// 檢查空值
		if (orderDto.getAdditionalFreight() == null ) {
			orderDto.setAdditionalFreight(0D);
		}
		for(ItemDto item : items) {
		}
		
		// 檢查合同號
		checkContractNumber(orderDto);

		// is b2c
		if (items != null) {
			items.forEach(i -> {
				if (StringUtils.isNotEmpty(i.getB2cComments())) {
					orderDto.setIsB2c(1);
				}
			});
		}

		// 行项目金额合计
		double itemsAmount = 0; 
		for (ItemDto itemDto : items) {
			double discount = itemDto.getDiscount();
			itemsAmount += itemDto.getActualPrice() * itemDto.getQuantity() * discount;
		}
		// merge discount
		orderDto.setItemsAmount(itemsAmount);
		orderDto.setDiscount(BigDecimal.valueOf(itemsAmount / orderDto.getContractRmbValue())
				.setScale(4, RoundingMode.HALF_UP).doubleValue());

		// calculate gross profit margin
		List<MaterialGroups> margin = grossProfitMarginService.calculate(orderDto);
		orderDto.setGrossProfitMargin(new ObjectMapper().writeValueAsString(margin));

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
	 * 檢查合同號
	 * @param orderDto
	 */
	private void checkContractNumber(final OrderDto orderDto) {
		String contractNumber = StringUtils.trimToEmpty(orderDto.getContractNumber());
		if (contractNumber.length() > 0) {
			contractNumber = contractNumber.toUpperCase();
			if (contractNumber.length() > 10) {
				throw new RuntimeException("合同号已存在或格式不正确");
			}
			Map<String, Object> params = new HashMap<>();
			params.put("contractNumber", contractNumber);
			int count = sapOrderMapper.countByParams(params);
			if (count > 0) {
				throw new RuntimeException("合同号已存在或格式不正确");
			}
			params.clear();
			params.put("id", orderDto.getId());
			params.put("contractNumber", contractNumber);
			List<String> existsConstractNumberList =  orderInfoMapper.checkContractNumber(params);
			if (existsConstractNumberList.size() > 0) {
				throw new RuntimeException("合同号已存在或格式不正确");
			}
			orderDto.setContractNumber(contractNumber);
		}
	}

	/**
	 * 订单变更，BPM审批通过后的订单修改，产生新的版本
	 * 
	 * @param user
	 * @param orderInfoId
	 * @return
	 * @throws Exception
	 */
	@Transactional
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
	
	/**
	 * 复制订单
	 * @param user
	 * @param orderInfoId
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public OrderDto copy(String user, Integer orderInfoId) throws Exception {
		OrderDto order = this.findOrder(orderInfoId);
		
		order.setId(null);
		order.setOrderId(null);
		order.setCreater(user);
		order.setAttachments(null); // 清除订单附件
		order.setContractNumber(""); // 合同號
		for (ItemDto item : order.getItems()) { 
			item.setAttachments(null); // 清除调研表附件
		}
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
	 * 报价单下订单
	 * @param user
	 * @param orderInfoId
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public OrderDto transfer(String user, Integer orderInfoId) throws Exception {
		OrderDto order = this.findOrder(orderInfoId);
		String stOrderType = order.getStOrderType();
		String quoteStatus = order.getQuoteStatus();
		String status = order.getStatus();
		if (!stOrderType.equals("3")) {
			throw new RuntimeException("非直签客户投标报价单，不允许下单");
		}
		if (!quoteStatus.equals("01")) {
			throw new RuntimeException("还未中标，不允许下单");
		}
		if (!status.equals(OrderDto.ORDER_STATUS_APPROVED) && !status.equals(OrderDto.ORDER_STATUS_APPROVED_UPDATE)) {
			throw new RuntimeException("报价单未审批通过，不允许下单");
		}
		
		order.setId(null);
		order.setOrderId(null);
		order.setStOrderType("4"); // 下单，转为【直签客户下定单】
		order.setAttachments(null); // 清除订单附件
		for (ItemDto item : order.getItems()) { 
			item.setAttachments(null); // 清除调研表附件
		}
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
		
		this.updateQuoteStatus(user, orderInfoId, "02"); // 修改报价单报价状态

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
		if (attachments == null || attachments.size() == 0) {
			return;
		}
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
		if (payments == null || payments.size() == 0) {
			return;
		}
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
		if (addresses == null || addresses.size() == 0) {
			return;
		}
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
		// 先刪除訂單所有行項目及關聯表數據
		deleteItems(orderInfoId);
		List<ItemDto> items = orderDto.getItems();
		if (items == null || items.size() == 0) {
			return;
		}
		for (ItemDto itemDto : items) {
			if ( StringUtils.isEmpty(itemDto.getItemStatus())) {
				itemDto.setItemStatus("00");
			}
			
			String materialCode = itemDto.getMaterialCode();
			// BG1GD1000000-X  其他项目收费，成本固定1元，清除用户输入的标准价和转移价
			if (materialCode.equals("BG1GD1000000-X")) {
				itemDto.setTransactionPrice(0);
				itemDto.setStandardPrice(0);
			}
			// BG1R8R00000-X 冷库
			// BG1R8L00000-X 不可预估费
			// 设置标准价=用户输入的转移价
			if (materialCode.equals("BG1R8R00000-X") || materialCode.equals("BG1R8L00000-X")) {
				itemDto.setStandardPrice(itemDto.getTransactionPrice());
			}
			
			Item item = new Item();

			BeanUtils.copyProperties(item, itemDto);

			item.setId(null);
			item.setOrderInfoId(orderInfoId);

			itemMapper.insert(item);

			String colorOptions = "";
			List<CharacteristicDto> configs = itemDto.getConfigs();
			if (configs != null && configs.size() > 0) {
				for (CharacteristicDto dto : configs) {
					if (dto.isColor()) {
						ItemColor itemColor = new ItemColor();
						itemColor.setItemId(item.getId());
						itemColor.setPaintingClass(dto.getKeyCode());
						itemColor.setColorCode(dto.getValueCode());
						
						itemColorMapper.insert(itemColor);
						colorOptions += "," + itemColor.getPaintingClass() + ":" + itemColor.getColorCode();
					} else {
						Characteristics c = new Characteristics();
	
						BeanUtils.copyProperties(c, dto);
						c.setIsConfigurable(dto.isConfigurable() ? 1 : 0);
	
						c.setId(null);
						c.setItemId(item.getId());
	
						characteristicsMapper.insert(c);
					}
				}
			} else {
				// 没有配置调研表，取默认值颜色选项和物料特征
				List<Characteristic> characs = materialService.getCharactersByClazzCode(itemDto.getMaterialCode());
				if (characs != null && characs.size() > 0) {
					for (Characteristic c : characs) {
						boolean configurable = c.getConfigs() != null && c.getConfigs().size() > 0;
						boolean isColor = c.isColor();
						if (isColor) {
							ItemColor itemColor = new ItemColor();
							itemColor.setItemId(item.getId());
							itemColor.setPaintingClass(c.getCode());
							c.getConfigs().forEach(e -> {
								if (e.isDefault()) {
									itemColor.setColorCode(e.getCode());
								}
							});
							
							if (StringUtils.isEmpty(itemColor.getColorCode())) {
								itemColor.setColorCode(c.getConfigs().get(0).getCode());
							}
							itemColorMapper.insert(itemColor);
							colorOptions += "," + itemColor.getPaintingClass() + ":" + itemColor.getColorCode();
						} else {
							Characteristics cs = new Characteristics();
							cs.setItemId(item.getId());
							cs.setIsConfigurable(configurable ? 1 : 0);
							cs.setKeyCode(c.getCode());
							c.getConfigs().forEach(e -> {
								if (e.isDefault()) {
									cs.setValueCode(e.getCode());
								}
							});
							
							if (StringUtils.isEmpty(cs.getValueCode())) {
								cs.setValueCode(c.getConfigs().get(0).getCode());
							}
							characteristicsMapper.insert(cs);
						}
					}
				}
			}
			colorOptions = colorOptions.length() > 0 ? colorOptions.substring(1) : colorOptions;
			item.setColorOptions(colorOptions);
			itemMapper.update(item);
			
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
		// delete k_item_color
		this.itemColorMapper.deleteByOrderInfoId(orderInfoId);
		
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
	@Transactional
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
	 * 驳回订单
	 * 
	 * @param order
	 * @throws Exception
	 */
	@Transactional
	public void reject(String user, Integer orderInfoId) throws Exception {
		orderInfoMapper.updateStatus(orderInfoId, user, OrderDto.ORDER_STATUS_REJECT, null, null, null, null);
	}

	/**
	 * 
	 * @return sales type
	 */
	public List<SalesType> getSalesTypes() {
		return salesTypeRepo.findAll();
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
		List<Area> bas = districtRepo.findAll();
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

		// 标准折扣，Code：std_discount
		Settings p = settingsRepository.findEnabledInfo("std_discount");
		if (p != null) {
			oo.setStandardDiscount(p.getsValue());
		}

		// 税率，Code：tax_rate
		p = settingsRepository.findEnabledInfo("tax_rate");
		if (p != null) {
			Map<String, Double> taxRate = new HashMap<String, Double>();
			taxRate.put("10", Double.valueOf(p.getsValue()));
			taxRate.put("30", Double.valueOf(p.getsValue()));

			oo.setTaxRate(taxRate);
		}

		// 税率，Code：tax_rate
		List<Settings> settings = settingsService.findDistinctInfo();
		Map<String, String> settingsMap = new HashMap<String, String>();
		oo.setSettings(settingsMap);
		for (Settings s : settings) {
			settingsMap.put(s.getCode(), s.getsValue());
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
	 * @throws Exception 
	 */
	@Transactional
	public String sendToSap(String user, Integer orderInfoId) throws Exception {
		OrderDto orderDto = this.findOrder(orderInfoId);
		// this.save(user, orderDto);
		String stOrderType = orderDto.getStOrderType();
		String status = orderDto.getStatus();
		
		if ("3".equals(stOrderType)) {
			throw new RuntimeException("直销客户投标报价单不能下发SAP");
		}
		
		if (!OrderDto.ORDER_STATUS_APPROVED.equals(status) && !OrderDto.ORDER_STATUS_APPROVED_UPDATE.equals(status)) {
			throw new RuntimeException("当前状态不能下发SAP");
		}

		if (orderDto.getVersionNum() > 1) {
			sapOrderService.updateOrder(orderDto);
		} else {
			sapOrderService.createOrder(orderDto);
		}
//		  	logger.info("SAP同步开单结果==>"+sapRes);
		// 修改订单状态为已下发SAP
		orderInfoMapper.updateStatus(orderDto.getId(), user, OrderDto.ORDER_STATUS_SAP, null, null, null, null);
		//  修改行项目状体为已下发SAP
		Item item = new Item(); 
		item.setOrderInfoId(orderInfoId);
		item.setItemStatus("10");
		itemMapper.updateSendSapStatusByOrderInfo(item);

		return "success";

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
			item.setVolumeCube(m.getMaterialSize());

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

			List<CharacteristicDto> configs = new ArrayList<>();
			item.setConfigs(configs);
			
			// colors
			List<CharacteristicDto> colors = itemColorMapper.findByItemId(itemId);
			configs.addAll(colors);
			// characteristics
			List<CharacteristicDto> characteristics = characteristicsMapper.findByItemId(itemId);
			configs.addAll(characteristics);
			
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
	@Transactional
	public void submitBpm(String user, OrderDto order) throws Exception {
		order = save(user, order);
		List<ItemDto> items = order.getItems();
		if (items == null) {
			items = new ArrayList<ItemDto>();
		}
		List<Attachment> attachments = order.getAttachments();
		if (attachments == null) {
			attachments = new ArrayList<Attachment>();
		}
		List<MaterialGroups> grossProfitMargins = new ObjectMapper().readValue(order.getGrossProfitMargin(), new TypeReference<List<MaterialGroups>>() {});
		MaterialGroups sumMargin = grossProfitMargins.get(grossProfitMargins.size() - 1);

		BpmOrder bpmOrder = new BpmOrder();
		OrderHeader bpmHeader = new OrderHeader();
		List<OrderAttachment> bpmAttachments = new ArrayList<OrderAttachment>(attachments.size());
		List<OrderItem> bpmItems = new ArrayList<OrderItem>(items.size());
		List<OrderMargin> bpmMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());
		List<OrderMargin> bpmWtwMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());

		bpmOrder.setOrder(bpmHeader);
		bpmOrder.setAttachments(bpmAttachments);
		bpmOrder.setItems(bpmItems);
		bpmOrder.setMargin(bpmMargins);
		bpmOrder.setWtwMargin(bpmWtwMargins);

		// set bpm order
		List<DeliveryAddressDto> addresses = order.getDeliveryAddress();
		if (addresses != null && addresses.size() > 0) {
			StringBuilder strAddress = new StringBuilder(512);
			for (DeliveryAddressDto deliveryAddressDto : addresses) {
				String provinceName = StringUtils.isEmpty(deliveryAddressDto.getProvinceCode()) ? "" : provinceRepo.findById(deliveryAddressDto.getProvinceCode()).get().getName();
				String cityName = StringUtils.isEmpty(deliveryAddressDto.getCityCode()) ? "" : cityRepo.findById(deliveryAddressDto.getCityCode()).get().getName();
				String districtName = StringUtils.isEmpty(deliveryAddressDto.getDistrictCode()) ? "" : districtRepo.findById(deliveryAddressDto.getDistrictCode()).get().getName();
				String address = StringUtils.trimToEmpty(deliveryAddressDto.getAddress());
				strAddress.append(",").append(provinceName).append(cityName).append(districtName).append(address);
			}
			bpmHeader.setAddress(strAddress.substring(1));
		} else {
			bpmHeader.setAddress("");
		}
		bpmHeader.setApprovalDiscount(order.getDiscount());
		bpmHeader.setB2c(String.valueOf(order.getIsB2c()));
		bpmHeader.setBodyDiscount(order.getBodyDiscount());
		bpmHeader.setComments(StringUtils.trimToEmpty(order.getComments()));
		bpmHeader.setContractAmount(order.getContractValue());
		bpmHeader.setContractNumber(StringUtils.trimToEmpty(order.getContractNumber()));
		bpmHeader.setContractRmbAmount(order.getContractRmbValue());
		bpmHeader.setCreateTime(order.getCreateTime());
		String currencyName = constService.findAllCurrency().get(order.getCurrency()).getName();
		bpmHeader.setCurrencyName(StringUtils.trimToEmpty(currencyName));
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
		// 结算方式
		String paymentType = StringUtils.trimToEmpty(order.getPaymentType());
		String paymentTypeName = constService.findDealerPaymentTerms().get(paymentType);
		if (StringUtils.isEmpty(paymentTypeName)) {
			paymentTypeName = paymentType;
		}
		bpmHeader.setPaymentTypeName(paymentTypeName);
		// 运输类型
		String receiveTypeName = constService.findShippingTypes().get(order.getTransferType());
		bpmHeader.setReceiveTypeName(StringUtils.trimToEmpty(receiveTypeName));
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
		bpmHeader.setIsUrgentDelivery(order.getIsUrgentDelivery());
		bpmHeader.setIsSpecialOrder(order.getIsSpecialOrder());

		// set bpm order attachements
		for (Attachment attachment : attachments) {
			OrderAttachment orderAttachment = new OrderAttachment();
			bpmAttachments.add(orderAttachment);
			
			orderAttachment.setName(attachment.getFileName());
			orderAttachment.setUrl(attachment.getFileUrl());
		}

		// set bpm order item
		if (items.size() > 0) {
			StringBuilder strGroupName = new StringBuilder(256);
			for (ItemDto itemDto : items) {
				strGroupName.append(",").append(itemDto.getMaterialGroupName());
				OrderItem bpmItem = new OrderItem();
				bpmItems.add(bpmItem);

				bpmItem.setActuralAmount(itemDto.getActualPrice() * itemDto.getQuantity());
				bpmItem.setActuralAmountOfOptional(itemDto.getOptionalActualPrice() * itemDto.getQuantity());
				bpmItem.setActuralPrice(itemDto.getActualPrice());
				bpmItem.setActuralPriceOfOptional(itemDto.getOptionalActualPrice());
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
				bpmItem.setTransactionPriceOfOptional(itemDto.getOptionalTransactionPrice());
				bpmItem.setTransfterPrice(itemDto.getTransactionPrice());
				double standardCost = itemDto.getStandardPrice() + (itemDto.getOptionalStandardPrice() == null ? 0 : itemDto.getOptionalStandardPrice()) ;
				bpmItem.setStandardCost(standardCost);
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
	@Transactional
	public void updateBpmStatus(String user, String sequenceNumber, String status, Double bodyDiscount,
			Double unitDiscount) throws Exception {
		OrderInfo orderInfo = orderInfoMapper.findByParams(null, sequenceNumber, null, "1").get(0);
		Order order = orderMapper.findById(orderInfo.getOrderId());
		
		String orderStatus = orderInfo.getStatus();
		// 如果定状态已经是bpm审批通过，而存在未审批通过的特批发货申请，则修改特批发货申请状态
		if (OrderDto.ORDER_STATUS_APPROVED_UPDATE.equals(orderStatus) || OrderDto.ORDER_STATUS_APPROVED.equals(orderStatus)) {
			SpecialOrderApplication specialOrderApplication = specialOrderApplicationMapper.findByOrderInfo(orderInfo.getId());
			if (specialOrderApplication != null && specialOrderApplication.getApplyStatus().equals("1")) {
				if (status.equals("1")) {
					// 审批通过
					specialOrderApplication.setApplyStatus(21);
				} else {
					// 审批拒绝
					specialOrderApplication.setApplyStatus(3);
				}
				
				specialOrderApplication.setApprovalTime(new Date());
				specialOrderApplication.setApprover(user);
				
				specialOrderApplicationMapper.update(specialOrderApplication);
			}
			
			return;
		}

		// 更新订单信息
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

			// 经销商非标准折扣订单，并且柜体折扣或机组折扣在bpm审批时被修改，并且不是长期折扣
			if (order.getStOrderType().equals("2")
					&& (orderInfo.getBodyDiscount() != bodyDiscount || orderInfo.getMainDiscount() != unitDiscount)
					&& (ObjectUtils.defaultIfNull(orderInfo.getIsLongterm(), 0) != 1)) {
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
				List<MaterialGroups> margin = grossProfitMarginService.calculate(orderDto);
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

	/**
	 * 修改报价单报价状态
	 * 
	 * @param user
	 * @param orderInfoId
	 * @param quoteStatus
	 * @return
	 */
	@Transactional
	public String updateQuoteStatus(String user, Integer orderInfoId, String quoteStatus) {
		OrderInfo orderInfo = orderInfoMapper.findById(orderInfoId);
		Order order = orderMapper.findById(orderInfo.getOrderId());
		order.setQuoteStatus(quoteStatus);
		orderMapper.update(order);
		return null;
	}

}
