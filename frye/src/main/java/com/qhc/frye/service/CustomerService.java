/**
 * 
 */
package com.qhc.frye.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CustomerAffiliationRepository;
import com.qhc.frye.dao.CustomerIndustryRepository;
import com.qhc.frye.dao.CustomerRepository;
import com.qhc.frye.dao.SapLastUpdatedRepository;
import com.qhc.frye.domain.CustomerAffiliation;
import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.domain.Industry;
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
	private SapLastUpdatedRepository lastUpdate;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CustomerIndustryRepository industryRepo;
	
	@Autowired
	private CustomerAffiliationRepository affilitionRepo;
	
	@Autowired
	private ConstantService constService;
	
	
	public Date getLastUpdated(String code) {
		Optional<LastUpdated> lu = lastUpdate.findById(code);
		if(lu.isPresent()) {
			return lu.get().getLastUpdate();
		}
		Date d = new Date(DEFAULT_DATE);

		return d;
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Page<DCustomer> searchCustomers(String name,int pageNo) {
		
		if( pageNo >0){
			pageNo = pageNo-1;
		}
		
		Page<DCustomer> dcuList = customerRepo.findByName(name,PageRequest.of(pageNo,2));
		for(DCustomer dc:dcuList) {		
			dc.setClazzName(constService.findCustomerClazzByCode(dc.getClazzCode()));
		}
		
		return dcuList;
	}

	
	/**
	 * 
	 * @param customers
	 */
	public void saveCustomers(List<Customer> customers) {
		Set<DCustomer> dcList = new HashSet<DCustomer>();
		Set<Industry> induList = new HashSet<Industry>();
		Set<CustomerAffiliation> caList = new HashSet<CustomerAffiliation>();
		for(Customer cus:customers) {
			List<Object> objs = cus.toDaos();
			for(Object obj:objs) {
				if( obj instanceof Industry) {
					induList.add((Industry)obj);
				}else if (obj instanceof CustomerAffiliation) {
					caList.add((CustomerAffiliation)obj);
				}else if (obj instanceof DCustomer){
					dcList.add((DCustomer)obj);
				}			
			}
		}
		customerRepo.saveAll(dcList);
		industryRepo.saveAll(induList);
		affilitionRepo.saveAll(caList);
	}
	
	
	
}
