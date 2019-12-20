package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.EnginingCost;

@Repository
public interface EnginingCostRepository extends JpaRepository<EnginingCost,Integer>{
	@Query(value = "select * from k_engining_cost where k_order_info_id = :infoId ",  nativeQuery = true)
	List<EnginingCost> findAllEnginingByItem(@Param(value="infoId")String infoId );
}
