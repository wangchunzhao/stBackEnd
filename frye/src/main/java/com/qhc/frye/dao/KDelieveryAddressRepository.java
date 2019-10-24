/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.domain.KDelieveryAddress;

/**
 * @author 
 *
 */

public interface KDelieveryAddressRepository extends JpaRepository<KDelieveryAddress, String> {
	public List<KDelieveryAddress> findByOrderInfoId(String orderId);
}
