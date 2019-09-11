/**
 * 
 */
package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.MaterialClazz;


/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface MaterialClazzRepository extends JpaRepository<MaterialClazz, String>{

}
