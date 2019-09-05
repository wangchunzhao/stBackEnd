/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import com.qhc.frye.domain.DCustomer;

/**
 * @author wang@dxc.com
 *
 */
public class Customer extends DCustomer {
	private static final long serialVersionUID = -9169262959918008183L;
	public final static String CODE_CUSTOMER = "59870645008146f9938f7e8818031778";
	public final static String NAME_CUSTOMER = "Customer master date";
	
	public DCustomer toDao() {
		DCustomer dc = new DCustomer();
		dc.setCode(this.getCode());
		dc.setName(this.getName());
		dc.setAddress(this.getAddress());
		dc.setChangedDate(this.getChangedDate());
		dc.setClazzCode(this.getClazzCode());
		dc.setGroupCode(this.getGroupCode());
		dc.setLevelCode(this.getLevelCode());
		return dc;
	}
}
