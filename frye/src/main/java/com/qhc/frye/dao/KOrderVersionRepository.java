/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.domain.KOrderVersion;

/**
 * @author 
 *
 */

public interface KOrderVersionRepository extends JpaRepository<KOrderVersion, String> {
	public List<KOrderVersion> findByOrderIdOrderByCreateTime(String orderId);
}
