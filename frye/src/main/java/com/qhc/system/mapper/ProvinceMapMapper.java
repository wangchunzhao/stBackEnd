package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.ProvinceMap;

/**
 * 
 * ProvinceMap数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ProvinceMapMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param stProvince stProvince
   * @return ProvinceMap对象
   */
	ProvinceMap findById(String stProvince);

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
   * 按参数查询ProvinceMap信息.
   * 
   * @param params 查询参数
   * @return ProvinceMap列表
   */
	List<ProvinceMap> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增ProvinceMap.
   * 
   * @param provinceMap 新增的ProvinceMap对象
   * @return 新增的记录数
   */
	int insert(ProvinceMap provinceMap);

  /**
   * 
   * 修改ProvinceMap.
   * 
   * @param provinceMap 修改的ProvinceMap对象
   * @return 修改的记录数
   */
	int update(ProvinceMap provinceMap);

  /**
   * 
   * 按主键删除单条ProvinceMap.
   * 
   * @param stProvince stProvince
   * @return
   */
	int deleteById(String stProvince);

} 