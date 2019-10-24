/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.KOrderVersionView;

/**
 * @author  zuwei.su@dxc.com
 *
 */
@Repository
public interface KOrderVersionViewRepository extends JpaRepository<KOrderVersionView, String> {
	public List<KOrderVersionView> findByOrderIdOrderByCreateTime(String orderId);
	public List<KOrderVersionView> findBySequenceNumberOrderByCreateTime(String sequenceNumber);
	public KOrderVersionView findBySequenceNumberAndVersionIdOrderByCreateTime(String sequenceNumber, String versionId);
}
