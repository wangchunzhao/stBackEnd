/**
 * 
 */
package com.qhc.sap.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qhc.sap.entity.identity.ClassCharacterIdentity;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name="sap_clazz_and_character")
public class ClassAndCharacter {
	
	@EmbeddedId
	private ClassCharacterIdentity cci;

	public ClassCharacterIdentity getCci() {
		return cci;
	}

	public void setCci(ClassCharacterIdentity cci) {
		this.cci = cci;
	}
	
	
}
