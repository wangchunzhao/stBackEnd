package com.qhc.sap.entity.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SapProductClassIdentity implements Serializable{
	
	@Column(name = "product_class", nullable = false, length = 10)
	private String productClass;

	@Column(name = "painting_class", nullable = false, length = 10)
	private String paintingCode;

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getPaintingCode() {
		return paintingCode;
	}

	public void setPaintingCode(String paintingCode) {
		this.paintingCode = paintingCode;
	}
	
	

}
