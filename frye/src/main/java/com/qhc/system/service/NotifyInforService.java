package com.qhc.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.system.entity.UserRole;
import com.qhc.system.dao.NotifyInforRepository;
import com.qhc.system.entity.NotifyInfor;

@Service
public class NotifyInforService {

	@Autowired
	private NotifyInforRepository bNotifyInforRepository;

	@Transactional
	public NotifyInfor add(NotifyInfor bNotifyInfor) {
		return bNotifyInforRepository.save(bNotifyInfor);
	}
	
	
	
}
