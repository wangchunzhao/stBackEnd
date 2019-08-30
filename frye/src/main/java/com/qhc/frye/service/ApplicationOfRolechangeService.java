package com.qhc.frye.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.ApplicationRepository;
import com.qhc.frye.domain.ApplicationOfRolechange;

/**
 */
@Service
public class ApplicationOfRolechangeService {

	@Autowired
	private ApplicationRepository applicationRepository;



	public List<ApplicationOfRolechange> findByBUsersId(int userId) {
		return applicationRepository.findByBUsersId(userId);
	}

}
