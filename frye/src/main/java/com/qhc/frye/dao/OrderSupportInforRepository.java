/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qhc.frye.domain.OrderSupportInfo;

/**
 * @author wang@dxc.com
 *
 */
public interface OrderSupportInforRepository extends JpaRepository<OrderSupportInfo, Integer>{
	@Query(value="select * from k_order_support_info where k_orders_id = :orderId",nativeQuery = true)
	public OrderSupportInfo findByOrderId(@Param("orderId") String orderId);
}
