package com.qhc.frye.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * 
 *  <br> 
 *
 * @author walker
 */
@Entity
@Table(name = "k_acceptance_criteria")
public class AcceptanceCriteria
		implements Serializable {
	private static final long serialVersionUID = 9091466251207918989L;
		
	public static String tableName = "k_acceptance_criteria";
	
	// Primary key
	// Column(code) - 验收标准code	
	@Id
	@NotNull
	@Column(name="code", columnDefinition="CHAR", length=4)
	private String code = null;

	/* Column(name) - 验收标准名称 */
	@NotNull
	@Column(name="name", columnDefinition="TEXT")
	private String name = null;


	public AcceptanceCriteria(){
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	 
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AcceptanceCriteria other = (AcceptanceCriteria) obj;
		return (this.code == null ? other.code == null : this.code.equals(other.code));
	}
	
	public String toString() {
	    final String tab = "  ";
	    String str = "";
	    str = "KAcceptanceCriteria ( "
	        + "code = " + this.code + tab
	        + "name = " + this.name + tab
	        + " )";
	
	    return str;
	}

}