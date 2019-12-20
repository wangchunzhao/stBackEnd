package com.qhc.frye.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BNotifyInforRepository;
import com.qhc.frye.entity.ApplicationOfRolechange;
import com.qhc.frye.entity.BNotifyInfor;

@Service
public class BNotifyInforService {

	@Autowired
	private BNotifyInforRepository bNotifyInforRepository;
	
	public BNotifyInfor add(BNotifyInfor bNotifyInfor) {
		return bNotifyInforRepository.save(bNotifyInfor);
	}
	
	
	
}
