package com.qhc.sap.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import com.qhc.system.entity.Settings;
import com.qhc.system.service.SettingsService;
import io.netty.util.internal.ObjectUtil;

/**
 * @author Walker
 *
 */
public class MaterialBom {
	private List<Bom> standard;
	private List<Bom> optional;
	
	private double standardPrice = 0;
	private double retailPrice = 0;
	private double transferPrice = 0;
    
   /**
    * 计算差异物料价格
    * @param transferRate 转移价比率
    */
    public void calculatePriceGap(double transferRate) {
		for (Bom bom : optional) {
			if (bom.isMarked()) {
				double standardPrice = bom.getPrice();
//				double retailPrice = getPrice(bom);
//				double transferPrice = bom.getTransferPrice();
				
				this.standardPrice += standardPrice * bom.getQuantity();
//				this.retailPrice += retailPrice * bom.getQuantity();
//				this.transferPrice += transferPrice * bom.getQuantity();
			}
		}

		for (Bom bom : standard) {
			if (bom.isMarked()) {
				double standardPrice = bom.getPrice();
//				double retailPrice = getPrice(bom);
//				double transferPrice = bom.getTransferPrice();
				
				this.standardPrice -= standardPrice * bom.getQuantity();
//				this.retailPrice -= retailPrice * bom.getQuantity();
//				this.transferPrice -= transferPrice * bom.getQuantity();
			}
		}
		
		// 转移价差 = 标准价差 * 转移价比率
		transferPrice = standardPrice * transferRate;
		
		standardPrice = BigDecimal.valueOf(standardPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		retailPrice = BigDecimal.valueOf(retailPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		transferPrice = BigDecimal.valueOf(transferPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	private double getPrice(Bom bom) {
	  double price = ObjectUtils.defaultIfNull(bom.getRetailPrice(), 0d);
	  double annualPrice = ObjectUtils.defaultIfNull(bom.getAnnualPrice(), 0d);
	  if (annualPrice > 0) {
	    price = annualPrice;
	  }
	  return price;
	}
	
	public List<Bom> getStandard() {
		return standard;
	}

	public void setStandard(List<Bom> standard) {
		this.standard = standard;
	}

	public List<Bom> getOptional() {
		return optional;
	}

	public void setOptional(List<Bom> optional) {
		this.optional = optional;
	}

	public double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(double transferPrice) {
		this.transferPrice = transferPrice;
	}

}
