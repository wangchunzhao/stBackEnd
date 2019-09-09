package com.qhc.frye.domain;

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
	
	@Transient
    public List<ApplicationOfRolechange> apps;
	
	@Transient
	public SapSalesOffice region;
	
	@Transient
	public List<Role> roles;
	
	@Transient
	public List<Operations> operations;
	
    
    
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

	public List<ApplicationOfRolechange> getApps() {
		return apps;
	}

	public void setApps(List<ApplicationOfRolechange> apps) {
		this.apps = apps;
	}

	public List<Operations> getOperations() {
		return operations;
	}

	public void setOperations(List<Operations> operations) {
		this.operations = operations;
	}



}
