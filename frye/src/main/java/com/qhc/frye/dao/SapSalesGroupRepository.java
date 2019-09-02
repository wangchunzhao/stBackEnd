/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface SapSalesGroupRepository extends JpaRepository<SapSalesGroup, String> {
	SapSalesOffice findByCode(String code);
	

}
