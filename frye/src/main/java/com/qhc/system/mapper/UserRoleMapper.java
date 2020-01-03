package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.Role;
import com.qhc.system.entity.UserRole;

/**
 * 
 * UserRole数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface UserRoleMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param userRole
   * @return UserRole对象
   */
	UserRole findById(UserRole userRole);

  /**
   * 
   * 按参数统计记录数.
   * 
   * @param params 查询参数
   * @return 满足条件的记录数
   */
	int countByParams(Map<String, Object> params);

  /**
   * 
   * 按参数查询UserRole信息.
   * 
   * @param params 查询参数
   * @return UserRole列表
   */
	List<UserRole> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增UserRole.
   * 
   * @param userRole 新增的UserRole对象
   * @return 新增的记录数
   */
	int insert(UserRole userRole);

  /**
   * 
   * 修改UserRole.
   * 
   * @param userRole 修改的UserRole对象
   * @return 修改的记录数
   */
	int update(UserRole userRole);

  /**
   * 
   * 按主键删除单条UserRole.
   * 
   * @param userRole
   * @return
   */
	int deleteById(UserRole userRole);
	
	void deleteByUserId(Integer id);

	/**
	 * 根据role_id删除UserRole
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(Integer roleId);

} 