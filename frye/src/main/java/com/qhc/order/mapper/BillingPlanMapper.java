package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.order.entity.BillingPlan;

/**
 * 
 * BillingPlan数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface BillingPlanMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return BillingPlan对象
   */
	BillingPlan findById(Integer id);

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
   * 按参数查询BillingPlan信息.
   * 
   * @param params 查询参数
   * @return BillingPlan列表
   */
	List<BillingPlan> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增BillingPlan.
   * 
   * @param billingPlan 新增的BillingPlan对象
   * @return 新增的记录数
   */
	int insert(BillingPlan billingPlan);

  /**
   * 
   * 修改BillingPlan.
   * 
   * @param billingPlan 修改的BillingPlan对象
   * @return 修改的记录数
   */
	int update(BillingPlan billingPlan);

  /**
   * 
   * 按主键删除单条BillingPlan.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);

	/**
	 * 查詢訂單的所有回款計劃
	 * 
	 * @param orderInfoId
	 * @return
	 */
	List<BillingPlan> findByOrderInfoId(Integer orderInfoId);

	/**
	 * 刪除訂單的所有回款計劃
	 * 
	 * @param orderInfoId
	 */
	void deleteByOrderInfoId(Integer orderInfoId);

} 