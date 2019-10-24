package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.BProvince;
import com.qhc.frye.domain.KBiddingPlan;
@Repository
public interface KBiddingPlanRepository extends JpaRepository<KBiddingPlan, Integer> {
	public List<KBiddingPlan> findByOrderInfoId(String orderInfoId);
}
