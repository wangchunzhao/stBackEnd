package com.qhc.sap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sap_material_product_class")
public class SapMaterialProductClass {
	
	@Id
    @NotNull
    @Column(name="material_code",length=18)
	private String materialCode;
	
	@NotNull
    @Column(name="product_class",length=10)
	private String productClass;

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	
}
