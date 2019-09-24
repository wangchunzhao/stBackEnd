package com.qhc.frye.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "k_order_info")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class KOrderInfo {
	
	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	public int id;
	
	
	@Column(name="contract_no",columnDefinition="TEXT")
	public String contractNo;
	
	
	@Column(name="contract_unit",columnDefinition="TEXT")
	public String contractUnit;
	
	@Column(name="area",length = 10)
	public int area;
	
	@Column(name="order_type",length = 10)
	public int orderType;
	
	@Column(name="b2c",length = 10)
	public int b2c;
	
	@Column(name="special_discount",length = 10)
	public int specialDiscount;
	
	@Column(name="create_time",columnDefinition="datetime")
	public Date createTime;
	
	@Column(name="status",length = 10)
	public int status;
	
	@Column(name="sap_status",length = 10)
	public int sapStatus;
	
	@Column(name="createId",length = 10)
	public int createId;
	
	@Transient
	public String startTime;
	
	@Transient
	public String endTime;
	
	@Transient
	public List<SpecialDelivery> applyList;
	
	
	@Column(name="order_number")
	private String orderNumber;//流水号
	
	@Column(name="customer_manager")
	private String	customerManager	;//	签约人//客户经理
	
	@Column(name="customer_type")
	private Integer	customerType	;//	客户性质
	
	@Column(name="shop_name")
	private String	shopName	;//	店名
	
	@Column(name="terminal_customers")
	private String	terminalCustomers	;//	终端客户性质
	
	@Column(name="is_reform_shop")
	private Integer	isReformShop	;//	是否改造店
	
	@Column(name="settlement_method")
	private String	settlementMethod	;//	结算方式
	
	@Column(name="is_new_customer",columnDefinition ="TINYINT",length=1)
	private Integer	isNewCustomer	;//是否新客户
	
	
	
	
	
	public KOrderInfo() {
		super();
	}

	/*
	 * public KOrderInfo(@NotNull int id, String contractNo, String contractUnit,
	 * int area, int orderType, int b2c, int specialDiscount, Date createTime, int
	 * status, int sapStatus, int createId) { super(); this.id = id; this.contractNo
	 * = contractNo; this.contractUnit = contractUnit; this.area = area;
	 * this.orderType = orderType; this.b2c = b2c; this.specialDiscount =
	 * specialDiscount; this.createTime = createTime; this.status = status;
	 * this.sapStatus = sapStatus; this.createId = createId; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractUnit() {
		return contractUnit;
	}

	public void setContractUnit(String contractUnit) {
		this.contractUnit = contractUnit;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getB2c() {
		return b2c;
	}

	public void setB2c(int b2c) {
		this.b2c = b2c;
	}

	public int getSpecialDiscount() {
		return specialDiscount;
	}

	public void setSpecialDiscount(int specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSapStatus() {
		return sapStatus;
	}

	public void setSapStatus(int sapStatus) {
		this.sapStatus = sapStatus;
	}

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
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

	public List<SpecialDelivery> getApplyList() {
		return applyList;
	}

	public void setApplyList(List<SpecialDelivery> applyList) {
		this.applyList = applyList;
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

	public Integer getIsReformShop() {
		return isReformShop;
	}

	public void setIsReformShop(Integer isReformShop) {
		this.isReformShop = isReformShop;
	}

	public String getSettlementMethod() {
		return settlementMethod;
	}

	public void setSettlementMethod(String settlementMethod) {
		this.settlementMethod = settlementMethod;
	}

	public Integer getIsNewCustomer() {
		return isNewCustomer;
	}

	public void setIsNewCustomer(Integer isNewCustomer) {
		this.isNewCustomer = isNewCustomer;
	}

	
	
}
