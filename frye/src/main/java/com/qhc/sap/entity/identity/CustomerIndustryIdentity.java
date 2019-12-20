/**
 * 
 */
package com.qhc.sap.entity.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author wang@dxc.com
 *
 */

@Embeddable
public class CustomerIndustryIdentity implements Serializable {
	private static final int FACTOR = 17;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1119059651546050760L;

	@Column(name = "sap_customer_code", nullable = false, length = 11)
	private String customerCode;

	@Column(name = "sap_industry_code", nullable = false, length = 11)
	private String industryCode;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	
	@Override
	public boolean equals(Object o) {

		if (this.getClass() == o.getClass()) {

			CustomerIndustryIdentity obj = (CustomerIndustryIdentity) o;
			if (obj.getCustomerCode().equals(this.getCustomerCode()))
				if (obj.getIndustryCode().equals(this.getIndustryCode()))
					return true;
		}

		return false;

	}

	@Override
	public int hashCode() {
		return this.getCustomerCode().hashCode() * FACTOR + this.getIndustryCode().hashCode();
	}
}
