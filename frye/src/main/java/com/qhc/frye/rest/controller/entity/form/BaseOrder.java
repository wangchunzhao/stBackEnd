/**
 * 
 */
package com.qhc.frye.rest.controller.entity.form;

import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.OrderSupportInfo;

/**
 * @author wang@dxc.com
 *
 */
/**
 * common order type used to present etc..
 */
public class BaseOrder extends AbsOrder{
	/**
	 * 
	 * @return
	 */
	public DOrder toDOrder() {
		DOrder dorder = new DOrder();
		//
		dorder.setSequenceNumber(this.getSequenceNumber());//序列号
		dorder.setOrderTypeCode(this.getOrderType());
		dorder.setOwnerDomainId(this.getSalesCode());
		dorder.setOwnerName(this.getSalesName());
		dorder.setSalesTel(this.getSalesTelnumber());
		dorder.setContractorCode(this.getContracterCode());
		dorder.setContractorName(this.getContracterName());
		dorder.setContractorClassCode(this.getCustomerClazzCode());//customer class
		dorder.setContractorClassName(this.getCustomerClazzName());//customer class name
		dorder.setOfficeCode(this.getUserOfficeCode());
		return dorder;
	}
	/*
	 * 
	 */
	public OrderSupportInfo toSupportInforOfOrder() {
		OrderSupportInfo osi = new OrderSupportInfo();
		osi.setContractNumber(this.getContractNumber());
		osi.setSupportorId(this.getCurrentUser());
		osi.setContractNumber(this.getContractNumber());
		osi.setOperationTime(this.getOptTime());
		return osi;
	}
	
}
