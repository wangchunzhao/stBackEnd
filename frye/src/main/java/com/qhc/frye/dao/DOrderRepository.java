package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.DOrder;
@Repository
public interface DOrderRepository extends JpaRepository<DOrder, String> ,JpaSpecificationExecutor<DOrder>{

}
