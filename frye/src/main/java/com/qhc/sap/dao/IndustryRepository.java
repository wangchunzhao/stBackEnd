package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.Industry;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, String> {
	
}
