package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_city_freight")
public class BCityFreight {
	
	@Column(name="province_name",columnDefinition="TEXT")
	public String provinceName;
	
	@Column(name="province_code",columnDefinition="TEXT")
	public String provinceCode;
	
	@Column(name="city_name",columnDefinition="TEXT")
	public String cityName;
	
	@Column(name="city_code",columnDefinition="TEXT")
	public String cityCode;
	
	@Column(name="county_name",columnDefinition="TEXT")
	public String countyName;
	
	@Id
	@Column(name="county_code",columnDefinition="TEXT")
	public String countyCode;
	
	@Column(name="price",columnDefinition="decimal")
	public Double price;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public BCityFreight(String provinceName, String provinceCode, String cityName, String cityCode, String countyName,
			String countyCode) {
		super();
		this.provinceName = provinceName;
		this.provinceCode = provinceCode;
		this.cityName = cityName;
		this.cityCode = cityCode;
		this.countyName = countyName;
		this.countyCode = countyCode;
	}

	public BCityFreight() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
