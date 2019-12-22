package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.Area;

/**
 * 
 * Area数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface AreaMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param code code
   * @return Area对象
   */
	Area findById(String code);

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
   * 按参数查询Area信息.
   * 
   * @param params 查询参数
   * @return Area列表
   */
	List<Area> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Area.
   * 
   * @param area 新增的Area对象
   * @return 新增的记录数
   */
	int insert(Area area);

  /**
   * 
   * 修改Area.
   * 
   * @param area 修改的Area对象
   * @return 修改的记录数
   */
	int update(Area area);

  /**
   * 
   * 按主键删除单条Area.
   * 
   * @param code code
   * @return
   */
	int deleteById(String code);

} 