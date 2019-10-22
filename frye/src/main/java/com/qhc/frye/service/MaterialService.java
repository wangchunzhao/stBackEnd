package com.qhc.frye.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.qhc.frye.rest.controller.entity.Material;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.dao.MaterialClazzRepository;
import com.qhc.frye.dao.MaterialInfoRepository;
import com.qhc.frye.dao.MaterialRepository;
import com.qhc.frye.dao.SapLastUpdatedRepository;
import com.qhc.frye.domain.DMaterial;
import com.qhc.frye.domain.LastUpdated;
import com.qhc.frye.domain.MaterialClazz;
import com.qhc.frye.domain.MaterialPrice;
import com.qhc.frye.domain.identity.MaterialClazzIdentity;



@Service
public class MaterialService {
	
	public final String MATERIAL_PRICE_TYPE_RETAIL_PRICE = "ZH01";
	public final String MATERIAL_PRICE_TYPE_ANNUAL_PRICE = "ZH02";
	public final String MATERIAL_PRICE_TYPE_DISCOUNT_PRICE = "ZH03";
	public final String MATERIAL_PRICE_TYPE_SALE_PRICE = "ZH05";
	public final String MATERIAL_PRICE_TYPE_TRANSACTION_PRICE = "ZH06";
	public final String MATERIAL_PRICE_TYPE_TRANSACTION_PERCENTAGE_PRICE = "ZH07";
	public final String MATERIAL_PRICE_TYPE_TRANSFER_PRICE = "ZH10";
	public final String MATERIAL_PRICE_TYPE_OUTSOURCING_PRICE = "ZH11";
	public final String MATERIAL_PRICE_TYPE_INTERNAL_PRICE = "ZHCS";
	
	@Autowired
	private MaterialRepository materialRepo;
	
	@Autowired
	private SapLastUpdatedRepository lastUpdatedRepo;
	
	@Autowired
	private MaterialClazzRepository mcRepo;
	
	@Autowired
	private MaterialInfoRepository materialInfoRepo;
	
	public void saveMaterials(List<Material> materials) {
		Set<DMaterial> mset = new HashSet<DMaterial>();
		Set<MaterialClazz> mcset = new HashSet<MaterialClazz>();
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(Material.MATERIAL_CODE);
		lastUpdated.setName("material");
		for(Material ma: materials){
			DMaterial dm = new DMaterial();
			dm.setCode(ma.getCode());
			dm.setDescription(ma.getDescription());
			dm.setConfigurable(ma.isConfigurable());
			dm.setPurchased(ma.isPurchased());
			dm.setPrice(ma.getStandardPrice());
			dm.setOptTime(ma.getOptTime());
			dm.setUnit(ma.getUnitCode());
			dm.setType(ma.getGroupCode());
			mset.add(dm);

			if(ma.getClazzCode()!=null && !ma.getClazzCode().isEmpty()) {
				MaterialClazz mc = new MaterialClazz();
				MaterialClazzIdentity mci = new MaterialClazzIdentity();
				mci.setClazzCode(ma.getClazzCode());
				mci.setMaterialCode(ma.getCode());
				mc.setMci(mci);
				mcset.add(mc);
			}
			lastUpdated.setLastUpdate(ma.getOptTime());
		}
		materialRepo.saveAll(mset);
		mcRepo.saveAll(mcset);
		lastUpdatedRepo.save(lastUpdated);
	}
	/**
	 * 
	 * @param name material name
	 * @param pageNo
	 * @return
	 */
	public PageHelper<DMaterial> findMaterialsByName(String name,int pageNo){
		if( pageNo >0){
			pageNo = pageNo-1;
		}
		Page<DMaterial> dms = materialRepo.findAllByName(name,PageRequest.of(pageNo,2));
		PageHelper<DMaterial> ph = new PageHelper<DMaterial>(dms);
		return ph;
	}
	/**
	 * 
	 * @param code id of material
	 * @return corresponded material information
	 */
	public Material getMaterialsById(String code){
		Material m = new Material();
		List<MaterialPrice> dmo = materialInfoRepo.findByMaterialId(code);
		for(MaterialPrice mp:dmo) {
			m.setCode(mp.getCode());
			m.setDescription(mp.getDescription());
			m.setConfigurable(mp.isConfigurable());
			m.setPurchased(mp.isPurchased());
			m.setStandardPrice(mp.getStandPrice());
			m.setGroupCode(mp.getGroupCode());
			//
			switch(mp.getPriceTypeCode()) {
				case MATERIAL_PRICE_TYPE_RETAIL_PRICE:
					m.setRetailPrice(mp.getPrice());
					break;
				case MATERIAL_PRICE_TYPE_ANNUAL_PRICE:
					m.setActuralPrice(mp.getPrice());
					break;
				case MATERIAL_PRICE_TYPE_TRANSACTION_PRICE:
					m.setTranscationPrice(mp.getPrice());
					break;

			}
		}

		
		return m;
	}
	
	
}
