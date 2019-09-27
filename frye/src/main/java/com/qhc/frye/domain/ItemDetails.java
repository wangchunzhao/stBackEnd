package com.qhc.frye.domain;

import java.io.Serializable;
import java.math.BigDecimal;

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
    @GeneratedValue(generator = "jpa-uuid")
	@Column(name="id",columnDefinition="char")
	public String id;
	
	@NotNull
	@Column(name = "k_forms_id",columnDefinition ="char")
	public String kFormsId;

	
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
	
	//销售金额
	@Column(name = "amount")
	public BigDecimal amount;
	
	//单位 
	@NotNull
	@Column(name = "measure_unit_code")
	public String measureUnitCode;
	
	@NotNull
	@Column(name = "measure_unit_name",columnDefinition = "TEXT")
	public String measureUnitName;

	
	@Column(name = "b2c_comments",columnDefinition = "TEXT")
	public String b2cComments;
	
//	@Column(name = "type")
	@Transient
	public String type;
	
	@Column(name = "row_number")
	public Integer rowNumber;
	
	@Column(name = "special_code")
	public String specialCode;
	
	@Column(name = "material_property_code")
	public String materialPropertyCode;
	
	@Column(name = "material_property_name",columnDefinition = "TEXT")
	public String materialPropertyName;
	
	@Column(name = "transfter_price")
	public BigDecimal transfterPrice;
	
	@Column(name = "discount")
	public Double discount;
	
	@Column(name = "item_category")
	public String itemCategory;
	
	
	@Column(name = "item_requirement_plan")
	public String itemRequirementPlan;
	

	
	
	
	

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getkFormsId() {
		return kFormsId;
	}

	public void setkFormsId(String kFormsId) {
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getSpecialCode() {
		return specialCode;
	}

	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}

	public String getMaterialPropertyCode() {
		return materialPropertyCode;
	}

	public void setMaterialPropertyCode(String materialPropertyCode) {
		this.materialPropertyCode = materialPropertyCode;
	}

	public String getMaterialPropertyName() {
		return materialPropertyName;
	}

	public void setMaterialPropertyName(String materialPropertyName) {
		this.materialPropertyName = materialPropertyName;
	}

	public BigDecimal getTransfterPrice() {
		return transfterPrice;
	}

	public void setTransfterPrice(BigDecimal transfterPrice) {
		this.transfterPrice = transfterPrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemRequirementPlan() {
		return itemRequirementPlan;
	}

	public void setItemRequirementPlan(String itemRequirementPlan) {
		this.itemRequirementPlan = itemRequirementPlan;
	}
	
	
	
	
	
}
