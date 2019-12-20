/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qhc.frye.entity.KOrderVersion;

/**
 * @author 
 *
 */

public interface KOrderVersionRepository extends JpaRepository<KOrderVersion, String> {
	
	public List<KOrderVersion> findByOrderIdOrderByCreateTime(String orderId);
	
	@Query(value="select * from k_order_version where k_orders_id=:orderId order by create_time desc limit 1",nativeQuery=true)
	public KOrderVersion findLastOneByOrderId(@Param("orderId")String orderId);
	
	@Query(value="select * from k_order_version left join k_orders on k_order_version.k_orders_id = k_orders.id where k_orders.sequence_number=:sequenceNumber order by create_time desc limit 1",nativeQuery=true)
	public KOrderVersion findLastVersion(@Param("sequenceNumber")String sequenceNumber);
	
	@Query(value="select * from k_order_version left join k_orders on k_order_version.k_orders_id = k_orders.id where k_order_version.version =:version and k_orders.sequence_number=:seqNum limit 1",nativeQuery=true)
	public KOrderVersion findVersion(@Param("seqNum")String seqNum,@Param("version")String version);
	
	@Query(value="select * from k_order_version where k_order_version.k_orders_id = :orderId and k_order_version.k_order_info_id=:orderInfoId limit 1",nativeQuery=true)
	public KOrderVersion findByOrder(@Param("orderId")String orderid,@Param("orderInfoId")String orderInfoId);
	
}
