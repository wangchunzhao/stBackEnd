package com.qhc.frye.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "k_parent_order_version")
public class KParentOrderVersion {
	@Id
	@Column(name = "k_order_version_id",columnDefinition="CHAR")
	private String orderVersionId;

	@Column(name = "k_order_version_id_parent", columnDefinition="CHAR")
	private String parentOrderVersionId;

	@NotNull
	@Column(name = "opt_time", columnDefinition = "datetime")
	@CreatedDate
	public Date optTime;

	@NotNull
	@Column(name = "k_order_info_id",columnDefinition="char",length=32)
	private String orderInfoId;

	public String getOrderVersionId() {
		return orderVersionId;
	}

	public void setOrderVersionId(String orderVersionId) {
		this.orderVersionId = orderVersionId;
	}

	public String getParentOrderVersionId() {
		return parentOrderVersionId;
	}

	public void setParentOrderVersionId(String parentOrderVersionId) {
		this.parentOrderVersionId = parentOrderVersionId;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(String orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

}
