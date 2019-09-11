package com.qhc.frye.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "k_order_info")
public class KOrderInfo {
	
	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="contract_no",columnDefinition="TEXT")
	private String contractNo;
	
	
	@Column(name="contract_unit",columnDefinition="TEXT")
	private String contractUnit;
	
	@Column(name="area",length = 10)
	private int area;
	
	@Column(name="order_type",length = 10)
	private int orderType;
	
	@Column(name="b2c",length = 10)
	private int b2c;
	
	@Column(name="special_discount",length = 10)
	private int specialDiscount;
	
	@Column(name="create_time",columnDefinition="datetime")
	private Date createTime;
	
	@Column(name="status",length = 10)
	private int status;
	
	@Column(name="sap_status",length = 10)
	private int sapStatus;
	
	

	public KOrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KOrderInfo(@NotNull int id, String contractNo, String contractUnit, int area, int orderType, int b2c,
			int specialDiscount, Date createTime, int status, int sapStatus) {
		super();
		this.id = id;
		this.contractNo = contractNo;
		this.contractUnit = contractUnit;
		this.area = area;
		this.orderType = orderType;
		this.b2c = b2c;
		this.specialDiscount = specialDiscount;
		this.createTime = createTime;
		this.status = status;
		this.sapStatus = sapStatus;
	}

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
	
	
}
