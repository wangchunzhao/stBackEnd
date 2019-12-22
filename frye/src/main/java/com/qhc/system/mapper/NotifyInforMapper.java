package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.NotifyInfor;

/**
 * 
 * NotifyInfor数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface NotifyInforMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return NotifyInfor对象
   */
	NotifyInfor findById(Integer id);

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
   * 按参数查询NotifyInfor信息.
   * 
   * @param params 查询参数
   * @return NotifyInfor列表
   */
	List<NotifyInfor> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增NotifyInfor.
   * 
   * @param notifyInfor 新增的NotifyInfor对象
   * @return 新增的记录数
   */
	int insert(NotifyInfor notifyInfor);

  /**
   * 
   * 修改NotifyInfor.
   * 
   * @param notifyInfor 修改的NotifyInfor对象
   * @return 修改的记录数
   */
	int update(NotifyInfor notifyInfor);

  /**
   * 
   * 按主键删除单条NotifyInfor.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);

} 