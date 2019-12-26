package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;


import com.qhc.order.entity.Attachment;

/**
 * 
 * Attachment数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface AttachmentMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return Attachment对象
   */
	Attachment findById(Integer id);

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
   * 按参数查询Attachment信息.
   * 
   * @param params 查询参数
   * @return Attachment列表
   */
	List<Attachment> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增Attachment.
   * 
   * @param attachment 新增的Attachment对象
   * @return 新增的记录数
   */
	int insert(Attachment attachment);

  /**
   * 
   * 修改Attachment.
   * 
   * @param attachment 修改的Attachment对象
   * @return 修改的记录数
   */
	int update(Attachment attachment);

  /**
   * 
   * 按主键删除单条Attachment.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);
	
	public List<Attachment> findByOrderInfoId(Integer orderInfoId);

	void deleteByOrderInfoId(Integer orderInforId);

} 