package com.qhc.order.domain.bpm;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderHeader {
	/* 客户经理域账号 */
	private String salesCode;
	/* 客户经理电话 */
	private String salesTel;
	/* 订单类型 */
	private String orderType;
	/* 需求流水号 */
	private String sequenceNumber;
	/* 折扣 */
	private double discount;
	/* 签约单位 */
	private String customerName;
	/* 合同号 */
	private String contractNumber;
	/* 店名 */
	private String shopName;
	/* 合同金额 */
	private double contractRmbAmount;
	/* 原币金额 */
	private double contractAmount;
	/* 币种 */
	private String currencyName;
	/* 汇率 */
	private double exchange;
	/* 提货方式 */
	private String receiveTypeName;
	/* 大区 */
	private String sapOffice;
	/* 销售类型 */
	private String saleType;
	/* 结算方式 */
	private String paymentTypeName;
	/* 税率 */
	private double taxRate;
	/* 柜体折扣 */
	private double bodyDiscount;
	/* 机组折扣 */
	private double unitDiscount;
	/* 合并折扣 */
	private double mergeDiscount;
	/* 批准折扣 */
	private double approvalDiscount;
	/* 要求发货时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date earliestDeliveryDate;
	/* 是否经销商 */
	private String dealer;
	/* 是否特批折扣 */
	private String specialDiscount;
	/* 是否B2C */
	private String b2c;
	/* 订单创建时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
	/* 毛利率 */
	private double margin;
	/* WTW毛利率 */
	private double wtwMargin;
	/* 订单状态 */
	private String status;
	/* 备注 */
	private String comments;
	/* 安装费 */
	private double installFee;
	/* 材料费 */
	private double materialFee;
	/* 电气费 */
	private double electricalFee;
	/* 冷库费 */
	private double refrigeratoryFee;
	/* 维保费 */
	private double maintenanceFee;
	/* 地址 */
	private String address;
	/* 物料类型 */
	private String materialGroupNames;
	/* 是否特批发货/紧急发货 */
	private Integer isUrgentDelivery = null;
	/* 是否特批下单 */
	private Integer isSpecialOrder = 0;
    
    private int isLongterm;//是否为长期折扣
    
    private int isSpecial; // 是否非标准折扣，默认为0，经销商非标下单为1
    
    private int isNonUniform; // 是否非統一折扣，默认为0，非統一折扣为1

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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
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

	public double getContractRmbAmount() {
		return contractRmbAmount;
	}

	public void setContractRmbAmount(double contractRmbAmount) {
		this.contractRmbAmount = contractRmbAmount;
	}

	public double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public double getExchange() {
		return exchange;
	}

	public void setExchange(double exchange) {
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

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getBodyDiscount() {
		return bodyDiscount;
	}

	public void setBodyDiscount(double bodyDiscount) {
		this.bodyDiscount = bodyDiscount;
	}

	public double getUnitDiscount() {
		return unitDiscount;
	}

	public void setUnitDiscount(double unitDiscount) {
		this.unitDiscount = unitDiscount;
	}

	public double getMergeDiscount() {
		return mergeDiscount;
	}

	public void setMergeDiscount(double mergeDiscount) {
		this.mergeDiscount = mergeDiscount;
	}

	public double getApprovalDiscount() {
		return approvalDiscount;
	}

	public void setApprovalDiscount(double approvalDiscount) {
		this.approvalDiscount = approvalDiscount;
	}

	public Date getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}

	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	public double getWtwMargin() {
		return wtwMargin;
	}

	public void setWtwMargin(double wtwMargin) {
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

	public double getInstallFee() {
		return installFee;
	}

	public void setInstallFee(double installFee) {
		this.installFee = installFee;
	}

	public double getMaterialFee() {
		return materialFee;
	}

	public void setMaterialFee(double materialFee) {
		this.materialFee = materialFee;
	}

	public double getElectricalFee() {
		return electricalFee;
	}

	public void setElectricalFee(double electricalFee) {
		this.electricalFee = electricalFee;
	}

	public double getRefrigeratoryFee() {
		return refrigeratoryFee;
	}

	public void setRefrigeratoryFee(double refrigeratoryFee) {
		this.refrigeratoryFee = refrigeratoryFee;
	}

	public double getMaintenanceFee() {
		return maintenanceFee;
	}

	public void setMaintenanceFee(double maintenanceFee) {
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

	public Integer getIsUrgentDelivery() {
		return isUrgentDelivery;
	}

	public void setIsUrgentDelivery(Integer isUrgentDelivery) {
		this.isUrgentDelivery = isUrgentDelivery;
	}

	public Integer getIsSpecialOrder() {
		return isSpecialOrder;
	}

	public void setIsSpecialOrder(Integer isSpecialOrder) {
		this.isSpecialOrder = isSpecialOrder;
	}

	public int getIsLongterm() {
    return isLongterm;
  }

  public int getIsSpecial() {
    return isSpecial;
  }

  public int getIsNonUniform() {
    return isNonUniform;
  }

  public void setIsLongterm(int isLongterm) {
    this.isLongterm = isLongterm;
  }

  public void setIsSpecial(int isSpecial) {
    this.isSpecial = isSpecial;
  }

  public void setIsNonUniform(int isNonUniform) {
    this.isNonUniform = isNonUniform;
  }

  @Override
  public String toString() {
    return "OrderHeader [salesCode=" + salesCode + ", salesTel=" + salesTel + ", orderType="
        + orderType + ", sequenceNumber=" + sequenceNumber + ", discount=" + discount
        + ", customerName=" + customerName + ", contractNumber=" + contractNumber + ", shopName="
        + shopName + ", contractRmbAmount=" + contractRmbAmount + ", contractAmount="
        + contractAmount + ", currencyName=" + currencyName + ", exchange=" + exchange
        + ", receiveTypeName=" + receiveTypeName + ", sapOffice=" + sapOffice + ", saleType="
        + saleType + ", paymentTypeName=" + paymentTypeName + ", taxRate=" + taxRate
        + ", bodyDiscount=" + bodyDiscount + ", unitDiscount=" + unitDiscount + ", mergeDiscount="
        + mergeDiscount + ", approvalDiscount=" + approvalDiscount + ", earliestDeliveryDate="
        + earliestDeliveryDate + ", dealer=" + dealer + ", specialDiscount=" + specialDiscount
        + ", b2c=" + b2c + ", createTime=" + createTime + ", margin=" + margin + ", wtwMargin="
        + wtwMargin + ", status=" + status + ", comments=" + comments + ", installFee=" + installFee
        + ", materialFee=" + materialFee + ", electricalFee=" + electricalFee
        + ", refrigeratoryFee=" + refrigeratoryFee + ", maintenanceFee=" + maintenanceFee
        + ", address=" + address + ", materialGroupNames=" + materialGroupNames
        + ", isUrgentDelivery=" + isUrgentDelivery + ", isSpecialOrder=" + isSpecialOrder
        + ", isLongterm=" + isLongterm + ", isSpecial=" + isSpecial + ", isNonUniform="
        + isNonUniform + "]";
  }

}
