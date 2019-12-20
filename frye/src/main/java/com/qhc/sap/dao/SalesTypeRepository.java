package com.qhc.sap.dao;
/**
 * 
 * @author wang@dxc.com
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.SalesType;

@Repository
public interface SalesTypeRepository extends JpaRepository<SalesType, String>{

}
