package com.qhc.frye.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qhc.frye.dao.OperationRepository;
import com.qhc.frye.domain.Operations;
import com.qhc.frye.domain.User;

/**
 * query operation info
 * 查询权限信息
 */
@Service
public class OperationService {

	@Autowired
	private OperationRepository operationRepository;


	/**
	 * 查询权限列表
	 * @return
	 */
	public List<Operations> findAll() {
		return operationRepository.findAll();
	}
	/**
	 * 通过id查询权限
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public Operations findById(Integer id) {
		return operationRepository.findById(id).get();
	}

	public Operations createOrUpdateOperations(@Valid Operations operations) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
