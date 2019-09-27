package com.qhc.frye.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BAreaRepository;
import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.KOrderInfo;

@Service
public class BAreaService {
	
	@Autowired
	private BAreaRepository bAreaRepository;
	
	public BArea add(BArea bArea) {
		return bAreaRepository.save(bArea);
	}
	
	public Page<List<Object>> getList(String name, Pageable pageable){
		return bAreaRepository.findByName(name, pageable);
	}

}
