/**
 * 
 */
package com.qhc.frye.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CustomerClassRepository;
import com.qhc.frye.dao.OrderTypeRepository;
import com.qhc.frye.domain.CustomerClass;
import com.qhc.frye.domain.OrderTypeCustomerClass;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class ConstantService {
	
	private final static String KEY ="00";
	
	@Autowired
	private CustomerClassRepository customerClassRepo;
	
	@Autowired
	private OrderTypeRepository orderTypeRepository;
	
	public static Map<String,String> customerClazz;
	
	public static Map<String,String> orderType;//Map<customerClassCode,List<ordertype code>> 
	
	public Map<String,String> findAllCustomerClazz() {
		if(customerClazz==null || customerClazz.isEmpty()) {
			customerClazz = new HashMap<String,String> ();
			List<CustomerClass> cucList = customerClassRepo.findAll();
			for(CustomerClass cc:cucList) {
				customerClazz.put(cc.getCode(), cc.getName());
			}
		}
		return customerClazz;
	}

	public String findCustomerClazzByCode(String code) {
		
			return this.findAllCustomerClazz().get(code);
		
	}
	
	public Map<String,String> getOrderTypes(){
		if(orderType==null || orderType.isEmpty()) {
			orderType = new HashMap<String,String>();
			List<OrderTypeCustomerClass> ods = orderTypeRepository.findAll();
			
			for(OrderTypeCustomerClass od:ods) {
				if(orderType.putIfAbsent(od.getOtcci().getClazzCode(),od.getOtcci().getTypeCode())!=null) {
					orderType.put(KEY,od.getOtcci().getTypeCode());
				}
			}
		}
		return orderType;
		
	}

}
