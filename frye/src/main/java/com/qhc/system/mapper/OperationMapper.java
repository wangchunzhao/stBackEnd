package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.system.domain.OperationDto;
import com.qhc.system.entity.Operation;

/**
 * 
 * Operation数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface OperationMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return Operation对象
   */
	Operation findById(String id);

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
   * 按参数查询Operation信息.
   * 
   * @param params 查询参数
   * @return Operation列表
   */
	List<Operation> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Operation.
   * 
   * @param operation 新增的Operation对象
   * @return 新增的记录数
   */
	int insert(Operation operation);

  /**
   * 
   * 修改Operation.
   * 
   * @param operation 修改的Operation对象
   * @return 修改的记录数
   */
	int update(Operation operation);

  /**
   * 
   * 按主键删除单条Operation.
   * 
   * @param id id
   * @return
   */
	int deleteById(String id);
	
	/**
	 * 查询用户所有权限
	 * 
	 * @param userIdentity
	 * @return
	 */
	List<OperationDto> findByUserIdentity(String userIdentity);
	
	/**
	 * 查询角色所有权限
	 * 
	 * @param userIdentity
	 * @return
	 */
	List<Operation> findByRoleId(Integer roleId);

} 