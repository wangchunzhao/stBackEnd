/**
 * 
 */
package com.qhc.sap.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.Material;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, String>{
	@Query(value ="SELECT * FROM sap_materials where (UPPER(description) like %:name% or UPPER(code) like %:name%)",
			countQuery="SELECT count(1) FROM sap_materials where (UPPER(description) like %:name% or UPPER(code) like %:name%)",nativeQuery = true)
	public Page<Material> findAllByName(@Param("name")String name,Pageable pageable); 
}
