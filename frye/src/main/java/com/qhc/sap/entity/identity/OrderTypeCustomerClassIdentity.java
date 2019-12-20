/**
 * 
 */
package com.qhc.sap.entity.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */
@Embeddable
public class OrderTypeCustomerClassIdentity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6225739348379407371L;
	private static final int FACTOR = 7;
	@NotNull
	@Column(name = "sap_order_type_code", columnDefinition="CHAR", length = 4)
	private String typeCode;
	@NotNull
	@Column(name = "sap_customer_class_code", columnDefinition="CHAR", length = 2)
	private String clazzCode;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getClazzCode() {
		return clazzCode;
	}

	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this.getClass() == o.getClass()) {

			OrderTypeCustomerClassIdentity obj = (OrderTypeCustomerClassIdentity) o;
			if (obj.getClazzCode().equals(this.getClazzCode()))
				if (obj.getTypeCode().equals(this.getTypeCode()))
					return true;
		}

		return false;

	}

	@Override
	public int hashCode() {
		return this.getClazzCode().hashCode() * FACTOR + this.getTypeCode().hashCode();
	}
	

}
