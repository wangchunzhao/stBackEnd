/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.domain.KParentOrderVersion;

/**
 * @author 
 *
 */

public interface KParentOrderVersionRepository extends JpaRepository<KParentOrderVersion, String> {
	public KParentOrderVersion findByOrderVersionId(String orderVersionId);
}
