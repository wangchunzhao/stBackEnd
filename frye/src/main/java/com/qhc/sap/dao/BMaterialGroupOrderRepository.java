package com.qhc.sap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.order.entity.MaterialGroupOrder;
import com.qhc.sap.entity.IndustryCode;

@Repository
public interface BMaterialGroupOrderRepository extends JpaRepository<MaterialGroupOrder, String> {
}
