package com.qhc.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sap_sales_group")
public class SapSalesGroup implements Serializable {
	

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
	
	@Transient
	private BigDecimal amount;//金额
	
	@Transient
	private BigDecimal excludingTaxAmount;//不含税金额
	
	@Transient
	private BigDecimal cost;//成本
	
	@Transient
	private BigDecimal grossProfit;//毛利
	
	@Transient
	private Double grossProfitMargin;//毛利率	
	

	
	
	
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getExcludingTaxAmount() {
		return excludingTaxAmount;
	}

	public void setExcludingTaxAmount(BigDecimal excludingTaxAmount) {
		this.excludingTaxAmount = excludingTaxAmount;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public Double getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(Double grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}

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
	
	@Override
	public boolean equals(Object obj) {
		SapSalesGroup sg = (SapSalesGroup)obj;
		if(sg.code.equals(this.getCode()))
			return true;
		return false;
	}
	
	

}
