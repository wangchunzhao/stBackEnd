package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.qhc.sap.entity.CharacteristicConfiguration;
import com.qhc.sap.entity.DefaultCharacterView;
import com.qhc.sap.entity.Industry;
import com.qhc.sap.entity.MaterialView;

/**
 * 
 * SAP 视图数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface SapViewMapper {
	/**
	 * 
	 * @param code
	 * @return
	 */
	public List<MaterialView> findMaterialInfoByMaterialId(String code);
	
	/**
	 * 查询物料特征信息
	 * 
	 * @param clazzCode
	 * @return
	 */
	public List<CharacteristicConfiguration> findCharacteristicValueByClazzCode(@Param("clazzCode")String clazzCode);
	
	/**
	 * 查询物料特征默认值信息
	 * @param materialCode
	 * @return
	 */
	public List<DefaultCharacterView> findDefaultCharacterValueByMaterial(@Param("material")String materialCode);
} 