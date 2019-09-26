package com.qhc.frye.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "k_order_version")
public class KOrderVersion {

	public enum Status {
		Saved("00"), Submited("01"), Approved("02");

		private final String flag;

		private Status(String flag) {
			this.flag = flag;
		}

		public String getCode() {
			return flag;
		}
	}

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@NotNull
	@Column(name = "version")
	public String version;

	@NotNull
	@Column(name="status") 
    @Enumerated(EnumType.STRING)
	private String status;

	@NotNull
	@Column(name = "create_time", columnDefinition = "datetime")
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
