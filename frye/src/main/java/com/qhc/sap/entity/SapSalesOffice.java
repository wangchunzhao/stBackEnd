package com.qhc.sap.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author lizuoshan
 *
 */
@Entity
@Table(name = "sap_sales_office")
public class SapSalesOffice implements Serializable{
	
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=32)
    private String code;
	
	@NotNull
	@Column(name="name",columnDefinition="TEXT")
    private String name;
	
	@NotNull
	@Column(name="sap_sales_type_code",columnDefinition="CHAR",length=2)
	private String typeCode;

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
	
	
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Override
	public boolean equals(Object obj) {
		SapSalesOffice sa = (SapSalesOffice)obj;
		if(sa.code.equals(this.getCode()))
			return true;
		return false;
	}


}
