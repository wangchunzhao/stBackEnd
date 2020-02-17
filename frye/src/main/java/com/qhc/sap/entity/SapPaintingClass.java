package com.qhc.sap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sap_painting_class")
public class SapPaintingClass {
	
	@Id
    @NotNull
    @Column(name="painting_class",length=10)
	private String paintingClass;
	
	@NotNull
    @Column(name="painting_parts",length=64)
	private String paintingParts;

	public String getPaintingClass() {
		return paintingClass;
	}

	public void setPaintingClass(String paintingClass) {
		this.paintingClass = paintingClass;
	}

	public String getPaintingParts() {
		return paintingParts;
	}

	public void setPaintingParts(String paintingParts) {
		this.paintingParts = paintingParts;
	}
	
	

}
