package com.qhc.frye.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "b_operation2role")
public class Operation2role {
	
	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	
	@NotNull
	@Column(name="opt_time",columnDefinition="datetime")
    private Date optTime;
	
	@NotNull
	@Column(name="isActive",columnDefinition="BIT")
    private int isActive;
	
	@NotNull
	@Column(name="b_operations_id",columnDefinition="CHAR",length = 32)
    private String operationId;
	
	@NotNull
	@Column(name="b_roles_id",columnDefinition="datetime")
    private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	

	
}
