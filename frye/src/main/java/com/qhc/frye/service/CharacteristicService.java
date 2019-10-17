/**
 * 
 */
package com.qhc.frye.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CharacteristicRepository;
import com.qhc.frye.dao.CharacteristicValueRepository;
import com.qhc.frye.dao.ClassAndCharacterRepository;
import com.qhc.frye.dao.ClazzOfMaterialRepository;
import com.qhc.frye.domain.DCharacteristic;
import com.qhc.frye.domain.DCharacteristicValue;
import com.qhc.frye.domain.DClassAndCharacter;
import com.qhc.frye.domain.DClazzOfMaterial;
import com.qhc.frye.rest.controller.entity.CharacteristicValue;
import com.qhc.frye.rest.controller.entity.Clazz;

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
			List<Object> objs =  cv.toDaos();
			for(Object obj:objs) {
				if(obj.getClass().equals(DClassAndCharacter.class)) {
					cacs.add((DClassAndCharacter)obj);
										
				}else if(obj.getClass().equals(DCharacteristic.class)) {
					dcs.add((DCharacteristic)obj);
					
				}else if(obj.getClass().equals(DCharacteristicValue.class)) {
					dcvs.add((DCharacteristicValue)obj);
					
				}
			}
			
		}	
		characterRepo.saveAll(dcs);
		charaValueRepo.saveAll(dcvs);
		classAndCharaRepo.saveAll(cacs);
	}
}
