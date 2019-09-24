package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_area")
public class BArea {
	
	@Id
	@Column(name="code",columnDefinition="TEXT")
	public String code;
	
	@Column(name="name",columnDefinition="TEXT")
	public String name;
	
	@Column(name="b_city_code",columnDefinition="TEXT")
	public String bCityCode;
	
	@Column(name="price",columnDefinition="decimal")
	public Double price;

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

	public String getbCityCode() {
		return bCityCode;
	}

	public void setbCityCode(String bCityCode) {
		this.bCityCode = bCityCode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public BArea(String code, String name, String bCityCode, Double price) {
		super();
		this.code = code;
		this.name = name;
		this.bCityCode = bCityCode;
		this.price = price;
	}

	public BArea() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
