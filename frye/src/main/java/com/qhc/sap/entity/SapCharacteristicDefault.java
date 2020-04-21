package com.qhc.sap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sap_material_default_characteristic")
@IdClass(SapCharacteristicDefaultKey.class)
public class SapCharacteristicDefault implements Serializable{
	
	@Id
	@Column(name="sap_materials_code",length=18)
	private String materialsCode;
	
	@Id
    @Column(name="sap_characteristic_code",length=30)
	private String characteristicCode;
	
	@Id
    @Column(name="sap_characteristic_value_id",length=11)
	private String valueId;

	public String getMaterialsCode() {
		return materialsCode;
	}

	public void setMaterialsCode(String materialsCode) {
		this.materialsCode = materialsCode;
	}

	public String getCharacteristicCode() {
		return characteristicCode;
	}

	public void setCharacteristicCode(String characteristicCode) {
		this.characteristicCode = characteristicCode;
	}

	public String getValueId() {
		return valueId;
	}

	public void setValueId(String valueId) {
		this.valueId = valueId;
	}

	
	
	

}
