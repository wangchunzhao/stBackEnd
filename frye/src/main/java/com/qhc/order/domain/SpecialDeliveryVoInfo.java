package com.qhc.order.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qhc.order.entity.SpecialDelivery;

/**
 * @author lizuoshan
 *
 */
@Entity
@Table(name = "k_speical_order_vo_view")
public class SpecialDeliveryVoInfo implements Serializable{
 
	@Id
	@Column(name="id")
	public String id;
	
	@Column(name="k_orders_id",columnDefinition="char")
	public String kOrderId;
	
	@Column(name="sequence_number",columnDefinition="char")
	public String sequenceNumber;
	
	@Column(name="order_type_code",columnDefinition="char")
	public String orderTypeCode;
	
	@Column(name="create_time",columnDefinition="datetime")
	public Date createTime;
	
	@Column(name="owner_domain_id")
	public String ownerDomainId;
	
	@Column(name="owner_name",columnDefinition="TEXT")
	private String	ownerName;
	
	@Column(name="sales_tel")
	private String	salesTel;
	
	@Column(name="contractor_code")
	private String	contractorCode;
	
	@Column(name="contractor_name",columnDefinition="TEXT")
	private String	contractorName;
	
	@Column(name="contractor_class_code",columnDefinition="char")
	private String	contractorClassCode;
	
	@Column(name="contractor_class_name",columnDefinition="TEXT")
	private String	contractorClassName;
	
	@Column(name="office_code",columnDefinition="char")
	private String	officeCode;
	
	
	
	@Column(name="k_order_version_id",columnDefinition="char")
	public String kOrderVersionId;
	
	@Transient
	public Double distcount;

	@Transient
	public String startTime;
	
	@Transient
	public String endTime;
	
	
	
	

	public Double getDistcount() {
		return distcount;
	}

	public void setDistcount(Double distcount) {
		this.distcount = distcount;
	}


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

	public String getOrderTypeCode() {
		return orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOwnerDomainId() {
		return ownerDomainId;
	}

	public void setOwnerDomainId(String ownerDomainId) {
		this.ownerDomainId = ownerDomainId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getSalesTel() {
		return salesTel;
	}

	public void setSalesTel(String salesTel) {
		this.salesTel = salesTel;
	}

	public String getContractorCode() {
		return contractorCode;
	}

	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public String getContractorClassCode() {
		return contractorClassCode;
	}

	public void setContractorClassCode(String contractorClassCode) {
		this.contractorClassCode = contractorClassCode;
	}

	public String getContractorClassName() {
		return contractorClassName;
	}

	public void setContractorClassName(String contractorClassName) {
		this.contractorClassName = contractorClassName;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
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

	public String getkOrderVersionId() {
		return kOrderVersionId;
	}

	public void setkOrderVersionId(String kOrderVersionId) {
		this.kOrderVersionId = kOrderVersionId;
	}

	public String getkOrderId() {
		return kOrderId;
	}

	public void setkOrderId(String kOrderId) {
		this.kOrderId = kOrderId;
	}


	
	
	
	
	
	
	
	
	


}
