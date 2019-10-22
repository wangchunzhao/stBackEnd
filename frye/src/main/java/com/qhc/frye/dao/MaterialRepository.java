/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DMaterial;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface MaterialRepository extends JpaRepository<DMaterial, String>{
	@Query(value ="SELECT * FROM sap_materials where description like %:name%",
			countQuery="SELECT count(*) FROM sap_materials where description like %:name%",nativeQuery = true)
	public Page<DMaterial> findAllByName(@Param("name")String name,Pageable pageable); 
}
