/**
 * 
 */
package com.qhc.order.domain;

/**
 * @author wwang67
 *
 */
public abstract class AbsConObject {
	private String code;
	private String name;
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
	
	//public abstract Object toDao();
	
}
