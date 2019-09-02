/**
 * 
 */
package com.qhc.frye.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.RoleRepository;
import com.qhc.frye.dao.SapSalesGroupRepository;
import com.qhc.frye.dao.SapSalesOfficeRepository;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.domain.User;
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
	private void clean() {
		groupRepo.deleteAll();
		officeRepo.deleteAll();
	}
	/**
	 * 
	 */
	public void refersh(List<SalesGroup> groups) {
		
		this.clean();
		//
		List<SapSalesGroup> gs = new ArrayList();
		List<SapSalesOffice> os = new ArrayList();
		//
		
		//
		officeRepo.saveAll(os);
		groupRepo.saveAll(gs);
		
	}
	
}
