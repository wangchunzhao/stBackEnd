package com.qhc.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.system.dao.ProvinceRepository;
import com.qhc.system.entity.Province;

@Service
public class ProvinceService {
	
	@Autowired
	private ProvinceRepository bProvinceRepository;

	@Transactional
	public Province add(Province bProvince) {
		return bProvinceRepository.save(bProvince);
	}
	
}
