package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{

}
