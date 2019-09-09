package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name="sap_clazz")
public class DClazz {
	@Id
    @NotNull
    @Column(name="code",length=18)
	private String code;
	@NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
	
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
	
}
