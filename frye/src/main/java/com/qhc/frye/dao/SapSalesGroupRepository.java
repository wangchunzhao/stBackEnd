/**
 * 
 */
package com.qhc.frye.dao;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "sap_sales_group")
public interface SapSalesGroupRepository extends JpaRepository<SapSalesGroup, String> {
	SapSalesOffice findByCode(String code);
	

}
