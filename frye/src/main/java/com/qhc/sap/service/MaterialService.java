package com.qhc.sap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qhc.exception.NotMatchException;
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
import com.qhc.system.entity.Settings;
import com.qhc.system.service.SettingsService;

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
	private SettingsService settingsService;

	@Autowired
	private MaterialRepository materialRepo;

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private SapLastUpdatedRepository lastUpdatedRepo;

	@Autowired
	private SapViewMapper sapViewMapper;

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
			dm.setMaterialStatus(ma.getMaterialStatus());
			dm.setDistributionStatus(ma.getDistributionStatus());
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

        Map<String, Object> params = new HashMap<>();
        params.put("list", true);
        params.put("name", name);
        List<MaterialDto> list = sapViewMapper.findMaterialInfo(params);

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
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		List<MaterialDto> list = sapViewMapper.findMaterialInfo(params);
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
		// 年采价
		List<MaterialPrice> prices = priceRepository.findByMaterialCodeAndIndustryCode(code, industryCode);
		// 零售价及转移百分比转移价，经销商客户会重复
        List<MaterialPrice> prices2 = priceRepository.findByMaterialCodeAndIndustryCode(code, "unkn");
        prices.addAll(prices2);
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
		
		// 对于所有以’H008’开头的物料转移价格的计算公式：标准价格*107%
		if (code.startsWith("H008")) {
			m.setTranscationPrice(standardPrice * 107 / 100);
		}
	}

	/**
	 * 
	 * @param materialCode 物料
	 * @return
	 */
	public List<Characteristic> getCharactersByClazzCode(String materialCode)
			throws NotMatchException {
		// character list
		List<Characteristic> chas = new ArrayList<Characteristic>();
		// 物料颜色特征
		Map<String, Object> params = new HashMap<>();
		params.put("materialCode", materialCode);
		List<ProductClass> productClassList = productClassMapper.findByParams(params);
		if (productClassList != null && productClassList.size() > 0) {
			for (ProductClass productClass : productClassList) {
				Characteristic ch = new Characteristic();
				chas.add(ch);
				ch.setCode(productClass.getPaintingClass());
				ch.setName(productClass.getPaintingParts());
				ch.setOptional(true);
				ch.setColor(true);
				ch.setClassCode(productClass.getProductClass());
				Map<String, Object> colorclassParams = new HashMap<>();
				colorclassParams.put("colorClass", productClass.getColorClass());
				List<ColorClass> colorClassList = colorClassMapper.findByParams(colorclassParams);
				for (ColorClass colorClass : colorClassList) {
					Configuration cfg = new Configuration();
					ch.getConfigs().add(cfg);
					
					cfg.setCode(colorClass.getColorCode());
					cfg.setName(colorClass.getColorMaterialCode() + " - " + colorClass.getColorDescription());
					cfg.setDefault(colorClass.getColorCode().equals(productClass.getDefaultColor()));
				}
			}
		}
		
		// 物料特征
		Material m = materialRepo.findById(materialCode).get();
		// 物料特征分组
		String clazzCode = m.getClazzCode();
		List<ClazzCharacteristicValueView> allmc = sapViewMapper.findCharacteristicValueByClazzCode(clazzCode, materialCode);
		if (allmc != null && allmc.size() > 0) {
			// template variable
			Map<String, Characteristic> cmap = new LinkedHashMap<String, Characteristic>();
			for (ClazzCharacteristicValueView mcview : allmc) {
			    String valueId = StringUtils.trimToEmpty(mcview.getValueId());
                String defaultValueId = StringUtils.trimToEmpty(mcview.getDefaultValueId());
				Characteristic mc = cmap.get(mcview.getKeyCode());
				if (mc == null) {
					mc = new Characteristic();
					mc.setCode(mcview.getKeyCode());
					mc.setName(mcview.getKeyName());
					mc.setOptional(false);
					mc.setColor(false);
					mc.setClassCode(clazzCode);
					cmap.put(mcview.getKeyCode(), mc);
					chas.add(mc);
				}
				List<Configuration> configs = mc.getConfigs();
				if (configs.size() == 0) {
				  Configuration mcEmptyValue = new Configuration();
				  configs.add(mcEmptyValue);
				  mcEmptyValue.setCode("-");
				  mcEmptyValue.setName("- 请选择 -");
				  mcEmptyValue.setDefault(defaultValueId.equals(""));
				}
				Configuration mcValue = new Configuration();
				configs.add(mcValue);
				mcValue.setCode(mcview.getValueCode());
				mcValue.setName(mcview.getValueName());
				mcValue.setDefault(valueId.equals(defaultValueId));
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

        double transferRate = 1.05;
        Settings rateSetting = settingsService.findByCode("refrigeratory_transaction_rate");
        if (rateSetting != null && StringUtils.isNoneEmpty(rateSetting.getsValue()) ) {
          transferRate = Double.valueOf(rateSetting.getsValue()); 
        }
		mb.calculatePriceGap(transferRate);
		
		return mb;
	}

	private void fillBomPrice(String industryCode, List<Bom> optional) {
		for (Bom bom : optional) {
			if (bom.isMarked()) {
				MaterialDto m = getMaterialsById(bom.getCode(), industryCode);
				
//				bom.setPrice(m.getStandardPrice());
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
