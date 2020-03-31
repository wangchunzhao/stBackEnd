package com.qhc.order.domain.bpm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderItem {
	/* 行号 */
	private int rowNumber;
	/* 规格型号 */
	private String materialName;
	/* 专用号 */
	private String materialCode;
	/* 物料属性 */
	private String materialAttribute;
	/* 类型 */
	private String materialGroup;
	/* 类型 */
	private String materialGroupName;
	/* 数量 */
	private double quantity;
	/* 计量单位 */
	private String measureUnitName;
	/* 产品实卖价 */
	private double acturalPrice;
	/* 产品实卖金额 */
	private double acturalAmount;
	/* 产品转移价 */
	private double transfterPrice;
	/* 可选项实卖价 */
	private double acturalPriceOfOptional;
	/* 可选项实卖金额 */
	private double acturalAmountOfOptional;
	/* 可选项转移价 */
	private double transactionPriceOfOptional;
	/* B2C预估价 */
	private double b2cPriceEstimated;
	/* B2C预估金额 */
	private double b2cAmountEstimated;
	/* B2C预估成本 */
	private double b2cCostOfEstimated;
	/* 实卖价合计 */
//	 private String 		;
	/* 实卖金额合计 */
//	 private String 		;
	/* 转移价合计 */
//	 private String 		;
	/* 市场零售价 */
	private double retailPrice;
	/* 市场零售金额 */
	private double retailAmount;
	/* 折扣率 */
	private double discount;
	/* 行项目类别 */
	private String itemCategoryName;
	/* 需求计划 */
	private String itemRequirementPlanName;
	/* 生产周期 */
	private int period;
	/* 工厂最早交货时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date deliveryDate;
	/* 生产开始时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date produceDate;
	/* 要求发货时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date shippDate;
	/* 地址 */
	private String address;
	/* 入库时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date onStoreDate;
	/* 采购周期 */
//	 private String 	period	;
	/* B2C备注 */
	private String b2cComments;
	/* 特殊备注 */
	private String specialComments;
	/* 颜色备注 */
	private String colorComments;
	/* 成本价 */
	private double standardCost;

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialAttribute() {
		return materialAttribute;
	}

	public void setMaterialAttribute(String materialAttribute) {
		this.materialAttribute = materialAttribute;
	}

	public String getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
	}

	public String getMaterialGroupName() {
		return materialGroupName;
	}

	public void setMaterialGroupName(String materialGroupName) {
		this.materialGroupName = materialGroupName;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getMeasureUnitName() {
		return measureUnitName;
	}

	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}

	public double getActuralPrice() {
		return acturalPrice;
	}

	public void setActuralPrice(double acturalPrice) {
		this.acturalPrice = acturalPrice;
	}

	public double getActuralAmount() {
		return acturalAmount;
	}

	public void setActuralAmount(double acturalAmount) {
		this.acturalAmount = acturalAmount;
	}

	public double getTransfterPrice() {
		return transfterPrice;
	}

	public void setTransfterPrice(double transfterPrice) {
		this.transfterPrice = transfterPrice;
	}

	public double getActuralPriceOfOptional() {
		return acturalPriceOfOptional;
	}

	public void setActuralPriceOfOptional(double acturalPriceOfOptional) {
		this.acturalPriceOfOptional = acturalPriceOfOptional;
	}

	public double getActuralAmountOfOptional() {
		return acturalAmountOfOptional;
	}

	public void setActuralAmountOfOptional(double acturalAmountOfOptional) {
		this.acturalAmountOfOptional = acturalAmountOfOptional;
	}

	public double getTransactionPriceOfOptional() {
		return transactionPriceOfOptional;
	}

	public void setTransactionPriceOfOptional(double transactionPriceOfOptional) {
		this.transactionPriceOfOptional = transactionPriceOfOptional;
	}

	public double getB2cPriceEstimated() {
		return b2cPriceEstimated;
	}

	public void setB2cPriceEstimated(double b2cPriceEstimated) {
		this.b2cPriceEstimated = b2cPriceEstimated;
	}

	public double getB2cAmountEstimated() {
		return b2cAmountEstimated;
	}

	public void setB2cAmountEstimated(double b2cAmountEstimated) {
		this.b2cAmountEstimated = b2cAmountEstimated;
	}

	public double getB2cCostOfEstimated() {
		return b2cCostOfEstimated;
	}

	public void setB2cCostOfEstimated(double b2cCostOfEstimated) {
		this.b2cCostOfEstimated = b2cCostOfEstimated;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getRetailAmount() {
		return retailAmount;
	}

	public void setRetailAmount(double retailAmount) {
		this.retailAmount = retailAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

	public String getItemRequirementPlanName() {
		return itemRequirementPlanName;
	}

	public void setItemRequirementPlanName(String itemRequirementPlanName) {
		this.itemRequirementPlanName = itemRequirementPlanName;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Date getShippDate() {
		return shippDate;
	}

	public void setShippDate(Date shippDate) {
		this.shippDate = shippDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getOnStoreDate() {
		return onStoreDate;
	}

	public void setOnStoreDate(Date onStoreDate) {
		this.onStoreDate = onStoreDate;
	}

	public String getB2cComments() {
		return b2cComments;
	}

	public void setB2cComments(String b2cComments) {
		this.b2cComments = b2cComments;
	}

	public String getSpecialComments() {
		return specialComments;
	}

	public void setSpecialComments(String specialComments) {
		this.specialComments = specialComments;
	}

	public String getColorComments() {
		return colorComments;
	}

	public void setColorComments(String colorComments) {
		this.colorComments = colorComments;
	}

	public double getStandardCost() {
		return standardCost;
	}

	public void setStandardCost(double standardCost) {
		this.standardCost = standardCost;
	}

	@Override
	public String toString() {
		return "OrderItem [rowNumber=" + rowNumber + ", materialName=" + materialName + ", materialCode=" + materialCode
				+ ", materialAttribute=" + materialAttribute + ", materialGroup=" + materialGroup
				+ ", materialGroupName=" + materialGroupName + ", quantity=" + quantity + ", measureUnitName="
				+ measureUnitName + ", acturalPrice=" + acturalPrice + ", acturalAmount=" + acturalAmount
				+ ", transfterPrice=" + transfterPrice + ", acturalPriceOfOptional=" + acturalPriceOfOptional
				+ ", acturalAmountOfOptional=" + acturalAmountOfOptional + ", transactionPriceOfOptional="
				+ transactionPriceOfOptional + ", b2cPriceEstimated=" + b2cPriceEstimated + ", b2cAmountEstimated="
				+ b2cAmountEstimated + ", b2cCostOfEstimated=" + b2cCostOfEstimated + ", retailPrice=" + retailPrice
				+ ", retailAmount=" + retailAmount + ", discount=" + discount + ", itemCategoryName=" + itemCategoryName
				+ ", itemRequirementPlanName=" + itemRequirementPlanName + ", period=" + period + ", deliveryDate="
				+ deliveryDate + ", produceDate=" + produceDate + ", shippDate=" + shippDate + ", address=" + address
				+ ", onStoreDate=" + onStoreDate + ", b2cComments=" + b2cComments + ", specialComments="
				+ specialComments + ", colorComments=" + colorComments + ", standardCost=" + standardCost + "]";
	}

}
