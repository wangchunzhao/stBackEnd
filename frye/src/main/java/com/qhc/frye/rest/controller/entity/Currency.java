package com.qhc.frye.rest.controller.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.SapCurrencySaleType;
import com.qhc.frye.domain.identity.CurrencySaleTypeIdentity;

/**
 * 
 * @author wang@dxc.com
 *
 */
public class Currency extends AbsConObject{

	private double rate;
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o.getClass().equals(this.getClass()) ) {
			DCurrency obj = (DCurrency)o;
			if(obj.getCode().equals(this.getCode())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.getCode().hashCode();
	}
	
	public List<Object> toDaos() {
		List<Object> objs = new ArrayList<Object>();
		DCurrency dCurrency = new DCurrency();
		dCurrency.setCode(this.getCode());
		dCurrency.setName(this.getName());
		dCurrency.setRate(this.getRate());
		objs.add(dCurrency);
		
		SapCurrencySaleType sc = new SapCurrencySaleType();
		CurrencySaleTypeIdentity ci = new CurrencySaleTypeIdentity();
		//
		ci.setSaleTypeCode("20");
		ci.setCurrencyCode(this.getCode());
		sc.setCii(ci);
		objs.add(sc);
		
		return objs;
	}
}
