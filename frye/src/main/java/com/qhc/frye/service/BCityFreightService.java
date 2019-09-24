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

import com.qhc.frye.dao.BCityFreightRepository;
import com.qhc.frye.domain.BCityFreight;
import com.qhc.frye.domain.KOrderInfo;

@Service
public class BCityFreightService {
	
	@Autowired
	private BCityFreightRepository bCityFreightRepository;
	
	public BCityFreight add(BCityFreight bCityFreight) {
		return bCityFreightRepository.save(bCityFreight);
	}
	
	
	public Page<BCityFreight> getListByConditions(BCityFreight bCityFreight, Pageable pageable) {
		Specification<BCityFreight> specification = new Specification<BCityFreight>() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {

				// 增加筛选条件
				Predicate predicate = cb.conjunction();
				System.out.println(bCityFreight.getCountyName()+"2223333333");
				// 模糊查找
				if (bCityFreight.getCountyName() != null && !"".equals(bCityFreight.getCountyName())) {
					predicate.getExpressions().add(cb.like(root.get("countyName").as(String.class), "%" + bCityFreight.getCountyName() + "%"));
				}
				
				return predicate;
			}
		};
		return bCityFreightRepository.findAll(specification, pageable);
	}

}
