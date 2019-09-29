package com.qhc.frye.service;

import java.text.SimpleDateFormat;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.qhc.frye.dao.ReportFormsInfoRepository;
import com.qhc.frye.domain.ReportFormsInfo;

@Service
public class ReportFormsInfoService {

	@Autowired
	private ReportFormsInfoRepository reportFormsInfoRepository;


	public Page<ReportFormsInfo> getReportFormsInfoByConditions(ReportFormsInfo reportFormsInfo, Pageable pageable) {
		Specification<ReportFormsInfo> specification = new Specification<ReportFormsInfo>() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
				// 增加筛选条件
				Predicate predicate = cb.conjunction();
				//订单编号
				if (reportFormsInfo.getSequenceNumber() != null && !"".equals(reportFormsInfo.getSequenceNumber())) {
					predicate.getExpressions().add(cb.like(root.get("sequenceNumber").as(String.class), "%" + reportFormsInfo.getSequenceNumber() + "%"));
				}
				//合同号（核算号）
				if (reportFormsInfo.getContractorCode() != null && !"".equals(reportFormsInfo.getContractorCode())) {
					predicate.getExpressions().add(cb.like(root.get("contractCode").as(String.class), "%" + reportFormsInfo.getContractorCode() + "%"));
				}
				//签约单位
				if (reportFormsInfo.getContractUnit() != null && !"".equals(reportFormsInfo.getContractUnit())) {
					predicate.getExpressions().add(cb.like(root.get("contractUnit").as(String.class),
							"%" + reportFormsInfo.getContractUnit() + "%"));
				}
				//需求创建日期
				if (reportFormsInfo.getStartTime() != null && !reportFormsInfo.getStartTime().trim().equals("")) {
					predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),reportFormsInfo.getStartTime()));
				}
				if (reportFormsInfo.getEndTime() != null && !reportFormsInfo.getEndTime().trim().equals("")) {
					predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), reportFormsInfo.getEndTime()));
				}
				//性质分类
				if (reportFormsInfo.getContractorClassCode() !=null&&!"".equals(reportFormsInfo.getContractorClassCode())) {
					predicate.getExpressions().add(cb.equal(root.get("contractorClassCode").as(String.class),reportFormsInfo.getContractorClassCode()));
				}
				//是否年采
				//是否特批折扣
				if (reportFormsInfo.getIsSpecialDiscount() > -1) {
					predicate.getExpressions().add(cb.equal(root.get("isSpecialDiscount").as(Integer.class), reportFormsInfo.getIsSpecialDiscount()));
				}
				//状态
				if (reportFormsInfo.getOrderTypeCode() !=null&&!"".equals(reportFormsInfo.getOrderTypeCode())) {
					predicate.getExpressions().add(cb.equal(root.get("orderTypeCode").as(String.class), reportFormsInfo.getOrderTypeCode()));
				}
				//大区
				if (reportFormsInfo.getOfficeCode() !=null&&!"".equals(reportFormsInfo.getOfficeCode())) {
					predicate.getExpressions().add(cb.equal(root.get("officeCode").as(String.class), reportFormsInfo.getOfficeCode()));
				}
				return predicate;
			}
		};
		return reportFormsInfoRepository.findAll(specification, pageable);
	}
	
	
}
