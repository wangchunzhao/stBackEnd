package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sap_material_default_characteristic")
public class DCharacteristicDefault {
	
	@Id
    @NotNull
    @Column(name="sap_materials_code",length=18)
	private String materialsCode;
	
	
	@NotNull
    @Column(name="sap_characteristic_code",length=30)
	private String characteristicCode;
	
	@NotNull
    @Column(name="sap_characteristic_value_id",length=11)
	private int valueId;

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

	public int getValueId() {
		return valueId;
	}

	public void setValueId(int valueId) {
		this.valueId = valueId;
	}
	
	

}
