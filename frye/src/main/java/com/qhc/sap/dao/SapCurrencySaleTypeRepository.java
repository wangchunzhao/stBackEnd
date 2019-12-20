package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.SapCurrencySaleType;
import com.qhc.sap.entity.identity.CurrencySaleTypeIdentity;
@Repository
public interface SapCurrencySaleTypeRepository extends JpaRepository<SapCurrencySaleType, CurrencySaleTypeIdentity>{

}
