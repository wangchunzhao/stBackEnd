/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.KDelieveryAddress;

/**
 * @author 
 *
 */

@Repository
public interface KDelieveryAddressRepository extends JpaRepository<KDelieveryAddress, String> {
	public List<KDelieveryAddress> findByOrderInfoId(String orderId);
}
