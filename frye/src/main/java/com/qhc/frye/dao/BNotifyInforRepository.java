package com.qhc.frye.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.qhc.frye.entity.BNotifyInfor;

public interface BNotifyInforRepository extends JpaRepository<BNotifyInfor, Integer> ,JpaSpecificationExecutor<BNotifyInfor>{

}
