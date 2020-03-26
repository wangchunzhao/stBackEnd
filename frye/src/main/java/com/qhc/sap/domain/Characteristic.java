/**
 * 
 */
package com.qhc.sap.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wang@dxc.com
 *
 */
public class Characteristic {
	private String classCode;
	private String code;
	private String name;
	private boolean isOptional;

	/* 颜色特征 */
	private boolean color = false;
	
	private List<Configuration> configs;
	
	public Characteristic() {
		configs = new ArrayList<Configuration>();
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

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

	public boolean isOptional() {
		return isOptional;
	}

	public void setOptional(boolean isOptional) {
		this.isOptional = isOptional;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public List<Configuration> getConfigs() {
		return configs;
	}

	public void setConfigs(List<Configuration> configs) {
		this.configs = configs;
	}
	
	
	
}
