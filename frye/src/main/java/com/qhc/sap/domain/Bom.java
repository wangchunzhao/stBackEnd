package com.qhc.sap.domain;

import java.io.Serializable;

/**
 * @author Walker
 *
 */
public class Bom implements Serializable {
	private static final long serialVersionUID = 5568458921204355045L;
	
	// 物料编码
	private String code;
	// 物料父级物料编码
	private String parentCode;
	// 物料标准价
	private double price;
	// 物料数量
	private double quantity;
	// 可配置
	private boolean configurable = false;
	// 变更标记
	private boolean marked = false;
	
	// 物料实卖价
	private Double retailPrice = null;
	// 物料转移价
	private Double transferPrice = null;
	// 物料年采价
	private Double annualPrice = null;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public boolean isConfigurable() {
		return configurable;
	}

	public void setConfigurable(boolean configurable) {
		this.configurable = configurable;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(Double transferPrice) {
		this.transferPrice = transferPrice;
	}

	public Double getAnnualPrice() {
		return annualPrice;
	}

	public void setAnnualPrice(Double annualPrice) {
		this.annualPrice = annualPrice;
	}

	@Override
	public String toString() {
		return "Bom [code=" + code + ", parentCode=" + parentCode + ", price=" + price + ", quantity=" + quantity
				+ ", configurable=" + configurable + ", marked=" + marked + ", retailPrice=" + retailPrice
				+ ", transferPrice=" + transferPrice + ", annualPrice=" + annualPrice + "]";
	}
}
