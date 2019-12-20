package com.qhc.system.domain;import java.io.Serializable;import java.util.Date;import java.util.List;/** *  * Function: Data transfer object. <br>  * * @author walker */public class UserDto		implements Serializable {	private static final long serialVersionUID = 2121940085189811342L;		/* Id */	private Integer id = null;	/* LDAP域账号，唯一 */	private String userIdentity = null;	/* 用户姓名 */	private String name = null;	/* 用户邮箱 */	private String userMail = null;	/* 1 启用； 0 禁用 */	private Integer isActive = null;	/* 创建人域账号 */	private String creater = null;	/* 创建时间 */	private Date createTime = null;	/* 修改人账号 */	private String updater = null;	/* 修改时间 */	private Date updateTime = null;	/* Tel */	private String tel = null;		private List<RoleDto> roles;		private List<OperationDto> operations;	public UserDto(){	}	public Integer getId() {		return this.id;	}	public void setId(Integer id) {		this.id = id;	}	 	public String getUserIdentity() {		return this.userIdentity;	}	public void setUserIdentity(String userIdentity) {		this.userIdentity = userIdentity;	}	 	public String getName() {		return this.name;	}	public void setName(String name) {		this.name = name;	}	 	public String getUserMail() {		return this.userMail;	}	public void setUserMail(String userMail) {		this.userMail = userMail;	}	 	public Integer getIsActive() {		return this.isActive;	}	public void setIsActive(Integer isActive) {		this.isActive = isActive;	}	 	public String getCreater() {		return this.creater;	}	public void setCreater(String creater) {		this.creater = creater;	}	 	public Date getCreateTime() {		return this.createTime;	}	public void setCreateTime(Date createTime) {		this.createTime = createTime;	}	 	public String getUpdater() {		return this.updater;	}	public void setUpdater(String updater) {		this.updater = updater;	}	 	public Date getUpdateTime() {		return this.updateTime;	}	public void setUpdateTime(Date updateTime) {		this.updateTime = updateTime;	}	 	public String getTel() {		return this.tel;	}	public void setTel(String tel) {		this.tel = tel;	}	 	public List<RoleDto> getRoles() {		return roles;	}	public void setRoles(List<RoleDto> roles) {		this.roles = roles;	}	public List<OperationDto> getOperations() {		return operations;	}	public void setOperations(List<OperationDto> operations) {		this.operations = operations;	}	@Override	public int hashCode() {		final int prime = 31;		int result = 1;		result = prime * result + ((id == null) ? 0 : id.hashCode());		return result;	}	@Override	public boolean equals(Object obj) {		if (this == obj) {			return true;		}		if (obj == null) {			return false;		}		if (getClass() != obj.getClass()) {			return false;		}		final UserDto other = (UserDto) obj;		return (this.id == null ? other.id == null : this.id.equals(other.id));	}		public String toString() {	    final String tab = "  ";	    String str = "";	    str = "User ( "	        + "id = " + this.id + tab	        + "userIdentity = " + this.userIdentity + tab	        + "name = " + this.name + tab	        + "userMail = " + this.userMail + tab	        + "isActive = " + this.isActive + tab	        + "creater = " + this.creater + tab	        + "createTime = " + this.createTime + tab	        + "updater = " + this.updater + tab	        + "updateTime = " + this.updateTime + tab	        + "tel = " + this.tel + tab	        + " )";		    return str;	}}