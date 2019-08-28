/**
 * 
 */
package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */

@Entity
@Table(name = "b_users")
public class User {
	
	

	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
    private int id;
	
	@NotNull
	@Column(name = "user_mail",length = 45)
    private String userMail;
	
	@NotNull
	@Column(name = "user_identity",length = 45)
    private String userIdentity;
	
	@NotNull
	@Column(name="isActive",columnDefinition = "INTEGER",length = 1)
    private int isActive;
	
	
	
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}




}
