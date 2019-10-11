/**
 * 
 */
package com.qhc.frye.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qhc.frye.domain.identity.OrderTypeCustomerClassIdentity;

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
