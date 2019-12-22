package com.qhc.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 合同列表信息实体
 * @author lizuoshan
 *
 */
//@Entity
//@Table(name = "k_report_bymaterial_view")
public class ReportFormsInfo implements Serializable {
	
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="sequence_number",columnDefinition = "char")
	private String	sequenceNumber	;//	单据编号//订单编号
	
//	@Column(name="sales_code",columnDefinition = "char")
	@Transient
	private String	salesCode	;//	签约人//客户经理
//	@Column(name="sales_name",columnDefinition = "text")
	@Transient
	private String	salesName	;//	签约人//客户经理
	
	@Column(name="office_code",columnDefinition = "char")
	private String	officeCode	;//	区域
	
	@Column(name="group_code",columnDefinition = "char")
	private String	groupCode	;//	中心
	@Column(name="group_name",columnDefinition = "text")
	private String	groupName	;//	中心
	
	@Column(name="create_time")
	private Date	createTime	;//	签约日期
	
//	@Column(name="coustomer_no")
	@Transient
	private String	coustomerNo	;//	客户编号
	
	@Column(name="contract_seq",columnDefinition = "char")
	private String	contractSeq	;//	合同号
	
	@Column(name="contractor_code",columnDefinition = "char")
	private String	contractorCode	;//	签约人合同号
	
//	@Column(name="contract_unit")
	@Transient
	private String	contractUnit	;//	签约单位
	
	@Column(name="contractor_class_name",columnDefinition = "text")
	private String	contractorClassName	;//	客户性质
	@Column(name="contractor_class_code",columnDefinition = "char")
	private String	contractorClassCode;//	客户性质
	
//	@Column(name="customer_name",columnDefinition="TEXT")
	@Transient
	public String customerName;//店名
	
	@Column(name="terminal_industry_code",columnDefinition = "char")
	private String	terminalIndustryCode	;//	终端客户性质
	@Column(name="terminal_industry_code_name",columnDefinition = "text")
	private String	terminalIndustryCodeName	;//	终端客户性质
	
//	@Column(name="is_special_discount")
	@Transient
	private Integer	isSpecialDiscount	;//	是否特批折扣
	
	@Column(name="is_reformed",columnDefinition = "bit")
	private Integer	isRreformed	;//	是否改造店
	
	@Column(name="contract_rmb_amount")
	private BigDecimal contractRmbAmount	;//	合同金额
	
	@Column(name="status",columnDefinition = "char")
	private String	status	;//	合同状态
	
//	@Column(name="gross_profit")
	@Transient
	private Double	grossProfit	;//	毛利率
	
//	@Column(name="is_long_term_discount")
	@Transient
	private Integer	isLongTermDiscount	;//	是否长期折扣
	
	//按照订单类型计算折扣 //A 标准 B 需要计算
	@Column(name="discount")
	private Double	discount	;//	折扣
	
	@Column(name="main_discount")
	private Double	mainDiscount	;//	合并折扣
	
	@Column(name="body_discount")
	private Double	bodyDiscount	;//	自身折扣
	
	
	@Column(name="material_code",columnDefinition = "char")
	private String	materialCode	;//	产品规格型号
	
//	@Column(name="material_specific_Number",columnDefinition = "char")
//	private String	materialSpecificNumber	;//	物料专用号
	
	@Column(name="is_purchased",columnDefinition = "BIT")
	private boolean	materialAttribute	;//	物料属性
	
	@Column(name="quantity")
	private double	quantity	;//	合同数量
	
	@Column(name="price")
	private double	price	;//	销售单价
	
	@Column(name="sale_amount")
	private BigDecimal	amount	;//	销售金额
	
//	@Column(name="measure_unit_name",columnDefinition = "text")
//	private String	measureUnitName	;//	单位
	
//	@Column(name="receiver_address")
	@Transient
	private String	receiverAddress	;//	到货地址
	
	@Column(name="earliest_delivery_date")
	private Date	earliestDeliveryDate	;//	要求发货时间
	
	@Column(name="install_term_code",columnDefinition = "char")
	private String	installTermCode	;//	安装公司
	@Column(name="install_term_name",columnDefinition = "text")
	private String	installTermName	;//	安装公司
	
//	@Column(name="receive_type")
	@Transient
	private String	receiveType	;//	收货方式
	
	@Column(name="contactor_1_id")
	private String	contactor1Id	;//	授权人及身份证号
	@Column(name="contactor_2_id")
	private String	contactor2Id	;//	授权人及身份证号
	@Column(name="contactor_3_id")
	private String	contactor3Id	;//	授权人及身份证号
	
	@Column(name="contactor_1_tel")
	private String	contactor1Tel	;//	授权人电话
	@Column(name="contactor_2_tel")
	private String	contactor2Tel	;//	授权人电话
	@Column(name="contactor_3_tel")
	private String	contactor3Tel	;//	授权人电话
	
//	@Column(name="receiver_id")
	@Transient
	private String	receiverID	;//	收货人身份证号
	
//	@Column(name="settlement_method")
	@Transient
	private String	settlementMethod	;//	结算方式
	
	
//	@Column(name="freight",columnDefinition = "DECIMAL")
	@Transient
	private BigDecimal	freight	;//运费
	
	@Column(name="warranty")
	private Integer	warranty;//保修期限（年）
	
	@Column(name="currency_code",columnDefinition = "char")
	private String	currencyCode;//币别
	@Column(name="currency_name",columnDefinition = "text")
	private String	currencyName;//币别
	

	@Column(name="contract_amount",columnDefinition = "DECIMAL")
	private BigDecimal	contractAmount	;//原币合同金额
	
	@Column(name="exchange",columnDefinition = "DECIMAL")
	private Double	exchange	;//汇率
	
	@Column(name="is_new",columnDefinition ="bit")
	private Integer	isNew	;//是否新客户
	
	
	
	@Column(name="office_name",columnDefinition = "text")
	private String officeName;//大区名称 
//	@Column(name="status_name",columnDefinition = "text")
	@Transient
	private String	statusName	;//	合同状态名称
//	@Column(name="customer_type_name",columnDefinition = "text")
	@Transient
	private String	customerTypeName	;//	客户性质名称
	
	@Column(name="order_type_code",columnDefinition = "char")
	private String	orderTypeCode	;//	订单状态
	
	@Transient
	private String queryType;//查询类型
	
	
	
	
	@Transient
	public String startTime;
	
	@Transient
	public String endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}


	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}



	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCoustomerNo() {
		return coustomerNo;
	}

	public void setCoustomerNo(String coustomerNo) {
		this.coustomerNo = coustomerNo;
	}

	public String getContractorCode() {
		return contractorCode;
	}

	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}

	public String getContractUnit() {
		return contractUnit;
	}

	public void setContractUnit(String contractUnit) {
		this.contractUnit = contractUnit;
	}

	public String getContractorClassName() {
		return contractorClassName;
	}

	public void setContractorClassName(String contractorClassName) {
		this.contractorClassName = contractorClassName;
	}

	public String getContractorClassCode() {
		return contractorClassCode;
	}

	public void setContractorClassCode(String contractorClassCode) {
		this.contractorClassCode = contractorClassCode;
	}

	public String getTerminalIndustryCode() {
		return terminalIndustryCode;
	}

	public void setTerminalIndustryCode(String terminalIndustryCode) {
		this.terminalIndustryCode = terminalIndustryCode;
	}

	public String getTerminalIndustryCodeName() {
		return terminalIndustryCodeName;
	}

	public void setTerminalIndustryCodeName(String terminalIndustryCodeName) {
		this.terminalIndustryCodeName = terminalIndustryCodeName;
	}

	public Integer getIsSpecialDiscount() {
		return isSpecialDiscount;
	}

	public void setIsSpecialDiscount(Integer isSpecialDiscount) {
		this.isSpecialDiscount = isSpecialDiscount;
	}

	public Integer getIsRreformed() {
		return isRreformed;
	}

	public void setIsRreformed(Integer isRreformed) {
		this.isRreformed = isRreformed;
	}

	public BigDecimal getContractRmbAmount() {
		return contractRmbAmount;
	}

	public void setContractRmbAmount(BigDecimal contractRmbAmount) {
		this.contractRmbAmount = contractRmbAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIsLongTermDiscount() {
		return isLongTermDiscount;
	}

	public void setIsLongTermDiscount(Integer isLongTermDiscount) {
		this.isLongTermDiscount = isLongTermDiscount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

//	public String getMaterialSpecificNumber() {
//		return materialSpecificNumber;
//	}
//
//	public void setMaterialSpecificNumber(String materialSpecificNumber) {
//		this.materialSpecificNumber = materialSpecificNumber;
//	}

	public boolean getMaterialAttribute() {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

//	public String getMeasureUnitName() {
//		return measureUnitName;
//	}
//
//	public void setMeasureUnitName(String measureUnitName) {
//		this.measureUnitName = measureUnitName;
//	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Date getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}

	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
	}

	public String getInstallTermCode() {
		return installTermCode;
	}

	public void setInstallTermCode(String installTermCode) {
		this.installTermCode = installTermCode;
	}

	public String getInstallTermName() {
		return installTermName;
	}

	public void setInstallTermName(String installTermName) {
		this.installTermName = installTermName;
	}

	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	public String getContactor1Tel() {
		return contactor1Tel;
	}

	public void setContactor1Tel(String contactor1Tel) {
		this.contactor1Tel = contactor1Tel;
	}

	public String getContactor2Tel() {
		return contactor2Tel;
	}

	public void setContactor2Tel(String contactor2Tel) {
		this.contactor2Tel = contactor2Tel;
	}

	public String getContactor3Tel() {
		return contactor3Tel;
	}

	public void setContactor3Tel(String contactor3Tel) {
		this.contactor3Tel = contactor3Tel;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getExchange() {
		return exchange;
	}

	public void setExchange(Double exchange) {
		this.exchange = exchange;
	}

	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getOrderTypeCode() {
		return orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	public String getContactor1Id() {
		return contactor1Id;
	}

	public void setContactor1Id(String contactor1Id) {
		this.contactor1Id = contactor1Id;
	}

	public String getContactor2Id() {
		return contactor2Id;
	}

	public void setContactor2Id(String contactor2Id) {
		this.contactor2Id = contactor2Id;
	}

	public String getContactor3Id() {
		return contactor3Id;
	}

	public void setContactor3Id(String contactor3Id) {
		this.contactor3Id = contactor3Id;
	}

	public Double getMainDiscount() {
		return mainDiscount;
	}

	public void setMainDiscount(Double mainDiscount) {
		this.mainDiscount = mainDiscount;
	}

	public Double getBodyDiscount() {
		return bodyDiscount;
	}

	public void setBodyDiscount(Double bodyDiscount) {
		this.bodyDiscount = bodyDiscount;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getContractSeq() {
		return contractSeq;
	}

	public void setContractSeq(String contractSeq) {
		this.contractSeq = contractSeq;
	}
	
	
	
	
	
}
