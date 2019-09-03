/**
 * 
 */
package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */

@Entity
@Table(name = "b_users")
public class User implements Serializable{
	
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
	
	@OneToMany(mappedBy = "id")
    public Set<ApplicationOfRolechange> apps;
	
	@Transient
	public String rolesName;
	
	@Transient
	public String region;
    
    
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

	public Set<ApplicationOfRolechange> getApps() {
		return apps;
	}

	public void setApps(Set<ApplicationOfRolechange> apps) {
		this.apps = apps;
	}


	public String getRolesName() {
		String roles="";
		if(apps!=null&&apps.size()>0){
			for(ApplicationOfRolechange app:apps) {
				roles = roles+app.getbRolesId()+",";
			}
			if(!"".equals(roles)) {
				roles = roles.substring(0, roles.length()-1);
			}
		}
		return roles;
	}

	public String getRegion() {
		String region="";
		if(apps!=null&&apps.size()>0){
			Iterator<ApplicationOfRolechange> it = apps.iterator();	
			ApplicationOfRolechange app=new ApplicationOfRolechange();
			if(it.hasNext()) {
				app = it.next();
				region = app.getAttachedCode();
			}	
		}
		return region;
	}



}
