/**
 * 
 */
package com.qhc.frye.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qhc.frye.domain.identity.CustomerIndustryIdentity;
import com.qhc.frye.domain.identity.MaterialClazzIdentity;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "sap_material_clazz")
public class MaterialClazz {
	
	@EmbeddedId
	private MaterialClazzIdentity mci;

	public MaterialClazzIdentity getMci() {
		return mci;
	}

	public void setMci(MaterialClazzIdentity mci) {
		this.mci = mci;
	}
	
	@Override
	public String toString() {
		return mci.getClazzCode()+","+mci.getMaterialCode();
	}
	
	

}
