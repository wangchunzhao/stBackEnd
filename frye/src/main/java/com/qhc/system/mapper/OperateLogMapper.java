package com.qhc.system.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.system.entity.OperateLog;

/**
 * 
 * OperateLog数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface OperateLogMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @return OperateLog对象
   */
	OperateLog findById();

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
   * 按参数查询OperateLog信息.
   * 
   * @param params 查询参数
   * @return OperateLog列表
   */
	List<OperateLog> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增OperateLog.
   * 
   * @param operateLog 新增的OperateLog对象
   * @return 新增的记录数
   */
	int insert(OperateLog operateLog);

  /**
   * 
   * 修改OperateLog.
   * 
   * @param operateLog 修改的OperateLog对象
   * @return 修改的记录数
   */
//	int update(OperateLog operateLog);

  /**
   * 
   * 按主键删除单条OperateLog.
   * 
   * @return
   */
//	int deleteById();

} 