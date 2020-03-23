package com.qhc.sap.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.sap.entity.Order;

/**
 * 
 * Order数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface SapOrderMapper {

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
   * 按参数查询Order信息.
   * 
   * @param params 查询参数
   * @return Order列表
   */
	List<Order> findByParams(Map<String, Object> params);


} 