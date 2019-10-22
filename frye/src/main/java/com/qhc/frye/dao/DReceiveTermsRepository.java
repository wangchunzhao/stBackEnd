package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DReceiveTerms;

@Repository
public interface DReceiveTermsRepository extends JpaRepository<DReceiveTerms, String> {
}
