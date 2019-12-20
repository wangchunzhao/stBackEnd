package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qhc.frye.entity.BMaterialGroupOrder;
import com.qhc.frye.entity.DIndustryCode;

@Repository
public interface BMaterialGroupOrderRepository extends JpaRepository<BMaterialGroupOrder, String> {
}
