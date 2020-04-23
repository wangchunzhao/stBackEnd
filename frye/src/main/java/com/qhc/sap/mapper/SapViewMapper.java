package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.entity.ClazzCharacteristicValueView;
import com.qhc.sap.entity.DefaultCharacterView;

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
	public List<MaterialDto> findMaterialInfo(Map<String, Object> params);
	
	/**
	 * 查询物料特征信息
	 * 
	 * @param clazzCode
	 * @return
	 */
	public List<ClazzCharacteristicValueView> findCharacteristicValueByClazzCode(@Param("clazzCode")String clazzCode, @Param("materialCode")String materialCode);
	
	/**
	 * 查询物料特征默认值信息
	 * @param materialCode
	 * @return
	 */
	public List<DefaultCharacterView> findDefaultCharacterValueByMaterial(@Param("material")String materialCode);
	
	/**
	 * 查询客户信息
	 * 
	 * @param clazzCode
	 * @param name
	 * @return
	 */
	public List<CustomerDto> findCustomer(@Param("clazzCode")String clazzCode, @Param("name")String name);
} 