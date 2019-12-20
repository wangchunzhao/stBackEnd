/**
 * 
 */
package com.qhc.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qhc.order.entity.ItemsForm;

/**
 * @author wang@dxc.com
 *
 */
public interface KOrderFormRepository  extends JpaRepository<ItemsForm, String>{
	@Query(value="select * from k_forms where k_order_info_id = :header limit 1",nativeQuery= true)
	public ItemsForm findOneByHeaderId(@Param("header") String headerId);
}