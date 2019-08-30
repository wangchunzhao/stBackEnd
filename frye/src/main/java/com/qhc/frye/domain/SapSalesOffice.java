package com.qhc.frye.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author lizuoshan
 *
 */
@Entity
@Table(name = "sap_sales_office")
public class SapSalesOffice implements Serializable{
	
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=32)
    private String code;
	
	@NotNull
	@Column(name="name",columnDefinition="TEXT")
    private String name;
	

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
	
	
	



}
