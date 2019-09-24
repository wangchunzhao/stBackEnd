package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.BCity;

@Repository
public interface BCityRepository extends JpaRepository<BCity, Integer> ,JpaSpecificationExecutor<BCity>{

}
