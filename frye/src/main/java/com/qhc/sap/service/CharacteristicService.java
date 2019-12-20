/**
 * 
 */
package com.qhc.sap.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.order.domain.CharacteristicValue;
import com.qhc.order.domain.Clazz;
import com.qhc.order.domain.DefaultCharacteristics;
import com.qhc.sap.dao.CharacteristicDefaultRepository;
import com.qhc.sap.dao.CharacteristicRepository;
import com.qhc.sap.dao.CharacteristicValueRepository;
import com.qhc.sap.dao.ClassAndCharacterRepository;
import com.qhc.sap.dao.ClazzOfMaterialRepository;
import com.qhc.sap.entity.DCharacteristic;
import com.qhc.sap.entity.DCharacteristicDefault;
import com.qhc.sap.entity.DCharacteristicValue;
import com.qhc.sap.entity.DClassAndCharacter;
import com.qhc.sap.entity.DClazzOfMaterial;

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
		Set<DClazzOfMaterial> dcs = new HashSet<DClazzOfMaterial>();
		for(Clazz cl:clazz) {
			DClazzOfMaterial dc = new DClazzOfMaterial();
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
	public void saveCharacteristicValue(List<CharacteristicValue> chaValues) {
		Set<DCharacteristic> dcs = new HashSet<DCharacteristic>();
		Set<DCharacteristicValue> dcvs = new HashSet<DCharacteristicValue>();
		Set<DClassAndCharacter> cacs = new HashSet<DClassAndCharacter>();
		//
		for(CharacteristicValue cv: chaValues) {		
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
	public void saveCharacteristicDefault(List<DefaultCharacteristics> defaultChavalue) {
		Set<DCharacteristicDefault> dcs = new HashSet<DCharacteristicDefault>();
		for(DefaultCharacteristics cd: defaultChavalue) {
			try {
				//根据特征值的code和特征的code查询出特征值的ID，因为默认特征的表和特征值的表是根据自增长的ID关联的
				int valueId =  charaValueRepo.selectId(cd.getCharacterValue(), cd.getCharacteristic());
				//
				DCharacteristicDefault defaultCh = new DCharacteristicDefault();
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
