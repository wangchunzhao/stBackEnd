/**
 * 
 */
package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.TermianlIndustryCode;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface TerminalIndustryCodeRepository extends JpaRepository<TermianlIndustryCode, String>{

}
