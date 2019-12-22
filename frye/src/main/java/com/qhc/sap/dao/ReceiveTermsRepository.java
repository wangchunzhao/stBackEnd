package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.sap.entity.ReceiveTerms;

@Repository
public interface ReceiveTermsRepository extends JpaRepository<ReceiveTerms, String> {
}
