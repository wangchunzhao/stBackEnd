package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.List;

import com.qhc.frye.rest.controller.entity.AbsOrder;
import com.qhc.frye.rest.controller.entity.SalesOrder;

/**
 * 合同列表信息实体
 * @author lizuoshan
 *
 */
public class GrossProfitDTO implements Serializable {
	

	private SalesOrder salesOrder;
	private List<SapSalesGroup> sapSalesGroupList;
	
	
	




	public List<SapSalesGroup> getSapSalesGroupList() {
		return sapSalesGroupList;
	}
	public void setSapSalesGroupList(List<SapSalesGroup> sapSalesGroupList) {
		this.sapSalesGroupList = sapSalesGroupList;
	}
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}
	

	
	
}
