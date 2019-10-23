/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.CharacteristicConfiguration;

/**
 * @author wang@dxc.com
 *
 */ 
@Repository
public interface CharacteristicConfigurationRepository extends JpaRepository<CharacteristicConfiguration, String> {
	@Query(value="select * from sap_class_characteristic_value_view where where sap_clazz_code = ':clazzCode'" ,nativeQuery=true)
	public List<CharacteristicConfiguration> findAllByClazzCode(@Param("clazzCode")String clazzCode);
}
