package com.qhc.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_city")
public class City {
	
	@Id
	@Column(name="code",columnDefinition="TEXT")
	public String code;
	
	@Column(name="name",columnDefinition="TEXT")
	public String name;
	
	@Column(name="province_code",columnDefinition="TEXT")
	public String provinceCode;

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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String bProvinceCode) {
		this.provinceCode = bProvinceCode;
	}

	public City(String code, String name, String bProvinceCode) {
		super();
		this.code = code;
		this.name = name;
		this.provinceCode = bProvinceCode;
	}

	public City() {
		super();
	}
	
	

}
