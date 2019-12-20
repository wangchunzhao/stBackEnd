package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.Role;

/**
 * 
 * Role数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface RoleMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return Role对象
   */
	Role findById(Integer id);

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
   * 按参数查询Role信息.
   * 
   * @param params 查询参数
   * @return Role列表
   */
	List<Role> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Role.
   * 
   * @param role 新增的Role对象
   * @return 新增的记录数
   */
	int insert(Role role);

  /**
   * 
   * 修改Role.
   * 
   * @param role 修改的Role对象
   * @return 修改的记录数
   */
	int update(Role role);

  /**
   * 
   * 按主键删除单条Role.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);
	
	/**
	 * 查询用户所属的角色
	 * 
	 * @param userIdentity
	 * @return
	 */
	List<Role> findByUserIdentity(String userIdentity);

} 