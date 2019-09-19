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
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CustomerAffiliationRepository;
import com.qhc.frye.dao.CustomerClassRepository;
import com.qhc.frye.dao.CustomerIndustryRepository;
import com.qhc.frye.dao.CustomerRepository;
import com.qhc.frye.dao.SapLastUpdatedRepository;
import com.qhc.frye.domain.CustomerAffiliation;
import com.qhc.frye.domain.CustomerClass;
import com.qhc.frye.domain.DClazzOfMaterial;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.domain.Industry;
import com.qhc.frye.domain.LastUpdated;
import com.qhc.frye.rest.controller.entity.Clazz;
import com.qhc.frye.rest.controller.entity.Currency;
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
	private CustomerClassRepository customerClassRepo;
	
	
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
	public List<Customer> searchCustomers(String name) {
		List<Customer> cuList = new ArrayList<Customer>();
		
		List<DCustomer> dcuList = customerRepo.findByName(name);
		for(DCustomer dc:dcuList) {
			Customer cu = new Customer();
			cu.setCode(dc.getCode());
			cu.setName(dc.getName());
			cu.setAddress(dc.getAddress());
			cuList.add(cu);
		}
		
		return cuList;
	}
	/**
	 * 
	 * @return customer class in the db table sap_customer_class
	 */
	public List<CustomerClass> getCustomerClasses(){
		List<CustomerClass> cucList = customerClassRepo.findAll();
		return cucList;
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
