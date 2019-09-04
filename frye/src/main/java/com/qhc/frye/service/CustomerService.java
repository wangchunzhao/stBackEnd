/**
 * 
 */
package com.qhc.frye.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.SapLastUpdatedRepository;
import com.qhc.frye.dao.SapSalesGroupRepository;
import com.qhc.frye.domain.LastUpdated;
import com.qhc.frye.rest.controller.entity.Customer;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CustomerService {
	
	public final static long DEFAULT_DATE = 1008005271098L;
	
	@Autowired
	SapLastUpdatedRepository lastUpdate;
	
	public Date getLastUpdated(String code) {
		Optional<LastUpdated> lu = lastUpdate.findById(code);
		if(lu.isPresent()) {
			return lu.get().getLastUpdate();
		}
		Date d = new Date(DEFAULT_DATE);
		System.out.println(d);
		return d;
	}
	
	public void save(List<Customer> customers) {
		
	}
	
}
