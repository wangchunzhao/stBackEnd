package com.qhc.frye.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
	
	//体积
	@NotNull
	@Column(name = "volume_cube")
	public double volumeCube;
	
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
	
	@NotNull
	@Column(name = "special_need",columnDefinition = "TEXT")
	public String specialNeed;

	
	@Column(name = "b2c_comments",columnDefinition = "TEXT")
	public String b2cComments;
	
//	@Column(name = "type")
	@Transient
	public String type;
	
	@Column(name = "row_number")
	public Integer rowNumber;
	
	@Column(name = "retail_price")
	public double retailPrice;
	
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
	
	@Column(name = "is_virtual")
	public boolean isVirtual;
	
	@Column(name = "b2c_estimation_amount")
	public double b2cEstimationAmount;
	
	@Column(name = "b2c_estimation_cost")
	public double b2cEstimationCost;
	
	@Column(name = "delievery_date")
	public Date delieveryDate;
	
	@Column(name = "mosaic_image")
	public String mosaicImage;
	
	@Column(name = "attached_image")
	public String attachedImage;
	
	@Column(name = "request_brand")
	public String requestBrand;
	
	@Column(name = "request_package")
	public String requestPackage;
	
	@Column(name = "request_nameplate")
	public String requestNameplate;
	
	@Column(name = "request_circuit")
	public String requestCircuit;
	
	@Column(name = "comments")
	public String comments;
	
	@Column(name = "color_comments")
	public String colorComments;
	
	public String getMosaicImage() {
		return mosaicImage;
	}

	public void setMosaicImage(String mosaicImage) {
		this.mosaicImage = mosaicImage;
	}

	public String getAttachedImage() {
		return attachedImage;
	}

	public void setAttachedImage(String attachedImage) {
		this.attachedImage = attachedImage;
	}

	public String getRequestBrand() {
		return requestBrand;
	}

	public void setRequestBrand(String requestBrand) {
		this.requestBrand = requestBrand;
	}

	public String getRequestPackage() {
		return requestPackage;
	}

	public void setRequestPackage(String requestPackage) {
		this.requestPackage = requestPackage;
	}

	public String getRequestNameplate() {
		return requestNameplate;
	}

	public void setRequestNameplate(String requestNameplate) {
		this.requestNameplate = requestNameplate;
	}

	public String getRequestCircuit() {
		return requestCircuit;
	}

	public void setRequestCircuit(String requestCircuit) {
		this.requestCircuit = requestCircuit;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getColorComments() {
		return colorComments;
	}

	public void setColorComments(String colorComments) {
		this.colorComments = colorComments;
	}

	public String getSpecialNeed() {
		return specialNeed;
	}

	public void setSpecialNeed(String specialNeed) {
		this.specialNeed = specialNeed;
	}

	public Date getDelieveryDate() {
		return delieveryDate;
	}

	public void setDelieveryDate(Date delieveryDate) {
		this.delieveryDate = delieveryDate;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getVolumeCube() {
		return volumeCube;
	}

	public void setVolumeCube(double volumeCube) {
		this.volumeCube = volumeCube;
	}

	public double getB2cEstimationAmount() {
		return b2cEstimationAmount;
	}

	public void setB2cEstimationAmount(double b2cEstimationAmount) {
		this.b2cEstimationAmount = b2cEstimationAmount;
	}

	public double getB2cEstimationCost() {
		return b2cEstimationCost;
	}

	public void setB2cEstimationCost(double b2cEstimationCost) {
		this.b2cEstimationCost = b2cEstimationCost;
	}

	public boolean isVirtual() {
		return isVirtual;
	}

	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

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
