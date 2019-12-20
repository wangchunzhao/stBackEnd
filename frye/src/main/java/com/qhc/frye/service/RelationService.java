package com.qhc.frye.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.Operation2roleRepository;
import com.qhc.frye.entity.Operation2role;
import com.qhc.frye.entity.Operations;
import com.qhc.frye.entity.Role;

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
	public List<Operation2role> findByRoleId(int roleId,int isActive) {
		
		return orRepository.getOperation2roleByRoleIdAndIsActive(roleId,isActive);
	}
	
	/**
	 * 通过id查询角色
	 * @param id
	 * @return
	 * @throws NoSuchElementException
	 */
	public List<Operation2role> findByRoleId(int roleId) {
		
		return orRepository.getOperation2roleByRoleId(roleId);
	}

	/**
	 * 通过权限id查询
	 * @param operationId,isActive
	 * @return
	 */
	public List<Operation2role> findByOperationId(String operationId,int isActive) {
		return orRepository.getOperation2roleByOperationIdAndIsActive(operationId, isActive);
	}
	
	/**
	 * 通过权限id查询
	 * @param operationId
	 * @return
	 */
	public List<Operation2role> findByOperationId(String operationId) {
		return orRepository.getOperation2roleByOperationId(operationId);
	}
	
	/**
	 * 通过权限id删除
	 * @param operationId,isActive
	 * @return
	 */
	public void delete(int id) {
		orRepository.deleteById(id);
	}
	
	/**
	 *  remove relationShip by role id
	 * @param operationId,isActive
	 * @return
	 */
	public void remove(int id) {
		//通过角色id查询所有的关系
		List<Operation2role> ors = orRepository.getOperation2roleByRoleIdAndIsActive(id,1);
		for(Operation2role or :ors) {
//			or.setIsActive(1);
//			orRepository.save(or);
			orRepository.delete(or);
		}
	}
	
	public List<Operation2role> saveRelation(int roleId,String[] operations) {
		
		Set<Operation2role> set = new HashSet<Operation2role>();
		for(String opId : operations) {
			Operation2role or = new Operation2role();
			or.setIsActive(1);
			or.setOperationId(opId);
			or.setRoleId(roleId);
			or.setOptTime(new Date());
			set.add(or);
		}
		return orRepository.saveAll(set);
	}

	public List<Operation2role> saveRelation(Role role) {
		List<Operation2role> list = new ArrayList<Operation2role>();
		//删除所有权限关系
		remove(role.getId());
		List<Operations> operations = role.getOperations();
		Set<Operation2role> orset = new HashSet<Operation2role>();
		if(operations!=null&&operations.size()>0) {
			for(Operations op : operations) {
				Operation2role or = new Operation2role();
				or.setIsActive(1);
				or.setOperationId(op.getId());
				or.setRoleId(role.getId());
				or.setOptTime(new Date());
				orset.add(or);
			}
			list = orRepository.saveAll(orset);
		}
		return list;
	}



}
