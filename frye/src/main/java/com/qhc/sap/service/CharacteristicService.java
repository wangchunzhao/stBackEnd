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
import com.qhc.sap.dao.SapColorClassRepository;
import com.qhc.sap.dao.SapMaterialProductClassRepository;
import com.qhc.sap.dao.SapPaintingClassRepository;
import com.qhc.sap.dao.SapProductClassRepository;
import com.qhc.sap.domain.CharacteristicValueDto;
import com.qhc.sap.domain.Clazz;
import com.qhc.sap.domain.ColorDto;
import com.qhc.sap.domain.DefaultCharacteristicsDto;
import com.qhc.sap.entity.SapCharacteristic;
import com.qhc.sap.entity.SapCharacteristicDefault;
import com.qhc.sap.entity.SapCharacteristicValue;
import com.qhc.sap.entity.SapColorClass;
import com.qhc.sap.entity.ClassAndCharacter;
import com.qhc.sap.entity.SapMaterialClazz;
import com.qhc.sap.entity.SapMaterialProductClass;
import com.qhc.sap.entity.SapPaintingClass;
import com.qhc.sap.entity.SapProductClass;
import com.qhc.sap.entity.identity.SapColorIdentity;
import com.qhc.sap.entity.identity.SapProductClassIdentity;

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
	
	@Autowired
	private SapMaterialProductClassRepository sapMaterialProductClassRep;
	
	@Autowired
	private SapColorClassRepository sapColorClassRep;
	
	@Autowired
	private SapPaintingClassRepository sapPaintingClassRep;
	
	@Autowired
	private SapProductClassRepository sapProductClassRep;
	
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
		clazzRepo.deleteAll();
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
//		CharacterDefaultRep.deleteAll();
		charaValueRepo.deleteAll();
		//
		charaValueRepo.saveAll(dcvs);
		classAndCharaRepo.saveAll(cacs);
	}
	/**
	 * 
	 * @param 颜色可选项1
	 */
	public void savePrclassColor(List<ColorDto> colorValues) {
		Set<SapMaterialProductClass> mpclist = new HashSet<SapMaterialProductClass>();
		
		for(ColorDto cl:colorValues) {
			SapMaterialProductClass mpc = new SapMaterialProductClass();
			mpc.setMaterialCode(cl.getMaterial());
			mpc.setProductClass(cl.getProductClass());
			mpclist.add(mpc);
		}
		sapMaterialProductClassRep.deleteAll();
		sapMaterialProductClassRep.saveAll(mpclist);
	}
	/**
	 * 
	 * @param 颜色可选项2
	 */
	public void saveCoclassColor(List<ColorDto> colorValues) {
		Set<SapColorClass> cclist = new HashSet<SapColorClass>();
		for(ColorDto cl:colorValues) {
			SapColorClass cc = new SapColorClass();
			SapColorIdentity ii = new SapColorIdentity();
			ii.setColorClass(cl.getColorClass());
			ii.setColorCode(cl.getColorCode());
			cc.setCci(ii);
			cc.setColorMaterialCode(cl.getPowderMaterial());
			cc.setColorDescription(cl.getMaterialDesc());
			cclist.add(cc);
		}
		sapColorClassRep.deleteAll();
		sapColorClassRep.saveAll(cclist);
	}
	/**
	 * 
	 * @param 颜色可选项3
	 */
	public void savePaclassColor(List<ColorDto> colorValues) {
		Set<SapPaintingClass> palist = new HashSet<SapPaintingClass>();
		for(ColorDto cl:colorValues) {
			SapPaintingClass pc = new SapPaintingClass();
			pc.setPaintingClass(cl.getPaintingClass());
			pc.setPaintingParts(cl.getClassName());
			palist.add(pc);
		}
		sapPaintingClassRep.deleteAll();
		sapPaintingClassRep.saveAll(palist);
	}
	/**
	 * 
	 * @param 颜色可选项4
	 */
	public void savePamappColor(List<ColorDto> colorValues) {
		Set<SapProductClass> palist = new HashSet<SapProductClass>();
		for(ColorDto cl:colorValues) {
			SapProductClass sp = new SapProductClass();
			SapProductClassIdentity pcii = new SapProductClassIdentity();
			pcii.setProductClass(cl.getProductClassPam());
			pcii.setPaintingCode(cl.getPaintingClassPam());
			sp.setCci(pcii);
			sp.setColorClass(cl.getColorClassPam());
			sp.setDefaultColor(cl.getColorCodePam());
			palist.add(sp);
		}
		sapProductClassRep.deleteAll();
		sapProductClassRep.saveAll(palist);
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
//				int valueId =  charaValueRepo.selectId(cd.getCharacterValue(), cd.getCharacteristic());
				//
				SapCharacteristicDefault defaultCh = new SapCharacteristicDefault();
				defaultCh.setMaterialsCode(cd.getMaterialCode());
				defaultCh.setCharacteristicCode(cd.getCharacteristic());
				defaultCh.setValueId(cd.getCharacterValue());
				dcs.add(defaultCh);
			} catch (Exception e) {
				System.out.println("查不到特征值的数据");
				continue;
			}
		}
		CharacterDefaultRep.deleteAll();
		CharacterDefaultRep.saveAll(dcs);
	}
	
	
	
}
