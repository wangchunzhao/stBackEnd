/**
 * 
 */
package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "sap_customer")
public class DCustomer implements Serializable{

	/**
	 * 
	 */
	
	
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=32)
	private String code;
	
	@NotNull
	@Column(name="name",columnDefinition="TEXT")
	private String name;
	
	@NotNull
	@Column(name="change_date",columnDefinition="DATETIME")
	private Date changedDate;
	
	@Column(name="address",columnDefinition="TEXT")
	private String address;
	
	@NotNull
	@Column(name="sap_account_group_code",columnDefinition="CHAR",length=4)
	private String groupCode;
	
	@NotNull
	@Column(name="sap_customer_class_code",columnDefinition="CHAR",length=2)
	private String clazzCode;
	
	@NotNull
	@Column(name="sap_customer_level_code",columnDefinition="CHAR",length=4)
	private String levelCode;
	
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getClazzCode() {
		return clazzCode;
	}
	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}