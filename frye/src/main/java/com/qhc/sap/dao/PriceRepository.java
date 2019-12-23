package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.MaterialPrice;

/**
 * 
 * @author wang@dxc.com
 *
 */
@Repository
public interface PriceRepository extends JpaRepository<MaterialPrice, String>{
	List<MaterialPrice> findByMaterialCodeAndIndustryCode(String materialCode, String industryCode);
}
