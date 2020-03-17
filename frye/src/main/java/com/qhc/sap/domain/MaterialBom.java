package com.qhc.sap.domain;

import java.math.BigDecimal;
import java.util.List;

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
	 */
	public void calculatePriceGap() {
		for (Bom bom : optional) {
			if (bom.isMarked()) {
				double standardPrice = bom.getPrice();
				double retailPrice = bom.getRetailPrice();
				double transferPrice = bom.getTransferPrice();
				
				this.standardPrice += standardPrice * bom.getQuantity();
				this.retailPrice += retailPrice * bom.getQuantity();
				this.transferPrice += transferPrice * bom.getQuantity();
			}
		}

		for (Bom bom : standard) {
			if (bom.isMarked()) {
				double standardPrice = bom.getPrice();
				double retailPrice = bom.getRetailPrice();
				double transferPrice = bom.getTransferPrice();
				
				this.standardPrice += standardPrice * bom.getQuantity();
				this.retailPrice += retailPrice * bom.getQuantity();
				this.transferPrice += transferPrice * bom.getQuantity();
			}
		}
		
		standardPrice = BigDecimal.valueOf(standardPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		retailPrice = BigDecimal.valueOf(retailPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		transferPrice = BigDecimal.valueOf(transferPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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
