package com.qhc.sap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


public class DCharacteristicDefaultKey implements Serializable{
	
	private String materialsCode;
	
	private String characteristicCode;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((characteristicCode == null) ? 0 : characteristicCode.hashCode());
		result = prime * result + ((materialsCode == null) ? 0 : materialsCode.hashCode());
		result = prime * result + valueId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DCharacteristicDefaultKey other = (DCharacteristicDefaultKey) obj;
		if (characteristicCode == null) {
			if (other.characteristicCode != null)
				return false;
		} else if (!characteristicCode.equals(other.characteristicCode))
			return false;
		if (materialsCode == null) {
			if (other.materialsCode != null)
				return false;
		} else if (!materialsCode.equals(other.materialsCode))
			return false;
		if (valueId != other.valueId)
			return false;
		return true;
	}

	public DCharacteristicDefaultKey(String materialsCode, String characteristicCode, int valueId) {
		super();
		this.materialsCode = materialsCode;
		this.characteristicCode = characteristicCode;
		this.valueId = valueId;
	}

	public DCharacteristicDefaultKey() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
