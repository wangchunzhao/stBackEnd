package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.BArea;
@Repository
public interface BAreaRepository extends JpaRepository<BArea, Integer> ,JpaSpecificationExecutor<BArea>{

}
