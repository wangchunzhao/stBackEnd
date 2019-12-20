/**
 * 
 */
package com.qhc.sap.domain;

import com.qhc.order.domain.AbsConObject;

/**
 * @author wang@dxc.com
 *
 */
public class IncotermDto extends AbsConObject{

	private String sapSalesTypeCode;

	public String getSapSalesTypeCode() {
		return sapSalesTypeCode;
	}

	public void setSapSalesTypeCode(String sapSalesTypeCode) {
		this.sapSalesTypeCode = sapSalesTypeCode;
	}
}
