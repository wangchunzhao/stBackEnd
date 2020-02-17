package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.SapProductClass;

@Repository
public interface SapProductClassRepository extends JpaRepository<SapProductClass, String>{

}
