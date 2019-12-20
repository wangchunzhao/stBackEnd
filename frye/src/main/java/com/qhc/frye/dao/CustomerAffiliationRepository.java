package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.ApplicationOfRolechange;
import com.qhc.frye.entity.CustomerAffiliation;
import com.qhc.frye.entity.identity.CustomerIndustryIdentity;
/**
 * 
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerAffiliationRepository extends JpaRepository<CustomerAffiliation, CustomerIndustryIdentity>{

}
