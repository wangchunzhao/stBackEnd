package com.qhc.frye.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qhc.frye.dao.AttachedInfoRepository;
import com.qhc.frye.dao.AttachementRepository;
import com.qhc.frye.dao.B2CCostRepository;
import com.qhc.frye.dao.BAreaRepository;
import com.qhc.frye.dao.BCityRepository;
import com.qhc.frye.dao.BProvinceRepository;
import com.qhc.frye.dao.CharacteristicDefaultRepository;
import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.dao.DOrderRepository;
import com.qhc.frye.dao.DefaultCharacterViewRepository;
import com.qhc.frye.dao.EnginingCostRepository;
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
import com.qhc.frye.dao.ParameterSettingsRepository;
import com.qhc.frye.dao.PaymentTermRepository;
import com.qhc.frye.dao.SalesGroupRepository;
import com.qhc.frye.dao.SalesOfficeRepository;
import com.qhc.frye.dao.SalesTypeRepository;
import com.qhc.frye.dao.SapMaterialGroupsRepository;
import com.qhc.frye.dao.TerminalIndustryCodeRepository;
import com.qhc.frye.domain.B2CCost;
import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.BCity;
import com.qhc.frye.domain.BProvince;
import com.qhc.frye.domain.DCharacteristicDefault;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.DMaterialGroups;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.DefaultCharacterView;
import com.qhc.frye.domain.EnginingCost;
import com.qhc.frye.domain.ItemDetails;
import com.qhc.frye.domain.ItemsForm;
import com.qhc.frye.domain.KAttachedInfo;
import com.qhc.frye.domain.KAttachment;
import com.qhc.frye.domain.KBiddingPlan;
import com.qhc.frye.domain.KCharacteristics;
import com.qhc.frye.domain.KDelieveryAddress;
import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.KOrderVersionView;
import com.qhc.frye.domain.KOrderView;
import com.qhc.frye.domain.OrderSupportInfo;
import com.qhc.frye.domain.Parameter;
import com.qhc.frye.domain.PaymentTerm;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.TermianlIndustryCode;
import com.qhc.frye.rest.controller.entity.form.AbsOrder;
import com.qhc.frye.rest.controller.entity.form.BaseChracteristic;
import com.qhc.frye.rest.controller.entity.form.BaseItem;
import com.qhc.frye.rest.controller.entity.form.BaseOrder;
import com.qhc.frye.rest.controller.entity.form.BiddingPayment;
import com.qhc.frye.rest.controller.entity.B2CComments;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.OrderQuery;
import com.qhc.frye.rest.controller.entity.OrderVersion;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.rest.controller.entity.PaymentPlan;
import com.qhc.frye.rest.controller.entity.SalesOrder;
import com.qhc.frye.rest.controller.entity.bpm.BpmOrder;
import com.qhc.frye.rest.controller.entity.bpm.Order;
import com.qhc.frye.rest.controller.entity.bpm.OrderItem;
import com.qhc.frye.rest.controller.entity.bpm.OrderMargin;
import com.qhc.frye.rest.controller.entity.form.AbsCharacteristic;
import com.qhc.frye.rest.controller.entity.form.AbsItem;
import com.qhc.frye.rest.controller.entity.form.BulkOrder;
import com.qhc.frye.rest.controller.entity.form.DealerOrder;
import com.qhc.frye.rest.controller.entity.form.KeyAccountOrder;
import com.qhc.frye.rest.controller.entity.form.OrderAddress;
import com.qhc.frye.rest.controller.entity.form.OrderHelper;
import com.qhc.frye.rest.controller.entity.sap.SapOrder;
import com.qhc.frye.rest.controller.entity.sap.SapOrderCharacteristics;
import com.qhc.frye.rest.controller.entity.sap.SapOrderHeader;
import com.qhc.frye.rest.controller.entity.sap.SapOrderItem;
import com.qhc.frye.rest.controller.entity.sap.SapOrderPlan;
import com.qhc.frye.rest.controller.entity.sap.SapOrderPrice;

@Service
public class OrderService {
	private final static String ORDER_TYPE_DEALER = "ZH0D"; // '经销商订单'
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
	private AttachementRepository attachementRepository;

	@Autowired
	private ConstantService constService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private BayernService<SapOrder> bayernService;

	@Autowired
	private KOrderFormRepository formRepo;

	@Autowired
	private AttachedInfoRepository attachedInfoRepository;

	@Autowired
	private ParameterSettingsRepository settingsRepository;

	@Autowired
	private SapMaterialGroupsRepository materialGroupsRepository;

	@Autowired
	private DefaultCharacterViewRepository defaultValueRepo;

	@Autowired
	private B2CCostRepository b2cRepo;

	@Autowired
	private EnginingCostRepository enginRepo;

	@Autowired
	private KCharacteristicsRepository kCharaRepo;

	private final static String ORDER_CREATION_SAP = "order/create/sapOrder";

	private final static String COST_INSTALLATION = "BG1GDA00000-X";
	private final static String COST_MATERIALS = "BG1GDB00000-X";
	private final static String COST_ELETRICAL = "BG1R8J00000-X";
	private final static String COST_COOLROOM = "BG1R8R00000-X";
	private final static String COST_MAINTANANCE = "BG1R8K00000-X";

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
//	/**
//	 * 
//	 * @param materialCode
//	 * @param itemId
//	 */
//	private void saveCharacter(String materialCode,String itemId) {
//		List<DefaultCharacterView> dvs = defaultValueRepo.findByMaterial(materialCode);
//		Set<KCharacteristics> kcs = new HashSet<KCharacteristics>();
//		for(DefaultCharacterView dcv:dvs) {
//			KCharacteristics kc = new KCharacteristics();
//			//kc.setIsConfigurable(0);
//			kc.setKeyCode(dcv.getKeyCode());
//			kc.setValueCode(dcv.getValueCode());
//			kc.setItemDetailsId(itemId);
//		}
//		characteristicsRepository.saveAll(kcs);
//	}

	/**
	 * 
	 * @param absOrder
	 */
	public void save(final AbsOrder order) throws Exception {
		this.submitOrder(order);
	}

	/**
	 * 
	 * @param order
	 * @param ohelper
	 * @return
	 */
	private String saveOrder(final AbsOrder order, final OrderHelper ohelper) {

		String seq = order.getSequenceNumber();

		DOrder orderDao = ohelper.toDOrder();
		DOrder extOrder = dOrderRepository.findBySequence(order.getSequenceNumber());
		if (extOrder != null) {
			orderDao.setId(extOrder.getId());
		}
		// save order header
		orderDao = dOrderRepository.saveAndFlush(orderDao);

		// if it is from supportmanager and able to be sent to bpm
		if (order.getContractNumber() != null && !order.getContractNumber().trim().isEmpty()) {
			OrderSupportInfo supportInfo = ohelper.toSupportInforOfOrder();
			OrderSupportInfo existSupport = supportRepo.findByOrderId(orderDao.getId());
			if (existSupport != null && !order.getContractNumber().equals(existSupport.getContractNumber())) {
				supportInfo.setId(existSupport.getId());
				supportRepo.save(supportInfo);
			}
		}

		return orderDao.getId();
	}

	/**
	 * 
	 * @param order
	 * @param ohelper
	 * @param orderId
	 * @return
	 */
	private String saveOrderInfo(final AbsOrder order, final OrderHelper ohelper, String orderId) {

		KOrderVersion lastVersion = orderVersionRepo.findLastOneByOrderId(orderId);
		String version = null;
		if (lastVersion != null) {
			version = lastVersion.getVersion();
		}
		KOrderInfo kOrderInfoDao = ohelper.toOrderInfo();
		if (version != null && version.equals(order.getCurrentVersion())) {
			KOrderInfo existOrderInfo = orderInfoRepo.findOrderInfoBySeqAndVersion(order.getSequenceNumber(), version);
			kOrderInfoDao.setId(existOrderInfo.getId());
		}
		kOrderInfoDao = orderInfoRepo.save(kOrderInfoDao);
		return kOrderInfoDao.getId();
	}

	private List<KCharacteristics> convertCharacters(List<DefaultCharacterView> dCharacters, String itemid) {
		List<KCharacteristics> temp = new ArrayList();
		for (DefaultCharacterView dcv : dCharacters) {
			KCharacteristics kc = new KCharacteristics();
			kc.setKeyCode(dcv.getKeyCode());
			kc.setValueCode(dcv.getValueCode());
			kc.setItemDetailsId(itemid);
			kc.setIsConfigurable(0);
			temp.add(kc);
		}
		return temp;
	}
	/**
	 * 
	 * @param detail
	 * @param item
	 */
	private void saveCharater(AbsItem item,String detailId) throws Exception {

		if (item.getConfigs() == null) {
	
			// 数据库中的设置
			List<DefaultCharacterView> dcs = defaultValueRepo.findByMaterial(item.getMaterialCode());
			List<KCharacteristics> kcs = convertCharacters(dcs, detailId);
			if(kcs!=null && !kcs.isEmpty())
				kCharaRepo.deleteInBatchByItemDetail(detailId);
				kCharaRepo.saveAll(kcs);
			
		} else {
			//定制配置
			List<BaseChracteristic> bas = item.getConfigs();
			List<KCharacteristics> kcs = new ArrayList();
			for (BaseChracteristic bc : bas) {
				KCharacteristics kc = new KCharacteristics();
				kc.setKeyCode(bc.getConfigCode());
				kc.setValueCode(bc.getConfigValueCode());
				kc.setItemDetailsId(detailId);
				kc.setIsConfigurable(1);
				kcs.add(kc);
			}
			//
			kCharaRepo.deleteInBatchByItemDetail(detailId);
			kCharaRepo.saveAll(kcs);
		}
	}

	/**
	 * 
	 * @param order
	 * @param ohelper
	 * @param orderInforId
	 * @return
	 */
	private boolean saveForm(final AbsOrder order, final OrderHelper ohelper, String orderInforId) throws Exception{

		ItemsForm existForm = formRepo.findOneByHeaderId(orderInforId);
		ItemsForm formDao = ohelper.toForm(orderInforId);

		if (existForm != null) {
			formDao.setId(existForm.getId());
		}
		formDao = formRepo.save(formDao);
		//
		List<ItemDetails> details = new ArrayList();
		boolean b2cFlag = false;
		List<ItemDetails> existItems = itemDetailRepository.findByKFormsId(formDao.getId());
		List<BaseItem> newItems = order.getItems();
		for (AbsItem item : newItems) {

			ItemDetails temp = OrderHelper.itemConversion(item, formDao.getId());
			if (temp.getB2cComments() != null && temp.getB2cComments().trim().length() > 0) {
				b2cFlag = true;
			}
			for (ItemDetails old : existItems) {
				if (old.getRowNumber() == temp.getRowNumber()) {
					temp.setId(old.getId());
					break;
				}
			}

			temp = itemDetailRepository.save(temp);
			// 特征
			if(item.isConfigurable())
				this.saveCharater(item,temp.getId());
		}
		//

		return b2cFlag;
	}

	private void saveVersion(final AbsOrder order, final OrderHelper ohelper, boolean b2cFlag, String orderId,
			String orderInforId) throws Exception {
		KOrderVersion lversion = ohelper.toOrderVersion(b2cFlag, false);
		lversion.setOrderId(orderId);
		lversion.setOrderInfoId(orderInforId);
		if (lversion.getCreateTime() == null)
			lversion.setCreateTime(new Date());
		//
		KOrderVersion extVersion = orderVersionRepo.findByOrder(orderId, orderInforId);
		if (extVersion != null)
			lversion.setId(extVersion.getId());
		// 订单版本保存
		orderVersionRepo.save(lversion);
	}

	private void submitOrder(final AbsOrder order) throws Exception {

		OrderHelper ohelper = new OrderHelper(order);

		String orderId = this.saveOrder(order, ohelper);
		//
		String orderInforId = saveOrderInfo(order, ohelper, orderId);

		boolean b2cFlag = saveForm(order, ohelper, orderInforId);

		// 订单版本
		saveVersion(order, ohelper, b2cFlag, orderId, orderInforId);

		// 订单地址
		List<KDelieveryAddress> adds = ohelper.toAddress(orderInforId);
		deliveryAddressRepository.saveAll(adds);
		// bidding plan
		List<KBiddingPlan> bidding = ohelper.toBiddingPlan(orderInforId);
		biddingPlanRepository.saveAll(bidding);
		// attachment

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

	public List<DMaterialGroups> calcWtwGrossProfit(String sequenceNumber, String version) {
		AbsOrder order = this.findOrder(sequenceNumber, version);
		order.setSubmitType(4);
		return calcGrossProfit(order);
	}

	public List<DMaterialGroups> calcGrossProfit(String sequenceNumber, String version) {
		AbsOrder order = this.findOrder(sequenceNumber, version);
		return calcGrossProfit(order);
	}

	// sap_material_group分组
	public List<DMaterialGroups> calcGrossProfit(AbsOrder order) {
		// 查询所有物料类型sap_material_group isenable != 0
		List<DMaterialGroups> groups = materialGroupsRepository.findByIsenableNotOrderByCode(0);
		List<BaseItem> items = new ArrayList<BaseItem>();

		items = order.getItems();

		// 提交类型 3.margin 4.wtw margin
		int submitType = order.getSubmitType();

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
			for (DMaterialGroups entity : groups) {
				BigDecimal amount = BigDecimal.ZERO;// 金额
				BigDecimal excludingTaxAmount = BigDecimal.ZERO;// 不含税金额
				BigDecimal wtwcost = BigDecimal.ZERO;// wtw成本
				BigDecimal cost = BigDecimal.ZERO;// 成本
				BigDecimal wtwgrossProfit = BigDecimal.ZERO;// wtw毛利
				BigDecimal grossProfit = BigDecimal.ZERO;// 毛利
				Double wtwgrossProfitMargin = 0D;// wtw毛利率
				Double grossProfitMargin = 0D;// 毛利率

				for (BaseItem item : items) {
					if (item.getGroupCode().equals(entity.getCode())) {
						// 总金额
						BigDecimal saleAmount = BigDecimal.valueOf(
								(item.getActuralPrice() + item.getActuralPricaOfOptional()) * item.getQuantity());
						amount = amount.add(saleAmount);
						// 总金额减去税金 = 不含税金额
						BigDecimal taxAmount = saleAmount.multiply(BigDecimal.valueOf(taxRate));
						excludingTaxAmount = excludingTaxAmount.add(saleAmount.subtract(taxAmount));
						// wtw成本
						if (submitType == 4) {
							wtwcost = wtwcost.add(BigDecimal.valueOf(item.getStandardPrice()));
							// 毛利
							wtwgrossProfit = excludingTaxAmount.subtract(wtwcost);
						}
						cost = cost.add(BigDecimal.valueOf(item.getTranscationPrice()));
						// 毛利
						grossProfit = excludingTaxAmount.subtract(cost);

					}
				}
				// WTW毛利率
				wtwgrossProfitMargin = this.CalculateGrossProfit(excludingTaxAmount, wtwcost);
				// 毛利率
				grossProfitMargin = this.CalculateGrossProfit(excludingTaxAmount, cost);

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

		DMaterialGroups sumssg = new DMaterialGroups();
		sumssg.setAmount(sumAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setExcludingTaxAmount(sumExcludingTaxAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setWtwCost(sumWtwCost.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setCost(sumCost.setScale(2, BigDecimal.ROUND_HALF_UP));
		sumssg.setWtwGrossProfit(sumWtwGrossProfit.setScale(4, BigDecimal.ROUND_HALF_UP));
		sumssg.setGrossProfit(sumGrossProfit.setScale(4, BigDecimal.ROUND_HALF_UP));
		// WTW毛利率
		sumWtwGrossProfitMargin = this.CalculateGrossProfit(sumExcludingTaxAmount, sumWtwCost);
		// 毛利率
		sumGrossProfitMargin = this.CalculateGrossProfit(sumExcludingTaxAmount, sumCost);
		sumssg.setWtwGrossProfitMargin(sumWtwGrossProfitMargin);
		sumssg.setGrossProfitMargin(sumGrossProfitMargin);
		sumssg.setCode("sum");
		sumssg.setName("合计");

		groups.add(sumssg);

		return groups;
	}

	public Double CalculateGrossProfit(BigDecimal afterTaxAmount, BigDecimal cost) {
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
		Parameter p = settingsRepository.findEnabledInfo("0d5d7ea6b2605e38b4f3dbd394168b3b");
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
	public String orderCreationForSAP(String sequenceNumber, String version) {

		try {
			// 1. 根据sequenceNumber组装数据
			SapOrder sapOrder = assembleSapOrder(sequenceNumber, version);

			// 2. 调用bayernService同步SAP
			bayernService.postJason(ORDER_CREATION_SAP, sapOrder);
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
		String orderId = null;
		String orderInfoId = null;
		String orderVersionId = null;

//		List<KOrderVersionView> verions = orderVersionViewRepository
//				.findBySequenceNumberOrderByCreateTime(sequenceNumber);
//		for (KOrderVersionView kOrderVersionView : verions) {
//			orderId = kOrderVersionView.getOrderId();
//			Integer status = kOrderVersionView.getStatus();
//			// 最后一个审批通过的版本
//			if (status.equals(3)) {
//				orderInfoId = kOrderVersionView.getOrderInfoId();
//				orderVersionId = kOrderVersionView.getVersionId();
//			}
//		}

//		if (orderInfoId == null) {
//			throw new NullPointerException(
//					MessageFormat.format("Not found approved order with sequence number [{0}]", sequenceNumber));
//		}

//		OrderQuery query = new OrderQuery();
//		List<SalesOrder> orders = findOrder(query);
//		SalesOrder order = orders.get(0);
		KOrderView orderView = orderViewRepository.findBySequenceNumberAndVersion(sequenceNumber, orderVersionId);
		String orderType = orderView.getOrderType();

		// assemble sap order header
		SapOrderHeader header = new SapOrderHeader();
		List<SapOrderItem> items = new ArrayList<SapOrderItem>();
		List<SapOrderCharacteristics> characs = new ArrayList<SapOrderCharacteristics>();
		List<SapOrderPrice> prices = new ArrayList<SapOrderPrice>();
		List<SapOrderPlan> plans = new ArrayList<SapOrderPlan>();

		// Header input
		header.setAuart(toString(orderView.getOrderType())); // Sales order type/订单类型
		header.setVkorg("0841"); // Sales org./销售组织 -- Fixed value/固定为 0841
		header.setVtweg(toString(orderView.getContractorClassCode())); // DC/分销渠道 -- 客户
		header.setName2(orderView.getCustomerName()); // Store name/店名
		header.setSpart(orderView.getSalesType()); // Division/产品组
		header.setVkbur(toString(orderView.getOfficeCode())); // Sales office/销售办公室 -- 大区
		header.setVkgrp(toString(orderView.getGroupCode())); // Sales group/销售组 -- 中心
		header.setVbeln(toString(orderView.getContractNumber())); // SO number/销售订单编号 -- 合同号
		header.setKvgr1(toString(orderView.getIsConvenientStore())); // Customer grp.1/客户组1 -- 是否便利店
		header.setKvgr2(toString(orderView.getIsNew())); // Customer grp.2/客户组2 -- 是否新客户
		header.setKvgr3(toString(orderView.getIsReformed())); // Customer grp.3/客户组3 -- 是否改造店
		header.setBstzd(toString(orderView.getWarranty())); // Additional/附加的 -- 保修年限
		header.setBstkdE(toString(orderView.getContractNumber())); // Ship-to PO/送达方-采购订单编号 -- 项目报备编号 - 合同号
		header.setVsart(toString(orderView.getTransferTypeCode())); // Shipping type/装运类型 -- 运输类型
//		header.setZterm();	// Payment terms/付款条款 -- 结算方式  大客户为空，dealer取billing_plan的第一条code（唯一一条） 
		header.setKunnr(toString(orderView.getContractorCode())); // Sold-to party/售达方 -- 签约单位
		header.setWaerk(toString(orderView.getCurrencyCode())); // Currency/币别 -- 币别
		header.setInco1(toString(orderView.getIncotermCode())); // Incoterms/国际贸易条款 -- 国际贸易条件 code
		header.setInco1(toString(orderView.getIncotermName())); // Incoterms2/国际贸易条款2 -- 国际贸易条件2 name
//		// 折扣
		header.setVbbkz120(String.valueOf(orderView.getContractRmbAmount())); // Contract amount/合同金额 -- 合同金额
		header.setVbbkz121(orderView.getContractorName()); // Sale rep./签约人 -- 客户经理
		header.setVbbkz109(orderView.getOpteratorDomainId()); // Order clerk/合同管理员 -- 合同管理员
		String contactorInfo = toString(orderView.getContactor1Id()) + "/" + toString(orderView.getContactor1Tel())
				+ toString(orderView.getContactor2Id()) + "/" + toString(orderView.getContactor2Tel())
				+ toString(orderView.getContactor3Id()) + "/" + toString(orderView.getContactor3Tel());
		header.setVbbkz108(contactorInfo); // Contact info./授权人信息 -- 授权人信息6个字段 3个联系人id+tel / 分隔
		String vbbkz122 = toString(orderView.getIsTerm1()) + "/" + toString(orderView.getIsTerm2())
				+ toString(orderView.getIsTerm3());
		header.setVbbkz122(vbbkz122); // Survey info. for header /调研表相关内容 -- 调研表相关内容3个字段
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
			String address = "{省code:省名称，市code:市名称,区code:区名称,address:街道}";
			// TODO
//			address = itemDetail.getAddress(); 
			if (!this.isEmpty(address)) {
				address = address.trim().substring(1);
				address = address.substring(0, address.length() - 1);
				String[] data = address.split(",");
				String[] provinces = data[0].split(":");
				String[] cities = data[1].split(":");
				String[] districts = data[2].split(":");
				String[] streets = data[3].split(":");
				// 街道名称
				item.setStreet(streets[1]);
//				// Province/省 -- 省code
				item.setRegion(provinces[0]);
//				// City/市 -- 市名称
				item.setCity1(cities[0]);
//				// District/区 -- 区名称
				item.setCity2(districts[0]);
			}

			// B2C note/B2C备注
			item.setVbbp0006(itemDetail.getB2cComments());
			// Survey info. for item /调研表基本信息
			StringBuilder sb = new StringBuilder(128);
			if (!isEmpty(itemDetail.getRequestBrand())) {
				sb.append(",").append(itemDetail.getRequestBrand());
			}
			if (!isEmpty(itemDetail.getRequestPackage())) {
				sb.append(",").append(itemDetail.getRequestPackage());
			}
			if (!isEmpty(itemDetail.getRequestNameplate())) {
				sb.append(",").append(itemDetail.getRequestNameplate());
			}
			if (!isEmpty(itemDetail.getRequestCircuit())) {
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
			if (orderType.equals(ORDER_TYPE_DEALER)) {
				header.setZterm(kBiddingPlan.getCode());
				break;
			}
//			if (kBiddingPlan.getAmount() == null || kBiddingPlan.getAmount().doubleValue() == 0 || orderType.equals(ORDER_TYPE_DEALER)) {
//				header.setZterm(kBiddingPlan.getCode());
//				break;
//			}

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
		List<KOrderVersionView> versions = orderVersionViewRepository
				.findBySequenceNumberOrderByCreateTime(sequenceNumber);
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
	 * 查询订单详情
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
		
		PageHelper<KOrderView> page = queryOrderView(orderQuery);
		List<KOrderView> orderViews = page.getRows();
		
		if (orderViews.size() > 0) {
			KOrderView orderView = orderViews.get(0);
			String orderType = orderView.getOrderType();
			switch (orderType) {
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
			assembleOrder(orderView, order, true);
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
		boolean includeDetail = orderQuery.isIncludeDetail();
		List<AbsOrder> orders = new ArrayList<>();

		PageHelper<KOrderView> page = queryOrderView(orderQuery);
		List<KOrderView> orderViews = page.getRows();
		for (KOrderView orderView : orderViews) {
			AbsOrder order = new BaseOrder();
//				switch (orderType) {
//				case ORDER_TYPE_DEALER:
//					order = new DealerOrder();
//					break;
//				case ORDER_TYPE_KEYACCOUNT:
//					order = new KeyAccountOrder();
//					break;
//				case ORDER_TYPE_BULK:
//					order = new BulkOrder();
//					break;
//				default:
//					throw new RuntimeException(MessageFormat.format("Unknown order type [{0}]", orderType));
//				}
			assembleOrder(orderView, order, includeDetail);
			orders.add(order);
		}

		PageHelper p = new PageHelper();
		p.setRows(orders);
		p.setTotal(page.getTotal());
		return p;
	}

	/**
	 * 组装订单对象
	 * 
	 * @param orderView
	 * @param order
	 * @param includeDetail
	 */
	private void assembleOrder(KOrderView orderView, AbsOrder order, boolean includeDetail) {
		String orderId = orderView.getOrderId();
		String orderInfoId = orderView.getOrderInfoId();
		String orderType = orderView.getOrderType();
		String formId = orderView.getFormId();

		BeanUtils.copyProperties(orderView, order);
		order.setSequenceNumber(orderView.getSequenceNumber());
		order.setConfirmTypeCode(orderView.getReceiveTermCode());
		order.setConfirmTypeName(orderView.getReceiveTermName());
		order.setCurrentVersion(orderView.getVersion());
		order.setCurrentVersionStatus(toString(orderView.getStatus()));
		order.setSaleType(orderView.getSalesType());
		order.setSalesTelnumber(orderView.getSalesTel());
		order.setSalesCode(orderView.getOwnerDomainId());
		order.setSalesName(orderView.getOwnerName());
		order.setContracterCode(orderView.getContractorCode());
		order.setContracterName(orderView.getContractorName());
		order.setCustomerClazzCode(orderView.getContractorClassCode());
		order.setCustomerClazzName(orderView.getContractorClassName());
		order.setIncoterm(orderView.getIncotermCode());
		order.setUserOfficeCode(orderView.getSalesOfficeCode());
		order.setContractManager(orderView.getOpteratorDomainId());
		order.setCurrency(orderView.getCurrencyCode());
		order.setInstallCode(orderView.getInstallTermCode());
		order.setInstallName(orderView.getInstallTermName());
		order.setTerminalType(orderView.getTerminalIndustryCode());
		order.setWarrenty(orderView.getWarranty());
		order.setContractValue(toDouble(orderView.getContractAmount()));
		order.setContractRMBValue(toDouble(orderView.getContractRmbAmount()));
		order.setCurrencyExchange(orderView.getExchange());
		// 购销明细金额合计 Aggregate amount
//			order.setItemsAmount(orderView.geti);
		// 运费
		order.setFreight(toDouble(orderView.getFreight()));

		if (order instanceof DealerOrder) {
			DealerOrder dealerOrder = (DealerOrder)order;
			
			dealerOrder.setRecordCode(orderView.getRecordCode());
			
			List<KBiddingPlan> billingPlanList = biddingPlanRepository.findByOrderInfoId(orderInfoId);
			if (billingPlanList.size() > 0) {
				dealerOrder.setPaymentType(billingPlanList.get(0).getCode());
			}
		}

		if (includeDetail) {
			assembleOrderDetail(order, orderId, orderInfoId, formId);
		}
	}

	private void assembleOrderDetail(AbsOrder order, String orderId, String orderInfoId, String formId) {
		// Attached File
		List<KAttachment> attachments = attachementRepository.findByOrderInfo(orderInfoId);
		List<String> attachmentNames = new ArrayList<String>();
		for (KAttachment attachment : attachments) {
			attachmentNames.add(attachment.getFileName());
		}
		order.setAttachedFileName(attachmentNames);

		// 收货地址
		List<KDelieveryAddress> addressList = deliveryAddressRepository.findByOrderInfoId(orderInfoId);
		List<OrderAddress> addresses = new ArrayList<OrderAddress>(addressList.size());
		for (KDelieveryAddress delieveryAddress : addressList) {
			OrderAddress address = new OrderAddress();
			addresses.add(address);
			BeanUtils.copyProperties(delieveryAddress, address);
		}
		order.setOrderAddress(addresses);

		// billing plan
		List<KBiddingPlan> billingPlanList = biddingPlanRepository.findByOrderInfoId(orderInfoId);
		List<BiddingPayment> payments = new ArrayList<BiddingPayment>();
		for (KBiddingPlan kBiddingPlan : billingPlanList) {
			BiddingPayment payment = new BiddingPayment();
			payment.setPayDate(kBiddingPlan.getPayDate());
			payment.setPercentage(kBiddingPlan.getAmount() == null ? 0 : kBiddingPlan.getAmount().doubleValue());
			payment.setReason(kBiddingPlan.getReason());
			payment.setTermCode(kBiddingPlan.getCode());
			payment.setTermName(kBiddingPlan.getName());
			payments.add(payment);
		}
		order.setPayments(payments);

		// TODO bpm dicision

		// TODO items
		assembleItems(order, formId);
	}

	private void assembleItems(AbsOrder order, String formId) {
		List<ItemDetails> detailsList = itemDetailRepository.findByKFormsId(formId);
		// 新的AbsOrder
		List<BaseItem> items = new ArrayList<BaseItem>(detailsList.size());
		for (ItemDetails itemDetails : detailsList) {
			String itemId = itemDetails.getId();
			BaseItem item = new BaseItem();
			BeanUtils.copyProperties(itemDetails, item, (String[]) null);
			item.setDeliveryDate(itemDetails.getDelieveryDate());
			item.setShippDate(itemDetails.getDelieveryDate());
			item.setUnitCode(itemDetails.getMeasureUnitCode());
//			item.setUnitName(unitName);
			item.setGroupCode(itemDetails.getMaterialGroupCode());
			item.setGroupName(itemDetails.getMaterialGroupName());
			item.setSpecialComments(itemDetails.getSpecialNeed());
			item.setConfigComments(itemDetails.getComments());
			item.setMosaicImage(itemDetails.getMosaicImage());
			item.setRequestCircult(itemDetails.getRequestCircuit());

			// 产品实卖价 = 实卖金额 / 数量
			BigDecimal saleAmount = itemDetails.getAmount();
			BigDecimal quantity = BigDecimal.valueOf(itemDetails.getQuantity());
			item.setActuralPrice(saleAmount.divide(quantity, 4, BigDecimal.ROUND_HALF_UP).doubleValue());
//			item.setActuralPricaOfOptional(toDouble(itemDetails.geto));
			item.setTranscationPrice(toDouble(itemDetails.getTransfterPrice()));
//			item.setTranscationPriceOfOptional(toDouble(transcationPriceOfOptional));
			item.setRetailPrice(toDouble(itemDetails.getRetailPrice()));
			item.setStandardPrice(toDouble(itemDetails.getStandardPrice()));
//			item.setPeriod(itemDetails.getp);

			List<KAttachedInfo> attachedInfoList = attachedInfoRepository.findByItemDetailsId(itemId);
			if (attachedInfoList.size() > 0) {
				KAttachedInfo attached = attachedInfoList.get(0);
				item.setProduceDate(attached.getStartDateOfProduction());
				item.setOnStoreDate(attached.getDateOfOnStore());
			}

			items.add(item);

			// TODO item b2c
			// characteristics
			List<KCharacteristics> characs = characteristicsRepository.findByItemDetailsId(itemId);
			List<BaseChracteristic> configs = new ArrayList<BaseChracteristic>();
			for (KCharacteristics charac : characs) {
				if (toString(charac.getIsConfigurable()).equals("1")) {
					item.setConfigurable(true);
				}

				BaseChracteristic c = new BaseChracteristic();
				c.setConfigCode(charac.getKeyCode());
				c.setConfigValueCode(charac.getValueCode());
				c.setOptional(toString(charac.getIsConfigurable()).equals("1"));

				configs.add(c);
			}
			item.setConfigs(configs);
			// TODO configure material
			// TODO attached info
		}
		order.setItems(items);
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
			querySql.append(" and UPPER(sequence_number) like :sequenceNumber");// .append(query.getSequenceNumber());
			params.put("sequenceNumber", "%" + orderQuery.getSequenceNumber().toUpperCase() + "%");
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
		if (!isEmpty(orderQuery.getSalesCode())) {
			querySql.append(" and owner_domain_id = :ownerId");
			params.put("ownerId", orderQuery.getSalesCode());
		}
		if (!isEmpty(orderQuery.getSalesName())) {
			querySql.append(" and UPPER(owner_name) like :ownerName");
			params.put("ownerName", "%" + orderQuery.getSalesName().toUpperCase() + "%");
		}
		if (!isEmpty(orderQuery.getCreateTime())) {
			// 2019-04-07 - 2019-11-07
			String strtime = orderQuery.getCreateTime();
			String[] strtimes = strtime.split(" - ");
			String start = strtimes[0].trim();
			String end = strtimes[1].trim();
			querySql.append(
					" and DATE_FORMAT(create_time, '%Y-%m-%d') >= :start and DATE_FORMAT(create_time, '%Y-%m-%d') <= :end");
			params.put("start", start);
			params.put("end", end);
		}
		if (!isEmpty(orderQuery.getSubmitTime())) {
			// 2019-04-07 - 2019-11-07
			String strtime = orderQuery.getSubmitTime();
			String[] strtimes = strtime.split(" - ");
			String start = strtimes[0].trim();
			String end = strtimes[1].trim();
			querySql.append(
					" and DATE_FORMAT(submit_date, '%Y-%m-%d') >= :start and DATE_FORMAT(submit_date, '%Y-%m-%d') <= :end");
			params.put("start", start);
			params.put("end", end);
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
			querySql.append(" and UPPER(contract_number) like :contractNumber"); // .append(query.getStatus());
			params.put("contractNumber", "%" + orderQuery.getContractNumber().toUpperCase() + "%");
		}
		if (!isEmpty(orderQuery.getContracterName())) {
			querySql.append(" and UPPER(contractor_name) like :contractorName"); // .append(query.getStatus());
			params.put("contractorName", "%" + orderQuery.getContracterName().toUpperCase() + "%");
		}
		if (orderQuery.getStatusList() != null && orderQuery.getStatusList().size() > 0) {
			if (orderQuery.getDominStatusList() != null && orderQuery.getDominStatusList().size() > 0) {
				querySql.append(" and (status in (:statuslist)").append(" or (owner_domain_id = :ownerId and status in (:domainStatuslist)))");
				params.put("statuslist", orderQuery.getStatusList());
				params.put("ownerId", orderQuery.getDominSalesCode());
				params.put("domainStatuslist", orderQuery.getDominStatusList());
			} else {
				querySql.append(" and status in (:statuslist)");
				params.put("statuslist", orderQuery.getStatusList());
			}
		}
		if (!isEmpty(orderQuery.getSpecialDiscount())) {
			boolean special = orderQuery.getSpecialDiscount().equals("1");
			if (special) {
				querySql.append(" and (body_discount + main_discount) != 96");
			} else {
				querySql.append(" and (body_discount + main_discount) = 96");
			}
		}
		if (orderQuery.isLast()) {
			querySql.append(
					" and version_create_time = (select max(create_time) from k_order_version where k_order_version.k_orders_id = k_order_view.order_id)"); // .append(query.getStatus());
		}
		if (!isEmpty(orderQuery.getB2c())) {
			boolean has = orderQuery.getB2c().equals("1");
			querySql.append(
					" and exists (select 1 from k_item_details where k_item_details.k_forms_id = k_order_view.form_id");
			if (has) {
				querySql.append(" and b2c_comments is not null and LENGTH(TRIM(b2c_comments)) > 0");
			} else {
				querySql.append(" and (b2c_comments is null or LENGTH(TRIM(b2c_comments)) = 0)");
			}
			querySql.append(")");
		}

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
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			countQuery.setParameter(entry.getKey(), entry.getValue());
		}
		BigInteger totalCount = (BigInteger) countQuery.getSingleResult();
		PageHelper page = new PageHelper<AbsOrder>();
		page.setRows(orderViews);
		page.setTotal(totalCount.intValue());

		return page;
	}

	/**
	 */
	public void enginingCost(String operator, boolean isApproved, String seqnum, String version, double installation,
			double materials, double electrical, double coolrome, double maintanance) {

		KOrderInfo orderInfo = orderInfoRepo.findOrderInfoBySeqAndVersion(seqnum, version);
		List<EnginingCost> engins = enginRepo.findAllEnginingByItem(orderInfo.getId());
		if (engins == null || engins.isEmpty()) {
			engins = new ArrayList<EnginingCost>();
			EnginingCost ins = new EnginingCost();
			ins.setMaterialCode(this.COST_INSTALLATION);
			ins.setOrderId(orderInfo.getId());
			ins.setOperator(operator);
			ins.setOptTime(new Date());
			ins.setCost(installation);
			engins.add(ins);
			//
			EnginingCost mat = new EnginingCost();
			mat.setMaterialCode(this.COST_MATERIALS);
			mat.setOrderId(orderInfo.getId());
			mat.setOperator(operator);
			mat.setOptTime(new Date());
			mat.setCost(materials);
			engins.add(mat);
			//
			EnginingCost ele = new EnginingCost();
			ele.setMaterialCode(this.COST_ELETRICAL);
			ele.setOrderId(orderInfo.getId());
			ele.setOperator(operator);
			ele.setOptTime(new Date());
			ele.setCost(electrical);
			engins.add(ele);
			//
			EnginingCost cool = new EnginingCost();
			cool.setMaterialCode(this.COST_COOLROOM);
			cool.setOrderId(orderInfo.getId());
			cool.setOperator(operator);
			cool.setOptTime(new Date());
			cool.setCost(coolrome);
			engins.add(cool);
			//
			EnginingCost man = new EnginingCost();
			man.setMaterialCode(this.COST_MAINTANANCE);
			man.setOrderId(orderInfo.getId());
			man.setOperator(operator);
			man.setOptTime(new Date());
			man.setCost(maintanance);
			engins.add(man);
		} else {
			for (EnginingCost enc : engins) {
				switch (enc.getMaterialCode()) {
				case COST_INSTALLATION:
					enc.setOperator(operator);
					enc.setOptTime(new Date());
					enc.setCost(maintanance);
					break;
				case COST_MATERIALS:
					enc.setOperator(operator);
					enc.setOptTime(new Date());
					enc.setCost(maintanance);
					break;
				case COST_ELETRICAL:
					enc.setOperator(operator);
					enc.setOptTime(new Date());
					enc.setCost(maintanance);
					break;
				case COST_COOLROOM:
					enc.setOperator(operator);
					enc.setOptTime(new Date());
					enc.setCost(maintanance);
					break;
				case COST_MAINTANANCE:
					enc.setOperator(operator);
					enc.setOptTime(new Date());
					enc.setCost(maintanance);
					break;
				}
			}
		}
		//
		enginRepo.saveAll(engins);

	}

	/**
	 * 
	 * @param isApproved
	 * @param seqnum
	 * @param version
	 * @param b2cs
	 */
	public void b2cCost(boolean isApproved, String seqnum, String version, String operator,List<B2CComments> b2cs) {

		List<ItemDetails> items = itemDetailRepository.findByOrder(seqnum, version);
		KOrderVersion ov = orderVersionRepo.findVersion(seqnum, version);
		String status = ov.getStatus();
		String orderStatus = status.substring(0, 2);
		char lastchar = status.charAt(3); 
		String turnkeyStatus = String.valueOf(lastchar);
		
		if (isApproved) {
			
			ov.setStatus(orderStatus + "2" + turnkeyStatus);
		} else {
			ov.setStatus(orderStatus + "0" + turnkeyStatus);
		}
		//
		for (ItemDetails item : items) {
			for (B2CComments b2 : b2cs) {
				if (item.getRowNumber() == b2.getRowNumber()) {
					B2CCost cost = b2cRepo.findAllB2CByItem(item.getId());
					if (cost == null) {
						cost = new B2CCost();
					}
					cost.setItemId(item.getId());
					cost.setOptTime(new Date());
					cost.setCost(b2.getCost());
					cost.setOperator(operator);

					b2cRepo.save(cost);
					if(!item.getB2cComments().equals(b2.getB2cComments())) {
						itemDetailRepository.save(item);
					}
				}
			}
		}

	}
	
	/**
	 * 
	 * 发送订单到BPM审批
	 * 
	 * @param order
	 */
	public void sendToBpm(AbsOrder order) {
		List<BaseItem> items = order.getItems();
		if (items == null) {
			items = new ArrayList<BaseItem>();
		}
		List<DMaterialGroups> grossProfitMargins = this.calcGrossProfit(order);
		DMaterialGroups sumMargin = grossProfitMargins.get(grossProfitMargins.size() - 1);
		
		BpmOrder bpmOrder = new BpmOrder();
		Order bpmHeader = new Order();
		List<OrderItem> bpmItems = new ArrayList<OrderItem>(items.size());
		List<OrderMargin> bpmMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());
		List<OrderMargin> bpmWtwMargins = new ArrayList<OrderMargin>(grossProfitMargins.size());
		
		bpmOrder.setOrder(bpmHeader);
		bpmOrder.setItems(bpmItems);
		bpmOrder.setMargin(bpmMargins);
		bpmOrder.setWtwMargin(bpmWtwMargins);
		
		// set bpm order
//		bpmHeader.setAddress();
		bpmHeader.setApprovalDiscount(order.getApprovedDiscount());
		bpmHeader.setB2c("0");
		List<BaseItem> l = order.getItems();
		for (BaseItem baseItem : l) {
			String c = baseItem.getB2cComments();
			if (c != null && c.trim().length() > 0) {
				bpmHeader.setB2c("1");
				break;
			}
		}
		bpmHeader.setBodyDiscount(order.getBodyDiscount());
		bpmHeader.setComments(order.getComments());
		bpmHeader.setContractAmount(order.getContractValue());
		bpmHeader.setContractNumber(order.getContractNumber());
		bpmHeader.setContractRmbAmount(order.getContractRMBValue());
		bpmHeader.setCreateTime(order.getCreateTime());
		bpmHeader.setCurrencyName(order.getCurrencyName());
		bpmHeader.setCustomerName(order.getCustomerName());
		bpmHeader.setDealer(order.getOrderType().equals(AbsOrder.ORDER_TYPE_CODE_DEALER) ? "1" : "0");
		if (order instanceof DealerOrder) {
			bpmHeader.setDiscount(((DealerOrder)order).getDiscount());
		}
		bpmHeader.setEarliestDeliveryDate(order.getEarliestDeliveryDate());
		// TODO 
//		bpmHeader.setElectricalFee(0);
		bpmHeader.setExchange(order.getCurrencyExchange());
//		bpmHeader.setInstallFee();
//		bpmHeader.setMaintenanceFee();
		bpmHeader.setMargin(sumMargin.getGrossProfitMargin());
//		bpmHeader.setMaterialFee();
//		bpmHeader.setMaterialGroupNames();
		bpmHeader.setMergeDiscount(bpmHeader.getDiscount());
		bpmHeader.setOrderType(order.getOrderType());
		if (order.getPayments() != null && order.getPayments().size() > 0) {
			bpmHeader.setPaymentTypeName(order.getPayments().get(0).getTermName());
		}
		bpmHeader.setReceiveTypeName(order.getConfirmTypeName());
//		bpmHeader.setRefrigeratoryFee();
		bpmHeader.setSalesCode(order.getSalesCode());
		bpmHeader.setSalesTel(order.getSalesTelnumber());
		bpmHeader.setSaleType(order.getSaleType());
		bpmHeader.setSapOffice(order.getOfficeName());
		bpmHeader.setSequenceNumber(order.getSequenceNumber());
		bpmHeader.setShopName(order.getCustomerName());
		bpmHeader.setSpecialDiscount(bpmHeader.getDiscount() == 0.48 ? "1" : "0");
		bpmHeader.setStatus(order.getCurrentVersionStatus());
		bpmHeader.setTaxRate(order.getTaxRate());
		bpmHeader.setUnitDiscount(order.getMainDiscount());
		bpmHeader.setWtwMargin(sumMargin.getWtwGrossProfitMargin());
		
		// set bpm order item
		for (BaseItem baseItem : items) {
			OrderItem bpmItem = new OrderItem();
			bpmItems.add(bpmItem);
			
			bpmItem.setActuralAmount(baseItem.getActuralPrice() * baseItem.getQuantity());
			bpmItem.setActuralAmountOfOptional(baseItem.getActuralPricaOfOptional() * baseItem.getQuantity());
			bpmItem.setActuralPrice(baseItem.getActuralPrice());
			bpmItem.setActuralPriceOfOptional(baseItem.getActuralPricaOfOptional());
//			bpmItem.setAddress(baseItem.geta);
			bpmItem.setB2cAmountEstimated(baseItem.getB2CPriceEstimated() * baseItem.getQuantity());
			bpmItem.setB2cComments(baseItem.getB2cComments());
			bpmItem.setB2cCostOfEstimated(baseItem.getB2CCostOfEstimated());
			bpmItem.setB2cPriceEstimated(baseItem.getB2CPriceEstimated());
			bpmItem.setColorComments(baseItem.getColorComments());
			bpmItem.setDeliveryDate(baseItem.getDeliveryDate());
			bpmItem.setDiscount(baseItem.getDiscount());
			bpmItem.setItemCategoryName(baseItem.getItemCategory());
			bpmItem.setItemRequirementPlanName(baseItem.getItemRequirementPlan());
			bpmItem.setMaterialCode(baseItem.getMaterialCode());
//			bpmItem.setMaterialAttribute();
			bpmItem.setMaterialGroupName(baseItem.getGroupName());
			bpmItem.setMaterialName(baseItem.getMaterialName());
			bpmItem.setMeasureUnitName(baseItem.getMaterialName());
			bpmItem.setOnStoreDate(baseItem.getOnStoreDate());
			bpmItem.setPeriod(baseItem.getPeriod());
			bpmItem.setProduceDate(baseItem.getProduceDate());
			bpmItem.setQuantity(baseItem.getQuantity());
			bpmItem.setRetailAmount(baseItem.getRetailPrice() * baseItem.getQuantity());
			bpmItem.setRetailPrice(baseItem.getRetailPrice());
			bpmItem.setRowNumber(baseItem.getRowNumber());
			bpmItem.setShippDate(baseItem.getShippDate());
			bpmItem.setSpecialComments(baseItem.getSpecialComments());
			bpmItem.setTranscationPriceOfOptional(baseItem.getTranscationPriceOfOptional());
			bpmItem.setTransfterPrice(baseItem.getTranscationPrice());
		}
		
		// set bpm order margins and wtw margins
		for (DMaterialGroups grossProfitMargin : grossProfitMargins) {
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
	}
	
	/**
	 * 更新BPM审批状态和折扣 
	 */
	public void updateBpmStatus(String sequenceNumber, String status, Double bodyDiscount, Double unitDiscount) {
		KOrderVersion version = this.orderVersionRepo.findLastVersion(sequenceNumber);
		if (status.equals("1")) {
			// 审批通过
			status = version.getStatus();
			status = "03" + status.substring(2);
		} else {
			// 审批拒绝
			status = version.getStatus();
			status = "04" + status.substring(2);
		}
		version.setStatus(status);
		orderVersionRepo.save(version);
		
		String orderInfoId = version.getOrderInfoId();
		KOrderInfo orderInfo = this.orderInfoRepo.findById(orderInfoId).get();
		orderInfo.setBodyDiscount(bodyDiscount);
		orderInfo.setMainDiscount(unitDiscount);
		orderInfoRepo.save(orderInfo);		
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
