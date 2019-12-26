package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.DeliveryAddressDto;
import com.qhc.order.entity.DeliveryAddress;

/**
 * 
 * DeliveryAddress数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface DeliveryAddressMapper {
	/**
	 * 
	 * 按主键查找.
	 * 
	 * @param id id
	 * @return DeliveryAddress对象
	 */
	DeliveryAddress findById(Integer id);

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
	 * 按参数查询DeliveryAddress信息.
	 * 
	 * @param params 查询参数
	 * @return DeliveryAddress列表
	 */
	List<DeliveryAddress> findByParams(Map<String, Object> params);

	/**
	 * 
	 * 新增DeliveryAddress.
	 * 
	 * @param deliveryAddress 新增的DeliveryAddress对象
	 * @return 新增的记录数
	 */
	int insert(DeliveryAddress deliveryAddress);

	/**
	 * 
	 * 修改DeliveryAddress.
	 * 
	 * @param deliveryAddress 修改的DeliveryAddress对象
	 * @return 修改的记录数
	 */
	int update(DeliveryAddress deliveryAddress);

	/**
	 * 
	 * 按主键删除单条DeliveryAddress.
	 * 
	 * @param id id
	 * @return
	 */
	int deleteById(Integer id);

	List<DeliveryAddressDto> findByOrderInfoId(Integer orderInfoId);

	void deleteByOrderInfoId(Integer orderInfoId);

}