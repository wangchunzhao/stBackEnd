/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.CustomerClass;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerClassRepository extends JpaRepository<CustomerClass, String>{

}
