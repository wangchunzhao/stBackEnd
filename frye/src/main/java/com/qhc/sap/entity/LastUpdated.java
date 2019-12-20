/**
 * 
 */
package com.qhc.sap.entity;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "sap_last_updated")
public class LastUpdated implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7439262850290896716L;

	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=32)
	private String code;
	
	@NotNull
	@Column(name = "name",columnDefinition="TEXT")
	public String name;
	
	@NotNull
	@Column(name = "update_date")
	public Date lastUpdate;

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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	

}
