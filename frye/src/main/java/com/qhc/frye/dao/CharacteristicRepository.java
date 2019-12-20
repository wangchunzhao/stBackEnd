/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.DCharacteristic;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface CharacteristicRepository extends JpaRepository<DCharacteristic, String>{

}
