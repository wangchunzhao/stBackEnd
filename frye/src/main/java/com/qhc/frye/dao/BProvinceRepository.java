package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.BProvince;
@Repository
public interface BProvinceRepository extends JpaRepository<BProvince, Integer> ,JpaSpecificationExecutor<BProvince>{

}
