/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import com.qhc.frye.domain.DCustomer;

/**
 * @author wang@dxc.com
 *
 */
public class Customer extends AbsConObject {
	
	public final static String CODE_CUSTOMER = "59870645008146f9938f7e8818031778";
	public final static String NAME_CUSTOMER = "Customer master date";
	
	public DCustomer toDao() {
		DCustomer dc = new DCustomer();
		return dc;
	}

}
