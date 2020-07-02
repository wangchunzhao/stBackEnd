package com.qhc.sap.domain;

public class QueryOrderStatusDto {
	
	private String vbeln;//订单号 vbeln
	
	private String updkz;//是否订单变更    updkz
	
	private String subrc;//订单创建状态   subrc
	
	private String message;//订单创建message   message 

	public String getVbeln() {
		return vbeln;
	}

	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}

	public String getUpdkz() {
		return updkz;
	}

	public void setUpdkz(String updkz) {
		this.updkz = updkz;
	}

	public String getSubrc() {
		return subrc;
	}

	public void setSubrc(String subrc) {
		this.subrc = subrc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
