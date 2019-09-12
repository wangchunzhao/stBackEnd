/**
 * 
 */
package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "sap_unit_of_measurement")
public class DUnit {

	@Id
    @NotNull
    @Column(name="code",columnDefinition="VARCHAR",length=3)
    private int String;
	
	@NotNull
	@Column(name="name",columnDefinition="TEXT")
    private String name;

	public int getString() {
		return String;
	}

	public void setString(int string) {
		String = string;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

