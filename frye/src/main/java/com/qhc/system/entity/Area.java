package com.qhc.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_area")
public class Area {
	
	@Id
	@Column(name="code",columnDefinition="TEXT")
	public String code;
	
	@Column(name="name",columnDefinition="TEXT")
	public String name;
	
	@Column(name="city_code",columnDefinition="TEXT")
	public String bCityCode;
	
	@Column(name="price",columnDefinition="decimal")
	public Double price;
	
	@Column(name="price1",columnDefinition="decimal")
	public Double price1;
	
	@Column(name="price2",columnDefinition="decimal")
	public Double price2;
	
	@Column(name="price3",columnDefinition="decimal")
	public Double price3;
	
	@Column(name="price4",columnDefinition="decimal")
	public Double price4;
	
	@Column(name="price5",columnDefinition="decimal")
	public Double price5;
	
	@Column(name="price6",columnDefinition="decimal")
	public Double price6;
	
	@Column(name="price7",columnDefinition="decimal")
	public Double price7;
	
	@Column(name="price8",columnDefinition="decimal")
	public Double price8;
	
	@Column(name="price9",columnDefinition="decimal")
	public Double price9;
	
	@Column(name="price10",columnDefinition="decimal")
	public Double price10;
	
	@Column(name="price11",columnDefinition="decimal")
	public Double price11;

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

	public Double getPrice1() {
		return price1;
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}

	public Double getPrice2() {
		return price2;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

	public Double getPrice3() {
		return price3;
	}

	public void setPrice3(Double price3) {
		this.price3 = price3;
	}

	public Double getPrice4() {
		return price4;
	}

	public void setPrice4(Double price4) {
		this.price4 = price4;
	}

	public Double getPrice5() {
		return price5;
	}

	public void setPrice5(Double price5) {
		this.price5 = price5;
	}

	public Double getPrice6() {
		return price6;
	}

	public void setPrice6(Double price6) {
		this.price6 = price6;
	}

	public Double getPrice7() {
		return price7;
	}

	public void setPrice7(Double price7) {
		this.price7 = price7;
	}

	public Double getPrice8() {
		return price8;
	}

	public void setPrice8(Double price8) {
		this.price8 = price8;
	}

	public Double getPrice9() {
		return price9;
	}

	public void setPrice9(Double price9) {
		this.price9 = price9;
	}

	public Double getPrice10() {
		return price10;
	}

	public void setPrice10(Double price10) {
		this.price10 = price10;
	}

	public Double getPrice11() {
		return price11;
	}

	public void setPrice11(Double price11) {
		this.price11 = price11;
	}

	public Area(String code, String name, String bCityCode, Double price, Double price1, Double price2, Double price3,
			Double price4, Double price5, Double price6, Double price7, Double price8, Double price9, Double price10,
			Double price11) {
		super();
		this.code = code;
		this.name = name;
		this.bCityCode = bCityCode;
		this.price = price;
		this.price1 = price1;
		this.price2 = price2;
		this.price3 = price3;
		this.price4 = price4;
		this.price5 = price5;
		this.price6 = price6;
		this.price7 = price7;
		this.price8 = price8;
		this.price9 = price9;
		this.price10 = price10;
		this.price11 = price11;
	}

	public Area() {
		super();
	}

	

}
