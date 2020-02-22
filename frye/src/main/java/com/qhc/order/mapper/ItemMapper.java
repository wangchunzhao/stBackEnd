package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.ItemDto;
import com.qhc.order.entity.Item;

/**
 * 
 * Item数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ItemMapper {
	/**
	 * 
	 * 按主键查找.
	 * 
	 * @param id id
	 * @return Item对象
	 */
//	Item findById(Integer id);

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
	 * 新增Item.
	 * 
	 * @param item 新增的Item对象
	 * @return 新增的记录数
	 */
	int insert(Item item);

	/**
	 * 
	 * 修改Item.
	 * 
	 * @param item 修改的Item对象
	 * @return 修改的记录数
	 */
	int update(Item item);

	/**
	 * 
	 * 按主键删除单条Item.
	 * 
	 * @param id id
	 * @return
	 */
	int deleteById(Integer id);

	/**
	 * 
	 * 按参数查询Item信息.
	 * 
	 * @param params 查询参数
	 * @return Item列表
	 */
	List<ItemDto> findByParams(Map<String, Object> params);

	List<Item> findByOrderInfoId(Integer orderInfoId);

	void deleteByOrderInfoId(Integer orderInfoId);
	
	/**
	 * 修改订单下所有行项目状态为下发sap
	 * @param item
	 */
	void updateSendSapStatusByOrderInfo(Item item);

}