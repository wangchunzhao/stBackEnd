/**
 * 
 */
package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qhc.frye.domain.ItemDetails;

/**
 * @author lizuoshan
 *
 */
public interface ItemDetailRepository extends JpaRepository<ItemDetails, String> ,JpaSpecificationExecutor<ItemDetails> {
	
	@Query(value="select * from k_item_details where k_forms_id =?1" ,nativeQuery=true)
	List<ItemDetails> findByKFormsId(String id);
	
	@Query(value="SELECT k_item_details.* FROM k_orders left join k_order_version on k_orders.id = k_order_version.k_orders_id "+
			"left join k_order_info on k_order_version.k_order_info_id = k_order_info.id "+
			"left join k_forms on k_forms.k_order_info_id = k_order_info.id "+
			"left join k_item_details on k_item_details.k_forms_id = k_forms.id "+
			"where k_orders.sequence_number = :seqNumber and k_order_version.version=:version" ,nativeQuery=true)
	List<ItemDetails> findByOrder(@Param(value = "seqNumber") String seqNumber,@Param(value="version") String version);

}
