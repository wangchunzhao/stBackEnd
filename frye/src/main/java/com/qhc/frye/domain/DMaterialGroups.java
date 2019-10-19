/**
 * 
 */
package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name="sap_material_groups")
public class DMaterialGroups {
	
	@Id
    @NotNull
    @Column(name="code",columnDefinition="CHAR",length=3)
	private String code;

    @NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
    
    @Column(name="b_material_group_order_code",columnDefinition="CHAR")
	private String materialGroupOrderCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaterialGroupOrderCode() {
		return materialGroupOrderCode;
	}

	public void setMaterialGroupOrderCode(String materialGroupOrderCode) {
		this.materialGroupOrderCode = materialGroupOrderCode;
	}
}
