package com.qhc.frye.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.SalesTypeRepository;
import com.qhc.frye.domain.DSalesType;

import com.qhc.frye.rest.controller.entity.Order;

@Service
public class OrderService {
	
	@Autowired
	private SalesTypeRepository salesTypeRepo;
	
	/**
	 * 
	 * @return sales type
	 */
	public List<DSalesType> getSalesTypes(){
		return salesTypeRepo.findAll();
	}
	
	public void save(Order order){
		
	}
	public void update(Order order){
		
	}

}
