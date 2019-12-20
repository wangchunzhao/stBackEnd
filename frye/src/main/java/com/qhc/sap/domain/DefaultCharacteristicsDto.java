package com.qhc.sap.domain;

public class DefaultCharacteristicsDto {
	
	private String materialCode;//物料编码    matnr 
	
	private String classCode;//类   class
	
	private String characteristic;//特征  atnam
	
	private String characterDescription;//特征描述    atbez
	
	private String characterValue;//特征值   atwrt
	
	private String characterValueDes;//特征值描述    atwtb

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getCharacterDescription() {
		return characterDescription;
	}

	public void setCharacterDescription(String characterDescription) {
		this.characterDescription = characterDescription;
	}

	public String getCharacterValue() {
		return characterValue;
	}

	public void setCharacterValue(String characterValue) {
		this.characterValue = characterValue;
	}

	public String getCharacterValueDes() {
		return characterValueDes;
	}

	public void setCharacterValueDes(String characterValueDes) {
		this.characterValueDes = characterValueDes;
	}

	
}
