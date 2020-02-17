package com.qhc.sap.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qhc.sap.entity.identity.ClassCharacterIdentity;
import com.qhc.sap.entity.identity.SapColorIdentity;

@Entity
@Table(name="sap_color_class")
public class SapColorClass {
	
	@EmbeddedId
	private SapColorIdentity cci;
	
	@Column(name="color_material_code",length=32)
	private String colorMaterialCode;
	
	@Column(name="color_description",length=64)
	private String colorDescription;

	public SapColorIdentity getCci() {
		return cci;
	}

	public void setCci(SapColorIdentity cci) {
		this.cci = cci;
	}

	public String getColorMaterialCode() {
		return colorMaterialCode;
	}

	public void setColorMaterialCode(String colorMaterialCode) {
		this.colorMaterialCode = colorMaterialCode;
	}

	public String getColorDescription() {
		return colorDescription;
	}

	public void setColorDescription(String colorDescription) {
		this.colorDescription = colorDescription;
	}
	
	

}
