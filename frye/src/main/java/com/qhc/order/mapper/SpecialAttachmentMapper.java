package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.order.entity.SpecialAttachment;

/**
 * 
 * SpecialAttachment数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface SpecialAttachmentMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return SpecialAttachment对象
   */
	SpecialAttachment findById(Integer id);

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
   * 按参数查询SpecialAttachment信息.
   * 
   * @param params 查询参数
   * @return SpecialAttachment列表
   */
	List<SpecialAttachment> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增SpecialAttachment.
   * 
   * @param specialAttachment 新增的SpecialAttachment对象
   * @return 新增的记录数
   */
	int insert(SpecialAttachment specialAttachment);

  /**
   * 
   * 修改SpecialAttachment.
   * 
   * @param specialAttachment 修改的SpecialAttachment对象
   * @return 修改的记录数
   */
	int update(SpecialAttachment specialAttachment);

  /**
   * 
   * 按主键删除单条SpecialAttachment.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);
	
	/**
	 * 查询特批发货所有附件
	 * 
	 * @param specialId
	 * @return
	 */
	List<SpecialAttachment> findBySpecialId(Integer specialId);
	
	/**
	 * 删除特批发货所有附件
	 * 
	 * @param specialId
	 */
	void deleteBySpecialId(Integer specialId);

} 