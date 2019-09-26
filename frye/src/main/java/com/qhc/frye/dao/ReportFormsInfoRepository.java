package com.qhc.frye.dao;
//
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.domain.ReportFormsInfo;
//
//
//@Repository
public interface ReportFormsInfoRepository extends JpaRepository<ReportFormsInfo, String>,JpaSpecificationExecutor<ReportFormsInfo>{
//
//
}
