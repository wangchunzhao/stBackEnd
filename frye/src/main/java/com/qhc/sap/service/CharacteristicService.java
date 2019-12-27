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
import com.qhc.sap.entity.SapCharacteristic;
import com.qhc.sap.entity.SapCharacteristicDefault;
import com.qhc.sap.entity.SapCharacteristicValue;
import com.qhc.sap.entity.ClassAndCharacter;
import com.qhc.sap.entity.SapMaterialClazz;

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
		Set<SapMaterialClazz> dcs = new HashSet<SapMaterialClazz>();
		for(Clazz cl:clazz) {
			SapMaterialClazz dc = new SapMaterialClazz();
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
		Set<SapCharacteristic> dcs = new HashSet<SapCharacteristic>();
		Set<SapCharacteristicValue> dcvs = new HashSet<SapCharacteristicValue>();
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
		Set<SapCharacteristicDefault> dcs = new HashSet<SapCharacteristicDefault>();
		for(DefaultCharacteristicsDto cd: defaultChavalue) {
			try {
				//根据特征值的code和特征的code查询出特征值的ID，因为默认特征的表和特征值的表是根据自增长的ID关联的
				int valueId =  charaValueRepo.selectId(cd.getCharacterValue(), cd.getCharacteristic());
				//
				SapCharacteristicDefault defaultCh = new SapCharacteristicDefault();
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
