package com.qhc.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.system.dao.CityRepository;
import com.qhc.system.entity.City;

@Service
public class CityService {
	
	@Autowired
	private CityRepository bCityRepository;

	@Transactional
	public City add(City bCity) {
		return bCityRepository.save(bCity);
	}

}
