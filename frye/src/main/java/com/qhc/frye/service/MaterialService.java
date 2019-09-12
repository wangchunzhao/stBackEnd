package com.qhc.frye.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.rest.controller.entity.Material;
import com.qhc.frye.dao.CustomerRepository;
import com.qhc.frye.dao.MaterialClazzRepository;
import com.qhc.frye.dao.MaterialRepository;
import com.qhc.frye.dao.SapLastUpdatedRepository;
import com.qhc.frye.domain.DMaterial;
import com.qhc.frye.domain.MaterialClazz;
import com.qhc.frye.domain.identity.MaterialClazzIdentity;



@Service
public class MaterialService {
	
	@Autowired
	private MaterialRepository materialRepo;
	
	@Autowired
	private MaterialClazzRepository mcRepo;
	
	public void saveMaterials(List<Material> materials) {
		Set<DMaterial> mset = new HashSet<DMaterial>();
		Set<MaterialClazz> mcset = new HashSet<MaterialClazz>();
		for(Material ma: materials){
			DMaterial dm = new DMaterial();
			dm.setCode(ma.getCode());
			dm.setConfigurable(ma.isConfigurable());
			dm.setDescription(ma.getDescription());
			dm.setMkPrice(ma.getMkPrice());
			dm.setMvPrice(ma.getMvPrice());
			dm.setOptTime(ma.getOptTime());
			dm.setTrPrice(ma.getTrPrice());
			dm.setType(ma.getType());
			dm.setUnit(ma.getUnit());
			mset.add(dm);
			if(ma.getClazz()!=null && !ma.getClazz().isEmpty()) {
				MaterialClazz mc = new MaterialClazz();
				MaterialClazzIdentity mci = new MaterialClazzIdentity();
				mci.setClazzCode(ma.getClazz());
				mci.setMaterialCode(ma.getCode());
				mc.setMci(mci);
				mcset.add(mc);
			}
		}
		
		materialRepo.saveAll(mset);
		mcRepo.saveAll(mcset);
	}
}
