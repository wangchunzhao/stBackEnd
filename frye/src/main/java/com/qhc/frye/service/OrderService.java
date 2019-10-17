package com.qhc.frye.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BAreaRepository;
import com.qhc.frye.dao.BCityRepository;
import com.qhc.frye.dao.BProvinceRepository;
import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.dao.DOrderRepository;

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
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.ItemDetails;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.OrderSupportInfo;
import com.qhc.frye.domain.PaymentTerm;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.domain.TermianlIndustryCode;
import com.qhc.frye.rest.controller.entity.AbsOrder;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.SalesOrder;
//import com.qhc.frye.rest.controller.entity.SapOrder;

@Service
public class OrderService {

	@Autowired
	private SalesTypeRepository salesTypeRepo;

	@Autowired
	private SalesorderVersionRepository versionRepo;

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
	private ConstantService constService;
	
	@Autowired
//	private BayernService<SapOrder> bayernService;
	
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
	 * @param absOrder
	 */
	public void save(AbsOrder order) {

		DOrder sDorder = dOrderRepository.saveAndFlush(order.getDorder());
		OrderSupportInfo ori = order.getSupportInforOfOrder();
		if (!order.getContractNumber().trim().isEmpty())
			ori.setOrderId(sDorder.getId());
		supportRepo.saveAndFlush(ori);
		KOrderVersion over = order.getOrderVersion();
		over.setkOrdersId(sDorder.getId());
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
			if (pt.isPaymentTerm()) {
				payments.put(pt.getCode(), pt.getName());
			} else {
				bidding.put(pt.getCode(), pt.getName());
			}
		}
		//
		oo.setOrderTypes(constService.getOrderTypes());
		return oo;
	}
	
	
	/**
	 * 根据流水号组装数据并同步SAP
	 * @param sequenceNumber
	 * @return
	 */
	public String orderCreationForSAP(String sequenceNumber) {
		
//		//1. 根据sequenceNumber组装数据
//		SapOrder sapOrder = assembleOrderForSAP(sequenceNumber);
//		
//		
//		//2. 调用bayernService同步SAP
//		bayernService.postJason(ORDER_CREATION_SAP, sapOrder);
	
		return "SUCCESS";
		
	}
	
	/**
	 * 根据流水号组装数据
	 * @param sequenceNumber
	 * @return
//	 */
//	private SapOrder assembleOrderForSAP(String sequenceNumber) {
//		
//		//TODO: 根据流水号组装数据
//	
//		return new SapOrder();
//		
//	}
	
	
	
	
}
