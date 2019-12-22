package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.order.entity.MaterialGroupOrder;

/**
 * 
 * MaterialGroupOrder数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface MaterialGroupOrderMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param code code
   * @return MaterialGroupOrder对象
   */
	MaterialGroupOrder findById(String code);

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
   * 按参数查询MaterialGroupOrder信息.
   * 
   * @param params 查询参数
   * @return MaterialGroupOrder列表
   */
	List<MaterialGroupOrder> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增MaterialGroupOrder.
   * 
   * @param materialGroupOrder 新增的MaterialGroupOrder对象
   * @return 新增的记录数
   */
	int insert(MaterialGroupOrder materialGroupOrder);

  /**
   * 
   * 修改MaterialGroupOrder.
   * 
   * @param materialGroupOrder 修改的MaterialGroupOrder对象
   * @return 修改的记录数
   */
	int update(MaterialGroupOrder materialGroupOrder);

  /**
   * 
   * 按主键删除单条MaterialGroupOrder.
   * 
   * @param code code
   * @return
   */
	int deleteById(String code);

} 