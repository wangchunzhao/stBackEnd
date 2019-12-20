package com.qhc.frye.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_city")
public class BCity {
	
	@Id
	@Column(name="code",columnDefinition="TEXT")
	public String code;
	
	@Column(name="name",columnDefinition="TEXT")
	public String name;
	
	@Column(name="b_province_code",columnDefinition="TEXT")
	public String bProvinceCode;

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

	public String getbProvinceCode() {
		return bProvinceCode;
	}

	public void setbProvinceCode(String bProvinceCode) {
		this.bProvinceCode = bProvinceCode;
	}

	public BCity(String code, String name, String bProvinceCode) {
		super();
		this.code = code;
		this.name = name;
		this.bProvinceCode = bProvinceCode;
	}

	public BCity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
