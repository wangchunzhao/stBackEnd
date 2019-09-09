/**
 * 
 */
package com.qhc.frye.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.HashCode;
import com.qhc.frye.dao.CharacteristicRepository;
import com.qhc.frye.dao.CharacteristicValueRepository;
import com.qhc.frye.dao.ClassAndCharacter;
import com.qhc.frye.dao.ClazzRepository;
import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.domain.DCharacteristic;
import com.qhc.frye.domain.DCharacteristicValue;
import com.qhc.frye.domain.DClassAndCharacter;
import com.qhc.frye.rest.controller.entity.CharacteristicValue;
import com.qhc.frye.rest.controller.entity.Clazz;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CharacteristicService {
	@Autowired
	private ClazzRepository clazzRepo;
	
	@Autowired
	private CharacteristicRepository characterRepo;
	
	@Autowired
	private CharacteristicValueRepository charaValueRepo;
	
	@Autowired
	private ClassAndCharacter classAndCharaRepo;
	/**
	 * 
	 * @param clazz
	 */
	public void saveClass(List<Clazz> clazz) {
		clazzRepo.deleteAll();
		clazzRepo.saveAll(clazz);
				
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
				if(obj instanceof ClassAndCharacter) {
					cacs.add((DClassAndCharacter)obj);
										
				}else if(obj instanceof DCharacteristic) {
					dcs.add((DCharacteristic)obj);
					
				}else if(obj instanceof CharacteristicValue) {
					dcvs.add((DCharacteristicValue)obj);
					
				}
			}
			
		}	
		characterRepo.saveAll(dcs);
		charaValueRepo.saveAll(dcvs);
		classAndCharaRepo.saveAll(cacs);
	}
}
