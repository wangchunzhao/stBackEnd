/**
 * 
 */
package com.qhc.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.order.entity.DOrder;

/**
 * @author wang@dxc.com
 *
 */
public interface SalesOrderRepository extends JpaRepository<DOrder, Integer>{

}
