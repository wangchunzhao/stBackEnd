/**
 * 
 */
package com.qhc.sap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wang@dxc.com
 *
 */
//@Entity
//@Table(name = "sap_default_character_value_view")
public class DefaultCharacterView {
	@Id 
	@Column(name = "id",columnDefinition ="int")
	private int id;
	
	@Column(name = "code",columnDefinition ="CHAR")
	private String valueCode;
	
	@Column(name = "name",columnDefinition ="TEXT")
	private String name;
	
	@Column(name = "sap_materials_code",columnDefinition ="CHAR")
	private String materialCode;
	
	@Column(name = "key_code",columnDefinition ="CHAR")
	private String keyCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	
	

}
