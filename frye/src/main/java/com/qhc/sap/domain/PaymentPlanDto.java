package com.qhc.sap.domain;

public class PaymentPlanDto {

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
