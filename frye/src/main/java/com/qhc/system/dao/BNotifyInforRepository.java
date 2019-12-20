package com.qhc.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.qhc.system.entity.NotifyInfor;

public interface BNotifyInforRepository extends JpaRepository<NotifyInfor, Integer> ,JpaSpecificationExecutor<NotifyInfor>{

}
