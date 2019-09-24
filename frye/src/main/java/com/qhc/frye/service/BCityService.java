package com.qhc.frye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BCityRepository;
import com.qhc.frye.domain.BCity;

@Service
public class BCityService {
	
	@Autowired
	private BCityRepository bCityRepository;
	
	public BCity add(BCity bCity) {
		return bCityRepository.save(bCity);
	}

}
