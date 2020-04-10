package com.qhc.order.mapper;

import java.util.List;
import java.util.Map;

import com.qhc.order.domain.SpecialDeliveryDto;
import com.qhc.order.entity.SpecialOrderApplication;

/**
 * 
 * SpecialOrderApplication数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface SpecialOrderApplicationMapper {
	/**
	 * 
	 * 按主键查找.
	 * 
	 * @param id id
	 * @return SpecialOrderApplication对象
	 */
	SpecialOrderApplication findById(Integer id);

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
	 * 按参数查询SpecialOrderApplication信息.
	 * 
	 * @param params 查询参数
	 * @return SpecialOrderApplication列表
	 */
	List<SpecialDeliveryDto> findByParams(Map<String, Object> params);

	/**
	 * 
	 * 新增SpecialOrderApplication.
	 * 
	 * @param specialOrderApplication 新增的SpecialOrderApplication对象
	 * @return 新增的记录数
	 */
	int insert(SpecialOrderApplication specialOrderApplication);

	/**
	 * 
	 * 修改SpecialOrderApplication.
	 * 
	 * @param specialOrderApplication 修改的SpecialOrderApplication对象
	 * @return 修改的记录数
	 */
	int update(SpecialOrderApplication specialOrderApplication);

	/**
	 * 
	 * 按主键删除单条SpecialOrderApplication.
	 * 
	 * @param id id
	 * @return
	 */
	int deleteById(Integer id);

	SpecialOrderApplication findByOrderInfo(Integer orderInfoId);

}