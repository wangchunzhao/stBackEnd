package com.qhc.sap.entity;

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
@Table(name="sap_industry")
public class Industry {
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=4)
	private String code;
	
	@NotNull
	@Column(name = "name",columnDefinition="TEXT")
	private String name;
	
	@NotNull
	@Column(name = "is_reserved",columnDefinition="BIT")
	private boolean isReserved;
	
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
	
	public boolean isReserved() {
		return isReserved;
	}
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	@Override
	public boolean equals(Object o) {
		if(o.getClass().equals(this.getClass()) ) {
			Industry obj = (Industry)o;
			if(obj.getCode().equals(this.getCode())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.getCode().hashCode();
	}
	
}
