/**
 * 
 */
package com.qhc.sap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wang@dxc.com
 *
 */
//@Entity
//@Table(name = "sap_material_info_view")
public class MaterialView implements Serializable {
		
	@Column(name = "code",columnDefinition ="CHAR")
	private String code;
	
	@Column(name = "description",columnDefinition ="TEXT")
	private String description;
	@Id 
	@Column(name = "price_type_code",columnDefinition ="CHAR")
	private String priceTypeCode;
	
	@Column(name = "price_type_name",columnDefinition ="TEXT")
	private String priceTypeName;
	
	
	@Column(name = "price",columnDefinition ="DECIMAL")
	private double price;
    
	@Column(name = "is_configurable",columnDefinition ="BIT")
	private boolean isConfigurable;
	
	@Column(name = "is_purchased",columnDefinition ="BIT")
	private boolean isPurchased;
	
	@Column(name = "stand_price",columnDefinition ="DECIMAL")
	private double standPrice;
	
	@Column(name ="sap_unit_of_measurement_code")
	private String unitCode;
	
	@Column(name ="unit_name",columnDefinition ="TEXT")
	private String unitName;
	
	@Column(name ="sap_material_groups_code",columnDefinition ="CHAR",length=4)
	private String groupCode;
	
	@Column(name ="group_name",columnDefinition ="TEXT")
	private String groupName;
	
	@Column(name ="sap_clazz_code",columnDefinition ="CHAR")
	private String clazzCode;

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

	public String getPriceTypeCode() {
		return priceTypeCode;
	}

	public void setPriceTypeCode(String priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getClazzCode() {
		return clazzCode;
	}

	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}

	
}
