/**
 * 
 */
package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.DefaultCharacterView;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface DefaultCharacterViewRepository extends JpaRepository<DefaultCharacterView, Integer>{
	@Query(value="select * from sap_default_character_value_view where sap_materials_code =:material",nativeQuery=true)
	public List<DefaultCharacterView> findByMaterial(@Param("material")String materialCode);
}
