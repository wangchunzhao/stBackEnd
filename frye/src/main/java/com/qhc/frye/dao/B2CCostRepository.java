/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.B2CCost;

/**
 * @author wang@dxc.com	
 *
 */
@Repository
public interface B2CCostRepository  extends JpaRepository<B2CCost, Integer>{
	@Query(value = "select * from k_item_b2c where k_item_details_id = :itemId limit 1",  nativeQuery = true)
	B2CCost findAllB2CByItem(@Param(value="itemId")String itemId );
}
