package com.qhc.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.order.domain.ItemDto;
import com.qhc.order.domain.OrderDto;
import com.qhc.sap.dao.SapMaterialGroupsRepository;
import com.qhc.sap.entity.MaterialGroups;

/**
 * 订单毛利率计算服务
 * 
 * @author zsu4
 *
 */
@Service
public class GrossProfitMarginService {

	@Autowired
	private SapMaterialGroupsRepository materialGroupsRepository;

	public List<MaterialGroups> calculate(OrderDto order) {
		String stOrderType = order.getStOrderType();
		// 报价单不需要计算毛利率
		if ("3".equals(stOrderType)) {
			return null;
		}
		// 查询所有物料类型sap_material_group isenable != 0
		List<MaterialGroups> groups = materialGroupsRepository.findByIsenableNotOrderByCode(0);
		List<ItemDto> items = order.getItems();
		double installFee = ObjectUtils.defaultIfNull(order.getInstallFee(), 0d);
		double materialFee = ObjectUtils.defaultIfNull(order.getMaterialFee(), 0d);
		double electricalFee = ObjectUtils.defaultIfNull(order.getElectricalFee(), 0d);
		double maintenanceFee = ObjectUtils.defaultIfNull(order.getMaintenanceFee(), 0d);
		double freight = ObjectUtils.defaultIfNull(order.getFreight(), 0d);
		double additionalFreight = ObjectUtils.defaultIfNull(order.getAdditionalFreight(), 0d);

		// 毛利率合计行
		MaterialGroups sumMaterialGroup = new MaterialGroups();
		sumMaterialGroup.setCode("sum");
		sumMaterialGroup.setName("合计");

		if (items == null || items.size() == 0) {
			setMaterialGroupMargin(sumMaterialGroup, 0, 0, 0, 0);
			groups.add(sumMaterialGroup);

			return groups;
		}

		// 合计值
		double amount = 0;
		double excludingTaxAmount = 0;
		double cost = 0;
		double wtwCost = 0;
		for (MaterialGroups mgroup : groups) {
			String code = mgroup.getCode();
			String orderGroupCode = mgroup.getMaterialGroupOrderCode();
			switch (code) {
			case "3101": // "整机柜"
				calcItemMargin(mgroup, order);
				break;
			case "3102": // "分体柜"
				calcItemMargin(mgroup, order);
				break;
			case "3109": // "机组"
				calcItemMargin(mgroup, order);
				break;
			case "3103": // "换热器和冷凝器"
				calcItemMargin(mgroup, order);
				break;
			case "3104": // "侧板"
				calcItemMargin(mgroup, order);
				break;
			case "1000": // "散件"
				calcItemMargin(mgroup, order);
				break;
			case "3105": // "冷库"
				calcItemMargin(mgroup, order);
				break;
			case "3231": // "其他项目收费"
				// 1元，固定值
				setMaterialGroupMargin(mgroup, 0, 0, 1, 1);
				break;
			case "3212": // "安装费"
				setMaterialGroupMargin(mgroup, 0, 0, installFee, installFee);
				break;
			case "3233": // "材料费"
				setMaterialGroupMargin(mgroup, 0, 0, materialFee, materialFee);
				break;
			case "3235": // "电气费"
				setMaterialGroupMargin(mgroup, 0, 0, electricalFee, electricalFee);
				break;
			case "3236": // "维保费"
				setMaterialGroupMargin(mgroup, 0, 0, maintenanceFee, maintenanceFee);
				break;
			case "3234": // "销售运费"
				setMaterialGroupMargin(mgroup, 0, 0, freight, freight);
				break;
			case "3237": // "不可预估费"
				// 不可预估费是作为行项目录入，不在订单详情
				calcItemMargin(mgroup, order);
				break;
			case "9103": // "追加运费"
				setMaterialGroupMargin(mgroup, 0, 0, additionalFreight, additionalFreight);
				break;
			case "9101": // "B2C"
				setMaterialGroupMargin(mgroup, 0, 0, 0, 0);
				break;
			case "9102": // "可选项"
				setMaterialGroupMargin(mgroup, 0, 0, 0, 0);
				break;
			case "9999": // "不可用物料类别"
				setMaterialGroupMargin(mgroup, 0, 0, 0, 0);
				break;
			}

			amount += mgroup.getAmount().doubleValue();
			excludingTaxAmount += mgroup.getExcludingTaxAmount().doubleValue();
			cost += mgroup.getCost().doubleValue();
			wtwCost += mgroup.getWtwCost().doubleValue();
		}
		// 合计行
		setMaterialGroupMargin(sumMaterialGroup, amount, excludingTaxAmount, cost, wtwCost);
		groups.add(sumMaterialGroup);

		return groups;
	}

	/**
	 * 按物料分组计算行项目毛利率
	 * 
	 * @param taxRate
	 * @param group
	 * @param items
	 */
	private void calcItemMargin(MaterialGroups group, OrderDto order) {
		List<ItemDto> items = order.getItems();
		double taxRate = order.getTaxRate();
		String orderGroupCode = group.getMaterialGroupOrderCode();
		String materialGroupCode = group.getCode();
		double amount = 0;
		double excludingTaxAmount = 0;
		double cost = 0;
		double wtwCost = 0;
		for (ItemDto item : items) {
			String itemMaterialGroupCode = item.getMaterialGroupCode();
			if (itemMaterialGroupCode.equals(materialGroupCode)) {
				// 1. 金额= sum（实卖金额合计），实卖金额=实卖价*数量，实卖价=零售价*折扣+可选项实卖价（差价）+b2c预估价
				amount +=  (item.getActualPrice() + item.getOptionalActualPrice() + item.getB2cEstimatedPrice()) * item.getQuantity();
				// 成本（销售）
				cost += item.getTransationPrice() * item.getQuantity();
				// 成本（生产）
				wtwCost += item.getStandardPrice() * item.getQuantity();
			}
		}

		// 2. 不含税金额=金额/(1+税率)
		excludingTaxAmount = amount / (1 + taxRate);

		setMaterialGroupMargin(group, amount, excludingTaxAmount, cost, wtwCost);
	}

	private BigDecimal toBigDecimal(double amount, int scale) {
		return BigDecimal.valueOf(amount).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 设置物料分组毛利率值
	 * 
	 * @param groups
	 * @param amount
	 * @param excludingTaxAmount
	 * @param cost
	 * @param wtwCost
	 */
	private void setMaterialGroupMargin(MaterialGroups mgroup, double amount, double excludingTaxAmount, double cost,
			double wtwCost) {
		// 毛利（销售）=不含税金额-成本（销售）
		double grossProfit = excludingTaxAmount - cost;
		// 毛利（生产）=不含税金额-成本（生产）
		double wtwGrossProfit = excludingTaxAmount - wtwCost;
		// 毛利率（销售）
		double grossProfitMargin = calcGrossProfitMargin(excludingTaxAmount, cost);
		// 毛利率（生产）
		double wtwGrossProfitMargin = calcGrossProfitMargin(excludingTaxAmount, wtwCost);
		mgroup.setAmount(toBigDecimal(amount, 2));
		mgroup.setExcludingTaxAmount(toBigDecimal(excludingTaxAmount, 2));
		mgroup.setWtwCost(toBigDecimal(wtwCost, 2));
		mgroup.setCost(toBigDecimal(cost, 2));
		mgroup.setWtwGrossProfit(toBigDecimal(wtwGrossProfit, 2));
		mgroup.setGrossProfit(toBigDecimal(grossProfit, 2));
		mgroup.setWtwGrossProfitMargin(wtwGrossProfitMargin);
		mgroup.setGrossProfitMargin(grossProfitMargin);
	}

	/**
	 * 计算毛利率
	 * 
	 * @param amount 金额
	 * @param cost   成本
	 * @return
	 */
	private double calcGrossProfitMargin(double amount, double cost) {
		double margin = 0D;
		if (amount != 0) {
			margin = (amount - cost) / amount;
		}
		
		margin = toBigDecimal(margin, 2).doubleValue();

		return margin;
	}

}
