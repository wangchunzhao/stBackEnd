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

import com.qhc.frye.dao.KOrderInfoRepository;
import com.qhc.frye.domain.KOrderInfo;

@Service
public class KOrderInfoService {
	
	@Autowired
	private KOrderInfoRepository kOrderInfoRepository;
	
	public Page<KOrderInfo> getKOrdersByConditions(KOrderInfo kOrderInfo, Pageable pageable) {
		 Specification<KOrderInfo> specification = new Specification<KOrderInfo>() {
	            @Override
	            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
	            	SimpleDateFormat sdfmat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	String startTime = "2019-09-10";
	            	String endTime = "2019-09-11";
	                //增加筛选条件
	                Predicate predicate = cb.conjunction();

	                if (startTime != null && !startTime.trim().equals("")) {
	                	predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), startTime));
	                }
	                if(endTime != null && !endTime.trim().equals("")) {
	                	predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), endTime));
	                }
	                if(kOrderInfo.getB2c()!=-1) {
	                	predicate.getExpressions().add(cb.equal(root.get("b2c").as(Integer.class), kOrderInfo.getB2c()));
	                }
	                 //模糊查找
	                if(kOrderInfo.getContractNo()!=null&&!"".equals(kOrderInfo.getContractNo())) {
	                	//predicate.getExpressions().add(cb.equal(root.get("creator").as(String.class), kOrders.getCreator()));
	                	predicate.getExpressions().add(cb.like(root.get("contractNo").as(String.class), "%" + kOrderInfo.getContractNo() + "%"));
	                }
	                if(kOrderInfo.getContractUnit()!=null&&!"".equals(kOrderInfo.getContractUnit())) {
	                	predicate.getExpressions().add(cb.like(root.get("contractUnit").as(String.class), "%" + kOrderInfo.getContractUnit() + "%"));
	                }
	                return predicate;
	            }
	       };
		return kOrderInfoRepository.findAll(specification,pageable);
	}
}
