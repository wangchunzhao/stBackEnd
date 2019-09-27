package com.qhc.frye.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "k_order_version")
public class KOrderVersion {


	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@NotNull
	@Column(name = "version")
	public String version;

	@NotNull
	@Column(name = "status", columnDefinition = "tinyint")
	private Integer status;

	@NotNull
	@Column(name = "create_time", columnDefinition = "datetime")
	@CreatedDate
	private Date createTime;

	@NotNull
	@Column(name = "k_orders_id")
	private int kOrdersId;
	
	
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getkOrdersId() {
		return kOrdersId;
	}

	public void setkOrdersId(int kOrdersId) {
		this.kOrdersId = kOrdersId;
	}

}
