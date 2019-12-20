/**
 * 
 */
package com.qhc.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.Order;
import com.qhc.order.entity.OrderVersion;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface SalesorderVersionRepository extends JpaRepository<OrderVersion, Integer> {

}
