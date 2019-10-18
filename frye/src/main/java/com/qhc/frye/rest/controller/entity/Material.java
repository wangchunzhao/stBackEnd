/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wang@dxc.com
 *
 */
public class Material implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4087877900159424776L;
	
	public final static String MATERIAL_CODE = "59870645008146f9938f7e8718031778";
	
	private String code;
	private String description;
	private boolean isConfigurable;
	private String groupCode;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isConfigurable() {
		return isConfigurable;
	}
	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	
}
