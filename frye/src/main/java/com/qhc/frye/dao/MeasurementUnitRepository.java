/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.DUnit;

/**
 * @author 
 *
 */
@Repository
public interface MeasurementUnitRepository extends JpaRepository<DUnit, String> {
}
