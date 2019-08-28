package com.qhc.frye.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.Operation2roleRepository;
import com.qhc.frye.dao.RoleRepository;
import com.qhc.frye.domain.Operation2role;
import com.qhc.frye.domain.Role;

/**
 * query role info
 * 查询角色信息
 */
@Service
public class RelationService {

	@Autowired
	private Operation2roleRepository orRepository;

	/**
	 * 通过id查询角色
	 * @param id,isActive
	 * @return
	 * @throws NoSuchElementException
	 */
	public List<Operation2role> getByRoleId(int id,int isActive) {
		
		return orRepository.getOperation2roleByRoleIdAndIsActive(id,isActive);
	}

	/**
	 * 通过权限id查询
	 * @param operationId,isActive
	 * @return
	 */
	public List<Operation2role> getByOperationId(String operationId,int isActive) {
		return orRepository.getOperation2roleByOperationIdAndIsActive(operationId, isActive);
	}

}
