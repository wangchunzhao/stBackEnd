package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.sap.entity.ColorClass;

/**
 * 
 * ColorClass数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ColorClassMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param colorClass
   * @return ColorClass对象
   */
	ColorClass findById(ColorClass colorClass);

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
   * 按参数查询ColorClass信息.
   * 
   * @param params 查询参数
   * @return ColorClass列表
   */
	List<ColorClass> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增ColorClass.
   * 
   * @param colorClass 新增的ColorClass对象
   * @return 新增的记录数
   */
	int insert(ColorClass colorClass);

  /**
   * 
   * 修改ColorClass.
   * 
   * @param colorClass 修改的ColorClass对象
   * @return 修改的记录数
   */
	int update(ColorClass colorClass);

  /**
   * 
   * 按主键删除单条ColorClass.
   * 
   * @param colorClass
   * @return
   */
	int deleteById(ColorClass colorClass);

} 