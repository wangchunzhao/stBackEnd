/**
 * 
 */
package com.qhc.sap.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.sap.dao.SalesGroupRepository;
import com.qhc.sap.dao.SalesOfficeRepository;
import com.qhc.sap.domain.SalesGroup;
import com.qhc.sap.entity.SapSalesGroup;
import com.qhc.sap.entity.SapSalesOffice;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class LocationService {

	@Autowired
	private SalesGroupRepository groupRepo;

	@Autowired
	private SalesOfficeRepository officeRepo;

	/**
	 * delete all data in DB table
	 */
	@Transactional
	public void clean() {
		groupRepo.deleteAll();
		officeRepo.deleteAll();
	}
	/**
	 * put data to db table , please clean it before this.
	 */
	@Transactional
	public void put(List<SalesGroup> salesGroups) {
		
		Set<SapSalesGroup> groups = new HashSet<SapSalesGroup>();
		Set<SapSalesOffice> offices = new HashSet<SapSalesOffice>();
		for (SalesGroup sg : salesGroups) {
			SapSalesGroup ssg = new SapSalesGroup();
			ssg.setCode(sg.getCode());
			ssg.setName(sg.getName());
			ssg.setOfficeCode(sg.getOfficeCode());
			
			SapSalesOffice sso = new SapSalesOffice();
			sso.setCode(sg.getOfficeCode());
			sso.setName(sg.getOfficeName());
			//出口
			if("S007".equals(sg.getOfficeCode())) {
				sso.setTypeCode("20");
			}else if("S006".equals(sg.getOfficeCode())) {
				//冷库
				sso.setTypeCode("30");
			}else {
				//内销
				sso.setTypeCode("10");
			}
			groups.add(ssg);
			offices.add(sso);
		}
		officeRepo.saveAll(offices);
		groupRepo.saveAll(groups);
	}
	
}
