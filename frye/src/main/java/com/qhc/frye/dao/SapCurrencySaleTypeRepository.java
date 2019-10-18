package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.SapCurrencySaleType;
import com.qhc.frye.domain.identity.CurrencySaleTypeIdentity;
@Repository
public interface SapCurrencySaleTypeRepository extends JpaRepository<SapCurrencySaleType, CurrencySaleTypeIdentity>{

}