package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.SapMaterialProductClass;

@Repository
public interface SapMaterialProductClassRepository extends JpaRepository<SapMaterialProductClass, String>{

}
