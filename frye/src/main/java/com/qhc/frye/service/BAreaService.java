package com.qhc.frye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BAreaRepository;
import com.qhc.frye.domain.BArea;

@Service
public class BAreaService {
	
	@Autowired
	private BAreaRepository bAreaRepository;
	
	public BArea add(BArea bArea) {
		return bAreaRepository.save(bArea);
	}

}
