package com.qhc.system.service;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.system.domain.OperationDto;
import com.qhc.system.entity.Operation;
import com.qhc.system.mapper.OperationMapper;

/**
 * query operation info
 * 查询权限信息
 */
@Service
public class OperationService implements Serializable{

	@Autowired
	private OperationMapper operationMapper;


	/**
	 * 查询权限列表
	 * @return
	 */
	public List<Operation> findAll() {
		return operationMapper.findByParams(null);
	}
	/**
	 * 通过id查询权限
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public Operation findById(String id) {
		return operationMapper.findById(id);
	}

	public Operation createOrUpdateOperations(@Valid Operation operations) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 查询用过的所有权限
	 * 
	 * @param userIdentity
	 */
	public List<OperationDto> findByUser(String userIdentity) {
		return operationMapper.findByUserIdentity(userIdentity);
	}
	

}
