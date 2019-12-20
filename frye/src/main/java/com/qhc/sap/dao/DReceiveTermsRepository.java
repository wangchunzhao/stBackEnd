package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.DReceiveTerms;

@Repository
public interface DReceiveTermsRepository extends JpaRepository<DReceiveTerms, String> {
}
