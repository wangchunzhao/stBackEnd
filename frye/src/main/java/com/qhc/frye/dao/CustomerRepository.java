/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.domain.User;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<DCustomer, Integer>{

}
