package com.qhc.frye.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
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

				// 增加筛选条件
				Predicate predicate = cb.conjunction();

				if (kOrderInfo.getStartTime() != null && !kOrderInfo.getStartTime().trim().equals("")) {
					predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),
							kOrderInfo.getStartTime()));
				}
				if (kOrderInfo.getEndTime() != null && !kOrderInfo.getEndTime().trim().equals("")) {
					predicate.getExpressions().add(
							cb.lessThanOrEqualTo(root.get("createTime").as(String.class), kOrderInfo.getEndTime()));
				}
/*				if (kOrderInfo.getB2c() > -1) {
					predicate.getExpressions().add(cb.equal(root.get("b2c").as(Integer.class), kOrderInfo.getB2c()));
				}
				if (kOrderInfo.getArea() > -1) {
					predicate.getExpressions().add(cb.equal(root.get("area").as(Integer.class), kOrderInfo.getArea()));
				}
				if (kOrderInfo.getOrderType() > -1) {
					predicate.getExpressions().add(cb.equal(root.get("order_type").as(Integer.class), kOrderInfo.getOrderType()));
				}
				if (kOrderInfo.getSpecialDiscount() > -1) {
					predicate.getExpressions().add(cb.equal(root.get("special_discount").as(Integer.class), kOrderInfo.getSpecialDiscount()));
				}
				if (kOrderInfo.getStatus() > -1) {
					predicate.getExpressions().add(cb.equal(root.get("status").as(Integer.class), kOrderInfo.getStatus()));
				}
				if (kOrderInfo.getCreateId() > -1) {
					predicate.getExpressions().add(cb.equal(root.get("createId").as(Integer.class), kOrderInfo.getCreateId()));
				}
				// 模糊查找
				if (kOrderInfo.getContractNo() != null && !"".equals(kOrderInfo.getContractNo())) {
					// predicate.getExpressions().add(cb.equal(root.get("creator").as(String.class),
					// kOrders.getCreator()));
					predicate.getExpressions().add(
							cb.like(root.get("contractNo").as(String.class), "%" + kOrderInfo.getContractNo() + "%"));
				}
				if (kOrderInfo.getContractUnit() != null && !"".equals(kOrderInfo.getContractUnit())) {
					predicate.getExpressions().add(cb.like(root.get("contractUnit").as(String.class),
							"%" + kOrderInfo.getContractUnit() + "%"));
				}*/
				return predicate;
			}
		};
		return kOrderInfoRepository.findAll(specification, pageable);
	}
	
	public Page<KOrderInfo> getKOrdersByConditions2(KOrderInfo kOrderInfo, Pageable pageable) {
		Integer[] ids = {6,7};
		Specification<KOrderInfo> specification = new Specification<KOrderInfo>() {
 
			@Override
			public Predicate toPredicate(Root<KOrderInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
/*				if (kOrderInfo.getContractNo() != null && !"".equals(kOrderInfo.getContractNo())) {
					Predicate p1 = cb.and(cb.like(root.get("contractNo").as(String.class), "%" + kOrderInfo.getContractNo() + "%"));	
					list.add(p1);
				}
				if (kOrderInfo.getStartTime() != null && !kOrderInfo.getStartTime().trim().equals("")) {
					list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class),kOrderInfo.getStartTime()));
				}
				if (kOrderInfo.getEndTime() != null && !kOrderInfo.getEndTime().trim().equals("")) {
					list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), kOrderInfo.getEndTime()));
				}
				if (kOrderInfo.getStatus() > -1) {
					list.add(cb.equal(root.get("status").as(Integer.class), kOrderInfo.getStatus()));
				}
				if (kOrderInfo.getCreateId() > -1) {
					list.add(cb.equal(root.get("createId").as(Integer.class), kOrderInfo.getCreateId()));
				}
				
				In<Integer> in = cb.in(root.get("status"));
			    for (Integer id : ids) {
			        in.value(id);
			    }
			    list.add(in);*/
			    Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			} 
		};
 
		return this.kOrderInfoRepository.findAll(specification, pageable);
	}

	public KOrderInfo findById(Integer orderId) {
		return kOrderInfoRepository.getOne(orderId);
	}
	
	
	
}
