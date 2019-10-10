package com.qhc.frye.rest.controller.entity;

import java.util.Date;

import com.qhc.frye.domain.DCurrency;

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
}
