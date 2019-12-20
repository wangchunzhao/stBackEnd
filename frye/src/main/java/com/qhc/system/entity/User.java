package com.qhc.system.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.qhc.sap.entity.SapSalesOffice;



@Entity
@Table(name = "b_users")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
	public int id;
	
	@NotNull
	@Column(name = "user_mail",length = 45)
	public String userMail;
	
	@NotNull
	@Column(name = "user_identity",length = 45)
	public String userIdentity;
	
	@NotNull
	@Column(name="isActive",columnDefinition ="TINYINT",length=1)
	public int isActive;
	
	@Column(name = "name",columnDefinition ="TEXT",length = 45)
	public String userName;
	
	@Column(name = "tel",length = 16)
	public String tel;
	
	@Transient
    public List<UserRole> apps;
	
	@Transient
	public SapSalesOffice region;
	
	@Transient
	public List<Role> roles;
	
	@Transient
	public List<Operation> operations;
	
    
    
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SapSalesOffice getRegion() {
		return region;
	}

	public void setRegion(SapSalesOffice region) {
		this.region = region;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<UserRole> getApps() {
		return apps;
	}

	public void setApps(List<UserRole> apps) {
		this.apps = apps;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	

}
