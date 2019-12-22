package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.entity.Order;

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
   * 按主键查找.
   * 
   * @param id id
   * @return Order对象
   */
	Order findById(Integer id);

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

  /**
   * 
   * 新增Order.
   * 
   * @param order 新增的Order对象
   * @return 新增的记录数
   */
	int insert(Order order);

  /**
   * 
   * 修改Order.
   * 
   * @param order 修改的Order对象
   * @return 修改的记录数
   */
	int update(Order order);

  /**
   * 
   * 按主键删除单条Order.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);
} 
