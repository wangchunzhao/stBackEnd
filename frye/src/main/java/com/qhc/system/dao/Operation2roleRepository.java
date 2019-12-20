package com.qhc.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.system.entity.RoleOperation;

@Repository
public interface Operation2roleRepository extends JpaRepository<RoleOperation, Integer> {

	/**
	 * 通过角色id及激活状态查询
	 * @param id isActive
	 * @return
	 */
	List<RoleOperation> getOperation2roleByRoleIdAndIsActive(int roleId,int isActive);
	/**
	 * 通过权限id及激活状态查询
	 * @param id isActive
	 * @return
	 */
	List<RoleOperation> getOperation2roleByOperationIdAndIsActive(String operationId,int isActive);
	List<RoleOperation> getOperation2roleByRoleId(int roleId);
	List<RoleOperation> getOperation2roleByOperationId(String operationId);

}
