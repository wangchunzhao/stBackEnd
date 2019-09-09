/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

/**
 * @author wang@dxc.com
 *
 */
public class Clazz {
	private String code;
	private String name;
	private String characteristicCode; 
	private String CharacteristicName;
	public String getCharacteristicCode() {
		return characteristicCode;
	}
	public void setCharacteristicCode(String characteristicCode) {
		this.characteristicCode = characteristicCode;
	}
	public String getCharacteristicName() {
		return CharacteristicName;
	}
	public void setCharacteristicName(String characteristicName) {
		CharacteristicName = characteristicName;
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

}
