package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.order.entity.EnginingTerm;

/**
 * 
 * EnginingTerm数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface EnginingTermMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param code code
   * @return EnginingTerm对象
   */
	EnginingTerm findById(String code);

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
   * 按参数查询EnginingTerm信息.
   * 
   * @param params 查询参数
   * @return EnginingTerm列表
   */
	List<EnginingTerm> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增EnginingTerm.
   * 
   * @param enginingTerm 新增的EnginingTerm对象
   * @return 新增的记录数
   */
	int insert(EnginingTerm enginingTerm);

  /**
   * 
   * 修改EnginingTerm.
   * 
   * @param enginingTerm 修改的EnginingTerm对象
   * @return 修改的记录数
   */
	int update(EnginingTerm enginingTerm);

  /**
   * 
   * 按主键删除单条EnginingTerm.
   * 
   * @param code code
   * @return
   */
	int deleteById(String code);

} 