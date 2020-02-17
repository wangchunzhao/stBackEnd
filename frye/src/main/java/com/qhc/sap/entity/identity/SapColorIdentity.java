package com.qhc.sap.entity.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class SapColorIdentity implements Serializable{
	
	private static final int FACTOR = 35;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7675567765392676053L;

	@Column(name = "color_class", nullable = false, length = 10)
	private String colorClass;

	@Column(name = "color_code", nullable = false, length = 10)
	private String colorCode;

	public String getColorClass() {
		return colorClass;
	}

	public void setColorClass(String colorClass) {
		this.colorClass = colorClass;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	
	

}
