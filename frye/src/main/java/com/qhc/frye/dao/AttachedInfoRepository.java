/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.entity.KAttachedInfo;

/**
 * @author wang@dxc.com
 *
 */
public interface AttachedInfoRepository extends JpaRepository<KAttachedInfo, Integer> {
	public List<KAttachedInfo> findByItemDetailsId(String itemDetailsId);
}
