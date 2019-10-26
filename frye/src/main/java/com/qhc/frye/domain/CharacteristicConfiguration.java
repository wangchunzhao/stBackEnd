/**
 * 
 */
package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wang@dxc.com
 *
 */ 
@Entity
@Table(name = "sap_class_characteristic_value_view")
public class CharacteristicConfiguration {
	
	@Id 
	@Column(name = "value_code",columnDefinition ="CHAR")
	private String valCode;
	
	@Column(name = "value_name",columnDefinition ="TEXT")
	private String valName;
	
	@Column(name = "sap_clazz_code",columnDefinition ="CHAR")
	private String classCode;
	
	@Column(name = "key_code",columnDefinition ="CHAR")
	private String keyCode;
	
	@Column(name = "key_name",columnDefinition ="TEXT")
	private String keyName;


	public String getValCode() {
		return valCode;
	}

	public void setValCode(String valCode) {
		this.valCode = valCode;
	}

	public String getValName() {
		return valName;
	}

	public void setValName(String valName) {
		this.valName = valName;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}


}
