package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.DIndustryCode;

@Repository
public interface DIndustryCodeRepository extends JpaRepository<DIndustryCode, String> {
	
	DIndustryCode findByCode(String code);
	
	@Query(value="select * from sap_industry_code where is_forDealer = 1", nativeQuery=true)
	List<DIndustryCode> findAllFordealer();
}
