/**
 * 
 */
package com.qhc.sap.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name="sap_material_groups")
public class MaterialGroups {
	
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=3)
	private String code;

    @NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
    
    @Column(name="b_material_group_order_code",columnDefinition="CHAR")
	private String materialGroupOrderCode;
    
    @Column(name="isenable",columnDefinition="BIT")
	private Integer isenable;
	
	@Transient
	private BigDecimal amount;//金额
	
	@Transient
	private BigDecimal excludingTaxAmount;//不含税金额
	
	@Transient
	private BigDecimal wtwCost;//wtw成本
	
	@Transient
	private BigDecimal cost;//成本
	
	@Transient
	private BigDecimal wtwGrossProfit;//wtw毛利
	
	@Transient
	private BigDecimal grossProfit;//毛利
	
	@Transient
	private Double wtwGrossProfitMargin;//毛利率	
	
	@Transient
	private Double grossProfitMargin;//毛利率	

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

	public String getMaterialGroupOrderCode() {
		return materialGroupOrderCode;
	}

	public void setMaterialGroupOrderCode(String materialGroupOrderCode) {
		this.materialGroupOrderCode = materialGroupOrderCode;
	}

	public Integer getIsenable() {
		return isenable;
	}

	public void setIsenable(Integer isenable) {
		this.isenable = isenable;
	}

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

	public BigDecimal getWtwCost() {
		return wtwCost;
	}

	public void setWtwCost(BigDecimal wtwCost) {
		this.wtwCost = wtwCost;
	}

	public BigDecimal getWtwGrossProfit() {
		return wtwGrossProfit;
	}

	public void setWtwGrossProfit(BigDecimal wtwGrossProfit) {
		this.wtwGrossProfit = wtwGrossProfit;
	}

	public Double getWtwGrossProfitMargin() {
		return wtwGrossProfitMargin;
	}

	public void setWtwGrossProfitMargin(Double wtwGrossProfitMargin) {
		this.wtwGrossProfitMargin = wtwGrossProfitMargin;
	}
}
