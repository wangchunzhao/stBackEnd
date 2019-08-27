package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.Operations;

@Repository
public interface OperationRepository extends JpaRepository<Operations, Integer> {



}
