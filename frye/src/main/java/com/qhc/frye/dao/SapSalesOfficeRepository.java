package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.SapSalesOffice;

@Repository
public interface SapSalesOfficeRepository extends JpaRepository<SapSalesOffice, Integer> {

	SapSalesOffice findByCode(String code);

}
