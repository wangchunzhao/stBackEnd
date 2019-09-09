/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.domain.DIncoterm;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface IncotermRepository extends JpaRepository<DIncoterm, Integer>{

}
