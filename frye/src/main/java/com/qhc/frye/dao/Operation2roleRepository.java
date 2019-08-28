package com.qhc.frye.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qhc.frye.domain.Operation2role;

@Repository
public interface Operation2roleRepository extends JpaRepository<Operation2role, Integer> {

	/**
	 * 通过角色id及激活状态查询
	 * @param id isActive
	 * @return
	 */
	List<Operation2role> getOperation2roleByRoleIdAndIsActive(int roleId,int isActive);
	/**
	 * 通过权限id及激活状态查询
	 * @param id isActive
	 * @return
	 */
	List<Operation2role> getOperation2roleByOperationIdAndIsActive(String operationId,int isActive);
	List<Operation2role> getOperation2roleByRoleId(int roleId);
	List<Operation2role> getOperation2roleByOperationId(String operationId);

}
