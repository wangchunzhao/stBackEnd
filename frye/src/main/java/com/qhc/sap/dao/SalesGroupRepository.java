package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.SapSalesGroup;

/**
 * @author 
 *
 */
@Repository
public interface SalesGroupRepository extends JpaRepository<SapSalesGroup, String> {
	
}
