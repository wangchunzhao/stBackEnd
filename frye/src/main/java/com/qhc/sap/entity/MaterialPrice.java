/**
 * 
 */
package com.qhc.sap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name="sap_materials_price")
@IdClass(MaterialPriceKey.class)
public class MaterialPrice  implements Serializable{
	
	@Id
	@NotNull
	@Column(name="sap_price_type_code",columnDefinition ="CHAR",length=4)
	private String type;
	
	@Id
	@NotNull      
	@Column(name="sap_materials_code",length=18)
	private String materialCode;
	
	@Id
	@NotNull      
	@Column(name="sap_industry_code",length=4)
	private String industryCode;
	
	@NotNull
	@Column(name="price",columnDefinition="DECIMAL",precision = 13 ,scale=2)
	private double price;
	
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
	
	
}
