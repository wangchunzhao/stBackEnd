package com.qhc.sap.entity.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class CurrencySaleTypeIdentity implements Serializable{
	
	private static final int FACTOR = 17;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1119059651546050760L;
	
	@Column(name = "sap_sales_type_code",columnDefinition="CHAR")
	private String saleTypeCode;

	@Column(name = "sap_currency_code", columnDefinition="CHAR")
	private String currencyCode;

	public String getSaleTypeCode() {
		return saleTypeCode;
	}

	public void setSaleTypeCode(String saleTypeCode) {
		this.saleTypeCode = saleTypeCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this.getClass() == o.getClass()) {

			CurrencySaleTypeIdentity obj = (CurrencySaleTypeIdentity) o;
			if (obj.getSaleTypeCode().equals(this.getSaleTypeCode()))
				if (obj.getCurrencyCode().equals(this.getCurrencyCode()))
					return true;
		}

		return false;

	}

	@Override
	public int hashCode() {
		return this.getSaleTypeCode().hashCode() * FACTOR + this.getCurrencyCode().hashCode();
	}

}
