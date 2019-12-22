package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.Province;

/**
 * 
 * Province数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ProvinceMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param code code
   * @return Province对象
   */
	Province findById(String code);

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
   * 按参数查询Province信息.
   * 
   * @param params 查询参数
   * @return Province列表
   */
	List<Province> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Province.
   * 
   * @param province 新增的Province对象
   * @return 新增的记录数
   */
	int insert(Province province);

  /**
   * 
   * 修改Province.
   * 
   * @param province 修改的Province对象
   * @return 修改的记录数
   */
	int update(Province province);

  /**
   * 
   * 按主键删除单条Province.
   * 
   * @param code code
   * @return
   */
	int deleteById(String code);

} 