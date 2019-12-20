package com.qhc.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.system.dao.BProvinceRepository;
import com.qhc.system.entity.Province;

@Service
public class ProvinceService {
	
	@Autowired
	private BProvinceRepository bProvinceRepository;
	
	public Province add(Province bProvince) {
		return bProvinceRepository.save(bProvince);
	}
	
}
