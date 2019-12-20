package com.qhc.sap.entity;

import java.io.Serializable;

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
@Table(name="sap_industry_code")
public class IndustryCode implements Serializable{
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=3)
	private String code;

    @NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
    
    @NotNull
    @Column(name="is_fordealer",columnDefinition="BIT",length=1)
    private boolean isFordealer;
    

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

	public boolean isFordealer() {
		return isFordealer;
	}

	public void setFordealer(boolean isFordealer) {
		this.isFordealer = isFordealer;
	}

	@Override
	public boolean equals(Object o) {
		if(o.getClass().equals(this.getClass()) ) {
			IndustryCode obj = (IndustryCode)o;
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
