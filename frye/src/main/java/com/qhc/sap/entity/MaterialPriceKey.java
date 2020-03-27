package com.qhc.sap.entity;

import java.io.Serializable;

public class MaterialPriceKey implements Serializable{
	
	private String type;
	
	private String materialCode;
	
	private String industryCode;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((industryCode == null) ? 0 : industryCode.hashCode());
		result = prime * result + ((materialCode == null) ? 0 : materialCode.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MaterialPriceKey other = (MaterialPriceKey) obj;
		if (industryCode == null) {
			if (other.industryCode != null)
				return false;
		} else if (!industryCode.equals(other.industryCode))
			return false;
		if (materialCode == null) {
			if (other.materialCode != null)
				return false;
		} else if (!materialCode.equals(other.materialCode))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public MaterialPriceKey(String type, String materialCode, String industryCode) {
		super();
		this.type = type;
		this.materialCode = materialCode;
		this.industryCode = industryCode;
	}

	public MaterialPriceKey() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
