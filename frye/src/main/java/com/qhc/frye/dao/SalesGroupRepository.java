package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.SapSalesGroup;

/**
 * @author 
 *
 */
@Repository
public interface SalesGroupRepository extends JpaRepository<SapSalesGroup, String> {
	
}
