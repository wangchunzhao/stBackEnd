package com.qhc.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.system.entity.Province;
@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> ,JpaSpecificationExecutor<Province>{

}
