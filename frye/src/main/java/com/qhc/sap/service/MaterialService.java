package com.qhc.sap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qhc.exception.NotMatchException;
import com.qhc.sap.dao.CharacteristicDefaultRepository;
import com.qhc.sap.dao.MaterialRepository;
import com.qhc.sap.dao.PriceRepository;
import com.qhc.sap.dao.SapLastUpdatedRepository;
import com.qhc.sap.domain.Bom;
import com.qhc.sap.domain.Characteristic;
import com.qhc.sap.domain.Configuration;
import com.qhc.sap.domain.MaterialBom;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.entity.ClazzCharacteristicValueView;
import com.qhc.sap.entity.ColorClass;
import com.qhc.sap.entity.LastUpdated;
import com.qhc.sap.entity.Material;
import com.qhc.sap.entity.MaterialPrice;
import com.qhc.sap.entity.MaterialProductClass;
import com.qhc.sap.entity.ProductClass;
import com.qhc.sap.entity.SapCharacteristicDefault;
import com.qhc.sap.entity.identity.MaterialClazzIdentity;
import com.qhc.sap.mapper.ColorClassMapper;
import com.qhc.sap.mapper.MaterialProductClassMapper;
import com.qhc.sap.mapper.ProductClassMapper;
import com.qhc.sap.mapper.SapViewMapper;

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

	public final static String PATH_CHARACTERISTIC_DEFAULT_VALUE = "defaultCharacteristic/";
	
	@Autowired
	private SapService sapService;

	@Autowired
	private MaterialRepository materialRepo;

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private SapLastUpdatedRepository lastUpdatedRepo;

	@Autowired
	private SapViewMapper sapViewMapper;

	@Autowired
	private CharacteristicDefaultRepository defaultCharacterRep;

	@Autowired
	private MaterialProductClassMapper materialProductClassMapper;

	@Autowired
	private ProductClassMapper productClassMapper;

	@Autowired
	private ColorClassMapper colorClassMapper;

	@Transactional
	public void saveMaterials(List<MaterialDto> materials) {
		Set<Material> mset = new HashSet<Material>();
//		Set<MaterialClazz> mcset = new HashSet<MaterialClazz>();
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(MaterialDto.MATERIAL_CODE);
		lastUpdated.setName("material");
		//
		for (MaterialDto ma : materials) {
			Material dm = new Material();
			dm.setCode(ma.getCode());
			dm.setDescription(ma.getDescription());
			dm.setConfigurable(ma.isConfigurable());
			dm.setPurchased(ma.isPurchased());
			dm.setPrice(ma.getStandardPrice());
			dm.setOptTime(ma.getOptTime());
			dm.setUnit(ma.getUnitCode());
			dm.setType(ma.getGroupCode());
			dm.setMaterialSize(ma.getMaterialSize());
			dm.setMaterialType(ma.getMaterialType());
			dm.setClazzCode(ma.getClazzCode());
			mset.add(dm);

			if (ma.getClazzCode() != null && !ma.getClazzCode().isEmpty()) {
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
	 * @param name   material name
	 * @param pageNo
	 * @return
	 */
	public PageInfo<MaterialDto> findMaterialsByName(String name, String industryCode, int pageNo, int pageSize) {
		com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);

		List<MaterialDto> list = sapViewMapper.findMaterialInfo(null, name);

		for (MaterialDto materialDto : list) {
			fillMaterialPrice(materialDto, industryCode);
		}

		return new PageInfo(list);
	}

	/**
	 * 
	 * @param code id of material
	 * @param code id of customer industry
	 * @return corresponded material information
	 */
	public MaterialDto getMaterialsById(String code, String industryCode) {
		MaterialDto m = null;
		List<MaterialDto> list = sapViewMapper.findMaterialInfo(code, null);
		if (list.size() > 0) {
			m = list.get(0);
		} else {
			return null;
		}

		fillMaterialPrice(m, industryCode);

		return m;
	}

	private void fillMaterialPrice(MaterialDto m, String industryCode) {
		if (industryCode == null) {
			return;
		}
		String code = m.getCode();
		double standardPrice = m.getStandardPrice();
		List<MaterialPrice> prices = priceRepository.findByMaterialCodeAndIndustryCode(code, industryCode);
		for (MaterialPrice materialPrice : prices) {
			String priceTypeCode = materialPrice.getType();
			switch (priceTypeCode) {
			case MATERIAL_PRICE_TYPE_RETAIL_PRICE:
				m.setRetailPrice(materialPrice.getPrice());
				break;
			case MATERIAL_PRICE_TYPE_ANNUAL_PRICE:
				m.setAnnualPrice(materialPrice.getPrice());
				break;
				// 转移价，不用
//			case MATERIAL_PRICE_TYPE_TRANSACTION_PRICE:
//				m.setTranscationPrice(materialPrice.getPrice());
//				break;
				// 转移加价百分比
			case MATERIAL_PRICE_TYPE_TRANSACTION_PERCENTAGE_PRICE:
				m.setTranscationPrice(standardPrice * materialPrice.getPrice() / 100);
				break;
			}
		}
	}

	/**
	 * 
	 * @param materialCode 物料
	 * @param clazzCode 物料特征分组
	 * @return
	 */
	public List<Characteristic> getCharactersByClazzCode(String materialCode, String clazzCode)
			throws NotMatchException {
		// character list
		List<Characteristic> chas = new ArrayList<Characteristic>();
		// 物料颜色特征
		Map<String, Object> params = new HashMap<>();
		params.put("materialCode", materialCode);
		List<ProductClass> productClassList = productClassMapper.findByParams(params);
		List<ColorClass> colorClassList = colorClassMapper.findByParams(null);
		if (productClassList != null && productClassList.size() > 0) {
			for (ProductClass productClass : productClassList) {
				Characteristic ch = new Characteristic();
				chas.add(ch);
				ch.setCode(productClass.getPaintingClass());
				ch.setName(productClass.getPaintingParts());
				ch.setOptional(true);
				ch.setColor(true);
				ch.setClassCode(productClass.getProductClass());
				for (ColorClass colorClass : colorClassList) {
					if (colorClass.getColorClass().equals(productClass.getColorClass())) {
						Configuration cfg = new Configuration();
						ch.getConfigs().add(cfg);
						
						cfg.setCode(colorClass.getColorCode());
						cfg.setName(colorClass.getColorDescription());
						cfg.setDefault(colorClass.getColorCode().equals(productClass.getDefaultColor()));
					}
				}
			}
		}
		
		// 物料特征
		List<ClazzCharacteristicValueView> ccs = sapViewMapper.findCharacteristicValueByClazzCode(clazzCode);
		if (ccs != null && ccs.size() > 0) {
			List<SapCharacteristicDefault> defaultValues = defaultCharacterRep.findbyMaterialCode(materialCode);
			Set<Integer> ids = new HashSet<Integer>();
			for (SapCharacteristicDefault dc : defaultValues) {
				ids.add(dc.getValueId());
			}
			// template variable
			Map<String, Characteristic> cs = new HashMap<String, Characteristic>();
			for (ClazzCharacteristicValueView cc : ccs) {
				Characteristic ch = cs.get(cc.getKeyCode());
				if (ch == null) {
					ch = new Characteristic();
					ch.setCode(cc.getKeyCode());
					ch.setName(cc.getKeyName());
					ch.setOptional(false);
					ch.setColor(false);
					ch.setClassCode(clazzCode);
					cs.put(cc.getKeyCode(), ch);
					chas.add(ch);
				}
				Configuration con = new Configuration();
				ch.getConfigs().add(con);
				con.setCode(cc.getValueCode());
				con.setName(cc.getValueName());
				if (ids.contains(cc.getId())) {
					con.setDefault(true);
				} else {
					con.setDefault(false);
				}
			}
		}

		return chas;
	}

	/**
	 * 从SAP获取产品标配和选配的BOM清单
	 * 
	 * @param pars
	 * @return
	 */
	public MaterialBom findBomPrice(Map<String, String> pars) {
		String industryCode = pars.remove("industry");
		industryCode = industryCode == null ? "unkn" : industryCode;
		
		Map<String, List<Bom>> boms = sapService.getBomExplosion(pars);
		List<Bom> standard = boms.get("standard");
		List<Bom> optional = boms.get("optional");
		
		// 获取物料转移价、零售价、年采价等
		fillBomPrice(industryCode, optional);
		fillBomPrice(industryCode, standard);
		
		MaterialBom mb = new MaterialBom();
		mb.setOptional(optional);
		mb.setStandard(standard);
		
		mb.calculatePriceGap();
		
		return mb;
	}

	private void fillBomPrice(String industryCode, List<Bom> optional) {
		for (Bom bom : optional) {
			if (bom.isMarked()) {
				MaterialDto m = getMaterialsById(bom.getCode(), industryCode);
				
				bom.setAnnualPrice(m.getAnnualPrice());
				bom.setTransferPrice(m.getTranscationPrice());
				bom.setRetailPrice(m.getRetailPrice());
			}
		}
	}

	/**
	 * 
	 * @param materialCode 物料
	 * @param clazzCode 物料特征分组
	 * @return
	 */
	public List<ProductClass> getMaterialColorConfig(String materialCode) {
		MaterialProductClass materialProductClass = materialProductClassMapper.findById(materialCode);
		if (materialProductClass == null) {
			return new ArrayList<>();
		}
		Map<String, Object> params = new HashMap<>();
		params.put("productClass", materialProductClass.getProductClass());
		List<ProductClass> productClassList = productClassMapper.findByParams(params);
		
		return productClassList;
	}
}
