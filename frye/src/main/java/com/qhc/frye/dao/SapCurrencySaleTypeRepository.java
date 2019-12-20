package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.SapCurrencySaleType;
import com.qhc.frye.entity.identity.CurrencySaleTypeIdentity;
@Repository
public interface SapCurrencySaleTypeRepository extends JpaRepository<SapCurrencySaleType, CurrencySaleTypeIdentity>{

}
