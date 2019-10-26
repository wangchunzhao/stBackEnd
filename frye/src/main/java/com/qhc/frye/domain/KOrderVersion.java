package com.qhc.frye.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "k_order_version")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class KOrderVersion {


	@Id
	@NotNull
	@Column(name="id",columnDefinition="char",length=32)
	@GeneratedValue(generator = "jpa-uuid")
	public String id;

	@NotNull
	@Column(name = "version")
	public String version;

	@NotNull
	@Column(name = "status", columnDefinition = "tinyint",length=2)
	private Integer status;

	@NotNull
	@Column(name = "create_time", columnDefinition = "datetime")
	@CreatedDate
	private Date createTime;

	@NotNull
	@Column(name = "k_orders_id",columnDefinition="char",length=32)
	private String orderId;
	
	@NotNull
	@Column(name = "k_order_info_id",columnDefinition="char",length=32)
	private String orderInfoId;
	
	@Column(name = "submit_date")
	private Date submitDate;
	
	@Column(name = "bpm_submit_date")
	private Date bpmSubmitDate;
	
	@Column(name = "opt_time")
	private Date optTime;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(String orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getBpmSubmitDate() {
		return bpmSubmitDate;
	}

	public void setBpmSubmitDate(Date bpmSubmitDate) {
		this.bpmSubmitDate = bpmSubmitDate;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	
	
}
