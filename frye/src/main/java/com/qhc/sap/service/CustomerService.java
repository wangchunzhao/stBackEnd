/**
 * 
 */
package com.qhc.sap.service;


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
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qhc.order.service.ConstantService;
import com.qhc.sap.dao.CustomerAffiliationRepository;
import com.qhc.sap.dao.CustomerIndustryRepository;
import com.qhc.sap.dao.CustomerRepository;
import com.qhc.sap.dao.SapLastUpdatedRepository;
import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.entity.CustomerAffiliation;
import com.qhc.sap.entity.Customer;
import com.qhc.sap.entity.IndustryCode;
import com.qhc.sap.entity.Industry;
import com.qhc.sap.entity.LastUpdated;
import com.qhc.sap.mapper.SapViewMapper;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CustomerService {
	
	public final static int QUANTITY_PAGE= 10;
	
	public final static long DEFAULT_DATE = 1008005271098L;
	
	@Autowired
	SapViewMapper sapViewMapper; 
	
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
	public PageInfo<CustomerDto> searchCustomers(String clazzCode,String name,int pageNo,int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CustomerDto> list = sapViewMapper.findCustomer(clazzCode, name);	
		return new PageInfo(list);
	}

	
	/**
	 * 
	 * @param customers
	 */
	@Transactional
	public void saveCustomers(List<CustomerDto> customers) {
		Set<Customer> dcList = new HashSet<Customer>();
		Set<Industry> induList = new HashSet<Industry>();
		Set<CustomerAffiliation> caList = new HashSet<CustomerAffiliation>();
		for(CustomerDto cus:customers) {
			List<Object> objs = cus.toEntity();
			for(Object obj:objs) {
				if( obj instanceof Industry) {
					induList.add((Industry)obj);
				}else if (obj instanceof CustomerAffiliation) {
					caList.add((CustomerAffiliation)obj);
				}else if (obj instanceof Customer){
					dcList.add((Customer)obj);
				}			
			}
		}
		customerRepo.saveAll(dcList);
		industryRepo.saveAll(induList);
		affilitionRepo.saveAll(caList);
	}
	
	
	
}
