package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.frye.domain.BProvince;
import com.qhc.frye.domain.KBiddingPlan;

@Repository
@Transactional
public interface KBiddingPlanRepository extends JpaRepository<KBiddingPlan, Integer> {
	public List<KBiddingPlan> findByOrderInfoId(String orderInfoId);
	
	@Query(value = "delete FROM bohemian.k_delievery_address where k_order_info_id= :orderInfoId", nativeQuery = true)
	@Modifying
	@Transactional
	public void deleteByOrderInfoId(@Param("orderInfoId")String orderInfoId);
}
