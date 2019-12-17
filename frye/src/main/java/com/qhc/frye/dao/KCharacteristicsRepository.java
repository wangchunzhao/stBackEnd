package com.qhc.frye.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.frye.domain.KCharacteristics;

@Repository
@Transactional
public interface KCharacteristicsRepository extends JpaRepository<KCharacteristics, Integer> {
	
	List<KCharacteristics> findByItemDetailsId(String itemId);
	
	@Query(value = "delete from k_characteristics where k_item_details_id=:itemDetailId", nativeQuery = true)
	@Modifying
	@Transactional
	void deleteInBatchByItemDetail(@Param(value="itemDetailId")String itemDetailId);
}
