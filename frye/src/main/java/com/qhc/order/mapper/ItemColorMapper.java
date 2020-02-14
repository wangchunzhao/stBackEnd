package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.CharacteristicDto;
import com.qhc.order.entity.ItemColor;

/**
 * 
 * ItemColor数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ItemColorMapper {
  /**
   * 
   * 按主键查找.
   * 
   * @param id id
   * @return ItemColor对象
   */
	ItemColor findById(Integer id);

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
   * 按参数查询ItemColor信息.
   * 
   * @param params 查询参数
   * @return ItemColor列表
   */
	List<ItemColor> findByParams(Map<String, Object> params);

  /**
   * 
   * 新增ItemColor.
   * 
   * @param itemColor 新增的ItemColor对象
   * @return 新增的记录数
   */
	int insert(ItemColor itemColor);

  /**
   * 
   * 修改ItemColor.
   * 
   * @param itemColor 修改的ItemColor对象
   * @return 修改的记录数
   */
	int update(ItemColor itemColor);

  /**
   * 
   * 按主键删除单条ItemColor.
   * 
   * @param id id
   * @return
   */
	int deleteById(Integer id);

	List<CharacteristicDto> findByItemId(Integer itemId);

	void deleteByOrderInfoId(Integer orderInfoId);

} 