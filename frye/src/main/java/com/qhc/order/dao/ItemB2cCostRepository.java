/**
 * 
 */
package com.qhc.order.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.ItemB2c;

/**
 * @author wang@dxc.com	
 *
 */
@Repository
public interface ItemB2cCostRepository  extends JpaRepository<ItemB2c, Integer>{
	@Query(value = "select * from k_item_b2c where k_item_details_id = :itemId limit 1",  nativeQuery = true)
	ItemB2c findAllB2CByItem(@Param(value="itemId")String itemId );
}
