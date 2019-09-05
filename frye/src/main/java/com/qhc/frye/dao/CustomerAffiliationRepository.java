package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.CustomerAffiliation;
/**
 * 
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerAffiliationRepository extends JpaRepository<CustomerAffiliation, Integer>{

}
