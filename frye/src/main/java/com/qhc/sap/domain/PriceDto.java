/**
 * 
 */
package com.qhc.sap.domain;

import java.io.Serializable;

/**
 * @author wang@dxc.com
 *
 */
public class PriceDto implements Serializable{
	
	public final static String PRICE_CODE = "59870645008146f9938f7e8718031779";
	public final static String PRICE_CHANGE_CODE = "59870645008146f9938f7e8718031781";
	public final static String PRICEA_CODE = "59870645008146f9938f7e8718031780";
	public final static String PRICEA_CHANGE_CODE = "59870645008146f9938f7e8718031782";
	
	private double price;
	private String type;
	private String materialCode;
	private String industryCode;
	private String lastDate;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	
	
	
}
