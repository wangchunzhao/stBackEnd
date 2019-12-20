package com.qhc.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_material_group_order")
public class MaterialGroupOrder {
	
	@Id
	@Column(name="code",columnDefinition="CHAR")
	public String code;
	
	@Column(name="name",columnDefinition="TEXT")
	public String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MaterialGroupOrder(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public MaterialGroupOrder() {
		super();
	}

}
