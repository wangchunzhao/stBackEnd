package com.qhc.frye.rest.controller.entity.bpm;

public class Order {
	/* 客户经理域账号 */
	private String salesCode;
	/* 客户经理电话 */
	private String salesTel;
	/* 订单类型 */
	private String orderType;
	/* 需求流水号 */
	private String sequenceNumber;
	/* 折扣 */
	private String discount;
	/* 签约单位 */
	private String customerName;
	/* 合同号 */
	private String contractNumber;
	/* 店名 */
	private String shopName;
	/* 合同金额 */
	private String contractRmbAmount;
	/* 原币金额 */
	private String contractAmount;
	/* 币种 */
	private String currencyName;
	/* 汇率 */
	private String exchange;
	/* 提货方式 */
	private String receiveTypeName;
	/* 大区 */
	private String sapOffice;
	/* 销售类型 */
	private String saleType;
	/* 结算方式 */
	private String paymentTypeName;
	/* 税率 */
	private String taxRate;
	/* 柜体折扣 */
	private String bodyDiscount;
	/* 机组折扣 */
	private String unitDiscount;
	/* 合并折扣 */
	private String mergeDiscount;
	/* 批准折扣 */
	private String approvalDiscount;
	/* 要求发货时间 */
	private String earliestDeliveryDate;
	/* 是否经销商 */
	private String dealer;
	/* 是否特批折扣 */
	private String specialDiscount;
	/* 是否B2C */
	private String b2c;
	/* 订单创建时间 */
	private String createTime;
	/* 毛利率 */
	private String margin;
	/* WTW毛利率 */
	private String wtwMargin;
	/* 订单状态 */
	private String status;
	/* 备注 */
	private String comments;
	/* 安装费 */
	private String installFee;
	/* 材料费 */
	private String materialFee;
	/* 电气费 */
	private String electricalFee;
	/* 冷库费 */
	private String refrigeratoryFee;
	/* 维保费 */
	private String maintenanceFee;
	/* 地址 */
	private String address;
	/* 物料类型 */
	private String materialGroupNames;

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public String getSalesTel() {
		return salesTel;
	}

	public void setSalesTel(String salesTel) {
		this.salesTel = salesTel;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getContractRmbAmount() {
		return contractRmbAmount;
	}

	public void setContractRmbAmount(String contractRmbAmount) {
		this.contractRmbAmount = contractRmbAmount;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getReceiveTypeName() {
		return receiveTypeName;
	}

	public void setReceiveTypeName(String receiveTypeName) {
		this.receiveTypeName = receiveTypeName;
	}

	public String getSapOffice() {
		return sapOffice;
	}

	public void setSapOffice(String sapOffice) {
		this.sapOffice = sapOffice;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getBodyDiscount() {
		return bodyDiscount;
	}

	public void setBodyDiscount(String bodyDiscount) {
		this.bodyDiscount = bodyDiscount;
	}

	public String getUnitDiscount() {
		return unitDiscount;
	}

	public void setUnitDiscount(String unitDiscount) {
		this.unitDiscount = unitDiscount;
	}

	public String getMergeDiscount() {
		return mergeDiscount;
	}

	public void setMergeDiscount(String mergeDiscount) {
		this.mergeDiscount = mergeDiscount;
	}

	public String getApprovalDiscount() {
		return approvalDiscount;
	}

	public void setApprovalDiscount(String approvalDiscount) {
		this.approvalDiscount = approvalDiscount;
	}

	public String getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}

	public void setEarliestDeliveryDate(String earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public String getSpecialDiscount() {
		return specialDiscount;
	}

	public void setSpecialDiscount(String specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	public String getB2c() {
		return b2c;
	}

	public void setB2c(String b2c) {
		this.b2c = b2c;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getWtwMargin() {
		return wtwMargin;
	}

	public void setWtwMargin(String wtwMargin) {
		this.wtwMargin = wtwMargin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getInstallFee() {
		return installFee;
	}

	public void setInstallFee(String installFee) {
		this.installFee = installFee;
	}

	public String getMaterialFee() {
		return materialFee;
	}

	public void setMaterialFee(String materialFee) {
		this.materialFee = materialFee;
	}

	public String getElectricalFee() {
		return electricalFee;
	}

	public void setElectricalFee(String electricalFee) {
		this.electricalFee = electricalFee;
	}

	public String getRefrigeratoryFee() {
		return refrigeratoryFee;
	}

	public void setRefrigeratoryFee(String refrigeratoryFee) {
		this.refrigeratoryFee = refrigeratoryFee;
	}

	public String getMaintenanceFee() {
		return maintenanceFee;
	}

	public void setMaintenanceFee(String maintenanceFee) {
		this.maintenanceFee = maintenanceFee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMaterialGroupNames() {
		return materialGroupNames;
	}

	public void setMaterialGroupNames(String materialGroupNames) {
		this.materialGroupNames = materialGroupNames;
	}

	@Override
	public String toString() {
		return "Order [salesCode=" + salesCode + ", salesTel=" + salesTel + ", orderType=" + orderType
				+ ", sequenceNumber=" + sequenceNumber + ", discount=" + discount + ", customerName=" + customerName
				+ ", contractNumber=" + contractNumber + ", shopName=" + shopName + ", contractRmbAmount="
				+ contractRmbAmount + ", contractAmount=" + contractAmount + ", currencyName=" + currencyName
				+ ", exchange=" + exchange + ", receiveTypeName=" + receiveTypeName + ", sapOffice=" + sapOffice
				+ ", saleType=" + saleType + ", paymentTypeName=" + paymentTypeName + ", taxRate=" + taxRate
				+ ", bodyDiscount=" + bodyDiscount + ", unitDiscount=" + unitDiscount + ", mergeDiscount="
				+ mergeDiscount + ", approvalDiscount=" + approvalDiscount + ", earliestDeliveryDate="
				+ earliestDeliveryDate + ", dealer=" + dealer + ", specialDiscount=" + specialDiscount + ", b2c=" + b2c
				+ ", createTime=" + createTime + ", margin=" + margin + ", wtwMargin=" + wtwMargin + ", status="
				+ status + ", comments=" + comments + ", installFee=" + installFee + ", materialFee=" + materialFee
				+ ", electricalFee=" + electricalFee + ", refrigeratoryFee=" + refrigeratoryFee + ", maintenanceFee="
				+ maintenanceFee + ", address=" + address + ", materialGroupNames=" + materialGroupNames + "]";
	}

}
