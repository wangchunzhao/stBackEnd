package com.qhc.frye.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.qhc.frye.rest.controller.entity.Bom;
import com.qhc.frye.rest.controller.entity.BomExplosion;
import com.qhc.frye.rest.controller.entity.Characteristic;
import com.qhc.frye.rest.controller.entity.Configuration;
import com.qhc.frye.rest.controller.entity.DefaultCharacteristics;
import com.qhc.frye.rest.controller.entity.Material;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.dao.CharacteristicConfigurationRepository;
import com.qhc.frye.dao.MaterialInfoRepository;
import com.qhc.frye.dao.MaterialRepository;
import com.qhc.frye.dao.SapLastUpdatedRepository;
import com.qhc.frye.domain.CharacteristicConfiguration;
import com.qhc.frye.domain.DMaterial;
import com.qhc.frye.domain.LastUpdated;
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
	
	public final static String BOM_PATH_EXPORSION = "material/bom";
	public final static String BOM_CONFIGURATION_DEFAULT = "default";
	public final static String BOM_CONFIGURATION_CONFIGURATED = "configurated";
	
	public final static String PATH_CHARACTERISTIC_DEFAULT_VALUE = "defaultCharacteristic/";

	@Autowired
	private MaterialRepository materialRepo;
	
	@Autowired
	private SapLastUpdatedRepository lastUpdatedRepo;
	
	@Autowired
	private BayernService bayernSer;
	
	@Autowired
	private MaterialInfoRepository materialInfoRepo;
	
	@Autowired
	private CharacteristicConfigurationRepository charaterRepo;
	
	public void saveMaterials(List<Material> materials) {
		Set<DMaterial> mset = new HashSet<DMaterial>();
//		Set<MaterialClazz> mcset = new HashSet<MaterialClazz>();
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(Material.MATERIAL_CODE);
		lastUpdated.setName("material");
		//
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
			dm.setMaterialSize(ma.getMaterialSize());
			dm.setClazzCode(ma.getClazzCode());
			mset.add(dm);

			if(ma.getClazzCode()!=null && !ma.getClazzCode().isEmpty()) {
//				MaterialClazz mc = new MaterialClazz();
				MaterialClazzIdentity mci = new MaterialClazzIdentity();
				mci.setClazzCode(ma.getClazzCode());
				mci.setMaterialCode(ma.getCode());
//				mc.setMci(mci);
//				mcset.add(mc);
			}
			lastUpdated.setLastUpdate(ma.getOptTime());
		}
		materialRepo.saveAll(mset);
//		mcRepo.saveAll(mcset);
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
		Page<DMaterial> dms = materialRepo.findAllByName(name,PageRequest.of(pageNo,CustomerService.QUANTITY_PAGE));
		PageHelper<DMaterial> ph = new PageHelper<DMaterial>(dms);
		return ph;
	}
	/**
	 * 
	 * @param code id of material
	 * @return corresponded material information
	 */
	public Material getMaterialsById(String code){
		Material m = null;
		List<MaterialPrice> dmo = materialInfoRepo.findByMaterialId(code);
		for(MaterialPrice mp:dmo) {
			m = new Material();
			m.setCode(mp.getCode());
			m.setDescription(mp.getDescription());
			m.setConfigurable(mp.isConfigurable());
			m.setPurchased(mp.isPurchased());
			m.setStandardPrice(mp.getStandPrice());
			m.setGroupCode(mp.getGroupCode());
			m.setClazzCode(mp.getClazzCode());
			//
			String priceTypeCode = mp.getPriceTypeCode();
			switch(priceTypeCode) {
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
	/**
	 * 
	 * @param clazzCode
	 * @param materialCode
	 * @return
	 */
	public List<Characteristic> getCharactersByClazzCode(String clazzCode,String materialCode){
		List<CharacteristicConfiguration> ccs = charaterRepo.findAllByClazzCode(clazzCode);
		List<Characteristic> chas = new ArrayList<Characteristic>();
		Map<String,Characteristic> cs = new HashMap<String,Characteristic>();
		for(CharacteristicConfiguration cc:ccs) {
			if(!cs.containsKey(cc.getKeyCode())) {
				Characteristic ch = new Characteristic();
				ch.setCode(cc.getKeyCode());
				ch.setName(cc.getKeyName());
				//ch.setOptional(cc.isOptional());
				ch.setClassCode(clazzCode);
				cs.put(cc.getKeyCode(), ch);
				Configuration con = new Configuration();
				con.setCode(cc.getValCode());
				con.setName(cc.getValName());
				ch.getConfigs().add(con);
			}else {
				Configuration con = new Configuration();
				con.setCode(cc.getValCode());
				con.setName(cc.getValName());
				cs.get(cc.getKeyCode()).getConfigs().add(con);
			}		
		}
		for(String key:cs.keySet()) {
			chas.add(cs.get(key));
		}
		//
		List<DefaultCharacteristics>  dcl= this.getDefaultCharacteristicValueByMaterialCode(materialCode);
		for(Characteristic c: chas) {
			for(DefaultCharacteristics dc:dcl) {
				if(c.getCode().equals(dc.getCharacteristic())) {
					for(Configuration f:c.getConfigs()) {
						if(f.getCode().equals(dc.getCharacterValue())) {
							f.setDefault(true);
						}else {
							f.setDefault(false);
						}
					}
				}
			}
		}
		return chas;
	}
	
	private List<DefaultCharacteristics> getDefaultCharacteristicValueByMaterialCode(String materialCode){
		
		List<DefaultCharacteristics>  dcl= bayernSer.getListInfo(PATH_CHARACTERISTIC_DEFAULT_VALUE+materialCode, DefaultCharacteristics.class);
		
		return dcl;
	}
	/**
	 * 
	 * @param pars
	 * @return
	 */
	public BomExplosion findBOMWithPrice(Map<String,String> pars){
		Map<String,List<Bom>> boms = (Map<String, List<Bom>>) bayernSer.postForm(BOM_PATH_EXPORSION, pars, Map.class);
		
		if(boms!=null && boms.keySet().size()==2 && boms.containsKey(BOM_CONFIGURATION_DEFAULT) && boms.containsKey(BOM_CONFIGURATION_CONFIGURATED)) {
			
			BomExplosion be = new BomExplosion();
			boolean result = be.fillIn(boms.get(BOM_CONFIGURATION_DEFAULT), boms.get(BOM_CONFIGURATION_CONFIGURATED));
			if(result)
				return be;

		}
			
		return null;
	}
}
