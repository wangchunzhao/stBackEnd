package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.DIndustryCode;
import com.qhc.frye.entity.KOrderView;

@Repository
public interface KOrderViewRepository extends JpaRepository<KOrderView, String> {
	
	List<KOrderView> findBySequenceNumberOrderByVersionCreateTime(String sequenceNumber);

	KOrderView findBySequenceNumberAndVersion(String sequenceNumber, String version);

	KOrderView findBySequenceNumberAndVersionAndStatus(String sequenceNumber, String version, String status);

//	@Query(value="select * from sap_industry_code where is_forDealer = 1", nativeQuery=true)
//	List<KOrderView> findAllFordealer();
}
