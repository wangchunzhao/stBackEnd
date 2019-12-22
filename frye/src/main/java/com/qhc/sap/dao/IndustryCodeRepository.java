package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.IndustryCode;

@Repository
public interface IndustryCodeRepository extends JpaRepository<IndustryCode, String> {
	
	IndustryCode findByCode(String code);
	
	@Query(value="select * from sap_industry_code where is_forDealer = 1", nativeQuery=true)
	List<IndustryCode> findAllFordealer();
}
