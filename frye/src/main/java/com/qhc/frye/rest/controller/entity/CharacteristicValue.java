/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import java.util.ArrayList;
import java.util.List;

import com.qhc.frye.domain.DCharacteristic;
import com.qhc.frye.domain.DCharacteristicValue;
import com.qhc.frye.domain.DClassAndCharacter;
import com.qhc.frye.domain.identity.ClassCharacterIdentity;

/**
 * @author wang@dxc.com
 *
 */
public class CharacteristicValue implements InterEntityToDao{
	
	private String code;
	private String name;
	
	private String characteristicCode; 
	private String CharacteristicName;
	
	private String clazzCode;
	
	

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



	public String getClazzCode() {
		return clazzCode;
	}



	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}



	@Override
	public List<Object> toDaos() {
		// TODO Auto-generated method stub
		List<Object> objs = new ArrayList();
		DCharacteristicValue dcv = new DCharacteristicValue();
		dcv.setCode(this.getCode());
		dcv.setName(this.getName());
		dcv.setCharacter(this.getCharacteristicCode());
		
		DCharacteristic dc = new DCharacteristic();
		dc.setCode(this.getCharacteristicCode());
		dc.setName(this.getCharacteristicName());
		
		DClassAndCharacter dcac = new DClassAndCharacter();
		ClassCharacterIdentity cci = new ClassCharacterIdentity();
		cci.setClazzCode(this.getClazzCode());
		cci.setCharacterCode(this.getCharacteristicCode());
		dcac.setCci(cci);
		
		objs.add(dcv);
		objs.add(dc);
		objs.add(dcac);
		return objs;
	}

}
