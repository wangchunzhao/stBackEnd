package com.qhc.frye.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BAreaRepository;
import com.qhc.frye.dao.BCityRepository;
import com.qhc.frye.dao.BProvinceRepository;
import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.dao.DOrderRepository;
import com.qhc.frye.dao.ItemDetailRepository;
import com.qhc.frye.dao.KBiddingPlanRepository;
import com.qhc.frye.dao.KCharacteristicsRepository;
import com.qhc.frye.dao.KDelieveryAddressRepository;
import com.qhc.frye.dao.KOrderFormRepository;
import com.qhc.frye.dao.KOrderInfoRepository;
import com.qhc.frye.dao.KOrderVersionRepository;
import com.qhc.frye.dao.KOrderVersionViewRepository;
import com.qhc.frye.dao.KOrderViewRepository;
import com.qhc.frye.dao.OrderSupportInforRepository;
import com.qhc.frye.dao.PaymentTermRepository;
import com.qhc.frye.dao.SalesGroupRepository;
import com.qhc.frye.dao.SalesOfficeRepository;
import com.qhc.frye.dao.SalesTypeRepository;
import com.qhc.frye.dao.TerminalIndustryCodeRepository;
import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.BCity;
import com.qhc.frye.domain.BProvince;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.ItemDetails;
import com.qhc.frye.domain.ItemsForm;
import com.qhc.frye.domain.KBiddingPlan;
import com.qhc.frye.domain.KCharacteristics;
import com.qhc.frye.domain.KDelieveryAddress;
import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.KOrderVersionView;
import com.qhc.frye.domain.KOrderView;
import com.qhc.frye.domain.OrderSupportInfo;
import com.qhc.frye.domain.PaymentTerm;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.domain.TermianlIndustryCode;
import com.qhc.frye.rest.controller.entity.form.AbsOrder;
import com.qhc.frye.rest.controller.entity.form.BaseOrder;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.OrderQuery;
import com.qhc.frye.rest.controller.entity.OrderVersion;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.rest.controller.entity.PaymentPlan;
import com.qhc.frye.rest.controller.entity.SalesOrder;
import com.qhc.frye.rest.controller.entity.form.AbsCharacteristic;
import com.qhc.frye.rest.controller.entity.form.AbsItem;
import com.qhc.frye.rest.controller.entity.form.BulkOrder;
import com.qhc.frye.rest.controller.entity.form.DealerOrder;
import com.qhc.frye.rest.controller.entity.form.KeyAccountOrder;
import com.qhc.frye.rest.controller.entity.form.OrderAddress;
import com.qhc.frye.rest.controller.entity.form.OrderHelper;
import com.qhc.frye.rest.controller.entity.form.ProductItem;
import com.qhc.frye.rest.controller.entity.sap.SapOrder;
import com.qhc.frye.rest.controller.entity.sap.SapOrderCharacteristics;
import com.qhc.frye.rest.controller.entity.sap.SapOrderHeader;
import com.qhc.frye.rest.controller.entity.sap.SapOrderItem;
import com.qhc.frye.rest.controller.entity.sap.SapOrderPlan;
import com.qhc.frye.rest.controller.entity.sap.SapOrderPrice;

@Service
public class OrderService {
	private final static String ORDER_TYPE_DEALER = "ZH0D"; //'经销商订单'
	private final static String ORDER_TYPE_BULK = "ZH0M"; // '备货订单'
	private final static String ORDER_TYPE_KEYACCOUNT = "ZH0T"; // '大客户订单'

	@Autowired
	private SalesTypeRepository salesTypeRepo;

	@Autowired
	private DOrderRepository dOrderRepository;

	@Autowired
	private OrderSupportInforRepository supportRepo;

	@Autowired
	private BAreaRepository distinctRepo;

	@Autowired
	private BCityRepository cityRepo;

	@Autowired
	private BProvinceRepository provinceRepo;

	@Autowired
	private TerminalIndustryCodeRepository industryCodeRepo;

	@Autowired
	private SalesTypeRepository saleTypeRepo;

	@Autowired
	private SalesOfficeRepository officeRepo;

	@Autowired
	private SalesGroupRepository groupsRepo;

	@Autowired
	private CurrencyRepository currencyRepo;

	@Autowired
	private PaymentTermRepository paymentRepo;

	@Autowired
	private KOrderVersionViewRepository orderVersionViewRepository;
	
	@Autowired
	private KOrderVersionRepository orderVersionRepo;

	@Autowired
	private KOrderViewRepository orderViewRepository;

	@Autowired
	private ItemDetailRepository itemDetailRepository;

	@Autowired
	private KCharacteristicsRepository characteristicsRepository;

	@Autowired
	private KBiddingPlanRepository biddingPlanRepository;

	@Autowired
	private KDelieveryAddressRepository deliveryAddressRepository;

	@Autowired
	private KOrderInfoRepository orderInfoRepo;

	@Autowired
	private ConstantService constService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private BayernService<SapOrder> bayernService;
	
	@Autowired
	private KOrderFormRepository formRepo;

	private final static String ORDER_CREATION_SAP = "order/create/sapOrder";

	/**
	 * 
	 * @return sales type
	 */
	public List<DSalesType> getSalesTypes() {
		return salesTypeRepo.findAll();
	}

	/**
	 * 
	 * @param paymentPlan
	 */
	public void savePaymentPlan(List<PaymentPlan> paymentPlan) {
		Set<PaymentTerm> incos = new HashSet<PaymentTerm>();
		for (PaymentPlan inco : paymentPlan) {
			PaymentTerm temp = new PaymentTerm();
			temp.setCode(inco.getCode());
			temp.setName(inco.getName());
			temp.setIsPaymentTerm(inco.getPaymentTerm());
			incos.add(temp);
		}
		paymentRepo.saveAll(incos);
	}

	/**
	 * 
	 * @param absOrder
	 */
	public void save(AbsOrder order,boolean fromSupportor) {

		String seq = order.getSequenceNumber();
		DOrder dorder = dOrderRepository.findBySequence(order.getSequenceNumber());
		OrderHelper ohelper = new OrderHelper(order);
		dorder = ohelper.toDOrder();
		dorder = dOrderRepository.save(dorder);
		//
		if(fromSupportor) {
			OrderSupportInfo supportInfo = ohelper.toSupportInforOfOrder();
			OrderSupportInfo osi = supportRepo.findByOrderId(dorder.getId());
			if(osi.getId()!=0) {
				supportInfo.setId(osi.getId());
			}
			supportRepo.save(supportInfo);
		}
		//
		
		//
		String orderId = null;
		KOrderVersion lversion = orderVersionRepo.findLastOneByOrderId(dorder.getId());
		if(lversion!=null) {
			if(lversion.getStatus().equals("05")||lversion.getStatus().equals("06")||lversion.getStatus().equals("10")) {
				lversion = new KOrderVersion();
			}
			
				
		}else {
			KOrderInfo kOrderInfo = ohelper.toOrderInfo();
			kOrderInfo = orderInfoRepo.save(kOrderInfo);
			lversion.setOrderId(kOrderInfo.getId());
			//
			List<KDelieveryAddress> adds=ohelper.toAddress(kOrderInfo.getId());
			deliveryAddressRepository.saveAll(adds);
			
			ItemsForm form = ohelper.toForm(kOrderInfo.getId());
			form = formRepo.save(form);
			
//			List<ItemDetails> details = baseOrder.toDetails(form.getId());
//			details =itemDetailRepository.saveAll(details);
			String formId = form.getId();
			//
			for(AbsItem item: order.getItems()) {
				ItemDetails temp = new ItemDetails();
				temp.setRowNumber(item.getRowNumber());
				temp.setMaterialCode(item.getMaterialCode());
				temp.setMaterialName(item.getMaterialName());
				if(item.getIsVirtual()==0)
					temp.setVirtual(false);
				else
					temp.setVirtual(true);
				temp.setMaterialAttribute(item.isPurchased());
				temp.setQuantity(item.getQuantity());
				temp.setMeasureUnitCode(item.getUnitCode());
				temp.setAmount(new BigDecimal(item.getRetailPrice()*item.getQuantity()));
				temp.setTransfterPrice(new BigDecimal(item.getTranscationPrice()));
				temp.setStandardPrice(new BigDecimal(item.getStandardPrice()));
				temp.setB2cComments(item.getB2cComments());
				temp.setB2cEstimationAmount(new BigDecimal(item.getB2CPriceEstimated()));
				temp.setB2cEstimationCost(new BigDecimal(item.getB2CCostOfEstimated()));
				temp.setMaterialCode(item.getMaterialCode());
				temp.setMaterialGroupCode(item.getGroupCode());
				temp.setMaterialGroupName(item.getGroupName());
				temp.setDiscount(item.getDiscount());
				temp.setItemCategory(item.getItemCategory());
				temp.setItemRequirementPlan(item.getItemRequirementPlan());
				temp.setVolumeCube(new BigDecimal(item.getVolumeCube()));
				temp.setFreight(new BigDecimal(item.getFeight()));
				temp.setRetailPrice(new BigDecimal(item.getRetailPrice()));
				temp.setDelieveryDate(item.getDeliveryDate());
				temp.setSpecialNeed(item.getSpecialComments());
				temp.setMosaicImage(item.getMosaicImage());
				temp.setAttachedImage(item.getAttachedImage());
				temp.setRequestBrand(item.getRequestBrand());
				temp.setRequestCircuit(item.getRequestCircult());
				temp.setRequestPackage(item.getRequestPackage());
				temp.setRequestNameplate(item.getRequestNameplate());
				temp.setComments(item.getComments());
				temp.setColorComments(item.getColorComments());
				//
				temp.setkFormsId(formId);
				//
				temp = itemDetailRepository.save(temp);
				for(AbsCharacteristic ac :item.getConfigs()) {
					KCharacteristics tc = new KCharacteristics();
					tc.setKeyCode(ac.getConfigCode());
					tc.setValueCode(ac.getConfigValueCode());
					
					tc = characteristicsRepository.save(tc);
				}
				
			}
			
		}

	}
	/**
	 * 
	 * @param absOrder
	 */
//	public void submit(AbsOrder order) {
//
//		DOrder sDorder = dOrderRepository.saveAndFlush(order.getDorder());
//		OrderSupportInfo ori = order.getSupportInforOfOrder();
//		if (!order.getContractNumber().trim().isEmpty())
//			ori.setOrderId(sDorder.getId());
//		supportRepo.saveAndFlush(ori);
//		KOrderVersion over = order.getOrderVersion();
//		over.setOrderId(sDorder.getId());
//		KOrderVersion kov = versionRepo.saveAndFlush(over);
//	}

	/**
	 * 
	 * @param absOrder
	 */
	public void update(AbsOrder absOrder) {

	}

	public DOrder findByKOrderVersionId(String id) {

		return dOrderRepository.getOne(id);
	}

	public List<SapSalesGroup> findGrossProfitBySalesOrder(SalesOrder saleOrder, List<SapSalesGroup> salesGroups) {

//		List<ItemDetails> details = new ArrayList<ItemDetails>();
//		;
//		if (saleOrder.getItemsForm() != null) {
//			details = saleOrder.getItemsForm().getDetailsList();
//		}
//		// 提交类型 3.margin 4.wtw margin
//		int submitType = saleOrder.getSubmitType();
//
//		// 税率
//		Double taxRate = saleOrder.getTaxRate();
//
//		// 物料类型表
//		// sapSalesGroups
//
//		// 毛利表
//		BigDecimal sumAmount = BigDecimal.ZERO;// 金额
//		BigDecimal sumExcludingTaxAmount = BigDecimal.ZERO;// 不含税金额
//		BigDecimal sumCost = BigDecimal.ZERO;// 成本
//		BigDecimal sumGrossProfit = BigDecimal.ZERO;// 毛利
//		Double sumGrossProfitMargin = 0D;// 毛利率
//		if (details != null && details.size() > 0) {
//			for (SapSalesGroup entity : salesGroups) {
//				BigDecimal amount = BigDecimal.ZERO;// 金额
//				BigDecimal excludingTaxAmount = BigDecimal.ZERO;// 不含税金额
//				BigDecimal cost = BigDecimal.ZERO;// 成本
//				BigDecimal grossProfit = BigDecimal.ZERO;// 毛利
//				Double grossProfitMargin = 0D;// 毛利率
//
//				for (ItemDetails item : details) {
//					if (item.getMaterialCode().equals(entity.getCode())) {
//						// 总金额
//						amount = amount.add(item.getAmount());
//						// 总金额减去税金 = 不含税金额
//						excludingTaxAmount = excludingTaxAmount
//								.subtract(sumAmount.multiply(BigDecimal.valueOf(taxRate)));
//						// 成本
//						if (submitType == 3) {
//							cost = cost.add(item.getStandardPrice());
//						}
//						if (submitType == 4) {
//							cost = cost.add(item.getTransfterPrice());
//						}
//						// 毛利
//						grossProfit = excludingTaxAmount.subtract(cost);
//						// 毛利率
//						grossProfitMargin = this.CalculateGrossProfit(excludingTaxAmount, cost);
//
//					}
//				}
//
//				entity.setAmount(amount);
//				entity.setExcludingTaxAmount(excludingTaxAmount);
//				entity.setCost(cost);
//				entity.setGrossProfit(grossProfit);
//				entity.setGrossProfitMargin(grossProfitMargin);
//
//				sumAmount = sumAmount.add(amount);
//				sumExcludingTaxAmount = sumExcludingTaxAmount.add(excludingTaxAmount);
//				sumCost = sumCost.add(cost);
//				sumGrossProfit = sumGrossProfit.add(grossProfit);
//				sumAmount = sumAmount.add(amount);
//			}
//
//		}
//
//		SapSalesGroup sumssg = new SapSalesGroup();
//		sumssg.setAmount(sumAmount);
//		sumssg.setExcludingTaxAmount(sumExcludingTaxAmount);
//		sumssg.setCost(sumCost);
//		sumssg.setGrossProfit(sumGrossProfit);
//		if (salesGroups.size() != 0) {
//			sumssg.setGrossProfitMargin(sumGrossProfitMargin / salesGroups.size());
//		} else {
//			sumssg.setGrossProfitMargin(0D);
//		}
//		sumssg.setCode("sum");
//		sumssg.setName("合计");
//
//		salesGroups.add(sumssg);
//
//		return salesGroups;
		return null;
	}

	public Double CalculateGrossProfit(BigDecimal afterTaxAmount, BigDecimal cost) {

		return (afterTaxAmount.subtract(cost)).divide(afterTaxAmount).setScale(4, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 
	 * @return
	 */
	public OrderOption getOrderOption() {
		OrderOption oo = new OrderOption();
		//
		List<BProvince> bps = provinceRepo.findAll();
		Map<String, String> provinces = oo.getProvinces();
		for (BProvince bp : bps) {
			provinces.put(bp.getCode(), bp.getName());
		}
		//
		List<BCity> bcs = cityRepo.findAll();
		Map<String, Map<String, String>> citys = oo.getCitys();
		for (BCity bc : bcs) {
			String pcode = bc.getbProvinceCode();
			Map<String, String> vals = new HashMap<String, String>();
			vals.put(bc.getCode(), bc.getName());
			if (citys.putIfAbsent(pcode, vals) != null) {
				citys.get(pcode).put(bc.getCode(), bc.getName());
			}

		}
		//
		List<BArea> bas = distinctRepo.findAll();
		Set<BArea> distincts = oo.getDistricts();
		distincts.addAll(bas);
		//
		Map<String, String> incs = oo.getTermialClass();
		List<TermianlIndustryCode> tics = industryCodeRepo.findAll();
		for (TermianlIndustryCode tic : tics) {
			incs.put(tic.getCode(), tic.getName());
		}
		//
		Map<String, String> st = oo.getSaleTypes();
		List<DSalesType> dsts = saleTypeRepo.findAll();
		for (DSalesType dst : dsts) {
			st.put(dst.getCode(), dst.getName());
		}
		//
		Map<String, Map<String, String>> offices = oo.getOffices();
		List<SapSalesOffice> ssos = officeRepo.findAll();
		for (SapSalesOffice so : ssos) {
			String tcode = so.getTypeCode();
			Map<String, String> vals = new HashMap<String, String>();
			vals.put(so.getCode(), so.getName());
			if (offices.putIfAbsent(tcode, vals) != null) {
				offices.get(tcode).put(so.getCode(), so.getName());
			}
		}
		//
		Map<String, Map<String, String>> groups = oo.getGroups();
		List<SapSalesGroup> ssgs = groupsRepo.findAll();
		for (SapSalesGroup ssg : ssgs) {
			String ocode = ssg.getOfficeCode();
			Map<String, String> vals = new HashMap<String, String>();
			vals.put(ssg.getCode(), ssg.getName());
			if (groups.putIfAbsent(ocode, vals) != null) {
				groups.get(ocode).put(ssg.getCode(), ssg.getName());
			}
		}
		//
		Map<String, Double> taxRate = oo.getTaxRate();
		taxRate.put("10", 0.13);
		taxRate.put("20", 0.0);
		taxRate.put("30", 0.13);

		//
		Map<String, Currency> currencys = oo.getExchangeRate();
		List<DCurrency> dcs = currencyRepo.findAll();
		for (DCurrency dc : dcs) {
			Currency val = new Currency();
			val.setCode(dc.getCode());
			val.setName(dc.getName());
			val.setRate(dc.getRate());
//			currencys.put(dc.getSalesTypeCode(), val);
		}
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

		return oo;
	}

	/**
	 * 根据流水号组装数据并同步SAP
	 * 
	 * @param sequenceNumber
	 * @return
	 */
	public String orderCreationForSAP(String sequenceNumber) {

		// 1. 根据sequenceNumber组装数据
		SapOrder sapOrder = assembleSapOrder(sequenceNumber);

		// 2. 调用bayernService同步SAP
		bayernService.postJason(ORDER_CREATION_SAP, sapOrder);

		return "SUCCESS";

	}

	/**
	 * 根据流水号组装数据
	 * 
	 * @param sequenceNumber
	 * @return //
	 */
	private SapOrder assembleSapOrder(String sequenceNumber) {
		List<KOrderVersionView> verions = orderVersionViewRepository
				.findBySequenceNumberOrderByCreateTime(sequenceNumber);

		String orderId = null;
		String orderInfoId = null;
		String orderVersionId = null;

		for (KOrderVersionView kOrderVersionView : verions) {
			orderId = kOrderVersionView.getOrderId();
			Integer status = kOrderVersionView.getStatus();
			// 最后一个审批通过的版本
			if (status.equals(3)) {
				orderInfoId = kOrderVersionView.getOrderInfoId();
				orderVersionId = kOrderVersionView.getVersionId();
			}
		}

		if (orderInfoId == null) {
			throw new NullPointerException(
					MessageFormat.format("Not found approved order with sequence number [{0}]", sequenceNumber));
		}

//		OrderQuery query = new OrderQuery();
//		List<SalesOrder> orders = findOrder(query);
//		SalesOrder order = orders.get(0);
		KOrderView orderView = orderViewRepository.findByOrderIdAndVersionId(orderId, orderVersionId);
		String orderType = orderView.getOrderTypeCode();

		// assemble sap order header
		SapOrderHeader header = new SapOrderHeader();
		List<SapOrderItem> items = new ArrayList<SapOrderItem>();
		List<SapOrderCharacteristics> characs = new ArrayList<SapOrderCharacteristics>();
		List<SapOrderPrice> prices = new ArrayList<SapOrderPrice>();
		List<SapOrderPlan> plans = new ArrayList<SapOrderPlan>();

		// Header input
		header.setAuart(toString(orderView.getOrderTypeCode()));	// Sales order type/订单类型
		header.setVkorg("0841");	// Sales org./销售组织 -- Fixed value/固定为 0841
		header.setVtweg(toString(orderView.getContractorClassCode()));	// DC/分销渠道 -- 客户
		header.setName2(orderView.getCustomerName());	// Store name/店名
		header.setSpart(orderView.getSalesType());	// Division/产品组
		header.setVkbur(toString(orderView.getOfficeCode()));	// Sales office/销售办公室 -- 大区
		header.setVkgrp(toString(orderView.getGroupCode()));	// Sales group/销售组 -- 中心
		header.setVbeln(toString(orderView.getContractNumber()));	// SO number/销售订单编号 -- 合同号
		header.setKvgr1(toString(orderView.getIsConvenientStore()));	// Customer grp.1/客户组1 -- 是否便利店
		header.setKvgr2(toString(orderView.getIsNew()));	// Customer grp.2/客户组2 -- 是否新客户
		header.setKvgr3(toString(orderView.getIsReformed()));	// Customer grp.3/客户组3 -- 是否改造店
		header.setBstzd(toString(orderView.getWarranty()));	// Additional/附加的 -- 保修年限
		header.setBstkdE(toString(orderView.getContractNumber()));	// Ship-to PO/送达方-采购订单编号 -- 项目报备编号 - 合同号
		header.setVsart(toString(orderView.getTransferTypeCode()));	// Shipping type/装运类型 -- 运输类型
//		header.setZterm();	// Payment terms/付款条款 -- 结算方式  大客户为空，dealer取billing_plan的第一条code（唯一一条） 
		header.setKunnr(toString(orderView.getContractorCode()));	// Sold-to party/售达方 -- 签约单位
		header.setWaerk(toString(orderView.getCurrencyCode()));	// Currency/币别 -- 币别
		header.setInco1(toString(orderView.getIncotermCode()));	// Incoterms/国际贸易条款 -- 国际贸易条件 code
		header.setInco1(toString(orderView.getIncotermName()));	// Incoterms2/国际贸易条款2 -- 国际贸易条件2 name
//		// 折扣
		header.setVbbkz120(String.valueOf(orderView.getContractRmbAmount()));	// Contract amount/合同金额 -- 合同金额
		header.setVbbkz121(orderView.getContractorName());	// Sale rep./签约人 -- 客户经理
		header.setVbbkz109(orderView.getOpteratorDomainId());	// Order clerk/合同管理员 -- 合同管理员
		String contactorInfo = toString(orderView.getContactor1Id()) + "/" + toString(orderView.getContactor1Tel())
		 + toString(orderView.getContactor2Id()) + "/" + toString(orderView.getContactor2Tel())
		 + toString(orderView.getContactor3Id()) + "/" + toString(orderView.getContactor3Tel());
		header.setVbbkz108(contactorInfo);	// Contact info./授权人信息 -- 授权人信息6个字段 3个联系人id+tel / 分隔
		String vbbkz122 = toString(orderView.getIsTerm1()) + "/" + toString(orderView.getIsTerm2())
		 + toString(orderView.getIsTerm3());
		header.setVbbkz122(vbbkz122);	// Survey info. for header /调研表相关内容 -- 调研表相关内容3个字段 
		header.setVbbkz106(orderView.getReceiveTermCode()); // Receiving method /收货方式 -- 收货方式

		List<ItemDetails> itemDetails = itemDetailRepository.findByKFormsId(orderView.getFormId());
		for (ItemDetails itemDetail : itemDetails) {
			int rowNumber = itemDetail.getRowNumber();
			SapOrderItem item = new SapOrderItem();
			// Ship-to PO item/送达方-采购订单编号项目
			item.setPosnr(rowNumber);
			// Material Number/物料编码
			item.setMatnr(itemDetail.getMaterialCode());
			// Target quantity/数量
			item.setZmeng(itemDetail.getAmount().intValue());
			// Req.dlv.date/请求发货日期
//			item.setEdatu(itemDetail.getDeliveryDate());
			// Item category/行项目类别 -- 项目类别
			item.setPstyv(itemDetail.getItemCategory());
			// Item usage/项目用途 -- 项目需求计划
			item.setVkaus(itemDetail.getItemRequirementPlan());

			// Ship-to address/送达方地址
			// json格式，{省code:省名称，市code:市名称,区code:区名称,address:街道}
//			String address = itemDetail.getAddress(); 
			// 街道名称
//			item.setStreet(街道); 
//			// Province/省 -- 省code
//			item.setRegion();
//			// City/市 -- 市名称
//			item.setCity1();
//			// District/区 -- 区名称
//			item.setCity2();

			// B2C note/B2C备注
			item.setVbbp0006(itemDetail.getB2cComments());
			// Survey info. for item /调研表基本信息
			StringBuilder sb = new StringBuilder(128);
			if(!isEmpty(itemDetail.getRequestBrand()) ) {
				sb.append(",").append(itemDetail.getRequestBrand());
			}
			if(!isEmpty(itemDetail.getRequestPackage()) ) {
				sb.append(",").append(itemDetail.getRequestPackage());
			}
			if(!isEmpty(itemDetail.getRequestNameplate()) ) {
				sb.append(",").append(itemDetail.getRequestNameplate());
			}
			if(!isEmpty(itemDetail.getRequestCircuit()) ) {
				sb.append(",").append(itemDetail.getRequestCircuit());
			}
			item.setVbbpz121(sb.length() > 0 ? sb.substring(1) : "");
			// Special note/特殊备注
			item.setVbbpz117(itemDetail.getSpecialNeed());
			// Color option/颜色可选项 -- Characteristics中处理
//			item.setVbbpz120(String);
			// Survey info. Note/调研表备注
			item.setVbbp0007(itemDetail.getComments());
			// Color Note/颜色备注
			item.setVbbpz118(itemDetail.getColorComments());

			items.add(item);

			// Price/condition record input
			// ZH05：实卖价合计
			SapOrderPrice price1 = new SapOrderPrice();
			price1.setPosnr(rowNumber);
			price1.setKschl("ZH05");
			price1.setKbetr(itemDetail.getAmount());
			prices.add(price1);
			// ZH08：转移价合计/成本合计
			SapOrderPrice price2 = new SapOrderPrice();
			price2.setPosnr(rowNumber);
			price2.setKschl("ZH08");
			price2.setKbetr(itemDetail.getTransfterPrice());
			prices.add(price2);

			// Characteristics value input
			List<KCharacteristics> characList = characteristicsRepository.findByItemDetailsId(itemDetail.getId());
			for (KCharacteristics charac : characList) {
				SapOrderCharacteristics c = new SapOrderCharacteristics();

				if (charac.getIsConfigurable() == 1) {
					// 设置 Item 的 Color option/颜色可选项
					String vbbpz120 = item.getVbbpz120();
					String tmp = charac.getKeyCode() + ":" + charac.getValueCode();
					vbbpz120 = (vbbpz120 == null || vbbpz120.length() == 0) ? tmp : "," + tmp;
					item.setVbbpz120(vbbpz120);
					continue;
				}

				// Item/行项目编号
				c.setPosnr(rowNumber);
				// Characteristic/特性
				c.setAtnam(charac.getKeyCode());
				// Char. value/特性值
				c.setAtwrt(charac.getValueCode());
				characs.add(c);
			}
		}

		// Billing plan
		// 当订单为经销商订单，billing plan只有一条数据，金额为空或0，不向sap发送billing plan数据，sap order
		// header的付款条款为billing plan 的 code
		List<KBiddingPlan> planList = biddingPlanRepository.findByOrderInfoId(orderInfoId);
		for (KBiddingPlan kBiddingPlan : planList) {
//			if (orderType.equals("dealer")) {
//				header.setZterm(kBiddingPlan.getCode());
//				break;
//			}
			if (kBiddingPlan.getAmount() == null || kBiddingPlan.getAmount().doubleValue() == 0 || orderType.equals("dealer")) {
				header.setZterm(kBiddingPlan.getCode());
				break;
			}
			
			SapOrderPlan plan = new SapOrderPlan();
			// Value to be billed/金额
			plan.setFakwr(kBiddingPlan.getAmount());
			// Settlement date/结算日期
			plan.setFkdat(new SimpleDateFormat("yyyyMMdd").format(kBiddingPlan.getPayDate()));
			// Date category/日期原因
			plan.setTetxt(kBiddingPlan.getReason());
			// Payment terms/付款条款
			plan.setZterm(kBiddingPlan.getCode());
		}

		SapOrder sapOrder = new SapOrder();
		sapOrder.setIsZhdr(header);
		sapOrder.setItZitem(items);
		sapOrder.setItZcharc(characs);
		sapOrder.setItZcond(prices);
		sapOrder.setItZplan(plans);

		return sapOrder;

	}

	/**
	 * 查询订单版本历史
	 * 
	 * @param sequenceNubmer
	 * @return
	 */
	public List<OrderVersion> findOrderVersions(String sequenceNumber) {
		List<KOrderVersionView> versions = orderVersionViewRepository.findBySequenceNumberOrderByCreateTime(sequenceNumber);
		List<OrderVersion> list = new ArrayList<>(versions.size());
		for (KOrderVersionView version : versions) {
			String versionId = version.getVersionId();

			OrderVersion v = new OrderVersion();
			list.add(v);
			v.setCreateTime(version.getCreateTime());
			v.setId(versionId);
			v.setkOrderInfoId(version.getOrderInfoId());
			v.setkOrdersId(version.getOrderId());
			v.setOptTime(version.getOptTime());
			v.setSubmitDate(version.getSubmitDate());
			v.setBpmSubmitTime(version.getBpmSubmitTime());
		}

		return list;
	}

	/**
	 * 查询订单类型
	 * 
	 * @param sequenceNumber
	 * @return
	 */
	public String getOrderType(String sequenceNumber) {
		String orderTypeCode = null;
		DOrder order = dOrderRepository.findBySequence(sequenceNumber);
		if (order != null) {
			orderTypeCode = order.getOrderTypeCode();
		}
		
		return orderTypeCode;
	}

	/**
	 * 查询订单
	 * 
	 * @param query
	 * @return
	 */
	public AbsOrder findOrder(String sequenceNumber, String version) {
		AbsOrder order = null;
		OrderQuery orderQuery = new OrderQuery();
		orderQuery.setSequenceNumber(sequenceNumber);
		orderQuery.setVersion(version);
		orderQuery.setIncludeDetail(true);
		
		List<AbsOrder> orders = findOrders(orderQuery).getRows();
		
		if (orders.size() > 0) {
			order = orders.get(0);
		}
		
		return order;
	}

	/**
	 * 查询订单
	 * 
	 * @param query
	 * @return
	 */
	public PageHelper<AbsOrder> findOrders(OrderQuery orderQuery) {
		List<AbsOrder> orders = new ArrayList<>();

		PageHelper<KOrderView> page = queryOrderView(orderQuery);
		List<KOrderView> orderViews = page.getRows();
		for (KOrderView orderView : orderViews) {
			String orderId = orderView.getOrderId();
			String orderInfoId = orderView.getOrderInfoId();
			String orderType = orderView.getOrderTypeCode();
			String formId = orderView.getFormId();

			AbsOrder order = null;
			switch(orderType) {
				case ORDER_TYPE_DEALER:
					order = new DealerOrder();
					break;
				case ORDER_TYPE_KEYACCOUNT:
					order = new KeyAccountOrder();
					break;
				case ORDER_TYPE_BULK:
		    		order = new BulkOrder();
		    		break;
				default:
					throw new RuntimeException(MessageFormat.format("Unknown order type [{0}]", orderType));
			}
			orders.add(order);

			BeanUtils.copyProperties(orderView, order);
			order.setSequenceNumber(orderView.getSequenceNumber());
//			order.setAddress(orderView.);
//			order.setApprovedDicount(orderView);
//			order.setBodyDiscount(orderView.getBodyDiscount());
//			order.setCityCode(orderView.getci);
//			order.setComments(orderView.getComments());
//			order.setConfirmTypeCode(orderView.getconf);
//			order.setContactor1Id(orderView.getContactor1Id());
//			order.setContactor1Tel(orderView.getContactor1Tel());
			order.setConfirmTypeCode(orderView.getReceiveTermCode());
			order.setConfirmTypeName(orderView.getReceiveTermName());
			
			if (orderQuery.isIncludeDetail()) {
				assembleOrderDetail(order, orderId, orderInfoId, formId);
			}
		}

		PageHelper p = new PageHelper();
		p.setRows(orders);
		p.setTotal(page.getTotal());
		return p;
	}

	private void assembleOrderDetail(AbsOrder order, String orderId,
			String orderInfoId, String formId) {
		// TODO Attached File
//			List<> attachments = attachementRepository.findByOrderInfoId(orderInfoId);
		List<String> attachmentNames = new ArrayList<String>();
//			for(KAttachment attachment : attachments) {
//				attachmentNames.add(attachment.getFileName());
//			}
		order.setAttachedFileName(attachmentNames);

		// 收货地址
		List<KDelieveryAddress> addressList = deliveryAddressRepository.findByOrderInfoId(orderId);
		List<OrderAddress> addresses = new ArrayList<OrderAddress>(addressList.size());
		for (KDelieveryAddress delieveryAddress : addressList) {
			OrderAddress address = new OrderAddress();
			addresses.add(address);
			BeanUtils.copyProperties(delieveryAddress, address);
		}
		order.setOrderAddress(addresses);

		// TODO billing plan
		List<KBiddingPlan> billingPlanList = biddingPlanRepository.findByOrderInfoId(orderInfoId);
//			order.setBillingPlans(billingPlanList);

		// TODO bpm dicision

		// TODO items
		assembleItems(order, formId);
	}

	/**
	 * 根据条件查询订单视图
	 * 
	 * @param orderQuery
	 * @return
	 */
	private PageHelper<KOrderView> queryOrderView(OrderQuery orderQuery) {
		StringBuilder querySql = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
		querySql.append("select * from k_order_view where 1=1 ");
		if (!isEmpty(orderQuery.getOrderId())) {
			querySql.append(" and order_id = :orderId");
			params.put("orderId", orderQuery.getOrderId());
		}
		if (!isEmpty(orderQuery.getSequenceNumber())) {
			querySql.append(" and sequence_number like :sequenceNumber");// .append(query.getSequenceNumber());
			params.put("sequenceNumber", "%" + orderQuery.getSequenceNumber() + "%");
		}
		if (!isEmpty(orderQuery.getVersionId())) {
			querySql.append(" and version_id = :versionId");// .append(query.getVersionId());
			params.put("versionId", orderQuery.getVersionId());
		}
		if (!isEmpty(orderQuery.getVersion())) {
			querySql.append(" and version = :version"); // .append(query.getVersion());
			params.put("version", orderQuery.getVersion());
		}
		if (!isEmpty(orderQuery.getStatus())) {
			querySql.append(" and status = :status"); // .append(query.getStatus());
			params.put("status", orderQuery.getStatus());
		}
		if (orderQuery.isLast()) {
			querySql.append(" and version_create_time = (select max(create_time) from k_order_version where k_order_version.k_orders_id = k_order_view.order_id)"); // .append(query.getStatus());
		}
		if (!isEmpty(orderQuery.getOfficeCode())) {
			querySql.append(" and office_code = :officeCode"); // .append(query.getStatus());
			params.put("officeCode", orderQuery.getOfficeCode());
		}
		if (!isEmpty(orderQuery.getOrderType())) {
			querySql.append(" and order_type_code = :orderType"); // .append(query.getStatus());
			params.put("orderType", orderQuery.getOrderType());
		}
		if (!isEmpty(orderQuery.getContractNumber())) {
			querySql.append(" and contract_number like :contractNumber"); // .append(query.getStatus());
			params.put("contractNumber", "%" + orderQuery.getContractNumber() + "%");
		}
//		if (!isEmpty(orderQuery.getContractorName())) {
//			querySql.append(" and contractor_name like :contractorNumber"); // .append(query.getStatus());
//			params.put("contractorNumber", "%" + orderQuery.getContractorName() + "%");
//		}

		Query query = entityManager.createNativeQuery(querySql.toString(), KOrderView.class);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		// 设置分页信息
		int pageNo = orderQuery.getPageNo() == null ? 0 : orderQuery.getPageNo().intValue();
		int pageSize = orderQuery.getPageSize() == null ? 10000 : orderQuery.getPageSize();
		query.setFirstResult(pageNo * pageSize);
		query.setMaxResults(pageSize);

		List<KOrderView> orderViews = query.getResultList();
		
		// 统计总记录数
		String countSql = querySql.toString();
		countSql = "select count(1) " + countSql.substring(countSql.indexOf("from"));
		Query countQuery = entityManager.createNativeQuery(countSql);
		for (Map.Entry<String,Object> entry : params.entrySet()) {
			countQuery.setParameter(entry.getKey(),entry.getValue());
		}
		BigInteger totalCount = (BigInteger)countQuery.getSingleResult();
		PageHelper page = new PageHelper<AbsOrder>();
		page.setRows(orderViews);
		page.setTotal(totalCount.intValue());

		return page;
	}

	private void assembleItems(AbsOrder order, String formId) {
		List<ItemDetails> detailsList = itemDetailRepository.findByKFormsId(formId);
		// 旧的AbsOrder格式
//		ItemsForm form = new ItemsForm();
////		order.setItemsForm(form);
//		form.setComments(orderView.getFormComments());
//		form.setEarliestDeliveryDate(orderView.getEarliestDeliveryDate());
//		form.setEarliestProductDate(orderView.getEarliestProductDate());
//		form.setId(orderView.getFormId());
//		form.setkOrderInfoId(orderView.getOrderInfoId());
//		form.setOperator(orderView.getFormOperator());
//		form.setOptTime(orderView.getFormOptTime());
//		form.setType(orderView.getFormType());
//
//		form.setDetailsList(detailsList);
//		for (ItemDetails itemDetails : detailsList) {
//			String itemId = itemDetails.getId();
//			// TODO item b2c
//			// TODO characteristics
//			List<KCharacteristics> characs = characteristicsRepository.findByItemDetailsId(itemId);
//			// TODO configure material
//			// TODO attached info
//		}
		
		// 新的AbsOrder
		List<AbsItem> items = new ArrayList<AbsItem>(detailsList.size()); 
		for (ItemDetails itemDetails : detailsList) { 
			String itemId = itemDetails.getId();
			ProductItem item = new ProductItem();
			BeanUtils.copyProperties(itemDetails, item, (String[]) null);
			
			items.add(item);
			
			// TODO item b2c
			// TODO characteristics
//			List<KCharacteristics> characs = characteristicsRepository.findByItemDetailsId(itemId);
			// TODO configure material
			// TODO attached info
		}
		order.setItems(items);
	}

	private boolean isEmpty(String v) {
		return v == null || v.length() == 0;
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

}
