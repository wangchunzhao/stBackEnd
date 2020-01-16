package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.order.entity.ItemAttachment;

/**
 * 
 * ItemAttachment数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ItemAttachmentMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return ItemAttachment对象
   */
	ItemAttachment findById(Integer id);

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
   * 按参数查询ItemAttachment信息.
   * 
   * @param params 查询参数
   * @return ItemAttachment列表
   */
	List<ItemAttachment> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增ItemAttachment.
   * 
   * @param itemAttachment 新增的ItemAttachment对象
   * @return 新增的记录数
   */
	int insert(ItemAttachment itemAttachment);

  /**
   * 
   * 修改ItemAttachment.
   * 
   * @param itemAttachment 修改的ItemAttachment对象
   * @return 修改的记录数
   */
	int update(ItemAttachment itemAttachment);

  /**
   * 
   * 按主键删除单条ItemAttachment.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);
	
	/**
	 * 查询行项目所有附件
	 * 
	 * @param specialId
	 * @return
	 */
	List<ItemAttachment> findByItemId(Integer itemId);
	
	/**
	 * 删除订单所有附件
	 * 
	 * @param specialId
	 */
	void deleteByOrderInfoId(Integer orderInfoId);

} 