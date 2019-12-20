/**
 * 
 */
package com.qhc.sap.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.qhc.sap.entity.identity.CustomerIndustryIdentity;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "sap_industry_and_customer")
public class CustomerAffiliation {
	
	@EmbeddedId
	private CustomerIndustryIdentity cii;

	public CustomerIndustryIdentity getCii() {
		return cii;
	}

	public void setCii(CustomerIndustryIdentity cii) {
		this.cii = cii;
	}
	@Override
	public String toString() {
		return cii.getCustomerCode()+","+cii.getIndustryCode();
	}
	
}
