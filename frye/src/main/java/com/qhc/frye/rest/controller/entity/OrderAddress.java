package com.qhc.frye.rest.controller.entity;

public class OrderAddress {
	
	
	//省市区 00/00/00
	private String pca;
	//地址
	private String address;
	public String getPca() {
		return pca;
	}
	public void setPca(String pca) {
		this.pca = pca;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}