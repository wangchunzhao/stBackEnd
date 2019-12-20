package com.qhc.order.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.AcceptanceCriteria;
import com.qhc.system.entity.Area;
@Repository
public interface AcceptanceCriteriaRepository extends JpaRepository<AcceptanceCriteria, String>{
	

}
