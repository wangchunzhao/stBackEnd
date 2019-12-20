package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.OrderInfo;
@Repository
public interface SapSaleGroupRepository extends JpaRepository<OrderInfo, Integer> ,JpaSpecificationExecutor<OrderInfo>{
	
	
	@Query(value="select * from k_order_info where id =(select k_order_info_id from k_parent_order_version where k_order_version_id =(SELECT id from k_order_version where k_orders_id=?1 and k_order_version_id=?2))" ,nativeQuery=true)
	OrderInfo findKOrderInfoByOrdersIdAndVersionId(String orderId,String sequenceNumber);

}
