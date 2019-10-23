package com.qhc.frye.service;

import java.math.BigDecimal;
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
import com.qhc.frye.dao.KOrderVersionRepository;
import com.qhc.frye.dao.KOrderVersionViewRepository;
import com.qhc.frye.dao.KOrderViewRepository;
import com.qhc.frye.dao.KParentOrderVersionRepository;
import com.qhc.frye.dao.OrderSupportInforRepository;
import com.qhc.frye.dao.PaymentTermRepository;
import com.qhc.frye.dao.SalesGroupRepository;
import com.qhc.frye.dao.SalesOfficeRepository;
import com.qhc.frye.dao.SalesTypeRepository;
import com.qhc.frye.dao.SalesorderVersionRepository;
import com.qhc.frye.dao.TerminalIndustryCodeRepository;
import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.BCity;
import com.qhc.frye.domain.BProvince;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.DIncoterm;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.ItemDetails;
import com.qhc.frye.domain.ItemsForm;
import com.qhc.frye.domain.KBiddingPlan;
import com.qhc.frye.domain.KCharacteristics;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.KOrderVersionView;
import com.qhc.frye.domain.KOrderView;
import com.qhc.frye.domain.KParentOrderVersion;
import com.qhc.frye.domain.OrderSupportInfo;
import com.qhc.frye.domain.PaymentTerm;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.domain.TermianlIndustryCode;
import com.qhc.frye.rest.controller.entity.AbsOrder;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.Incoterm;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.OrderQuery;
import com.qhc.frye.rest.controller.entity.OrderVersion;
import com.qhc.frye.rest.controller.entity.PaymentPlan;
import com.qhc.frye.rest.controller.entity.SalesOrder;
import com.qhc.frye.rest.controller.entity.SapOrder;
import com.qhc.frye.rest.controller.entity.SapOrderCharacteristics;
import com.qhc.frye.rest.controller.entity.SapOrderHeader;
//import com.qhc.frye.rest.controller.entity.SapOrder;
import com.qhc.frye.rest.controller.entity.SapOrderItem;
import com.qhc.frye.rest.controller.entity.SapOrderPlan;
import com.qhc.frye.rest.controller.entity.SapOrderPrice;

@Service
public class OrderService {

	@Autowired
	private SalesTypeRepository salesTypeRepo;

	@Autowired
	private KOrderVersionRepository versionRepo;

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
	private KParentOrderVersionRepository orderParentVersionRepository;
	
	@Autowired
	private KOrderVersionViewRepository orderVersionViewRepository;
	
	@Autowired
	private KOrderViewRepository orderViewRepository;
	
	@Autowired
	private ItemDetailRepository itemDetailRepository;
	
	@Autowired
	private KCharacteristicsRepository characteristicsRepository;
	
	@Autowired
	private KBiddingPlanRepository biddingPlanRepository;
	
	@Autowired
	private ConstantService constService;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private BayernService<SapOrder> bayernService;
	
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
		for(PaymentPlan inco:paymentPlan) {
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
	public void save(AbsOrder order) {

		DOrder sDorder = dOrderRepository.saveAndFlush(order.getDorder());
		OrderSupportInfo ori = order.getSupportInforOfOrder();
		if (!order.getContractNumber().trim().isEmpty())
			ori.setOrderId(sDorder.getId());
		supportRepo.saveAndFlush(ori);
		KOrderVersion over = order.getOrderVersion();
		over.setOrderId(sDorder.getId());
		KOrderVersion kov = versionRepo.saveAndFlush(over);
	}

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

		List<ItemDetails> details = new ArrayList<ItemDetails>();
		;
		if (saleOrder.getItemsForm() != null) {
			details = saleOrder.getItemsForm().getDetailsList();
		}
		// 提交类型 3.margin 4.wtw margin
		int submitType = saleOrder.getSubmitType();

		// 税率
		Double taxRate = saleOrder.getTaxRate();

		// 物料类型表
		// sapSalesGroups

		// 毛利表
		BigDecimal sumAmount = BigDecimal.ZERO;// 金额
		BigDecimal sumExcludingTaxAmount = BigDecimal.ZERO;// 不含税金额
		BigDecimal sumCost = BigDecimal.ZERO;// 成本
		BigDecimal sumGrossProfit = BigDecimal.ZERO;// 毛利
		Double sumGrossProfitMargin = 0D;// 毛利率
		if (details != null && details.size() > 0) {
			for (SapSalesGroup entity : salesGroups) {
				BigDecimal amount = BigDecimal.ZERO;// 金额
				BigDecimal excludingTaxAmount = BigDecimal.ZERO;// 不含税金额
				BigDecimal cost = BigDecimal.ZERO;// 成本
				BigDecimal grossProfit = BigDecimal.ZERO;// 毛利
				Double grossProfitMargin = 0D;// 毛利率

				for (ItemDetails item : details) {
					if (item.getMaterialCode().equals(entity.getCode())) {
						// 总金额
						amount = amount.add(item.getAmount());
						// 总金额减去税金 = 不含税金额
						excludingTaxAmount = excludingTaxAmount
								.subtract(sumAmount.multiply(BigDecimal.valueOf(taxRate)));
						// 成本
						if (submitType == 3) {
							cost = cost.add(item.getStandardPrice());
						}
						if (submitType == 4) {
							cost = cost.add(item.getTransfterPrice());
						}
						// 毛利
						grossProfit = excludingTaxAmount.subtract(cost);
						// 毛利率
						grossProfitMargin = this.CalculateGrossProfit(excludingTaxAmount, cost);

					}
				}

				entity.setAmount(amount);
				entity.setExcludingTaxAmount(excludingTaxAmount);
				entity.setCost(cost);
				entity.setGrossProfit(grossProfit);
				entity.setGrossProfitMargin(grossProfitMargin);

				sumAmount = sumAmount.add(amount);
				sumExcludingTaxAmount = sumExcludingTaxAmount.add(excludingTaxAmount);
				sumCost = sumCost.add(cost);
				sumGrossProfit = sumGrossProfit.add(grossProfit);
				sumAmount = sumAmount.add(amount);
			}

		}

		SapSalesGroup sumssg = new SapSalesGroup();
		sumssg.setAmount(sumAmount);
		sumssg.setExcludingTaxAmount(sumExcludingTaxAmount);
		sumssg.setCost(sumCost);
		sumssg.setGrossProfit(sumGrossProfit);
		if (salesGroups.size() != 0) {
			sumssg.setGrossProfitMargin(sumGrossProfitMargin / salesGroups.size());
		} else {
			sumssg.setGrossProfitMargin(0D);
		}
		sumssg.setCode("sum");
		sumssg.setName("合计");

		salesGroups.add(sumssg);

		return salesGroups;
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
		Map<String,Double> taxRate = oo.getTaxRate();
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
		
		// 销售区域Sales Goups
		oo.setGroups(constService.findSalesGroups());
		
		// 销售区域Sales Offices
		oo.setOffices(constService.findSalesOffices());
		
		// 运输方式ShippingTypes
		oo.setShippingTypes(constService.findShippingTypes());
		
		// 收货方式ReceiveTerms
		oo.setReceiveTerms(constService.findReceiveTerms());
		
		return oo;
	}
	
	
	/**
	 * 根据流水号组装数据并同步SAP
	 * @param sequenceNumber
	 * @return
	 */
	public String orderCreationForSAP(String sequenceNumber) {
		
		//1. 根据sequenceNumber组装数据
		SapOrder sapOrder = assembleOrderForSAP(sequenceNumber);
		
		
		//2. 调用bayernService同步SAP
		bayernService.postJason(ORDER_CREATION_SAP, sapOrder);
	
		return "SUCCESS";
		
	}
	
	/**
	 * 根据流水号组装数据
	 * @param sequenceNumber
	 * @return
//	 */
	private SapOrder assembleOrderForSAP(String sequenceNumber) {
		List<KOrderVersionView> verions = orderVersionViewRepository.findBySequenceNumberOrderByCreateTime(sequenceNumber);
		
		String orderId = null;
		String orderInfoId = null;
		String orderVersionId = null;
		
		for (KOrderVersionView kOrderVersionView : verions) {
			orderId = kOrderVersionView.getOrderId();
			String status = kOrderVersionView.getStatus();
			// 最后一个审批通过的版本
			if (status.equals("3")) {
				orderInfoId = kOrderVersionView.getOrderInfoId();
				orderVersionId = kOrderVersionView.getVersionId();
			}
		}
		
		if (orderInfoId == null) {
			throw new NullPointerException(MessageFormat.format("Not found approved order with sequence number [{}]", sequenceNumber));
		}
		
//		OrderQuery query = new OrderQuery();
//		List<SalesOrder> orders = findOrder(query);
//		SalesOrder order = orders.get(0);
		KOrderView orderView = orderViewRepository.findByOrderIdAndVersionId(orderId, orderVersionId);
		
		SapOrderHeader header = new SapOrderHeader();
		// TODO assemble sap order header
		List<SapOrderItem> items = new ArrayList<SapOrderItem>();
		List<SapOrderCharacteristics> characs = new ArrayList<SapOrderCharacteristics>();
		List<SapOrderPrice> prices = new ArrayList<SapOrderPrice>();
		List<SapOrderPlan> plans = new ArrayList<SapOrderPlan>();
		
//		List<ItemDetails> itemDetails = orderView.getItemsForm().getDetailsList();
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
			// Item category/行项目类别
			item.setPstyv(itemDetail.getItemCategory());
			
			// TODO delivery address
			// Item usage/项目用途
//			item.setVkaus(String);
			// Ship-to address/送达方地址
//			item.setStreet(String);
			// Province/省
//			item.setRegion(String);
			// City/市
//			item.setCity1(String);
			// District/区
//			item.setCity2(String);
			
			// B2C note/B2C备注
			item.setVbbp0006(itemDetail.getB2cComments());
			// Survey info. for item /调研表基本信息
//			item.setVbbpz121(String);
			// Special note/特殊备注
//			item.setVbbpz117(String);
			// Color option/颜色可选项
//			item.setVbbpz120(String);
			// Customer special request/客户物料说明
//			item.setVbbp0007(String);
			
			items.add(item);
			
			// Price/condition record input
			// ZH05：实卖价合计
			SapOrderPrice price1 = new SapOrderPrice();
			price1.setPosnr(rowNumber);
			price1.setKschl("ZH05");
//			price1.setKbetr(itemDetail.getSaleAmount());
			prices.add(price1);
			// ZH08：转移价合计/成本合计
			SapOrderPrice price2 = new SapOrderPrice();
			price2.setPosnr(itemDetail.getRowNumber());
			price1.setKschl("ZH08");
			price1.setKbetr(itemDetail.getTransfterPrice());
			prices.add(price2);
			
			// Characteristics value input
			List<KCharacteristics> characList = characteristicsRepository.findByKItemDetailsId(itemDetail.getId());
			for (KCharacteristics charac : characList) {
				SapOrderCharacteristics c = new SapOrderCharacteristics();
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
		// 当订单为经销商订单，billing plan只有一条数据，金额为空或0，不向sap发送billing plan数据，sap order header的付款条款为billing plan 的 code
		List<KBiddingPlan> planList = biddingPlanRepository.findByKOrderInfoId(orderInfoId);
		for (KBiddingPlan kBiddingPlan : planList) {
			SapOrderPlan plan = new SapOrderPlan();
			if (kBiddingPlan.getAmount() == null || kBiddingPlan.getAmount().doubleValue() == 0) {
				header.setZterm(kBiddingPlan.getCode());
			}
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
		sapOrder.setItZitem(items);
		sapOrder.setItZcharc(characs);
		sapOrder.setItZcond(prices);
		sapOrder.setItZplan(plans);
		
		return sapOrder;
		
	}
	
	/**
	   *  查询订单版本历史
	 * @param orderId
	 * @return
	 */
	public List<OrderVersion> findOrderVersionsByOrderId(String orderId) {
		List<KOrderVersion> versions = versionRepo.findByOrderIdOrderByCreateTime(orderId);
		List<OrderVersion> list = new ArrayList<>(versions.size());
		for (KOrderVersion version : versions) {
			String versionId = version.getId();
			KParentOrderVersion parentVersion = orderParentVersionRepository.findByOrderVersionId(versionId);
			
			OrderVersion v = new OrderVersion();
			list.add(v);
			v.setCreateTime(version.getCreateTime());
			v.setId(version.getId());
			v.setkOrderInfoId(parentVersion.getOrderInfoId());
			v.setkOrdersId(version.getOrderId());
			v.setOptTime(parentVersion.getOptTime());
			v.setParentVersionId(parentVersion.getParentOrderVersionId());
		}
		
		return list;
	}
	
	/**
	 * 查询订单
	 * 
	 * @param query
	 * @return
	 */
	public List<SalesOrder> findOrder(OrderQuery orderQuery) {
		List<SalesOrder> orders = new ArrayList<>();
		
//		String countSql = "";
//		Query countQuery = entityManager.createNativeQuery(countSql);
//	     for (Map.Entry<String,Object> entry : params.entrySet())
//	     {
//	         countQuery.setParameter(entry.getKey(),entry.getValue());
//	     }
//	     BigInteger totalCount = (BigInteger)countQuery.getSingleResult();
	 
		StringBuilder querySql = new StringBuilder();
		Map<String, Object> params = new HashMap<>();
	     querySql.append("select * from k_order_view where 1=1 ");
	     if(!isEmpty(orderQuery.getOrderId())) {
	    	 querySql.append(" order_id = :orderId");
	    	 params.put("orderId", orderQuery.getOrderId());
	     }
	     if(!isEmpty(orderQuery.getSequenceNumber())) {
	    	 querySql.append(" sequence_number = :sequenceNumber");//.append(query.getSequenceNumber());
	    	 params.put("sequenceNumber", orderQuery.getSequenceNumber());
	     }
	     if(!isEmpty(orderQuery.getVersionId())) {
	    	 querySql.append(" version_id = :versionId");// .append(query.getVersionId());
	    	 params.put("versionId", orderQuery.getVersionId());
	     }
	     if(!isEmpty(orderQuery.getVersion())) {
	    	 querySql.append(" version = :version"); // .append(query.getVersion());
	    	 params.put("version", orderQuery.getVersion());
	     }
	     
	     Query query = entityManager.createNativeQuery(querySql.toString(), KOrderView.class);
	     for (Map.Entry<String,Object> entry:params.entrySet())
	     {
	         query.setParameter(entry.getKey(),entry.getValue());
	     }
//	     query.setFirstResult((int) pageable.getOffset());
//	     query.setMaxResults(pageable.getPageSize());
	     
//	     Page<RankEntity> page = new PageImpl<>(rankEntities,pageable,totalCount.longValue());
	 
	     List<KOrderView> orderViews = query.getResultList();
	     for (KOrderView orderView : orderViews) {
			SalesOrder order = new SalesOrder();
			orders.add(order);
			order.setSequenceNumber(orderView.getSequenceNumber());
//			order.setAddress(orderView.);
//			order.setApprovedDicount(orderView);
			
			order.setBodyDiscount(orderView.getBodyDiscount());
			
//			order.setCityCode(orderView.getci);
			
			// TODO Attached File
//			order.setAttachedFileName(attachedFileName);
			
			// TODO 收货地址
			
			// TODO billing plan
			
			// TODO bpm dicision
			
			// TODO forms
			assembleItemsForm(order, orderView);
		}
	     
		return orders;
	}

	private void assembleItemsForm(SalesOrder order, KOrderView orderView) {
		ItemsForm form = new ItemsForm();
		order.setItemsForm(form);
		form.setComments(orderView.getFormComments());
		form.setEarliestDeliveryDate(orderView.getEarliestDeliveryDate());
		form.setEarliestProductDate(orderView.getEarliestProductDate());
		form.setId(orderView.getFormId());
		form.setkOrderInfoId(orderView.getOrderInfoId());
		form.setOperator(orderView.getFormOperator());
		form.setOptTime(orderView.getFormOptTime());
		form.setType(orderView.getFormType());
		
		List<ItemDetails> detailsList = itemDetailRepository.findByKFormsId(form.getId());
		form.setDetailsList(detailsList);
		for (ItemDetails itemDetails : detailsList) {
			// TODO item b2c
			// TODO characteristics
			// TODO configure material
			// TODO attached info
		}
	}
	
	private boolean isEmpty(String v) {
		return v == null || v.length() == 0;
	}
	
}
