/**
 * 
 */
package com.qhc.sap.domain;

import com.qhc.order.domain.AbsConObject;

/**
 * @author wang@dxc.com
 *
 */
public class SalesGroup extends AbsConObject{
	private String officeCode;
	private String officeName;
	
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	
}
