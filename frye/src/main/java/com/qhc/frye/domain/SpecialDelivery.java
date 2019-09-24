package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author lizuoshan
 *
 */
@Entity
@Table(name = "k_speical_order_application")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SpecialDelivery implements Serializable{
	
    
	@Id
	@NotNull
	@Column(name="id")
	public Integer applyId;
    
	@NotNull
	@Column(name="applyer")
	public String applyer;//申请人
    
	@Column(name="approver")
    public String approver;//通过人
    
	@NotNull
	@Column(name="apply_time",columnDefinition = "datetime")
    public Date applyTime;//申请时间
    
	@Column(name="approval_time",columnDefinition = "datetime")
    public Date approvalTime;//通过时间
    
//	@Column(name="k_order_version_id")
//    public Integer kOrderVersionId;//版本id
	
	@NotNull
	@Column(name = "k_orders_id")
	public Integer ordersId;
	
//	@Transient
	@Column(name="apply_status")
	public Integer applyStatus;


	@Transient
	public String startTime;
	
	@Transient
	public String endTime;
	
	@Column(name="receive_mail_time",columnDefinition = "text")
	private String receiveMailTime;
	
	@Column(name="contract_time",columnDefinition = "text")
	private String contractTime;
	
	@Column(name="pay_advance_payment_time",columnDefinition = "text")
	private String payAdvancePaymentTime;
	
	@Column(name="remark",columnDefinition = "text")
	private String remark;
	
	@Column(name="enclosure_path",columnDefinition = "text")
	private String enclosurePath;
	
	@Column(name="enclosure_name",columnDefinition = "text")
	private String enclosureName;
	

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}



	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
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

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public String getReceiveMailTime() {
		return receiveMailTime;
	}

	public void setReceiveMailTime(String receiveMailTime) {
		this.receiveMailTime = receiveMailTime;
	}

	public String getContractTime() {
		return contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}

	public String getPayAdvancePaymentTime() {
		return payAdvancePaymentTime;
	}

	public void setPayAdvancePaymentTime(String payAdvancePaymentTime) {
		this.payAdvancePaymentTime = payAdvancePaymentTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEnclosurePath() {
		return enclosurePath;
	}

	public void setEnclosurePath(String enclosurePath) {
		this.enclosurePath = enclosurePath;
	}

	public String getEnclosureName() {
		return enclosureName;
	}

	public void setEnclosureName(String enclosureName) {
		this.enclosureName = enclosureName;
	}


	
	
	


}
