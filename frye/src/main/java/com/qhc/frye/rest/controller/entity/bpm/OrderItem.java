package com.qhc.frye.rest.controller.entity.bpm;

public class OrderItem {
	/* 行号 */
	private String rowNumber;
	/* 规格型号 */
	private String materialName;
	/* 专用号 */
	private String material_code;
	/* 物料属性 */
	private String materialAttribute;
	/* 类型 */
	private String materialGroupName;
	/* 数量 */
	private String quantity;
	/* 计量单位 */
	private String measureUnitName;
	/* 产品实卖价 */
	private String acturalPrice;
	/* 产品实卖金额 */
	private String acturalAmount;
	/* 产品转移价 */
	private String transfterPrice;
	/* 可选项实卖价 */
	private String acturalPriceOfOptional;
	/* 可选项实卖金额 */
	private String acturalAmountOfOptional;
	/* 可选项转移价 */
	private String transcationPriceOfOptional;
	/* B2C预估价 */
	private String b2cPriceEstimated;
	/* B2C预估金额 */
	private String b2cAmountEstimated;
	/* B2C预估成本 */
	private String b2cCostOfEstimated;
	/* 实卖价合计 */
//	 private String 		;
	/* 实卖金额合计 */
//	 private String 		;
	/* 转移价合计 */
//	 private String 		;
	/* 市场零售价 */
	private String retailPrice;
	/* 市场零售金额 */
	private String retailAmount;
	/* 折扣率 */
	private String discount;
	/* 行项目类别 */
	private String itemCategoryName;
	/* 需求计划 */
	private String itemRequirementPlanName;
	/* 生产周期 */
	private String period;
	/* 工厂最早交货时间 */
	private String deliveryDate;
	/* 生产开始时间 */
	private String produceDate;
	/* 要求发货时间 */
	private String shippDate;
	/* 地址 */
	private String address;
	/* 入库时间 */
	private String onStoreDate;
	/* 采购周期 */
//	 private String 	period	;
	/* B2C备注 */
	private String b2cComments;
	/* 特殊备注 */
	private String specialComments;
	/* 颜色备注 */
	private String colorComments;

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterial_code() {
		return material_code;
	}

	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}

	public String getMaterialAttribute() {
		return materialAttribute;
	}

	public void setMaterialAttribute(String materialAttribute) {
		this.materialAttribute = materialAttribute;
	}

	public String getMaterialGroupName() {
		return materialGroupName;
	}

	public void setMaterialGroupName(String materialGroupName) {
		this.materialGroupName = materialGroupName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getMeasureUnitName() {
		return measureUnitName;
	}

	public void setMeasureUnitName(String measureUnitName) {
		this.measureUnitName = measureUnitName;
	}

	public String getActuralPrice() {
		return acturalPrice;
	}

	public void setActuralPrice(String acturalPrice) {
		this.acturalPrice = acturalPrice;
	}

	public String getActuralAmount() {
		return acturalAmount;
	}

	public void setActuralAmount(String acturalAmount) {
		this.acturalAmount = acturalAmount;
	}

	public String getTransfterPrice() {
		return transfterPrice;
	}

	public void setTransfterPrice(String transfterPrice) {
		this.transfterPrice = transfterPrice;
	}

	public String getActuralPriceOfOptional() {
		return acturalPriceOfOptional;
	}

	public void setActuralPriceOfOptional(String acturalPriceOfOptional) {
		this.acturalPriceOfOptional = acturalPriceOfOptional;
	}

	public String getActuralAmountOfOptional() {
		return acturalAmountOfOptional;
	}

	public void setActuralAmountOfOptional(String acturalAmountOfOptional) {
		this.acturalAmountOfOptional = acturalAmountOfOptional;
	}

	public String getTranscationPriceOfOptional() {
		return transcationPriceOfOptional;
	}

	public void setTranscationPriceOfOptional(String transcationPriceOfOptional) {
		this.transcationPriceOfOptional = transcationPriceOfOptional;
	}

	public String getB2cPriceEstimated() {
		return b2cPriceEstimated;
	}

	public void setB2cPriceEstimated(String b2cPriceEstimated) {
		this.b2cPriceEstimated = b2cPriceEstimated;
	}

	public String getB2cAmountEstimated() {
		return b2cAmountEstimated;
	}

	public void setB2cAmountEstimated(String b2cAmountEstimated) {
		this.b2cAmountEstimated = b2cAmountEstimated;
	}

	public String getB2cCostOfEstimated() {
		return b2cCostOfEstimated;
	}

	public void setB2cCostOfEstimated(String b2cCostOfEstimated) {
		this.b2cCostOfEstimated = b2cCostOfEstimated;
	}

	public String getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getRetailAmount() {
		return retailAmount;
	}

	public void setRetailAmount(String retailAmount) {
		this.retailAmount = retailAmount;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(String produceDate) {
		this.produceDate = produceDate;
	}

	public String getShippDate() {
		return shippDate;
	}

	public void setShippDate(String shippDate) {
		this.shippDate = shippDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOnStoreDate() {
		return onStoreDate;
	}

	public void setOnStoreDate(String onStoreDate) {
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

	@Override
	public String toString() {
		return "OrderItem [rowNumber=" + rowNumber + ", materialName=" + materialName + ", material_code="
				+ material_code + ", materialAttribute=" + materialAttribute + ", materialGroupName="
				+ materialGroupName + ", quantity=" + quantity + ", measureUnitName=" + measureUnitName
				+ ", acturalPrice=" + acturalPrice + ", acturalAmount=" + acturalAmount + ", transfterPrice="
				+ transfterPrice + ", acturalPriceOfOptional=" + acturalPriceOfOptional + ", acturalAmountOfOptional="
				+ acturalAmountOfOptional + ", transcationPriceOfOptional=" + transcationPriceOfOptional
				+ ", b2cPriceEstimated=" + b2cPriceEstimated + ", b2cAmountEstimated=" + b2cAmountEstimated
				+ ", b2cCostOfEstimated=" + b2cCostOfEstimated + ", retailPrice=" + retailPrice + ", retailAmount="
				+ retailAmount + ", discount=" + discount + ", itemCategoryName=" + itemCategoryName
				+ ", itemRequirementPlanName=" + itemRequirementPlanName + ", period=" + period + ", deliveryDate="
				+ deliveryDate + ", produceDate=" + produceDate + ", shippDate=" + shippDate + ", address=" + address
				+ ", onStoreDate=" + onStoreDate + ", b2cComments=" + b2cComments + ", specialComments="
				+ specialComments + ", colorComments=" + colorComments + "]";
	}

}
