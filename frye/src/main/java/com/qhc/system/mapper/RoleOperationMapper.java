package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.system.entity.Operation;
import com.qhc.system.entity.RoleOperation;

/**
 * 
 * RoleOperation数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface RoleOperationMapper {
	/**
	 * 
	 * 按主键查找.
	 * 
	 * @param roleOperation
	 * @return RoleOperation对象
	 */
	RoleOperation findById(RoleOperation roleOperation);

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
	 * 按参数查询RoleOperation信息.
	 * 
	 * @param params 查询参数
	 * @return RoleOperation列表
	 */
	List<RoleOperation> findByParams(Map<String, Object> params);

	/**
	 * 
	 * 新增RoleOperation.
	 * 
	 * @param roleOperation 新增的RoleOperation对象
	 * @return 新增的记录数
	 */
	int insert(RoleOperation roleOperation);

	/**
	 * 
	 * 修改RoleOperation.
	 * 
	 * @param roleOperation 修改的RoleOperation对象
	 * @return 修改的记录数
	 */
	int update(RoleOperation roleOperation);

	/**
	 * 
	 * 按主键删除单条RoleOperation.
	 * 
	 * @param roleOperation
	 * @return
	 */
	int deleteById(RoleOperation roleOperation);

	/**
	 * 
	 * 按主键删除单条RoleOperation.
	 * 
	 * @param roleOperation
	 * @return
	 */
	int deleteByRoleId(Integer roleId);

}