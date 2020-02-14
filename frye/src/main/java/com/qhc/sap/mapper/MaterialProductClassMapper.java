package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.sap.entity.MaterialProductClass;

/**
 * 
 * MaterialProductClass数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface MaterialProductClassMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param materialCode materialCode
   * @return MaterialProductClass对象
   */
	MaterialProductClass findById(String materialCode);

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
   * 按参数查询MaterialProductClass信息.
   * 
   * @param params 查询参数
   * @return MaterialProductClass列表
   */
	List<MaterialProductClass> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增MaterialProductClass.
   * 
   * @param materialProductClass 新增的MaterialProductClass对象
   * @return 新增的记录数
   */
	int insert(MaterialProductClass materialProductClass);

  /**
   * 
   * 修改MaterialProductClass.
   * 
   * @param materialProductClass 修改的MaterialProductClass对象
   * @return 修改的记录数
   */
	int update(MaterialProductClass materialProductClass);

  /**
   * 
   * 按主键删除单条MaterialProductClass.
   * 
   * @param materialCode materialCode
   * @return
   */
	int deleteById(String materialCode);

} 