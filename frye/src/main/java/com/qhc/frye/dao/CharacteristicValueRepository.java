/**
 * 
 */
package com.qhc.frye.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DCharacteristicValue;


/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CharacteristicValueRepository extends JpaRepository<DCharacteristicValue, String> {
	


}
