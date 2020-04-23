package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.User;

/**
 * 
 * User数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface UserMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return User对象
   */
	User findById(Integer id);
	
	/**
	 * 按identity查找.
	 * @param loginName
	 * @return
	 */
	User findByLoginName(String loginName);

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
   * 按参数查询User信息.
   * 
   * @param params 查询参数
   * @return User列表
   */
	List<User> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增User.
   * 
   * @param user 新增的User对象
   * @return 新增的记录数
   */
	int insert(User user);

  /**
   * 
   * 修改User.
   * 
   * @param user 修改的User对象
   * @return 修改的记录数
   */
	int update(User user);

  /**
   * 
   * 按主键删除单条User.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);

} 