package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.CharacteristicDto;
import com.qhc.order.entity.Characteristics;

/**
 * 
 * Characteristics数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface CharacteristicsMapper {
	/**
	 * 
	 * 按主键查找.
	 * 
	 * @param id id
	 * @return Characteristics对象
	 */
	Characteristics findById(Integer id);

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
	 * 按参数查询Characteristics信息.
	 * 
	 * @param params 查询参数
	 * @return Characteristics列表
	 */
	List<Characteristics> findByParams(Map<String, Object> params);

	/**
	 * 
	 * 新增Characteristics.
	 * 
	 * @param characteristics 新增的Characteristics对象
	 * @return 新增的记录数
	 */
	int insert(Characteristics characteristics);

	/**
	 * 
	 * 修改Characteristics.
	 * 
	 * @param characteristics 修改的Characteristics对象
	 * @return 修改的记录数
	 */
	int update(Characteristics characteristics);

	/**
	 * 
	 * 按主键删除单条Characteristics.
	 * 
	 * @param id id
	 * @return
	 */
	int deleteById(Integer id);

	List<CharacteristicDto> findByItemId(Integer itemId);

	void deleteByOrderInfoId(Integer orderInfoId);

}