/**
 * 
 */
package com.qhc.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.OrderVersion;
import com.qhc.order.entity.OrderVersionView;

/**
 * @author  zuwei.su@dxc.com
 *
 */
@Repository
public interface KOrderVersionViewRepository extends JpaRepository<OrderVersionView, String> {
	public List<OrderVersionView> findByOrderIdOrderByCreateTime(String orderId);
	public List<OrderVersionView> findBySequenceNumberOrderByCreateTime(String sequenceNumber);
	public OrderVersionView findBySequenceNumberAndVersionIdOrderByCreateTime(String sequenceNumber, String versionId);
}
