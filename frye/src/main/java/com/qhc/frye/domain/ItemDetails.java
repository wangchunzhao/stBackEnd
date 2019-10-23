package com.qhc.frye.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "k_item_details")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ItemDetails implements Serializable{
	
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull

    @Column(name="id",columnDefinition="char",length=32)
    @GeneratedValue(generator = "jpa-uuid")
	public String id;
	
	@NotNull
	@Column(name = "k_forms_id",columnDefinition="char",length=32)
	public String kFormsId;

	
	//产品规格型号
	@NotNull
	@Column(name = "material_code")
	public String materialCode;
	
	@NotNull
	@Column(name = "material_name",columnDefinition = "TEXT")
	public String materialName;
	
	//物料专用号
//	@NotNull
//	@Column(name = "material_specific_Number")
//	public String materialSpecificNumber;
	
	//物料属性
	@NotNull
	@Column(name = "is_purchased",columnDefinition="BIT",length=1)
	public boolean materialAttribute;
	
	//合同数量
	@NotNull
	@Column(name = "quantity")
	public Double quantity;
	
	//销售单价
	
	//销售金额
	@Column(name = "sale_amount")
	public BigDecimal amount;
	
	//单位 
	@NotNull
	@Column(name = "measure_unit_code")
	public String measureUnitCode;
	
//	@NotNull
//	@Column(name = "measure_unit_name",columnDefinition = "TEXT")
//	public String measureUnitName;

	
	@Column(name = "b2c_comments",columnDefinition = "TEXT")
	public String b2cComments;
	
//	@Column(name = "type")
	@Transient
	public String type;
	
	@Column(name = "row_number")
	public Integer rowNumber;
	
//	@Column(name = "special_code")
//	public String specialCode;
//	
	@Column(name = "material_group_code")
	public String materialGroupCode;
	
	@Column(name = "material_group_name",columnDefinition = "TEXT")
	public String materialGroupName;
	
	@Column(name = "transfter_price")
	public BigDecimal transfterPrice;
	
	@Column(name = "discount")
	public Double discount;
	
	@Column(name = "item_category")
	public String itemCategory;
	
	
	@Column(name = "item_requirement_plan")
	public String itemRequirementPlan;
	
	//
	@Column(name = "freight")
	public BigDecimal freight;
	
	
	@Column(name = "standard_price")
	public BigDecimal standardPrice;
	
	
	
	
	
	

	
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

//	public String getMaterialSpecificNumber() {
//		return materialSpecificNumber;
//	}
//
//	public void setMaterialSpecificNumber(String materialSpecificNumber) {
//		this.materialSpecificNumber = materialSpecificNumber;
//	}

	public boolean isMaterialAttribute() {
		return materialAttribute;
	}

	public void setMaterialAttribute(boolean materialAttribute) {
		this.materialAttribute = materialAttribute;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
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

//	public String getMeasureUnitName() {
//		return measureUnitName;
//	}
//
//	public void setMeasureUnitName(String measureUnitName) {
//		this.measureUnitName = measureUnitName;
//	}

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

//	public String getSpecialCode() {
//		return specialCode;
//	}
//
//	public void setSpecialCode(String specialCode) {
//		this.specialCode = specialCode;
//	}

	

	public String getMaterialGroupCode() {
		return materialGroupCode;
	}

	public void setMaterialGroupCode(String materialGroupCode) {
		this.materialGroupCode = materialGroupCode;
	}

	public String getMaterialGroupName() {
		return materialGroupName;
	}

	public void setMaterialGroupName(String materialGroupName) {
		this.materialGroupName = materialGroupName;
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

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}
	
	
	
	
	
}
