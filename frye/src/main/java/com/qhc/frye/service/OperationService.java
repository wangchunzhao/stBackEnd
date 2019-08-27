package com.qhc.frye.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qhc.frye.dao.OperationRepository;
import com.qhc.frye.domain.Operations;

/**
 * query operation info
 * 查询权限信息
 */
@Service
public class OperationService {

	@Autowired
	private OperationRepository operationRepository;

	/**
	 * 通过id查询权限
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public Operations getOperations(int id) throws NoSuchElementException{
		
		return operationRepository.findById(id).get();	
		
	}

	/**
	 * 查询权限列表
	 * @return
	 */
	public List<Operations> getOperationsList() {
		return operationRepository.findAll();
	}
	

}
