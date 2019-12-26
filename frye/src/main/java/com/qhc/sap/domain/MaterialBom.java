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
	
	private double price = 0;
	private double transferPrice = 0;

	/**
	 * 计算差异物料价格
	 */
	public void calculatePriceGap() {
		for (Bom bom : optional) {
			if (bom.isMarked()) {
				double price = getMaterialPrice(bom);
				double transferPrice = bom.getTransferPrice();
				
				this.price += price * bom.getQuantity();
				this.transferPrice += transferPrice;
			}
		}

		for (Bom bom : standard) {
			if (bom.isMarked()) {
				double price = getMaterialPrice(bom);
				double transferPrice = bom.getTransferPrice();
				
				this.price -= price * bom.getQuantity();
				this.transferPrice -= transferPrice;
			}
		}
		
		price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		transferPrice = BigDecimal.valueOf(transferPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * TODO 取bom物料的价格，可能是零售价，也可能是年采价，待定标准价
	 * 
	 * @param bom
	 * @return
	 */
	private double getMaterialPrice(Bom bom) {
		return bom.getPrice();
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(double transferPrice) {
		this.transferPrice = transferPrice;
	}

}
