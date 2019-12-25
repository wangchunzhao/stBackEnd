package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.ContractDto;
import com.qhc.order.entity.Contract;

/**
 * 
 * Contract数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ContractMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return Contract对象
   */
	Contract findById(Integer id);

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
   * 按参数查询Contract信息.
   * 
   * @param params 查询参数
   * @return Contract列表
   */
	List<ContractDto> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Contract.
   * 
   * @param contract 新增的Contract对象
   * @return 新增的记录数
   */
	int insert(Contract contract);

  /**
   * 
   * 修改Contract.
   * 
   * @param contract 修改的Contract对象
   * @return 修改的记录数
   */
	int update(Contract contract);

  /**
   * 
   * 按主键删除单条Contract.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);
	
	/**
	 * 修改合同状态
	 * 
	 * @param contract
	 * @return
	 */
	int updateStatus(Contract contract);

} 