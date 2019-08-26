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
    private String mail;
	
	@NotNull
	@Column(name="isActive",length = 1,columnDefinition = "TINYINT")
    private boolean isActive;
	
	@NotNull
	@Column(name = "user_identity",length = 45)
    private String domainId;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	




}
