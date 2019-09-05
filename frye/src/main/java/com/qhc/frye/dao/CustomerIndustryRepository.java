/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qhc.frye.domain.CustomerAffiliation;
import com.qhc.frye.domain.Industry;

/**
 * @author wang@dxc.com
 *
 */
public interface CustomerIndustryRepository extends JpaRepository<Industry, String>{

}
