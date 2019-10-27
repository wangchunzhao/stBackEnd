/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qhc.frye.domain.KOrderVersion;

/**
 * @author 
 *
 */

public interface KOrderVersionRepository extends JpaRepository<KOrderVersion, String> {
	
	public List<KOrderVersion> findByOrderIdOrderByCreateTime(String orderId);
	@Query(value="select * from k_order_version where k_order_id=:orderId sort by create_time desc limit 1",nativeQuery=true)
	public KOrderVersion findLastOneByOrderId(@Param("orderId")String orderId);
}
