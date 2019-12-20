package com.qhc.order.domain;

import java.io.Serializable;
import java.util.List;

import com.qhc.sap.entity.SapSalesGroup;

/**
 * 合同列表信息实体
 * @author lizuoshan
 *
 */
public class GrossProfitDto implements Serializable {
	

	private OrderDto salesOrder;
	private List<SapSalesGroup> sapSalesGroupList;
	
	
	




	public List<SapSalesGroup> getSapSalesGroupList() {
		return sapSalesGroupList;
	}
	public void setSapSalesGroupList(List<SapSalesGroup> sapSalesGroupList) {
		this.sapSalesGroupList = sapSalesGroupList;
	}
	public OrderDto getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(OrderDto salesOrder) {
		this.salesOrder = salesOrder;
	}
	

	
	
}
