/**
 * 
 */
package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.MaterialView;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface MaterialInfoRepository extends JpaRepository<MaterialView, String> {
	@Query(value = "select * from sap_material_info_view m where m.code=:code", nativeQuery = true)
	public List<MaterialView> findByMaterialId(@Param("code")String code);
}