package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.SapSalesOffice;

@Repository
public interface SalesOfficeRepository extends JpaRepository<SapSalesOffice, Integer> ,JpaSpecificationExecutor<SapSalesOffice>{

	SapSalesOffice findByCode(String code);
	

}
