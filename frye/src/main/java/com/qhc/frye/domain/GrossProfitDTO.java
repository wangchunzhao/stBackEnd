package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.List;

import com.qhc.frye.rest.controller.entity.AbsOrder;

/**
 * 合同列表信息实体
 * @author lizuoshan
 *
 */
public class GrossProfitDTO implements Serializable {
	

	private AbsOrder absOrder;
	private List<SapSalesGroup> sapSalesGroupList;
	
	
	




	public List<SapSalesGroup> getSapSalesGroupList() {
		return sapSalesGroupList;
	}
	public void setSapSalesGroupList(List<SapSalesGroup> sapSalesGroupList) {
		this.sapSalesGroupList = sapSalesGroupList;
	}
	public AbsOrder getAbsOrder() {
		return absOrder;
	}
	public void setAbsOrder(AbsOrder absOrder) {
		this.absOrder = absOrder;
	}

	
	
}
