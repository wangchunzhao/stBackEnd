package com.qhc.frye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BProvinceRepository;
import com.qhc.frye.entity.BProvince;

@Service
public class BProvinceService {
	
	@Autowired
	private BProvinceRepository bProvinceRepository;
	
	public BProvince add(BProvince bProvince) {
		return bProvinceRepository.save(bProvince);
	}
	
}
