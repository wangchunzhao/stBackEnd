/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.qhc.frye.domain.ItemsForm;

/**
 * @author lizuoshan
 *
 */
public interface OrderFormRepository extends JpaRepository<ItemsForm, String> ,JpaSpecificationExecutor<ItemsForm>{

	
	@Query(value="select * from k_forms where k_order_info_id =?1" ,nativeQuery=true)
	ItemsForm findByKOrderInfoId(String id);

}
