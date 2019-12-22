package com.qhc.sap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.OrderInfo;
import com.qhc.sap.entity.SapSalesGroup;
@Repository
public interface SapSaleGroupRepository extends JpaRepository<SapSalesGroup, String> ,JpaSpecificationExecutor<SapSalesGroup>{

}
