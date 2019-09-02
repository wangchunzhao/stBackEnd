package com.qhc.frye.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


public class SapSalesGroup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1176483521099061923L;

	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=3)
    private String code;
	
	@NotNull
	@Column(name="name",columnDefinition="TEXT")
    private String name;
	
	@NotNull
	@Column(name="sap_sales_office_code",columnDefinition="CHAR",length=4)
    private String officeCode;

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

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
