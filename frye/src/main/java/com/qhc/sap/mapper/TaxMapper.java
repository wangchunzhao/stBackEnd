package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.sap.entity.Tax;

/**
 * 
 * Tax数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface TaxMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param code code
   * @return Tax对象
   */
	Tax findById(String code);

  /**
   * 
   * 按参数统计记录数.
   * 
   * @param params 查询参数
   * @return 满足条件的记录数
   */
	int countByParams(Map<String, Object> params);

  /**
   * 
   * 按参数查询Tax信息.
   * 
   * @param params 查询参数
   * @return Tax列表
   */
	List<Tax> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Tax.
   * 
   * @param tax 新增的Tax对象
   * @return 新增的记录数
   */
	int insert(Tax tax);

  /**
   * 
   * 修改Tax.
   * 
   * @param tax 修改的Tax对象
   * @return 修改的记录数
   */
	int update(Tax tax);

  /**
   * 
   * 按主键删除单条Tax.
   * 
   * @param code code
   * @return
   */
	int deleteById(String code);

} 