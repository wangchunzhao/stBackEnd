package com.qhc.order.mapper;

/**
 * 报表DAO
 */
import java.util.List;
import java.util.Map;

/**
 * 
 * 报表数据访问接口.
 * <p>
 * 
 * @author Walker
 */
public interface ReportMapper {
	/**
	 * 
	 * 购销明细报表
	 * 
	 * @param params 查询参数
	 * @return 满足条件的记录数
	 */
	List<Map<String, Object>> findPurchaseAndSalesReport(Map<String, Object> params);

	/**
	 * 
	 * 投标跟踪报表
	 * 
	 * @param params 查询参数
	 * @return 满足条件的记录数
	 */
	List<Map<String, Object>> findBiddingReport(Map<String, Object> params);

	/**
	 * 
	 * 销售订单汇总报表
	 * 
	 * @param params 查询参数
	 * @return 满足条件的记录数
	 */
	List<Map<String, Object>> findOrderSummaryReport(Map<String, Object> params);
}
