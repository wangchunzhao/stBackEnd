/**
 * 
 */
package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.Customer;
import com.qhc.sap.entity.Incoterm;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface IncotermRepository extends JpaRepository<Incoterm, Integer>{

}
