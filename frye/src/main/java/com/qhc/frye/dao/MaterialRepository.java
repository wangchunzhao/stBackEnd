/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DMaterial;

/**
 * @author wang@dxc.com
 *
 */
@Repository
public interface MaterialRepository extends JpaRepository<DMaterial, String>{
//	public List<DMaterial> findAllByName(String name); 
}
