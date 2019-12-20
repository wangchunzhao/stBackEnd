/**
 * 
 */
package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.DCustomer;
import com.qhc.sap.entity.DIncoterm;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface IncotermRepository extends JpaRepository<DIncoterm, Integer>{

}
