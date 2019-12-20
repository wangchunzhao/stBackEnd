package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.CustomerAffiliation;
import com.qhc.sap.entity.identity.CustomerIndustryIdentity;
import com.qhc.system.entity.UserRole;
/**
 * 
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerAffiliationRepository extends JpaRepository<CustomerAffiliation, CustomerIndustryIdentity>{

}
