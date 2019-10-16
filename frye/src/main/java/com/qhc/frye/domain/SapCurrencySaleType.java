package com.qhc.frye.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qhc.frye.domain.identity.CurrencySaleTypeIdentity;
import com.qhc.frye.domain.identity.CustomerIndustryIdentity;

@Entity
@Table(name = "sap_currency_sale_type")
public class SapCurrencySaleType {
	
	@EmbeddedId
	private CurrencySaleTypeIdentity cii;

	public CurrencySaleTypeIdentity getCii() {
		return cii;
	}

	public void setCii(CurrencySaleTypeIdentity cii) {
		this.cii = cii;
	}
	
	@Override
	public String toString() {
		return cii.getSaleTypeCode()+","+cii.getCurrencyCode();
	}

}
