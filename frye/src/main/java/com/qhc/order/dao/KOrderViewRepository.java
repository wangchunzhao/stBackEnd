package com.qhc.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.OrderView;
import com.qhc.sap.entity.IndustryCode;

@Repository
public interface KOrderViewRepository extends JpaRepository<OrderView, String> {
	
	List<OrderView> findBySequenceNumberOrderByVersionCreateTime(String sequenceNumber);

	OrderView findBySequenceNumberAndVersion(String sequenceNumber, String version);

	OrderView findBySequenceNumberAndVersionAndStatus(String sequenceNumber, String version, String status);

//	@Query(value="select * from sap_industry_code where is_forDealer = 1", nativeQuery=true)
//	List<KOrderView> findAllFordealer();
}
