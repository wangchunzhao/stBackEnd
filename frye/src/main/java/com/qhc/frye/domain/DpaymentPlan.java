package com.qhc.frye.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sap_payment_term_bidding_plan")
public class DpaymentPlan implements Serializable{
	
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=3)
	private String code;

    @NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
    
    @NotNull
    @Column(name="is_payment_term",columnDefinition ="BIT",length=1)
	private int paymentTerm;

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

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	
    

}
