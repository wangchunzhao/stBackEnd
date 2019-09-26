package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.qhc.frye.domain.SpecialDelivery;

@Repository
public interface SpecialDeliveryRepository extends JpaRepository<SpecialDelivery, Integer>,JpaSpecificationExecutor<SpecialDelivery> {

//	List<SpecialDelivery> findByOrdersId(Integer kOrdersId);
}
