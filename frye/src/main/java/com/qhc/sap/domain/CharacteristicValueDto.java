/**
 * 
 */
package com.qhc.sap.domain;

import java.util.ArrayList;
import java.util.List;

import com.qhc.order.domain.InterEntityToEntity;
import com.qhc.sap.entity.SapCharacteristic;
import com.qhc.sap.entity.SapCharacteristicValue;
import com.qhc.sap.entity.ClassAndCharacter;
import com.qhc.sap.entity.identity.ClassCharacterIdentity;

/**
 * @author wang@dxc.com
 *
 */
public class CharacteristicValueDto implements InterEntityToEntity{
	
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
	public List<Object> toEntity() {
		List<Object> objs = new ArrayList();
		SapCharacteristicValue dcv = new SapCharacteristicValue();
		dcv.setCode(this.getCode());
		dcv.setName(this.getName());
		dcv.setCharacter(this.getCharacteristicCode());
		
		SapCharacteristic dc = new SapCharacteristic();
		dc.setCode(this.getCharacteristicCode());
		dc.setName(this.getCharacteristicName());
		//从SAP取数默认值为   0 
		dc.setOptional(false);
		
		ClassAndCharacter dcac = new ClassAndCharacter();
		ClassCharacterIdentity cci = new ClassCharacterIdentity();
		cci.setClazzCode(this.getClazzCode());
		cci.setCharacterCode(this.getCharacteristicCode());
		dcac.setCci(cci);
		
		objs.add(dcv);
		objs.add(dc);
		objs.add(dcac);
		return objs;
	}
	
	public SapCharacteristicValue toDCharacteristicValue() {
		SapCharacteristicValue dcv = new SapCharacteristicValue();
		dcv.setCode(this.getCode());
		dcv.setName(this.getName());
		dcv.setCharacter(this.getCharacteristicCode());
		return dcv;
	}
	
	public SapCharacteristic toDCharacteristic() {
		SapCharacteristic dc = new SapCharacteristic();
		dc.setCode(this.getCharacteristicCode());
		dc.setName(this.getCharacteristicName());
		//从SAP取数默认值为   0 
		dc.setOptional(false);
		return dc;
	}
	
	public ClassAndCharacter toDClassAndCharacter() {
		ClassAndCharacter dcac = new ClassAndCharacter();
		ClassCharacterIdentity cci = new ClassCharacterIdentity();
		cci.setClazzCode(this.getClazzCode());
		cci.setCharacterCode(this.getCharacteristicCode());
		dcac.setCci(cci);
		return dcac;
	}

}
