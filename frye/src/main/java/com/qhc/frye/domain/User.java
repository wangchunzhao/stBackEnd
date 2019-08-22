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
	@Column(length = 64)
    private String user_mail;
	
	@NotNull
	@Column(length = 1)
    private boolean active;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


}
