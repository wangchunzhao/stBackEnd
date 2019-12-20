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
@Table(name = "sap_payment_term_bidding_plan")
public class PaymentTerm {
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=4)
	private String code;
	@NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
	@NotNull
    @Column(name="is_payment_term",columnDefinition="BIT",length=1)
	private Boolean isPaymentTerm;
	
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
	public Boolean getIsPaymentTerm() {
		return isPaymentTerm;
	}
	public void setIsPaymentTerm(Boolean isPaymentTerm) {
		this.isPaymentTerm = isPaymentTerm;
	}

}
