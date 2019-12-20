package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.OrderQuery;
import com.qhc.order.entity.OrderView;

/**
 * 
 * 订单数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface OrderMapper {
  /**
   * 
   * 按参数查询KOrderView信息.
   * 
   * @param params 查询参数
   * @return KOrderView列表
   */
	List<OrderView> findOrderViewByParams(OrderQuery query);
} 