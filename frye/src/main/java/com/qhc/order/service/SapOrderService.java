package com.qhc.order.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qhc.order.domain.CharacteristicDto;
import com.qhc.order.domain.ItemDto;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.sap.SapOrder;
import com.qhc.order.domain.sap.SapOrderCharacteristics;
import com.qhc.order.domain.sap.SapOrderHeader;
import com.qhc.order.domain.sap.SapOrderItem;
import com.qhc.order.domain.sap.SapOrderPlan;
import com.qhc.order.domain.sap.SapOrderPrice;
import com.qhc.order.entity.BillingPlan;
import com.qhc.order.mapper.BillingPlanMapper;
import com.qhc.order.mapper.CharacteristicsMapper;
import com.qhc.utils.HttpUtil;

public class SapOrderService {
	private static Logger logger = LoggerFactory.getLogger(SapOrderService.class);
	
	@Value("${sap.paymentplan.addr}")
	public String paymentplanUrlStr;
	@Value("${sap.sapCreateOrder.addr}")
	public String orderCreationUrl;
	@Value("${sap.sapChangeOrder.addr}")
	public String orderChangeUrl;
	
	@Autowired
	private CharacteristicsMapper characteristicsMapper;
	@Autowired
	private BillingPlanMapper billingPlanMapper;

	public SapOrderService() {
	}
	/**
	 * 根据流水号组装数据
	 * 
	 * @param sequenceNumber
	 * @return //
	 */
	public SapOrder assembleSapOrder(OrderDto order) {
		Integer orderInfoId = order.getId();
//		String orderType = order.getOrderType();

		// assemble sap order header
		SapOrderHeader header = new SapOrderHeader();
		List<SapOrderItem> sapItems = new ArrayList<SapOrderItem>();
		List<SapOrderCharacteristics> sapCharacs = new ArrayList<SapOrderCharacteristics>();
		List<SapOrderPrice> sapPrices = new ArrayList<SapOrderPrice>();
		List<SapOrderPlan> sapPlans = new ArrayList<SapOrderPlan>();

		// Header input
		header.setAuart(StringUtils.defaultString(order.getOrderType(), "")); // Sales order type/订单类型
		header.setVkorg("0841"); // Sales org./销售组织 -- Fixed value/固定为 0841
		header.setVtweg(order.getCustomerClazz()); // DC/分销渠道 -- 客户
		header.setName2(order.getShopName()); // Store name/店名
		header.setSpart(order.getSaleType()); // Division/产品组
		header.setVkbur(StringUtils.trimToEmpty(order.getOfficeCode())); // Sales office/销售办公室 -- 大区
		header.setVkgrp(StringUtils.trimToEmpty(order.getGroupCode())); // Sales group/销售组 -- 中心
		header.setVbeln(StringUtils.trimToEmpty(order.getContractNumber()).toUpperCase()); // SO number/销售订单编号 -- 合同号
		header.setKvgr1(ObjectUtils.defaultIfNull(order.getIsConvenientStore(), "").toString()); // Customer grp.1/客户组1 -- 是否便利店
		header.setKvgr2(ObjectUtils.defaultIfNull(order.getIsNew(), "").toString()); // Customer grp.2/客户组2 -- 是否新客户
		header.setKvgr3(ObjectUtils.defaultIfNull(order.getIsReformed(), "").toString()); // Customer grp.3/客户组3 -- 是否改造店
		header.setBstzd(ObjectUtils.defaultIfNull(order.getWarranty(), "").toString()); // Additional/附加的 -- 保修年限
		header.setBstkdE(StringUtils.trimToEmpty(order.getContractNumber()).toUpperCase()); // Ship-to PO/送达方-采购订单编号 -- 项目报备编号 - 合同号
		header.setVsart(StringUtils.trimToEmpty(order.getTransferType())); // Shipping type/装运类型 -- 运输类型
		header.setZterm(order.getPaymentType()); // Payment terms/付款条款 -- 结算方式 大客户为空，dealer取billing_plan的第一条code（唯一一条）
		header.setKunnr(StringUtils.trimToEmpty(order.getCustomerCode())); // Sold-to party/售达方 -- 签约单位
		header.setWaerk(StringUtils.trimToEmpty(order.getCurrency())); // Currency/币别 -- 币别
		header.setInco1(StringUtils.trimToEmpty(order.getIncoterm())); // Incoterms/国际贸易条款 -- 国际贸易条件 code
		header.setInco1(StringUtils.trimToEmpty(order.getIncotermName())); // Incoterms2/国际贸易条款2 -- 国际贸易条件2 name
//		// 折扣
		header.setVbbkz120(String.valueOf(order.getContractRmbValue())); // Contract amount/合同金额 -- 合同金额
		header.setVbbkz121(order.getSalesCode()); // Sale rep./签约人 -- 客户经理
		header.setVbbkz109(order.getContractManager()); // Order clerk/合同管理员 -- 合同管理员
		String contactorInfo = StringUtils.trimToEmpty(order.getContactor1Id()) + "/" + StringUtils.trimToEmpty(order.getContactor1Tel())
				+ StringUtils.trimToEmpty(order.getContactor2Id()) + "/" + StringUtils.trimToEmpty(order.getContactor2Tel())
				+ StringUtils.trimToEmpty(order.getContactor3Id()) + "/" + StringUtils.trimToEmpty(order.getContactor3Tel());
		header.setVbbkz108(contactorInfo); // Contact info./授权人信息 -- 授权人信息6个字段 3个联系人id+tel / 分隔
		String vbbkz122 = ObjectUtils.defaultIfNull(order.getIsTerm1(), "") + "/" + ObjectUtils.defaultIfNull(order.getIsTerm2(), "")
				+ ObjectUtils.defaultIfNull(order.getIsTerm3(), "");
		header.setVbbkz122(vbbkz122); // Survey info. for header /调研表相关内容 -- 调研表相关内容3个字段
		header.setVbbkz106(order.getReceiveType()); // Receiving method /收货方式 -- 收货方式

//		ItemService itemService;
		List<ItemDto> items = order.getItems(); // itemMapper.findByOrderInfoId(order.getId());
		Integer addressSeq = null;
		boolean singleAddress = true;
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
			if (addressSeq == null) {
				addressSeq = item.getDeliveryAddressSeq();
			} else if (!addressSeq.equals(item.getDeliveryAddressSeq())) {
				singleAddress = false;
			}
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
			sapItem.setVbbpz117(item.getSpecialComments());
			// Color option/颜色可选项
			sapItem.setVbbpz120(item.getColorOptions());
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

				// Item/行项目编号
				c.setPosnr(rowNumber);
				// Characteristic/特性
				c.setAtnam(charac.getKeyCode());
				// Char. value/特性值
				c.setAtwrt(charac.getValueCode());
				sapCharacs.add(c);
			}
		}
		
		// 如果所有行项目只有一个地址，则设置sap order header的地址为此地址
		if (singleAddress) {
			// 街道名称
			header.setStreet(sapItems.get(0).getStreet());
//			// Province/省 -- 省code
			header.setRegion(sapItems.get(0).getStreet());
//			// City/市 -- 市名称
			header.setCity1(sapItems.get(0).getStreet());
//			// District/区 -- 区名称
			header.setCity2(sapItems.get(0).getStreet());
		}
		
		// TODO 将费用类物料加到行项目
//		其他项目收费  BG1GD1000000-X	
//		addFeeItem(sapItems, "BG1GD1000000-X", 0);
////		安装费  BG1GDA00000-X	
//		addFeeItem(sapItems, "BG1GDA00000-X", 0);
////		材料费  BG1GDB00000-X	
//		addFeeItem(sapItems, "BG1GDB00000-X", 0);
////		销售运费  BG1P7E00000-X	
//		addFeeItem(sapItems, "BG1P7E00000-X", 0);
////		电气费  BG1R8J00000-X	
//		addFeeItem(sapItems, "BG1R8J00000-X", 0);
////		维保费  BG1R8K00000-X	
//		addFeeItem(sapItems, "BG1R8K00000-X", 0);
////		冷库  BG1R8R00000-X	
//		addFeeItem(sapItems, "BG1R8R00000-X", 0);
////		不可预估费  BG1R8L00000-X	
//		addFeeItem(sapItems, "BG1R8L00000-X", 0);
		
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
	 * 添加物料费用行项目
	 * @param sapItems
	 * @param feeCode
	 * @param rowNumber
	 */
	private void addFeeItem(List<SapOrderItem> sapItems, String feeCode, Integer rowNumber) {
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
		
//		// Price/condition record input
//		// ZH05：实卖价合计
//		SapOrderPrice price1 = new SapOrderPrice();
//		price1.setPosnr(rowNumber);
//		price1.setKschl("ZH05");
//		price1.setKbetr(BigDecimal.valueOf(item.getActualPrice() * item.getQuantity()));
//		sapPrices.add(price1);
//		// ZH08：转移价合计/成本合计
//		SapOrderPrice price2 = new SapOrderPrice();
//		price2.setPosnr(rowNumber);
//		price2.setKschl("ZH08");
//		price2.setKbetr(BigDecimal.valueOf(item.getTransationPrice() * item.getQuantity()));
//		sapPrices.add(price2);

		sapItems.add(sapItem);
	}

	/**
	 * 下发订单到SAP，私有方法
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
}