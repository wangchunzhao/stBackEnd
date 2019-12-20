package com.qhc.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.system.dao.BCityRepository;
import com.qhc.system.entity.City;

@Service
public class CityService {
	
	@Autowired
	private BCityRepository bCityRepository;
	
	public City add(City bCity) {
		return bCityRepository.save(bCity);
	}

}
