package com.qhc.frye.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "k_item_details")
public class ItemDetails implements Serializable{
	
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
	public int id;
	
	@NotNull
	@Column(name = "k_forms_id")
	public Integer kFormsId;

	
	//产品规格型号
	@NotNull
	@Column(name = "material_code")
	public String materialCode;
	
	@NotNull
	@Column(name = "material_name",columnDefinition = "TEXT")
	public String materialName;
	
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
	@Column(name = "quantity")
	public Integer quantity;
	
	//销售单价
	@NotNull
	@Column(name = "price")
	public Double price;
	
	//销售金额
	@Transient
	public Double salesAmount;
	
	//单位 
	@NotNull
	@Column(name = "measure_unit_code")
	public String measureUnitCode;
	
	@NotNull
	@Column(name = "measure_unit_name",columnDefinition = "TEXT")
	public String measureUnitName;

	@NotNull
	@Column(name = "cost")
	public Double cost;
	
	@Column(name = "b2c_cost")
	public Double b2cCost;
	
	@Column(name = "b2c_comments")
	public String b2cComments;
	
	@Column(name = "type")
	public String type;
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getkFormsId() {
		return kFormsId;
	}

	public void setkFormsId(Integer kFormsId) {
		this.kFormsId = kFormsId;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getMeasureUnitCode() {
		return measureUnitCode;
	}

	public void setMeasureUnitCode(String measureUnitCode) {
		this.measureUnitCode = measureUnitCode;
	}

	public String getMeasureUnitName() {
		return measureUnitName;
	}

	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getB2cCost() {
		return b2cCost;
	}

	public void setB2cCost(Double b2cCost) {
		this.b2cCost = b2cCost;
	}

	public String getB2cComments() {
		return b2cComments;
	}

	public void setB2cComments(String b2cComments) {
		this.b2cComments = b2cComments;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
