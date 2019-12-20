package com.qhc.system.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.system.entity.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {

	Operation findById(String id);



}
