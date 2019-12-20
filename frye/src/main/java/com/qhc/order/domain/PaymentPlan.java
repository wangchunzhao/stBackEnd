package com.qhc.order.domain;

public class PaymentPlan {
	
	private String code;
	
	private String name;
	
	private Boolean paymentTerm;

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

	public Boolean getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(Boolean paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

}
