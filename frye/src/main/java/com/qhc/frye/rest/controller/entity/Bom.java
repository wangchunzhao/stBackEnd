/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

/**
 * @author wang@dxc.com
 *
 */
public class Bom {
	private String code;
	private String parentCode;
	private boolean isConfigurable;
	private double price;
	private double quantity;
	private boolean isMarked;
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
	public boolean isConfigurable() {
		return isConfigurable;
	}
	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
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
	public boolean isMarked() {
		return isMarked;
	}
	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
	
	

}
