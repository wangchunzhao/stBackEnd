package com.qhc.frye.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.Operations;

@Repository
public interface OperationRepository extends JpaRepository<Operations, Integer> {

	Operations findById(String id);



}
