package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qhc.sap.entity.CharacteristicDefault;

public interface CharacteristicDefaultRepository extends JpaRepository<CharacteristicDefault, String>{
	@Query(value="select * from sap_material_default_characteristic where sap_materials_code =:material",nativeQuery=true)
	public List<CharacteristicDefault> findbyMaterialCode(@Param("material")String materialCode);
}
