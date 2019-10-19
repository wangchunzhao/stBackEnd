/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wang@dxc.com
 *
 */
public class Material implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4087877900159424776L;
	
	public final static String MATERIAL_CODE = "59870645008146f9938f7e8718031778";
	
	private String code;   //matnr  物料编码
	private String description;  //maktx 物料描述
	private boolean isConfigurable;//kzkfg 是否可配置物料
	private boolean isPurchased;//物料属性
	private double standPrice;//verpr  标准价格
	private Date optTime;//laeda 
	private String measurementUnit;//meins  计量单位
	private String materialGroups;//matkl 物料分组
	private String clazz;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isConfigurable() {
		return isConfigurable;
	}
	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}
	public boolean isPurchased() {
		return isPurchased;
	}
	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}
	public double getStandPrice() {
		return standPrice;
	}
	public void setStandPrice(double standPrice) {
		this.standPrice = standPrice;
	}
	public Date getOptTime() {
		return optTime;
	}
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getMaterialGroups() {
		return materialGroups;
	}
	public void setMaterialGroups(String materialGroups) {
		this.materialGroups = materialGroups;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

          