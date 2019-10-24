package com.qhc.frye.domain;import java.io.Serializable;import java.util.Date;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.Id;import javax.persistence.Table;/** *  *  <br>  * * @author walker */@Entity@Table(name = "k_characteristics")public class KCharacteristics		implements Serializable {	private static final long serialVersionUID = -1044360665941018442L;		// Column(id) - Id		@Id	@Column(name="id")	private Integer id = null;	/* Column(key_code) - 选定的特征代码 */	@Column(name="key_code")	private String keyCode = null;	/* Column(value_code) - 选定的特征值的代码 */	@Column(name="value_code")	private String valueCode = null;	/* Column(k_item_details_id) - KItemDetailsId */	/* Foreign Column(id)) Reference from (k_item_details) */ 	@Column(name="k_item_details_id",columnDefinition="CHAR",length=32)	private String itemDetailsId = null;	public KCharacteristics(){	}	public Integer getId() {		return this.id;	}	public void setId(Integer id) {		this.id = id;	}	 	public String getKeyCode() {		return this.keyCode;	}	public void setKeyCode(String keyCode) {		this.keyCode = keyCode;	}	 	public String getValueCode() {		return this.valueCode;	}	public void setValueCode(String valueCode) {		this.valueCode = valueCode;	}	 	public String getItemDetailsId() {		return this.itemDetailsId;	}	public void setItemDetailsId(String itemDetailsId) {		this.itemDetailsId = itemDetailsId;	}	 	@Override	public int hashCode() {		final int prime = 31;		int result = 1;		result = prime * result + ((id == null) ? 0 : id.hashCode());		return result;	}	@Override	public boolean equals(Object obj) {		if (this == obj) {			return true;		}		if (obj == null) {			return false;		}		if (getClass() != obj.getClass()) {			return false;		}		final KCharacteristics other = (KCharacteristics) obj;		return (this.id == null ? other.id == null : this.id.equals(other.id));	}		public String toString() {	    final String tab = "  ";	    String str = "";	    str = "KCharacteristics ( "	        + "id = " + this.id + tab	        + "keyCode = " + this.keyCode + tab	        + "valueCode = " + this.valueCode + tab	        + "kItemDetailsId = " + this.itemDetailsId + tab	        + " )";		    return str;	}}