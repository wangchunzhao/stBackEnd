package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DIndustryCode;
import com.qhc.frye.domain.KOrderView;

@Repository
public interface KOrderViewRepository extends JpaRepository<KOrderView, String> {
	
	List<KOrderView> findByOrderIdOrderByVersionCreateTime(String orderId);

	KOrderView findByOrderIdAndVersionId(String orderId, String versionId);

	KOrderView findByOrderIdAndVersionIdAndStatus(String orderId, String versionId, String status);

//	@Query(value="select * from sap_industry_code where is_forDealer = 1", nativeQuery=true)
//	List<KOrderView> findAllFordealer();
}
