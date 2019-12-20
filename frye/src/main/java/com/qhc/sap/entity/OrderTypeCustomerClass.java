/**
 * 
 */
package com.qhc.sap.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qhc.sap.entity.identity.OrderTypeCustomerClassIdentity;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "sap_order_type_and_customer_class")
public class OrderTypeCustomerClass {
	@EmbeddedId
	private OrderTypeCustomerClassIdentity otcci;

	public OrderTypeCustomerClassIdentity getOtcci() {
		return otcci;
	}

	public void setOtcci(OrderTypeCustomerClassIdentity otcci) {
		this.otcci = otcci;
	}
	

	
}
