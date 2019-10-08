/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.qhc.frye.domain.ItemDetails;

/**
 * @author lizuoshan
 *
 */
public interface ItemDetailRepository extends JpaRepository<ItemDetails, String> ,JpaSpecificationExecutor<ItemDetails> {
	
	@Query(value="select * from k_item_details where k_forms_id =?1" ,nativeQuery=true)
	List<ItemDetails> findByKFormsId(String id);

}
