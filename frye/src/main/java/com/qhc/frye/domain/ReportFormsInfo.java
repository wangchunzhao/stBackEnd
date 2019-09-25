package com.qhc.frye.domain;

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
@Entity
@Table(name = "report_forms_info_view")
public class ReportFormsInfo implements Serializable {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="order_number")
	private String	orderNumber	;//	单据编号//订单编号
	
	@Column(name="customer_manager")
	private String	customerManager	;//	签约人//客户经理
	
	@Column(name="area")
	private Integer	area	;//	区域
	
	@Column(name="centre")
	private String	centre	;//	中心
	
	@Column(name="contract_date")
	private Date	contractDate	;//	签约日期
	
	@Column(name="contract_no")
	private String	contractNo	;//	合同号
	
	@Column(name="customer_no")
	private String	customerNo	;//	客户编号
	
	@Column(name="contract_unit")
	private String	contractUnit	;//	签约单位
	
	@Column(name="customer_type")
	private Integer	customerType	;//	客户性质
	
	@Column(name="shop_name")
	private String	shopName	;//	店名
	
	@Column(name="terminal_customers")
	private String	terminalCustomers	;//	终端客户性质
	
	@Column(name="is_special_discount")
	private Integer	isSpecialDiscount	;//	是否特批折扣
	
	@Column(name="is_reform_shop")
	private Integer	isReformShop	;//	是否改造店
	
	@Column(name="contract_amount")
	private BigDecimal contractAmount	;//	合同金额
	
	@Column(name="status")
	private Integer	status	;//	合同状态
	
	@Column(name="gross_profit")
	private BigDecimal	grossProfit	;//	毛利率
	
	@Column(name="is_long_term_discount")
	private Integer	isLongTermDiscount	;//	是否长期折扣
	
	@Column(name="discount")
	private String	discount	;//	折扣
	
	@Column(name="product_specification")
	private String	productSpecification	;//	产品规格型号
	
	@Column(name="material_specific_Number")
	private String	materialSpecificNumber	;//	物料专用号
	
	@Column(name="material_attribute")
	private String	materialAttribute	;//	物料属性
	
	@Column(name="contract_num")
	private Integer	contractNum	;//	合同数量
	
	@Column(name="sales_price")
	private BigDecimal	salesPrice	;//	销售单价
	
	@Column(name="sales_amount")
	private BigDecimal	salesAmount	;//	销售金额
	
	@Column(name="unit")
	private String	unit	;//	单位
	
	@Column(name="receiver_address")
	private String	receiverAddress	;//	到货地址
	
	@Column(name="require_delivery_time")
	private Date	requireDeliveryTime	;//	要求发货时间
	
	@Column(name="install_company")
	private String	installCompany	;//	安装公司
	
	@Column(name="receive_type")
	private String	receiveType	;//	收货方式
	
	@Column(name="authorized_person")
	private String	authorizedPerson	;//	授权人及身份证号
	
	@Column(name="authorized_person_phone")
	private String	authorizedPersonPhone	;//	授权人电话
	
	@Column(name="receiver_id")
	private String	receiverID	;//	收货人身份证号
	
	@Column(name="settlement_method")
	private String	settlementMethod	;//	结算方式
	
	
	@Column(name="freight",columnDefinition = "DECIMAL")
	private BigDecimal	freight	;//运费
	
	@Column(name="warranty_period",columnDefinition = "Double")
	private Double	warrantyPeriod	;//保修期限（年）
	
	@Column(name="currency")
	private Integer	currency	;//币别
	

	@Column(name="contract_curreny_amount",columnDefinition = "DECIMAL")
	private BigDecimal	contractCurrenyAmount	;//原币合同金额
	
	@Column(name="exchange_rate",columnDefinition = "DECIMAL")
	private BigDecimal	exchangeRate	;//汇率
	
	@Column(name="is_new_customer",columnDefinition ="TINYINT",length=1)
	private Integer	isNewCustomer	;//是否新客户
	
	
	
	
	@Column(name="area_name")
	private String areaName;//大区名称 
	@Column(name="status_name")
	private String	statusName	;//	合同状态名称
	@Column(name="customer_type_name")
	private String	customerTypeName	;//	客户性质名称
	
	
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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
	public Date getContractDate() {
		return contractDate;
	}
	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getContractUnit() {
		return contractUnit;
	}
	public void setContractUnit(String contractUnit) {
		this.contractUnit = contractUnit;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getCustomerType() {
		return customerType;
	}
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getTerminalCustomers() {
		return terminalCustomers;
	}
	public void setTerminalCustomers(String terminalCustomers) {
		this.terminalCustomers = terminalCustomers;
	}
	public Integer getIsSpecialDiscount() {
		return isSpecialDiscount;
	}
	public void setIsSpecialDiscount(Integer isSpecialDiscount) {
		this.isSpecialDiscount = isSpecialDiscount;
	}
	public Integer getIsReformShop() {
		return isReformShop;
	}
	public void setIsReformShop(Integer isReformShop) {
		this.isReformShop = isReformShop;
	}
	public BigDecimal getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
	public Integer getIsLongTermDiscount() {
		return isLongTermDiscount;
	}
	public void setIsLongTermDiscount(Integer isLongTermDiscount) {
		this.isLongTermDiscount = isLongTermDiscount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getProductSpecification() {
		return productSpecification;
	}
	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
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
	public Integer getContractNum() {
		return contractNum;
	}
	public void setContractNum(Integer contractNum) {
		this.contractNum = contractNum;
	}
	public BigDecimal getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}
	public BigDecimal getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public Date getRequireDeliveryTime() {
		return requireDeliveryTime;
	}
	public void setRequireDeliveryTime(Date requireDeliveryTime) {
		this.requireDeliveryTime = requireDeliveryTime;
	}
	public String getInstallCompany() {
		return installCompany;
	}
	public void setInstallCompany(String installCompany) {
		this.installCompany = installCompany;
	}
	public String getReceiveType() {
		return receiveType;
	}
	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	public String getAuthorizedPerson() {
		return authorizedPerson;
	}
	public void setAuthorizedPerson(String authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
	}
	public String getAuthorizedPersonPhone() {
		return authorizedPersonPhone;
	}
	public void setAuthorizedPersonPhone(String authorizedPersonPhone) {
		this.authorizedPersonPhone = authorizedPersonPhone;
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
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	
	
	
}
