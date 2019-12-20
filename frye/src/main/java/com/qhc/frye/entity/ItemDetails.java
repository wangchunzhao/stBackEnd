package com.qhc.frye.entity;

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
	private String id;
	
	@NotNull
	@Column(name = "k_forms_id",columnDefinition="char",length=32)
	private String kFormsId;

	
	//产品规格型号
	@NotNull
	@Column(name = "material_code",columnDefinition="char")
	private String materialCode;
	
	@NotNull
	@Column(name = "material_name",columnDefinition = "TEXT")
	private String materialName;
	
	//体积
	//@NotNull
	@Column(name = "volume_cube")
	private BigDecimal volumeCube;
	
	//物料属性
	@NotNull
	@Column(name = "is_purchased",columnDefinition="BIT",length=1)
	private boolean materialAttribute;
	
	//合同数量
	//@NotNull
	@Column(name = "quantity",columnDefinition="double")
	private double quantity;
	
	//销售单价
	
	//销售金额
	@Column(name = "sale_amount")
	private BigDecimal amount;
	
	//单位 
	//@NotNull
	@Column(name = "measure_unit_code",columnDefinition="char",length=3)
	private String measureUnitCode;
	
	@Column(name = "measure_unit_name",columnDefinition="text")
	private String measureUnitName;
	
	//@NotNull
	@Column(name = "special_need",columnDefinition = "TEXT")
	private String specialNeed;

	
	@Column(name = "b2c_comments",columnDefinition = "TEXT")
	private String b2cComments;
	
//	@Column(name = "type")
//	@Transient
//	public String type;
	
	@NotNull
	@Column(name = "row_num",columnDefinition = "int")
	private int rowNumber;
	
	@Column(name = "retail_price")
	private BigDecimal retailPrice;
	
	@NotNull
	@Column(name = "material_group_code")
	private String materialGroupCode;
	
	@NotNull
	@Column(name = "material_group_name",columnDefinition = "TEXT")
	private String materialGroupName;
	
	@Column(name = "transfter_price")
	private BigDecimal transfterPrice;
	
	@Column(name = "discount",columnDefinition = "double")
	private double discount;
	
	@NotNull
	@Column(name = "item_category")
	private String itemCategory;
	
	@NotNull
	@Column(name = "item_requirement_plan")
	private String itemRequirementPlan;
	
	//
	@Column(name = "freight")
	private BigDecimal freight;
	
	
	@Column(name = "standard_price")
	private BigDecimal standardPrice;
	
	@Column(name = "is_virtual",columnDefinition = "bit")
	private boolean isVirtual;
	
	@Column(name = "b2c_estimation_amount")
	private BigDecimal b2cEstimationAmount;
	
	@Column(name = "b2c_estimation_cost")
	private BigDecimal b2cEstimationCost;
	
	@Column(name = "delievery_date")
	private Date delieveryDate;
	
	@Column(name = "ship_date")
	private Date shipDate;
	
	@Column(name = "mosaic_image",columnDefinition = "TEXT")
	private String mosaicImage;
	
	@Column(name = "attached_image",columnDefinition = "TEXT")
	private String attachedImage;
	
	@Column(name = "request_brand",columnDefinition = "TEXT")
	private String requestBrand;
	
	@Column(name = "request_package",columnDefinition = "TEXT")
	private String requestPackage;
	
	@Column(name = "request_nameplate",columnDefinition = "TEXT")
	private String requestNameplate;
	
	@Column(name = "request_circuit",columnDefinition = "TEXT")
	private String requestCircuit;
	
	@Column(name = "comments",columnDefinition = "TEXT")
	private String comments;
	
	@Column(name = "color_comments",columnDefinition = "TEXT")
	private String colorComments;
	
	@Column(name = "is_configurable",columnDefinition = "BIT",length=1)
	private boolean isConfigurable;
	
	@NotNull
	@Column(name = "clazz_code",columnDefinition = "char",length=45)
	private String clazzCode;
	
	@Column(name = "delivery_provience_code",columnDefinition = "char",length=45)
	private String provienceCode;
	
	@Column(name = "delivery_provience_name",columnDefinition = "char",length=45)
	private String provienceName;
	
	@Column(name = "delivery_city_code",columnDefinition = "char",length=45)
	private String cityCode;
	
	@Column(name = "delivery_city_name",columnDefinition = "char",length=45)
	private String cityName;
	
	@Column(name = "delivery_district_code",columnDefinition = "char",length=45)
	private String districtCode;
	
	@Column(name = "delivery_district_name",columnDefinition = "char",length=45)
	private String districtName;
	
	@Column(name = "delivery_address",columnDefinition = "text")
	private String address;
	
	
	
	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public String getMeasureUnitName() {
		return measureUnitName;
	}

	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}

	
	public String getProvienceCode() {
		return provienceCode;
	}

	public void setProvienceCode(String provienceCode) {
		this.provienceCode = provienceCode;
	}

	public String getProvienceName() {
		return provienceName;
	}

	public void setProvienceName(String provienceName) {
		this.provienceName = provienceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getClazzCode() {
		return clazzCode;
	}

	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}

	public boolean isConfigurable() {
		return isConfigurable;
	}

	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}

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

	

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getVolumeCube() {
		return volumeCube;
	}

	public void setVolumeCube(BigDecimal volumeCube) {
		this.volumeCube = volumeCube;
	}

	public BigDecimal getB2cEstimationAmount() {
		return b2cEstimationAmount;
	}

	public void setB2cEstimationAmount(BigDecimal b2cEstimationAmount) {
		this.b2cEstimationAmount = b2cEstimationAmount;
	}

	
	public BigDecimal getB2cEstimationCost() {
		return b2cEstimationCost;
	}

	public void setB2cEstimationCost(BigDecimal b2cEstimationCost) {
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

//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}

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

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	
	
	
	
}
