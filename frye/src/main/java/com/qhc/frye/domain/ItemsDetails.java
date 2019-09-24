package com.qhc.frye.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "k_item_details")
public class ItemsDetails implements Serializable{
	
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
	public int id;
	
	//产品规格型号
	@NotNull
	@Column(name = "product_specification")
	public String productSpecification;
	
	//物料专用号
	@NotNull
	@Column(name = "material_specific_Number")
	public String materialSpecificNumber;
	
	//物料属性
	@NotNull
	@Column(name = "material_attribute")
	public String materialAttribute;
	
	//合同数量
	@NotNull
	@Column(name = "contract_num")
	public Integer contractNum;
	
	//销售单价
	@NotNull
	@Column(name = "sales_price")
	public BigDecimal salesPrice;
	
	//销售金额
	@NotNull
	@Column(name = "sales_amount")
	public BigDecimal salesAmount;
	
	//单位 
	@NotNull
	@Column(name = "unit")
	public String unit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductSpecification() {
		return productSpecification;
	}

	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}

	public String getMaterialSpecificNumber() {
		return materialSpecificNumber;
	}

	public void setMaterialSpecificNumber(String materialSpecificNumber) {
		this.materialSpecificNumber = materialSpecificNumber;
	}

	public String getMaterialAttribute() {
		return materialAttribute;
	}

	public void setMaterialAttribute(String materialAttribute) {
		this.materialAttribute = materialAttribute;
	}

	public Integer getContractNum() {
		return contractNum;
	}

	public void setContractNum(Integer contractNum) {
		this.contractNum = contractNum;
	}

	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	public BigDecimal getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}



}
