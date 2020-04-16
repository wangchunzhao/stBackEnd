package com.qhc.order.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.OrderVersion;
import com.qhc.order.entity.OrderInfo;

/**
 * 
 * OrderInfo数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface OrderInfoMapper {
	/**
	 * 
	 * 按主键查找.
	 * 
	 * @param id id
	 * @return OrderInfo对象
	 */
	OrderInfo findById(Integer id);

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
	 * 按参数查询OrderInfo信息.
	 * 
	 * @param params 查询参数
	 * @return OrderInfo列表
	 */
	List<OrderInfo> findByParams(Map<String, Object> params);

	/**
	 * 
	 * 新增OrderInfo.
	 * 
	 * @param orderInfo 新增的OrderInfo对象
	 * @return 新增的记录数
	 */
	int insert(OrderInfo orderInfo);

	/**
	 * 
	 * 修改OrderInfo.
	 * 
	 * @param orderInfo 修改的OrderInfo对象
	 * @return 修改的记录数
	 */
	int update(OrderInfo orderInfo);

	/**
	 * 
	 * 按主键删除单条OrderInfo.
	 * 
	 * @param id id
	 * @return
	 */
	int deleteById(Integer id);

	/**
	 * 
	 * 修改OrderInfo状态.
	 * 
	 * @param orderInfo 修改的OrderInfo对象
	 * @return 修改的记录数
	 */
	int updateStatus(@Param("id") Integer id, @Param("user") String user, @Param("status") String status,
			@Param("submitTime") Date submitTime, @Param("bpmSubmitTime") Date bpmSubmitTime,
			@Param("approvedBodyDiscount") Double approvedBodyDiscount,
			@Param("approvedMainDiscount") Double approvedMainDiscount);

	/**
	 * 将其他版本is_active设置为0
	 * 
	 * @param sequenceNumber
	 * @return
	 */
	int inactive(String sequenceNumber);

	/**
	 * 
	 * 按参数查询KOrderView信息.
	 * 
	 * @param params 查询参数
	 * @return KOrderView列表
	 */
	List<OrderDto> findOrderViewByParams(OrderQuery query);

	List<OrderVersion> findOrderVersions(String sequenceNumber);
	
	List<String> checkContractNumber(Map<String, Object> params);

}