package com.qhc.order.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qhc.order.domain.CharacteristicDto;
import com.qhc.order.domain.DeliveryAddressDto;
import com.qhc.order.domain.ItemDto;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.sap.SapOrder;
import com.qhc.order.domain.sap.SapOrderChangeItem;
import com.qhc.order.domain.sap.SapOrderCharacteristics;
import com.qhc.order.domain.sap.SapOrderHeader;
import com.qhc.order.domain.sap.SapOrderItem;
import com.qhc.order.domain.sap.SapOrderPlan;
import com.qhc.order.domain.sap.SapOrderPrice;
import com.qhc.order.entity.BillingPlan;
import com.qhc.order.mapper.BillingPlanMapper;
import com.qhc.order.mapper.CharacteristicsMapper;
import com.qhc.system.entity.ProvinceMap;
import com.qhc.system.mapper.ProvinceMapMapper;
import com.qhc.system.mapper.UserMapper;
import com.qhc.system.service.UserService;
import com.qhc.utils.HttpUtil;

@Service
public class SapOrderService {
	private static Logger logger = LoggerFactory.getLogger(SapOrderService.class);
	
	@Value("${sap.paymentplan.addr}")
	public String paymentplanUrlStr;
	@Value("${sap.sapCreateOrder.addr}")
	public String orderCreationUrl;
	@Value("${sap.sapChangeOrder.addr}")
	public String orderChangeUrl;

	@Autowired
	private ConstantService constantService;
	@Autowired
	private CharacteristicsMapper characteristicsMapper;
	@Autowired
	private BillingPlanMapper billingPlanMapper;
	@Autowired
	private ProvinceMapMapper provinceMapMapper;
	@Autowired
	private UserMapper userMapper;

	public SapOrderService() {
	}
	/**
	 * 根据流水号组装数据
	 * 
	 * @param sequenceNumber
	 * @return //
	 */
	private SapOrder assembleSapOrder(OrderDto order) {
	  if (StringUtils.trimToEmpty(order.getContractNumber()).length() == 0) {
	    throw new RuntimeException("合同号不能为空");
	  }
	  
		Integer orderInfoId = order.getId();
//		String orderType = order.getOrderType();
		double exchange = order.getCurrencyExchange();
		boolean isUpdate = order.getVersionNum() > 1;

		// assemble sap order header
		SapOrderHeader header = new SapOrderHeader();
		List<SapOrderItem> sapItems = new ArrayList<SapOrderItem>();
		List<SapOrderCharacteristics> sapCharacs = new ArrayList<SapOrderCharacteristics>();
		List<SapOrderPrice> sapPrices = new ArrayList<SapOrderPrice>();
		List<SapOrderPlan> sapPlans = new ArrayList<SapOrderPlan>();

		// Header input
		header.setAuart(StringUtils.defaultString(order.getOrderType(), "")); // Sales order type/订单类型
		header.setVkorg("0841"); // Sales org./销售组织 -- Fixed value/固定为 0841
		String customerClazz = order.getCustomerClazz();
		if (customerClazz.equals("01")) {
			customerClazz = "10";
		} else if (customerClazz.equals("02") || customerClazz.equals("03")) {
			customerClazz = "20";
		}
		header.setVtweg(customerClazz); // DC/分销渠道 -- 客户
		header.setName2(order.getShopName()); // Store name/店名
		header.setSpart(order.getSaleType()); // Division/产品组
		header.setVkbur(StringUtils.trimToEmpty(order.getOfficeCode())); // Sales office/销售办公室 -- 大区
		header.setVkgrp(StringUtils.trimToEmpty(order.getGroupCode())); // Sales group/销售组 -- 中心
		header.setVbeln(StringUtils.trimToEmpty(order.getContractNumber()).toUpperCase()); // SO number/销售订单编号 -- 合同号
		header.setKvgr1(ObjectUtils.defaultIfNull(order.getIsConvenientStore(), "").toString()); // Customer grp.1/客户组1 -- 是否便利店
		header.setKvgr2(ObjectUtils.defaultIfNull(order.getIsNew(), "").toString()); // Customer grp.2/客户组2 -- 是否新客户
		header.setKvgr3(ObjectUtils.defaultIfNull(order.getIsReformed(), "").toString()); // Customer grp.3/客户组3 -- 是否改造店
		header.setBstzd(ObjectUtils.defaultIfNull(order.getWarranty(), "").toString()); // Additional/附加的 -- 保修年限
		header.setBstkdE(StringUtils.trimToEmpty(order.getRecordCode()).toUpperCase()); // Ship-to PO/送达方-采购订单编号 -- 项目报备编号
		header.setVsart(StringUtils.trimToEmpty(order.getTransferType())); // Shipping type/装运类型 -- 运输类型
		if (order.getStOrderType().equals("4")) {
		  header.setVbbkz114(order.getPaymentType()); // Payment terms/付款条款 -- 结算方式 大客户不为空，直签订单
		} else {
		  header.setZterm(order.getPaymentType()); // Payment terms/付款条款 -- 结算方式 大客户为空
		}
		header.setKunnr(StringUtils.trimToEmpty(order.getCustomerCode())); // Sold-to party/售达方 -- 签约单位
		header.setKunnr_re(StringUtils.trimToEmpty(order.getCustomerCode())); // Sold-to party/开票方 -- 签约单位
		header.setKunnr_rg(StringUtils.trimToEmpty(order.getCustomerCode())); // Sold-to party/付款方 -- 签约单位
		header.setKunnr_we(StringUtils.trimToEmpty(order.getCustomerCode())); // Sold-to party/送达方 -- 签约单位
		header.setWaerk(StringUtils.trimToEmpty(order.getCurrency())); // Currency/币别 -- 币别
		header.setInco1(StringUtils.trimToEmpty(order.getIncoterm())); // Incoterms/国际贸易条款 -- 国际贸易条件 code
		header.setInco2(StringUtils.trimToEmpty(order.getIncotermContect())); // Incoterms2/国际贸易条款2 -- 国际贸易条件2
//		// 折扣
		header.setVbbkz120(String.valueOf(order.getContractValue())); // Contract amount/合同金额 -- 合同金额
		header.setVbbkz121(order.getSalesName()); // Sale rep./签约人 -- 客户经理
		String contractManager = StringUtils.trimToEmpty(order.getContractManager());
		String contractManagerName = contractManager;
		if (contractManager.length() > 0) {
		  contractManagerName = userMapper.findByLoginName(contractManager).getName();
		}
		header.setVbbkz109(contractManagerName); // Order clerk/合同管理员 -- 支持经理
		String contactorInfo = StringUtils.trimToEmpty(order.getContactor1Id()) + "/" + StringUtils.trimToEmpty(order.getContactor1Tel())
				+ StringUtils.trimToEmpty(order.getContactor2Id()) + "/" + StringUtils.trimToEmpty(order.getContactor2Tel())
				+ StringUtils.trimToEmpty(order.getContactor3Id()) + "/" + StringUtils.trimToEmpty(order.getContactor3Tel());
		header.setVbbkz108(contactorInfo); // Contact info./授权人信息 -- 授权人信息6个字段 3个联系人id+tel / 分隔
		String vbbkz122 = "";
		// 	柜体控制阀件是否甲供
		vbbkz122 += order.getIsTerm1() == 1 ? "/" + "柜体控制阀件甲供" : "";
		// 分体柜是否远程监控
		vbbkz122 += order.getIsTerm2() == 1 ? "/" + "分体柜远程监控" : "";
		// 立柜柜体是否在地下室
		vbbkz122 += order.getIsTerm3() == 1 ? "/" + "立柜柜体在地下室" : "";
		vbbkz122 = vbbkz122.length() > 0 ? vbbkz122.substring(1) : "";
		header.setVbbkz122(vbbkz122); // Survey info. for header /调研表相关内容 -- 调研表相关内容3个字段
		String receiveTypeName = constantService.findReceiveTerms().get(StringUtils.trimToEmpty(order.getReceiveType()));
		receiveTypeName = StringUtils.trimToEmpty(receiveTypeName);
		header.setVbbkz106(receiveTypeName); // Receiving method /收货方式 -- 收货方式
		String deliveryDate = order.getEarliestDeliveryDate() == null ? ""
				: new SimpleDateFormat("yyyyMMdd").format(order.getEarliestDeliveryDate());
		header.setVdatu(deliveryDate); // 交货日期 -- 要求发货日期
		if (isUpdate) {
			header.setUpdateflag("U");
		}

//		ItemService itemService;
		List<ItemDto> items = order.getItems(); // itemMapper.findByOrderInfoId(order.getId());
		Integer addressSeq = null;
		boolean singleAddress = true;
		for (ItemDto item : items) {
			int rowNumber = item.getRowNum();
			double quantity = item.getQuantity();
			String itemStatus = StringUtils.trimToEmpty(item.getItemStatus());
			
			SapOrderItem sapItem = new SapOrderItem();
			if (isUpdate) {
				if (itemStatus.equals("") || itemStatus.equals("00")) {
					sapItem.setUpdateflag("I");
				} else {
					sapItem.setUpdateflag("U");
				}
			}
			// Ship-to PO item/送达方-采购订单编号项目
			sapItem.setPosnr(rowNumber);
			// Material Number/物料编码
			sapItem.setMatnr(item.getMaterialCode());
			// Target quantity/数量
			sapItem.setZmeng((int) quantity);
			// Req.dlv.date/请求发货日期 yyyyMMdd
			String shippDate = item.getShippDate() == null ? ""
					: new SimpleDateFormat("yyyyMMdd").format(item.getShippDate());
			sapItem.setEdatu(shippDate);
			// Item category/行项目类别 -- 项目类别
			sapItem.setPstyv(item.getItemCategory());
			// Item usage/项目用途 -- 项目需求计划
			sapItem.setVkaus(item.getItemRequirementPlan());

			// 2020/03/05 行项目不传地址
//			// 出口定单的时候，省市区我记得是默认置灰，只填具体地址。
//			// Ship-to address/送达方地址
//			if (addressSeq == null) {
//				addressSeq = item.getDeliveryAddressSeq();
//			} else if (!addressSeq.equals(item.getDeliveryAddressSeq())) {
//				singleAddress = false;
//			}
//			// 街道名称
//			sapItem.setStreet(item.getAddress());
////			// Province/省 -- 省code
//			String province = getSapProvince(order.getSaleType(), item.getProvinceCode());
//			sapItem.setRegion(province);
////			// City/市 -- 市名称
//			sapItem.setCity1(item.getCityName());
////			// District/区 -- 区名称
//			sapItem.setCity2(item.getDistrictName());

			// B2C note/B2C备注
			sapItem.setVbbp0006(item.getB2cComments());
			// Survey info. for item /调研表基本信息
			StringBuilder sb = new StringBuilder(128);
			if (!StringUtils.isEmpty(item.getRequestBrand())) {
				sb.append(",").append(item.getRequestBrand());
			}
			if (!StringUtils.isEmpty(item.getRequestPackage())) {
				sb.append(",").append(item.getRequestPackage());
			}
			if (!StringUtils.isEmpty(item.getRequestNameplate())) {
				sb.append(",").append(item.getRequestNameplate());
			}
			if (!StringUtils.isEmpty(item.getRequestCircult())) {
				sb.append(",").append(item.getRequestCircult());
			}
			sapItem.setVbbpz121(sb.length() > 0 ? sb.substring(1) : "");
			// Special note/特殊备注
			sapItem.setVbbpz117(StringUtils.trimToEmpty(item.getSpecialComments()));
			// Color option/颜色可选项
			sapItem.setVbbpz120(StringUtils.trimToEmpty(item.getColorOptions()));
			// Survey info. Note/调研表备注
			sapItem.setVbbp0007(StringUtils.trimToEmpty(item.getConfigComments()));
			// Color Note/颜色备注
			sapItem.setVbbpz118(StringUtils.trimToEmpty(item.getColorComments()));
			// 拼接方式
			sapItem.setVbbpz119(StringUtils.trimToEmpty(item.getMosaicImage()));

			sapItems.add(sapItem);

			// add price condition
			// 实卖价合计
			double actualPriceSum = item.getActualPrice() + item.getOptionalActualPrice() + item.getB2cEstimatedPrice();
			// 转移价合计
			double transferPriceSum = item.getTransactionPrice() + item.getOptionalTransactionPrice()+ item.getB2cEstimatedCost();
			transferPriceSum /= quantity;
			actualPriceSum = actualPriceSum / exchange; // 转换未凭证货币
			transferPriceSum = transferPriceSum / exchange; // 转换未凭证货币
			addItemPrice(sapPrices, rowNumber, actualPriceSum, transferPriceSum);

			// Characteristics value input
			List<CharacteristicDto> characList = characteristicsMapper.findByItemId(item.getId());
			for (CharacteristicDto charac : characList) {
				SapOrderCharacteristics c = new SapOrderCharacteristics();

				// Item/行项目编号
				c.setPosnr(rowNumber);
				// Characteristic/特性
				c.setAtnam(charac.getKeyCode());
				// Char. value/特性值
				c.setAtwrt(charac.getValueCode());
				sapCharacs.add(c);
			}
		}
		
		// 出口定单的时候，省市区我记得是默认置灰，只填具体地址。
		// 如果所有行项目只有一个地址，则设置sap order header的地址为此地址
//		if (singleAddress) {
		List<DeliveryAddressDto> addresses = order.getDeliveryAddress();
		if (order.getDeliveryAddress() != null && order.getDeliveryAddress().size() > 0) {
			DeliveryAddressDto address = addresses.get(0);
			// 街道名称
			header.setStreet(address.getAddress()); // sapItems.get(0).getStreet());
//			// Province/省 -- 省code
			header.setRegion(this.getSapProvince(order.getSaleType(), address.getProvinceCode())); // sapItems.get(0).getRegion());
//			// City/市 -- 市名称
			header.setCity1(address.getCityName()); // sapItems.get(0).getCity1());
//			// District/区 -- 区名称
			header.setCity2(address.getDistrictName()); // sapItems.get(0).getCity2());
		}
		
		// 将费用类物料加到行项目
//		其他项目收费  BG1GD1000000-X	
//		addFeeItem(sapItems, "BG1GD1000000-X", 0);
//		安装费  BG1GDA00000-X  9901
		addFeeItem(sapItems, sapPrices, "BG1GDA00000-X", 9901, ObjectUtils.defaultIfNull(order.getInstallFee(), 0D) / exchange);
//		材料费  BG1GDB00000-X	  9902
		addFeeItem(sapItems, sapPrices, "BG1GDB00000-X", 9902, ObjectUtils.defaultIfNull(order.getMaterialFee(), 0D) / exchange);
//		销售运费  BG1P7E00000-X	9903
		double freight = ObjectUtils.defaultIfNull(order.getFreight(), 0D);
		addFeeItem(sapItems, sapPrices, "BG1P7E00000-X", 9903, freight / exchange);
		double additionalFreight = ObjectUtils.defaultIfNull(order.getAdditionalFreight(), 0D);
		if (freight > 0 && additionalFreight > 0) {
			// 附加运费添加到销售运费行项目，用ZH12：承载附加运费费用
			SapOrderPrice price3 = new SapOrderPrice();
			price3.setPosnr(9903);
			price3.setKschl("ZH12");
			price3.setKbetr(BigDecimal.valueOf(additionalFreight / exchange));
			sapPrices.add(price3);
		}
		
//		电气费  BG1R8J00000-X	
		addFeeItem(sapItems, sapPrices, "BG1R8J00000-X", 9904, ObjectUtils.defaultIfNull(order.getElectricalFee(), 0D) / exchange);
//		维保费  BG1R8K00000-X	
		addFeeItem(sapItems, sapPrices, "BG1R8K00000-X", 9905, ObjectUtils.defaultIfNull(order.getMaintenanceFee(), 0D) / exchange);
//		冷库  BG1R8R00000-X	
//		addFeeItem(sapItems, sapPrices, "BG1R8R00000-X", 0);
//		不可预估费  BG1R8L00000-X	
//		addFeeItem(sapItems, sapPrices, "BG1R8L00000-X", 0);
		
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
	private String getSapProvince(String saleType, String province) {
		// 出口定单的时候，省市区我记得是默认置灰，只填具体地址。
		if (saleType.equals("20")) {
			return province;
		}
		ProvinceMap provinceMap = provinceMapMapper.findById(province);
		if (provinceMap == null) {
			throw new RuntimeException("省【" + province + "】没有找到对应的SAP省对应关系");
		}
		province = provinceMap.getSapProvince();
		return province;
	}
	
	private void addItemPrice(List<SapOrderPrice> sapPrices, int rowNumber, double actualPriceSum,
			double transferPriceSum) {
		// Price/condition record input
		if (actualPriceSum > 0) {
			// ZH05：实卖价合计
			SapOrderPrice price1 = new SapOrderPrice();
			price1.setPosnr(rowNumber);
			price1.setKschl("ZH05");
			price1.setKbetr(BigDecimal.valueOf(actualPriceSum));
			sapPrices.add(price1);
		}
		if (transferPriceSum > 0) {
			// ZH08：转移价合计/成本合计
			SapOrderPrice price2 = new SapOrderPrice();
			price2.setPosnr(rowNumber);
			price2.setKschl("ZH08");
			price2.setKbetr(BigDecimal.valueOf(transferPriceSum));
			sapPrices.add(price2);
		}
	}
	
	/**
	 * 添加物料费用行项目
	 * @param sapItems
	 * @param feeCode
	 * @param rowNumber
	 * @param fee1 费用
	 */
	private void addFeeItem(List<SapOrderItem> sapItems, List<SapOrderPrice> sapPrices, String feeCode, Integer rowNumber, Double fee) {
		fee = ObjectUtils.defaultIfNull(fee, 0).doubleValue();
		// 费用为0则不传 
		if (fee <= 0) {
			return;
		}
		SapOrderItem sapItem = new SapOrderItem();
		// Ship-to PO item/送达方-采购订单编号项目
		sapItem.setPosnr(rowNumber);
		// Material Number/物料编码
		sapItem.setMatnr(feeCode);
		// Target quantity/数量
		sapItem.setZmeng(1);
		// Req.dlv.date/请求发货日期 yyyyMMdd
		sapItem.setEdatu("");
		// Item category/行项目类别 -- 项目类别
		sapItem.setPstyv("");
		// Item usage/项目用途 -- 项目需求计划
		sapItem.setVkaus("");

		// Ship-to address/送达方地址
		// 街道名称
		sapItem.setStreet("");
//		// Province/省 -- 省code
		sapItem.setRegion("");
//		// City/市 -- 市名称
		sapItem.setCity1("");
//		// District/区 -- 区名称
		sapItem.setCity2("");

		// B2C note/B2C备注
		sapItem.setVbbp0006("");
		// Survey info. for item /调研表基本信息
		sapItem.setVbbpz121("");
		// Special note/特殊备注
		sapItem.setVbbpz117("");
		// Color option/颜色可选项 -- Characteristics中处理
		sapItem.setVbbpz120("");
		// Survey info. Note/调研表备注
		sapItem.setVbbp0007("");
		// Color Note/颜色备注
		sapItem.setVbbpz118("");
		
		addItemPrice(sapPrices, rowNumber, 0, fee);
		
		sapItems.add(sapItem);
	}

	/**
	 * 下发订单到SAP
	 * 
	 * @param orderDto
	 * @return
	 * @throws JsonProcessingException
	 */
	public String createOrder(OrderDto orderDto) {
		// 1. 根据sequenceNumber组装数据
		SapOrder sapOrder = assembleSapOrder(orderDto);
		return this.sendToSap(sapOrder, orderCreationUrl);
	}

	/**
	 * 下发订单到SAP
	 * 
	 * @param orderDto
	 * @return
	 * @throws Exception
	 */
	public String updateOrder(OrderDto orderDto) throws Exception {
		// 1. 根据sequenceNumber组装数据
		SapOrder sapOrder = assembleSapOrder(orderDto);
		List<SapOrderItem> items = sapOrder.getItZitem();
		List<SapOrderItem> tempItems = new ArrayList<>();
		for (SapOrderItem sapOrderItem : items) {
          SapOrderChangeItem item = new SapOrderChangeItem();
          tempItems.add(item);
          BeanUtils.copyProperties(item, sapOrderItem);
          // 拒绝原因
//          item.setAbgru(abgru);
//          // 销售运费
          if (item.getPosnr().equals("9903")) {
            
          }
        }
		sapOrder.setItZitem(tempItems);
		return this.sendToSap(sapOrder, orderChangeUrl);
	}

	/**
	 * 下发订单到SAP
	 * 
	 * @param orderDto
	 * @return
	 * @throws JsonProcessingException
	 */
	private String sendToSap(SapOrder sapOrder, String url) {
		String res = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// 1.ͬ同步SAP开单 没有数据 先注释
			String sapStr = objectMapper.writeValueAsString(sapOrder);
			logger.info("Order Data: {}", sapStr);
			res = HttpUtil.postbody(url, sapStr, 300); // 5 minute
			objectMapper.getFactory().enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
			JsonNode tree = objectMapper.readTree(res);
			JsonNode result = tree.get("subrc");
			if (result == null || !result.asText("").equals("S")) {
				throw new RuntimeException("Sap错误，Message=" + tree.get("message").asText(""));
			}
		} catch (Exception e) {
			logger.error("ͬ下推SAP异常==>", e);
			throw new RuntimeException(e);
		}

		// 2. 处理返回结果
		logger.info("SAP返回结果==>" + res);

		return res;
	}
}