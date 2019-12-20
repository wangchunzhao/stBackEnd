/**
 * 
 */
package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.CustomerClass;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CustomerClassRepository extends JpaRepository<CustomerClass, String>{

}
