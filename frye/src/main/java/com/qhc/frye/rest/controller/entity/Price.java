/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

/**
 * @author wang@dxc.com
 *
 */
public class Price {
	
	private String typeCode;
	
	private String materialCode;
	
	private double price;
	
	private String lastDate;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	
	
}
