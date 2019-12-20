package com.qhc.order.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "k_orders")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DOrder implements Serializable{
	private final static int FACTOR = 43;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8162013779997326172L;

	@Id
    @NotNull
    @Column(name="id",columnDefinition="char",length=32)
    @GeneratedValue(generator = "jpa-uuid")
	public String id;
	
	@NotNull
	@Column(name="sequence_number",columnDefinition="char")
	public String sequenceNumber;
	
	@NotNull
	@Column(name="order_type_code",columnDefinition="char")
	public String orderTypeCode;
	
	@NotNull
	@Column(name="create_time",columnDefinition="datetime")
	@CreatedDate
	public Date createTime;
	
	@NotNull
	@Column(name="owner_domain_id")
	public String ownerDomainId;
	
	@NotNull
	@Column(name="owner_name",columnDefinition="TEXT")
	private String	ownerName;
	
	@Column(name="sales_tel")
	private String	salesTel;
	
	@NotNull
	@Column(name="contractor_code")
	private String	contractorCode;
	
	@NotNull
	@Column(name="contractor_name",columnDefinition="TEXT")
	private String	contractorName;
	
	@NotNull
	@Column(name="contractor_class_code",columnDefinition="char")
	private String	contractorClassCode;
	
	@NotNull
	@Column(name="contractor_class_name",columnDefinition="TEXT")
	private String	contractorClassName;
	
	@Column(name="office_code",columnDefinition="char")
	private String	officeCode;
	

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
	@Override
	public int hashCode() {
		return this.getSequenceNumber().hashCode()*FACTOR;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass().equals(this.getClass()) ) {
			DOrder obj = (DOrder)o;
			if(obj.getSequenceNumber().equals(this.getSequenceNumber())) {
				return true;
			}
		}
		return false;
	}
	
	
}
