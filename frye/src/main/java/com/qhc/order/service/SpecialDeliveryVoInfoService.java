package com.qhc.order.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.qhc.order.entity.SpecialDeliveryVoInfo;

@Service
public class SpecialDeliveryVoInfoService {
	
//	@Autowired
//	private SpecialDeliveryVoInfoRepository specialDeliveryVoInfoRepository;
	
	
	public Page<SpecialDeliveryVoInfo> getInfoByConditions(SpecialDeliveryVoInfo sdv, Pageable pageable) {
		/*
		String[] ids = {"6","7"};
		Specification<SpecialDeliveryVoInfo> specification = new Specification<SpecialDeliveryVoInfo>() {
 
			@Override
			public Predicate toPredicate(Root<SpecialDeliveryVoInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (sdv.getSequenceNumber() != null && !"".equals(sdv.getSequenceNumber())) {
					Predicate p1 = cb.and(cb.like(root.get("squenceNumber").as(String.class), "%" + sdv.getSequenceNumber() + "%"));	
					list.add(p1);
				}
				if (sdv.getStartTime() != null && !sdv.getStartTime().trim().equals("")) {
					list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),sdv.getStartTime()));
				}
				if (sdv.getEndTime() != null && !sdv.getEndTime().trim().equals("")) {
					list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), sdv.getEndTime()));
				}
				if (sdv.getOrderTypeCode()!= null && !"".equals(sdv.getOrderTypeCode())) {
					list.add(cb.equal(root.get("orderTypeCode").as(String.class), sdv.getOrderTypeCode()));
				}
				if (sdv.getContractorCode()!= null && !"".equals(sdv.getContractorCode())) {
					list.add(cb.equal(root.get("contractorCode").as(String.class), sdv.getContractorCode()));
				}
				
				In<String> in = cb.in(root.get("orderTypeCode"));
			    for (String id : ids) {
			        in.value(id);
			    }
			    list.add(in);
			    Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			} 
		};
 */
		
		Specification<SpecialDeliveryVoInfo> specification = new Specification<SpecialDeliveryVoInfo>() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

				// 增加筛选条件
				Predicate predicate = cb.conjunction();

				if (sdv.getSequenceNumber() != null && !"".equals(sdv.getSequenceNumber())) {
					predicate.getExpressions().add(cb.and(cb.like(root.get("squenceNumber").as(String.class), "%" + sdv.getSequenceNumber() + "%")));
				}
				if (sdv.getStartTime() != null && !sdv.getStartTime().trim().equals("")) {
					predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),sdv.getStartTime()));
				}
				if (sdv.getEndTime() != null && !sdv.getEndTime().trim().equals("")) {
					predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), sdv.getEndTime()));
				}
				if (sdv.getOrderTypeCode()!= null && !"".equals(sdv.getOrderTypeCode())) {
					predicate.getExpressions().add(cb.equal(root.get("orderTypeCode").as(String.class), sdv.getOrderTypeCode()));
				}
				if (sdv.getContractorCode()!= null && !"".equals(sdv.getContractorCode())) {
					predicate.getExpressions().add(cb.equal(root.get("contractorCode").as(String.class), sdv.getContractorCode()));
				}
				if (sdv.getOfficeCode()!= null && !"".equals(sdv.getOfficeCode())) {
					predicate.getExpressions().add(cb.equal(root.get("officeCode").as(String.class), sdv.getOfficeCode()));
				}
				if (sdv.getOwnerDomainId()!= null && !"".equals(sdv.getOwnerDomainId())) {
					predicate.getExpressions().add(cb.equal(root.get("ownerDomainId").as(String.class), sdv.getOwnerDomainId()));
				}
				return predicate;
			}
		};
		
//		return this.specialDeliveryVoInfoRepository.findAll(specification, pageable);
		return null;
	}

	

}
