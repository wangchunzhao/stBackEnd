package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "b_settings")
public class Parameter implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	public Integer id;
	
	@NotNull
	@Column(name="code",columnDefinition="CHAR")
	public String code;
	
	@NotNull
	@Column(name="s_value",columnDefinition="TEXT")
	public String sValue;
	
	
	@NotNull
	@Column(name="enable_date")
	public Date enableDate;
	
	@Column(name="comment",columnDefinition="TEXT")
	public String comment;
	
	@NotNull
	@Column(name="operater",columnDefinition="TEXT")
	public String operater;
	
	@NotNull
	@Column(name="opt_time",columnDefinition="datetime")
	public String optTime;
	
	
	@Transient
	public String preValue;
	
	@Transient
	public String afterValue;
	
	@Transient
	public Date preEnableDate;
	
	@Transient
	public Date afterEnableDate;
	
	
	public String getPreValue() {
		return preValue;
	}
	public void setPreValue(String preValue) {
		this.preValue = preValue;
	}
	public String getAfterValue() {
		return afterValue;
	}
	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}
	
	public String getsValue() {
		return sValue;
	}
	public void setsValue(String sValue) {
		this.sValue = sValue;
	}
	public Date getEnableDate() {
		return enableDate;
	}
	public void setEnableDate(Date enableDate) {
		this.enableDate = enableDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	
	public String getOptTime() {
		return optTime;
	}
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getPreEnableDate() {
		return preEnableDate;
	}
	public void setPreEnableDate(Date preEnableDate) {
		this.preEnableDate = preEnableDate;
	}
	public Date getAfterEnableDate() {
		return afterEnableDate;
	}
	public void setAfterEnableDate(Date afterEnableDate) {
		this.afterEnableDate = afterEnableDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
