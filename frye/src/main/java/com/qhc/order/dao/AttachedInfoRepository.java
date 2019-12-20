/**
 * 
 */
package com.qhc.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.order.entity.AttachedInfo;

/**
 * @author wang@dxc.com
 *
 */
public interface AttachedInfoRepository extends JpaRepository<AttachedInfo, Integer> {
	public List<AttachedInfo> findByItemDetailsId(String itemDetailsId);
}
