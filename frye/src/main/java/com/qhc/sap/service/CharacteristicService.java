/**
 * 
 */
package com.qhc.sap.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.sap.dao.CharacteristicDefaultRepository;
import com.qhc.sap.dao.CharacteristicRepository;
import com.qhc.sap.dao.CharacteristicValueRepository;
import com.qhc.sap.dao.ClassAndCharacterRepository;
import com.qhc.sap.dao.ClazzOfMaterialRepository;
import com.qhc.sap.domain.CharacteristicValueDto;
import com.qhc.sap.domain.Clazz;
import com.qhc.sap.domain.DefaultCharacteristicsDto;
import com.qhc.sap.entity.Characteristic;
import com.qhc.sap.entity.CharacteristicDefault;
import com.qhc.sap.entity.CharacteristicValue;
import com.qhc.sap.entity.ClassAndCharacter;
import com.qhc.sap.entity.ClazzOfMaterial;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CharacteristicService {
	@Autowired
	private ClazzOfMaterialRepository clazzRepo;
	
	@Autowired
	private CharacteristicRepository characterRepo;
	
	@Autowired
	private CharacteristicValueRepository charaValueRepo;
	
	@Autowired
	private ClassAndCharacterRepository classAndCharaRepo;
	
	@Autowired
	private CharacteristicDefaultRepository CharacterDefaultRep;
	/**
	 * 
	 * @param clazz
	 */
	public void saveClass(List<Clazz> clazz) {
		Set<ClazzOfMaterial> dcs = new HashSet<ClazzOfMaterial>();
		for(Clazz cl:clazz) {
			ClazzOfMaterial dc = new ClazzOfMaterial();
			dc.setCode(cl.getCode());
			dc.setName(cl.getName());
			//从sap抽取的数据该字段为0，其他自己存在的该字段为1
			dc.setReserved(false);
			dcs.add(dc);
		}
		clazzRepo.saveAll(dcs);
	}
	/**
	 * 
	 * @param chaValues
	 */
	public void saveCharacteristicValue(List<CharacteristicValueDto> chaValues) {
		Set<Characteristic> dcs = new HashSet<Characteristic>();
		Set<CharacteristicValue> dcvs = new HashSet<CharacteristicValue>();
		Set<ClassAndCharacter> cacs = new HashSet<ClassAndCharacter>();
		//
		for(CharacteristicValueDto cv: chaValues) {		
			cacs.add(cv.toDClassAndCharacter());
			dcs.add(cv.toDCharacteristic());
			if(!dcvs.contains(cv.toDCharacteristicValue())) {
				dcvs.add(cv.toDCharacteristicValue());
			}
		}	
		characterRepo.saveAll(dcs);
		//因为特征值的表有自增长的id，两表有关联，所以要先删除
		CharacterDefaultRep.deleteAll();
		charaValueRepo.deleteAll();
		//
		charaValueRepo.saveAll(dcvs);
		classAndCharaRepo.saveAll(cacs);
	}
	
	/**
	 * 
	 * @param defaultChavalue
	 */
	public void saveCharacteristicDefault(List<DefaultCharacteristicsDto> defaultChavalue) {
		Set<CharacteristicDefault> dcs = new HashSet<CharacteristicDefault>();
		for(DefaultCharacteristicsDto cd: defaultChavalue) {
			try {
				//根据特征值的code和特征的code查询出特征值的ID，因为默认特征的表和特征值的表是根据自增长的ID关联的
				int valueId =  charaValueRepo.selectId(cd.getCharacterValue(), cd.getCharacteristic());
				//
				CharacteristicDefault defaultCh = new CharacteristicDefault();
				defaultCh.setMaterialsCode(cd.getMaterialCode());
				defaultCh.setCharacteristicCode(cd.getCharacteristic());
				defaultCh.setValueId(valueId);
				dcs.add(defaultCh);
			} catch (Exception e) {
				System.out.println("查不到特征值的数据");
				continue;
			}
		}
		CharacterDefaultRep.saveAll(dcs);
	}
	
	
	
}
