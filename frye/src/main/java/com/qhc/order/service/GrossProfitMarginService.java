package com.qhc.order.service;

import java.math.BigDecimal;
import java.util.List;

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

		// 毛利率合计行
		MaterialGroups sumMaterialGroup = new MaterialGroups();
		sumMaterialGroup.setCode("sum");
		sumMaterialGroup.setName("合计");
		groups.add(sumMaterialGroup);

		if (items == null || items.size() == 0) {
			sumMaterialGroup(sumMaterialGroup, 0, 0, 0, 0, 0, 0);

			return groups;
		}

		double amount = 0;
		double excludingTaxAmount = 0;
		double cost = 0;
		double wtwCost = 0;
		double grossProfit = 0;
		double wtwGrossProfit = 0;
		for (MaterialGroups mgroup : groups) {
			String code = mgroup.getCode();
			String orderGroupCode = mgroup.getMaterialGroupOrderCode();
			// 税率
			Double taxRate = order.getTaxRate();

			switch (code) {
			case "3101": // "整机柜"
				calcItemMargin(taxRate, mgroup, items);
				break;
			case "3102": // "分体柜"
				calcItemMargin(taxRate, mgroup, items);
				break;
			case "3109": // "机组"
				calcItemMargin(taxRate, mgroup, items);
				break;
			case "3103": // "换热器和冷凝器"
				break;
			case "3104": // "侧板"
				break;
			case "1000": // "散件"
				break;
			case "3105": // "冷库"
				break;
			case "3231": // "其他项目收费"
				break;
			case "3212": // "安装费"
				break;
			case "3233": // "材料费"
				break;
			case "3235": // "电气费"
				break;
			case "3236": // "维保费"
				break;
			case "3234": // "销售运费"
				break;
			case "3237": // "不可预估费"
				break;
			case "9103": // "追加运费"
				break;
			case "9101": // "B2C"
				break;
			case "9102": // "可选项"
				break;
			case "9999": // "不可用物料类别"
				break;
			}
		}
		// 合计行
		sumMaterialGroup(sumMaterialGroup, amount, excludingTaxAmount, cost, wtwCost, grossProfit, wtwGrossProfit);

		return groups;
	}

	/**
	 * 按物料分组计算行项目毛利率
	 * 
	 * @param taxRate
	 * @param group
	 * @param items
	 */
	private void calcItemMargin(double taxRate, MaterialGroups group, List<ItemDto> items) {
		String orderGroupCode = group.getMaterialGroupOrderCode();
		String materialGroupCode = group.getCode();
		double amount = 0;
		double excludingTaxAmount = 0;
		double cost = 0;
		double wtwCost = 0;
		double grossProfit = 0;
		double grossProfitMargin = 0;
		double wtwGrossProfit = 0;
		double wtwGrossProfitMargin = 0;
		for (ItemDto item : items) {
			String itemMaterialGroupCode = item.getMaterialGroupCode();
			if (itemMaterialGroupCode.equals(materialGroupCode)) {
				// 1. 金额= sum（实卖金额合计），实卖金额=实卖价*数量，实卖价=零售价*折扣
				double saleAmount = (item.getActualPrice() + item.getOptionalActualPrice() + item.getB2cEstimatedPrice()) * item.getQuantity();
				amount += saleAmount;
				// 成本（销售）
				cost += item.getTransationPrice() * item.getQuantity();
				// 成本（生产）
				wtwCost += item.getStandardPrice() * item.getQuantity();
			}
		}

		// 2. 不含税金额=金额/(1+税率)
		excludingTaxAmount = amount / (1 + taxRate);
		// 毛利=不含税金额-成本
		grossProfit = excludingTaxAmount - cost;
		// 毛利（生产）=不含税金额-成本（生产）
		wtwGrossProfit = excludingTaxAmount - wtwCost;
		// 毛利率
		grossProfitMargin = calcGrossProfitMargin(excludingTaxAmount, cost);
		// WTW（生产）毛利率
		wtwGrossProfitMargin = calcGrossProfitMargin(excludingTaxAmount, wtwCost);

		group.setAmount(toBigDecimal(amount, 2));
		group.setExcludingTaxAmount(toBigDecimal(excludingTaxAmount, 2));
		group.setWtwCost(toBigDecimal(wtwCost, 2));
		group.setCost(toBigDecimal(cost, 2));
		group.setWtwGrossProfit(toBigDecimal(wtwGrossProfit, 2));
		group.setGrossProfit(toBigDecimal(grossProfit, 2));
		group.setWtwGrossProfitMargin(toBigDecimal(wtwGrossProfitMargin, 2).doubleValue());
		group.setGrossProfitMargin(toBigDecimal(grossProfitMargin, 2).doubleValue());
	}

	private BigDecimal toBigDecimal(double amount, int scale) {
		return BigDecimal.valueOf(amount).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 添加合计行
	 * 
	 * @param groups
	 * @param amount
	 * @param excludingTaxAmount
	 * @param cost
	 * @param wtwCost
	 * @param grossProfit
	 * @param wtwGrossProfit
	 */
	private void sumMaterialGroup(MaterialGroups group, double amount, double excludingTaxAmount, double cost,
			double wtwCost, double grossProfit, double wtwGrossProfit) {
		group.setAmount(toBigDecimal(amount, 2));
		group.setExcludingTaxAmount(toBigDecimal(excludingTaxAmount, 2));
		group.setWtwCost(toBigDecimal(wtwCost, 2));
		group.setCost(toBigDecimal(cost, 2));
		group.setWtwGrossProfit(toBigDecimal(wtwGrossProfit, 2));
		group.setGrossProfit(toBigDecimal(grossProfit, 2));
		// WTW毛利率
		double wtwGrossProfitMargin = calcGrossProfitMargin(excludingTaxAmount, wtwCost);
		// 毛利率
		double grossProfitMargin = calcGrossProfitMargin(excludingTaxAmount, cost);
		group.setWtwGrossProfitMargin(wtwGrossProfitMargin);
		group.setGrossProfitMargin(grossProfitMargin);
	}

	/**
	 * 计算毛利率
	 * 
	 * @param amount 金额
	 * @param cost   成本
	 * @return
	 */
	private Double calcGrossProfitMargin(double amount, double cost) {
		Double margin = 0D;
		if (cost != 0) {
			margin = (amount - cost) / amount;
		}

		return margin;
	}

}
