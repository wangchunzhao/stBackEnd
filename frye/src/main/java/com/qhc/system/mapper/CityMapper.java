package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.City;

/**
 * 
 * City数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface CityMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param code code
   * @return City对象
   */
	City findById(String code);

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
   * 按参数查询City信息.
   * 
   * @param params 查询参数
   * @return City列表
   */
	List<City> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增City.
   * 
   * @param city 新增的City对象
   * @return 新增的记录数
   */
	int insert(City city);

  /**
   * 
   * 修改City.
   * 
   * @param city 修改的City对象
   * @return 修改的记录数
   */
	int update(City city);

  /**
   * 
   * 按主键删除单条City.
   * 
   * @param code code
   * @return
   */
	int deleteById(String code);

} 