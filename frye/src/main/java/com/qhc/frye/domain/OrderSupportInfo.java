/**
 * 
 */
package com.qhc.frye.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "k_order_support_info")
@EntityListeners(AuditingEntityListener.class)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class OrderSupportInfo {
	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	
	@NotNull
	@Column(name="contract_number",columnDefinition="CHAR",length =10)
	private String contractNumber;
	
	@NotNull
	@Column(name="opterator_domain_id",columnDefinition="datetime",length=16)
	private String supportorId;
	
	@NotNull
	@LastModifiedDate
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@Column(name="opt_time",columnDefinition="datetime")
	private Date operationTime;
	
	
	@NotNull
	@Column(name="k_orders_id",columnDefinition="CHAR",length=32)
	private String orderId;

	
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getSupportorId() {
		return supportorId;
	}

	public void setSupportorId(String supportorId) {
		this.supportorId = supportorId;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	

}
