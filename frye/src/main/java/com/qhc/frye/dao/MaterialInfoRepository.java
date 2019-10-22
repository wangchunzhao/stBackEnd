/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.MaterialPrice;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface MaterialInfoRepository extends JpaRepository<MaterialPrice, String> {
	@Query(value = "select * from k_material_info_view m where m.code=:code", nativeQuery = true)
	public List<MaterialPrice> findByMaterialId(@Param("code")String code);
}
