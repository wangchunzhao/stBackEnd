package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.SapPaintingClass;

@Repository
public interface SapPaintingClassRepository extends JpaRepository<SapPaintingClass, String>{

}
