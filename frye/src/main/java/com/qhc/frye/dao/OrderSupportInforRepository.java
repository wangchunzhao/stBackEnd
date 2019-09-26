/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.domain.OrderSupportInfo;

/**
 * @author wang@dxc.com
 *
 */
public interface OrderSupportInforRepository extends JpaRepository<OrderSupportInfo, Integer>{
	
}
