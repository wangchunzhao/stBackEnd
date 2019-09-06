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
@Table(name = "sap_industry_and_customer")
public class CustomerAffiliation {
	@Id
	@NotNull
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "sap_industry_code",length = 4)
	private String industryCode;
	@NotNull
	@Column(name = "sap_customer_code",length = 16)
	private String customerCode;
	
	
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	
}
