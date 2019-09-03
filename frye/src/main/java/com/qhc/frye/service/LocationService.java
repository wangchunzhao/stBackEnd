/**
 * 
 */
package com.qhc.frye.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.SapSalesGroupRepository;
import com.qhc.frye.dao.SapSalesOfficeRepository;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;

import com.qhc.frye.rest.controller.entity.SalesGroup;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class LocationService {

	@Autowired
	private SapSalesGroupRepository groupRepo;

	@Autowired
	private SapSalesOfficeRepository officeRepo;

	/**
	 * delete all data in DB table
	 */
	public void clean() {
		groupRepo.deleteAll();
		officeRepo.deleteAll();
	}
	/**
	 * put data to db table , please clean it before this.
	 */
	public void put(List<SalesGroup> salesGroups) {
		
		List<SapSalesGroup> groups = new ArrayList();
		Set<SapSalesOffice> offices = new HashSet();
		for (SalesGroup sg : salesGroups) {
			SapSalesGroup ssg = new SapSalesGroup();
			ssg.setCode(sg.getCode());
			ssg.setName(sg.getName());
			ssg.setOfficeCode(sg.getOfficeCode());
			
			SapSalesOffice sso = new SapSalesOffice();
			sso.setCode(sg.getOfficeCode());
			sso.setName(sg.getOfficeName());			
		}
		officeRepo.saveAll(offices);
		groupRepo.saveAll(groups);
		
	}
	
}
