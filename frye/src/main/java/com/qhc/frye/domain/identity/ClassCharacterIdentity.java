/**
 * 
 */
package com.qhc.frye.domain.identity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author wang@dxc.com
 *
 */
@Embeddable
public class ClassCharacterIdentity implements Serializable{
	
	private static final int FACTOR = 31;
	/**
	 * 
	 */
	private static final long serialVersionUID = 7675567765392676052L;

	@Column(name = "sap_clazz_code", nullable = false, length = 18)
	private String classCode;

	@Column(name = "sap_characteristic_code", nullable = false, length = 30)
	private String characterCode;

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getCharacterCode() {
		return characterCode;
	}

	public void setCharacterCode(String characterCode) {
		this.characterCode = characterCode;
	}
	
	@Override
	public boolean equals(Object o) {

		if (this.getClass() == o.getClass()) {

			ClassCharacterIdentity obj = (ClassCharacterIdentity) o;
			if (obj.getClassCode().equals(this.getClassCode()))
				if (obj.getCharacterCode().equals(this.getCharacterCode()))
					return true;
		}

		return false;

	}

	@Override
	public int hashCode() {
		return this.getClassCode().hashCode() * FACTOR + this.getCharacterCode().hashCode();
	}

}
