/**
 * 
 */
package com.qhc.sap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name="sap_characteristic")
public class SapCharacteristic {
	@Id
    @NotNull
    @Column(name="code",length=30)
	private String code;
	
	@NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
	
	@NotNull
    @Column(name="is_optional",columnDefinition="BIT")
	private boolean isOptional;
	
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
	public boolean isOptional() {
		return isOptional;
	}
	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

}
