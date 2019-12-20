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
@Table(name="sap_incoterms")
public class Incoterm {
	
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=3)
	private String code;

    @NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
    
    @Column(name="sap_sales_type_code",columnDefinition="CHAR")
	private String sapSalesTypeCode;

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

	public String getSapSalesTypeCode() {
		return sapSalesTypeCode;
	}

	public void setSapSalesTypeCode(String sapSalesTypeCode) {
		this.sapSalesTypeCode = sapSalesTypeCode;
	}
    
    

}
