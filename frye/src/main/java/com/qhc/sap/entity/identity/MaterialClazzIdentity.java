/**
 * 
 */
package com.qhc.sap.entity.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author wang@dxc.com
 *
 */
@Embeddable
public class MaterialClazzIdentity implements Serializable{
	
	private static final int FACTOR = 17;

	/**
	 * 
	 */
	private static final long serialVersionUID = 8006132324944229389L;
	

	@Column(name = "sap_clazz_code", nullable = false, length = 18)
	private String clazzCode;

	@Column(name = "sap_materials_code", nullable = false, length = 18)
	private String materialCode;
	
	
	
	public String getClazzCode() {
		return clazzCode;
	}

	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	@Override
	public boolean equals(Object o) {

		if (this.getClass() == o.getClass()) {

			MaterialClazzIdentity obj = (MaterialClazzIdentity) o;
			if (obj.getClazzCode().equals(this.getClazzCode()))
				if (obj.getMaterialCode().equals(this.getMaterialCode()))
					return true;
		}

		return false;

	}

	@Override
	public int hashCode() {
		return this.getClazzCode().hashCode() * FACTOR + this.getMaterialCode().hashCode();
	}

}
