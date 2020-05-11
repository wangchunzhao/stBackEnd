package com.qhc.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.order.domain.ItemDto;
import com.qhc.order.domain.OrderDto;
import com.qhc.sap.dao.SapMaterialGroupsRepository;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.system.dao.AreaRepository;
import com.qhc.system.entity.Area;
import com.qhc.system.service.SettingsService;

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
	
	@Autowired
	private SettingsService settingsService;
	
	@Autowired
	private AreaRepository areaRepository;
	
    // 退货行项目分类
    private static List<String> returnCategorys = Arrays.asList("ZHR1", "ZHR2", "ZHR3", "ZHR4");

	public List<MaterialGroups> calculate(OrderDto order) {
		String stOrderType = order.getStOrderType();
		double exchange = order.getCurrencyExchange();
		// 查询所有物料类型sap_material_group isenable != 0
		List<MaterialGroups> groups = materialGroupsRepository.findByIsenableNotOrderByCode(0);
		List<ItemDto> items = order.getItems();
		String transferType = StringUtils.trimToEmpty(order.getTransferType());
		String saleType = StringUtils.trimToEmpty(order.getSaleType());
		// 自提运费为0
        if (transferType.equals("02")) {
            order.setFreight(0);
        }
		double freight = order.getFreight();
		// 计算运费
		// 自提不计算运费
		if (!transferType.equals("02")
		    // 销售类型为出口也不计算运费
		    && !saleType.equals("20") ) {
		    // 报价单不计算运费
//		    && !stOrderType.equals("3") 
		    // 备货单不计算运费
//		    && !stOrderType.equals("5")) {
          // 备货单省市区默认山东-青岛-黄岛区，发货方式默认自提
//          if (stOrderType.equals("5")) {
//            items.forEach(e -> {
//              e.setProvinceCode("20");
//              e.setCityCode("20.11");
//              e.setDistrictCode("20.11.13");
//            });
//          }
	//		•	预估运费 = Volume * ZCTP* + ZCTF*
	//				o	Case 1: Volume <=20m3, corresponding conditions were ‘ZCTP1/ZCTF1’
	//				o	Case 2: Volume >20<50m3, corresponding conditions were ‘ZCTP2/ZCTF2’
	//				o	Case 3: Volume>=50m3, corresponding conditions were ‘ZCTP3/ZCTF3’
	//		ZCTP = 客户单价
	//		ZCTF	 = 客户送货费
			freight = calculateFreight(items);
			order.setFreight(freight);
		}
		// 运费转换为凭证货币
		if (saleType.equals("20")) {
			// 出口外销运费手工填写，为凭证货币不需要转换
		} else {
			freight = freight / exchange; // 转换为凭证货币，用于毛利率计算
		}
		
		int warranty = order.getWarranty();
		double withholdRatio = Double.valueOf(settingsService.findByCode("withhold_ratio").getsValue()).doubleValue();
		double installFee = ObjectUtils.defaultIfNull(order.getInstallFee(), 0d) / exchange; // 转换为凭证货币
		double materialFee = ObjectUtils.defaultIfNull(order.getMaterialFee(), 0d) / exchange; // 转换为凭证货币
		double electricalFee = ObjectUtils.defaultIfNull(order.getElectricalFee(), 0d) / exchange; // 转换为凭证货币
		double maintenanceFee = ObjectUtils.defaultIfNull(order.getMaintenanceFee(), 0d) / exchange; // 转换为凭证货币
		double additionalFreight = ObjectUtils.defaultIfNull(order.getAdditionalFreight(), 0d) / exchange; // 转换为凭证货币

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
//			String orderGroupCode = mgroup.getMaterialGroupOrderCode();
			switch (code) {
			case "3101": // "整机柜"
			case "3102": // "分体柜"
			case "3109": // "机组"
			case "3103": // "换热器和冷凝器"
			case "3104": // "侧板"
			case "1000": // "散件"
				calcItemMargin(mgroup, order);
				break;
			case "3105": // "冷库"
				calcItemMargin(mgroup, order);
				break;
			case "3231": // "其他项目收费"
				// 收入为手工输入
				final List<String> l = new ArrayList<>();
				items.forEach(i -> { 
					if (i.getMaterialGroupCode().equals(code)) {
						l.add("1"); 
					}
				});
				if (l.size() > 0) {
					calcItemMargin(mgroup, order);
					double otherAmount = mgroup.getAmount().doubleValue();
					double otherExcludingTaxAmount = mgroup.getExcludingTaxAmount().doubleValue();
					double otherCost = 1 / exchange; // 转换为凭证货币
					// 1元，固定值
					setMaterialGroupMargin(mgroup, otherAmount, otherExcludingTaxAmount, otherCost, otherCost);
				} else {
					setMaterialGroupMargin(mgroup, 0, 0, 0, 0);
				}
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
				double b2cEstimatedAmount = 0d;
				double b2cEstimatedExcludingTaxAmount = 0d;
				double b2cEstimatedCost = 0d;
				for (ItemDto item : items) {
					b2cEstimatedAmount += item.getB2cEstimatedPrice() * item.getQuantity();
					b2cEstimatedCost += item.getB2cEstimatedCost() * item.getQuantity();
				}
				b2cEstimatedAmount /= exchange; // 转换为凭证货币
				b2cEstimatedExcludingTaxAmount = b2cEstimatedAmount / (1 + order.getTaxRate()) / exchange; // 转换为凭证货币
				b2cEstimatedCost /=  exchange; // 转换为凭证货币
				setMaterialGroupMargin(mgroup, b2cEstimatedAmount, b2cEstimatedExcludingTaxAmount, b2cEstimatedCost, b2cEstimatedCost);
				break;
			case "9102": // "可选项"
			  calcItemOptionalMargin(mgroup, order);
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
		
		// 预提备件费=总收入金额（不含税）*保修年限*系数（预提比率0.0023 或者ZH14）
		MaterialGroups withholdRatioMaterialGroup = new MaterialGroups();
		withholdRatioMaterialGroup.setCode("beiJianFei");
		withholdRatioMaterialGroup.setName("预提备件费");
		double withholdRatioFee = excludingTaxAmount * warranty  * withholdRatio;
		setMaterialGroupMargin(withholdRatioMaterialGroup, 0, 0, withholdRatioFee, withholdRatioFee);
		groups.add(withholdRatioMaterialGroup);
		cost += withholdRatioFee;
		wtwCost += withholdRatioFee;
		
		// 合计行
		setMaterialGroupMargin(sumMaterialGroup, amount, excludingTaxAmount, cost, wtwCost);
		groups.add(sumMaterialGroup);

		return groups;
	}

	/**
	 * 运费计算
	 * 
	 * @param items
	 * @return
	 */
	private double calculateFreight(List<ItemDto> items) {
		//		•	预估运费 = Volume * ZCTP* + ZCTF*
		//				o	Case 1: Volume <=20m3, corresponding conditions were ‘ZCTP1/ZCTF1’
		//				o	Case 2: Volume >20<50m3, corresponding conditions were ‘ZCTP2/ZCTF2’
		//				o	Case 3: Volume>=50m3, corresponding conditions were ‘ZCTP3/ZCTF3’
		//		ZCTP = 客户单价
		//		ZCTF	 = 客户送货费
		Map<String, Double> freightMap = new HashMap<>();
		double freight = 0;
		for(ItemDto item : items) {
			String districtCode = item.getDistrictCode();
			if (StringUtils.isEmpty(districtCode)) {
				throw new RuntimeException("地址的省市区不能为空！");
			}
			double volumn = item.getQuantity() * item.getVolumeCube();
			volumn += ObjectUtils.defaultIfNull(freightMap.get(districtCode), 0).doubleValue();
			freightMap.put(districtCode, volumn);
		}
		for (Map.Entry<String, Double> freightEntity : freightMap.entrySet()) {
			String districtCode = freightEntity.getKey();
			Double volumn = freightEntity.getValue();
			Area area = areaRepository.findById(districtCode).get();
			if (area == null) {
				continue;
			}
			double zctp = 0, zctf = 0;
			if (volumn <= 20) {
				zctp = area.getPrice6();
				zctf = area.getPrice7();
			} else if (volumn > 20 && volumn < 50) {
				zctp = area.getPrice8();
				zctf = area.getPrice9();
			} else {
				zctp = area.getPrice10();
				zctf = area.getPrice11();
			}
			
			freight += volumn * zctp + zctf;
		}
		return freight;
	}

	/**
	 * 按物料分组计算行项目毛利率
	 * 
	 * @param taxRate
	 * @param group
	 * @param items
	 */
	private void calcItemMargin(MaterialGroups group, OrderDto order) {
		double exchange = order.getCurrencyExchange();
		List<ItemDto> items = order.getItems();
		double taxRate = order.getTaxRate();
//		String orderGroupCode = group.getMaterialGroupOrderCode();
		String materialGroupCode = group.getCode();
		double amount = 0;
		double excludingTaxAmount = 0;
		double cost = 0;
		double wtwCost = 0;
		for (ItemDto item : items) {
			String itemMaterialGroupCode = item.getMaterialGroupCode();
			String status = item.getItemStatus();
			// 取消状态的行项目不在累计范围
			if (status.equals("Z2")) {
			  continue;
			}
			if (itemMaterialGroupCode.equals(materialGroupCode)) {
			  // 退货，减去
			  double flag = returnCategorys.contains(item.getItemCategory()) ? -1 : 1;
                // 1. 金额= sum（实卖金额合计），实卖金额=实卖价*数量，实卖价=零售价*折扣+可选项实卖价（差价）+b2c预估价
                // amount += ( item.getActualPrice() + ObjectUtils.defaultIfNull(item.getOptionalActualPrice(), 0D) +
                // item.getB2cEstimatedPrice() ) * item.getQuantity();
                // 1. 金额= sum（实卖金额合计），实卖金额=实卖价*数量，实卖价=零售价*折扣+可选项实卖价（差价）
//                amount += (item.getActualPrice() + item.getOptionalActualPrice()) * item.getQuantity();
                // 1. 金额= sum（实卖金额合计），实卖金额=实卖价*数量，实卖价=零售价*折扣
                amount += flag * (item.getActualPrice()) * item.getQuantity();
                // 成本（销售）
                cost += flag * item.getTransactionPrice() * item.getQuantity();
                // 成本（生产）
                wtwCost += flag * item.getStandardPrice() * item.getQuantity();
			}
		}

		// 2. 不含税金额=金额/(1+税率)
		excludingTaxAmount = amount / (1 + taxRate);
		
		amount = amount / exchange; // 转换为凭证货币
		excludingTaxAmount = excludingTaxAmount / exchange;  // 转换为凭证货币
		cost = cost / exchange;  // 转换为凭证货币
		wtwCost = wtwCost / exchange;  // 转换为凭证货币

		setMaterialGroupMargin(group, amount, excludingTaxAmount, cost, wtwCost);
	}

    /**
     * 计算行项目可选项毛利率
     * 
     * @param taxRate
     * @param group
     * @param items
     */
    private void calcItemOptionalMargin(MaterialGroups group, OrderDto order) {
        double exchange = order.getCurrencyExchange();
        List<ItemDto> items = order.getItems();
        double taxRate = order.getTaxRate();
        double amount = 0;
        double excludingTaxAmount = 0;
        double cost = 0;
        double wtwCost = 0;
        for (ItemDto item : items) {
            String status = item.getItemStatus();
            // 取消状态的行项目不在累计范围
            if (status.equals("Z2")) {
              continue;
            }
            // 退货，减去
            double flag = returnCategorys.contains(item.getItemCategory()) ? -1 : 1;
            // 1. 金额= sum（可选项实卖金额合计），可选项实卖金额=可选项实卖价*数量，可选项实卖价=可选项零售价*折扣
            amount += flag * (item.getOptionalActualPrice()) * item.getQuantity();
            // 成本（销售）
            cost += flag * item.getOptionalTransactionPrice() * item.getQuantity();
            // 成本（生产）
            wtwCost += flag * ObjectUtils.defaultIfNull(item.getOptionalStandardPrice(), 0D) * item.getQuantity();
        }

        // 2. 不含税金额=金额/(1+税率)
        excludingTaxAmount = amount / (1 + taxRate);
        
        amount = amount / exchange; // 转换为凭证货币
        excludingTaxAmount = excludingTaxAmount / exchange;  // 转换为凭证货币
        cost = cost / exchange;  // 转换为凭证货币
        wtwCost = wtwCost / exchange;  // 转换为凭证货币

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
		mgroup.setWtwGrossProfit(toBigDecimal(wtwGrossProfit, 4));
		mgroup.setGrossProfit(toBigDecimal(grossProfit, 4));
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
