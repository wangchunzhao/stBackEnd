package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.order.entity.BpmDicision;

/**
 * 
 * BpmDicision数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface BpmDicisionMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return BpmDicision对象
   */
	BpmDicision findById(Integer id);

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
   * 按参数查询BpmDicision信息.
   * 
   * @param params 查询参数
   * @return BpmDicision列表
   */
	List<BpmDicision> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增BpmDicision.
   * 
   * @param bpmDicision 新增的BpmDicision对象
   * @return 新增的记录数
   */
	int insert(BpmDicision bpmDicision);

  /**
   * 
   * 修改BpmDicision.
   * 
   * @param bpmDicision 修改的BpmDicision对象
   * @return 修改的记录数
   */
	int update(BpmDicision bpmDicision);

  /**
   * 
   * 按主键删除单条BpmDicision.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);

} 