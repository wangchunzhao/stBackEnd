package com.qhc.system.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.system.entity.RoleOperation;
import com.qhc.system.mapper.OperationMapper;
import com.qhc.system.mapper.RoleOperationMapper;
import com.qhc.system.domain.OperationDto;
import com.qhc.system.domain.RoleDto;
import com.qhc.system.entity.Operation;
import com.qhc.system.entity.Role;

/**
 * query role info
 * 查询角色信息
 */
@Service
public class RelationService {

	@Autowired
	private RoleOperationMapper roleOperationMapper;

	@Autowired
	private OperationMapper operationMapper;

	/**
	 * 通过id查询角色
	 * @param id,isActive
	 * @return
	 * @throws NoSuchElementException
	 */
	public List<OperationDto> findByRoleId(int roleId) {
		List<OperationDto> operations = new ArrayList<OperationDto>();
		List<Operation> list = operationMapper.findByRoleId(roleId);
		for (Operation operation : list) {
			OperationDto dto = new OperationDto();
			BeanUtils.copyProperties(operation, dto);
			operations.add(dto);
		}
		
		return operations;
	}

	/**
	 * 通过权限id查询
	 * @param operationId,isActive
	 * @return
	 */
//	public List<RoleOperation> findByOperationId(String operationId,int isActive) {
//		return orRepository.getOperation2roleByOperationIdAndIsActive(operationId, isActive);
//	}
	
	/**
	 * 通过权限id查询
	 * @param operationId
	 * @return
	 */
//	public List<RoleOperation> findByOperationId(String operationId) {
//		return orRepository.getOperation2roleByOperationId(operationId);
//	}
	
	/**
	 * 通过权限id删除
	 * @param operationId,isActive
	 * @return
	 */
//	public void delete(int id) {
//		roleOperationMapper.deleteById(id);
//	}
	
	/**
	 *  remove relationShip by role id
	 * @param operationId,isActive
	 * @return
	 */
//	public void remove(int id) {
//		//通过角色id查询所有的关系
//		List<RoleOperation> ors = orRepository.getOperation2roleByRoleIdAndIsActive(id,1);
//		for(RoleOperation or :ors) {
////			or.setIsActive(1);
////			orRepository.save(or);
//			orRepository.delete(or);
//		}
//	}

	@Transactional
	public List<OperationDto> saveRelation(int roleId,String[] operations) {
		this.roleOperationMapper.deleteByRoleId(roleId);
		
		for(String opId : operations) {
			RoleOperation or = new RoleOperation();
			or.setOperationId(opId);
			or.setRoleId(roleId);
			
			roleOperationMapper.insert(or);
		}
		
		List<OperationDto> list = new ArrayList<OperationDto>();
		
		return list;
	}

	@Transactional
	public List<RoleOperation> saveRelation(RoleDto role) {
		List<RoleOperation> list = new ArrayList<RoleOperation>();
		//删除所有权限关系
		this.roleOperationMapper.deleteByRoleId(role.getId());
		List<OperationDto> operations = role.getOperations();
		Set<RoleOperation> orset = new HashSet<RoleOperation>();
		if(operations!=null&&operations.size()>0) {
			for(OperationDto op : operations) {
				RoleOperation or = new RoleOperation();
				or.setOperationId(op.getId());
				or.setRoleId(role.getId());
				
				roleOperationMapper.insert(or);
			}
//			list = orRepository.saveAll(orset);
		}
		return list;
	}



}
