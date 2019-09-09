/**
 * 
 */
package com.qhc.frye.dao;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.rest.controller.entity.Clazz;

/**
 * @author @wang@dxc.com
 *
 */
@Repository
public interface ClazzRepository extends JpaRepository<Clazz, String> {

}
