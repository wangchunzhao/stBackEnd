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
import com.qhc.frye.domain.CustomerClass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wang@dxc.com
 *
 */
@Getter
@Service
public class ConstantService {
	
	@Autowired
	private CustomerClassRepository customerClassRepo;
	
	public static Map<String,String> customerClazz;
	
	public Map<String,String> findAllCustomerClazz() {
		if(customerClazz==null || customerClazz.isEmpty()) {
			List<CustomerClass> cucList = customerClassRepo.findAll();
			for(CustomerClass cc:cucList) {
				customerClazz.put(cc.getCode(), cc.getName());
			}
		}
		return customerClazz;
	}

	public String findCustomerClazzByCode(String code) {
		if(customerClazz==null || customerClazz.isEmpty()) {
			customerClazz = new HashMap();
			List<CustomerClass> cucList = customerClassRepo.findAll();
			for(CustomerClass cc:cucList) {
				customerClazz.put(cc.getCode(), cc.getName());
			}
		}
		return customerClazz.get(code);
	}

}
