package com.qhc.system.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * @author lizuoshan
 *
 */
@Entity
@Table(name = "user_operation_view")
public class UserOperationView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
    private String id;
	
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="user_mail")
    private String userMail;
	
	@Column(name="user_identity")
    private String userIdentity;
	
	@Column(name="user_isActive",columnDefinition ="TINYINT")
	private Integer userIsActive;
	
	@Column(name="role_id")
    private Integer roleId;
	
	@Column(name="role_name",columnDefinition = "TEXT")
	private String roleName;
	
	@Column(name="attached_code",columnDefinition = "CHAR")
	private String attachedCode;
	
	@Column(name="attached_name",columnDefinition = "TEXT")
	private String attachedName;
	
	@Column(name="operation_id",columnDefinition = "CHAR")
	private String operationId;
	
	@Column(name="operation_name",columnDefinition = "TEXT")
	private String operationName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAttachedCode() {
		return attachedCode;
	}

	public void setAttachedCode(String attachedCode) {
		this.attachedCode = attachedCode;
	}

	public String getAttachedName() {
		return attachedName;
	}

	public void setAttachedName(String attachedName) {
		this.attachedName = attachedName;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
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

	public Integer getUserIsActive() {
		return userIsActive;
	}

	public void setUserIsActive(Integer userIsActive) {
		this.userIsActive = userIsActive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	
	




}
