package com.qhc.sap.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qhc.sap.entity.identity.SapProductClassIdentity;

@Entity
@Table(name="sap_product_class")
public class SapProductClass {
	
	@EmbeddedId
	private SapProductClassIdentity cci;
	
	@Column(name="color_class",length=10)
	private String colorClass;
	
	@Column(name="default_color",length=10)
	private String defaultColor;

	public SapProductClassIdentity getCci() {
		return cci;
	}

	public void setCci(SapProductClassIdentity cci) {
		this.cci = cci;
	}

	public String getColorClass() {
		return colorClass;
	}

	public void setColorClass(String colorClass) {
		this.colorClass = colorClass;
	}

	public String getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(String defaultColor) {
		this.defaultColor = defaultColor;
	}
	
	

}
