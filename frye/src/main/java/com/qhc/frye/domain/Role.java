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
 * 
 * @author lizuoshan
 *
 */
@Entity
@Table(name = "b_roles")
public class Role {
	
	@Id
    @NotNull
  //  @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="CHAR",length = 32)
    private Integer id;
	
	@NotNull
	@Column(name="name",columnDefinition="TEXT",length = 64)
    private String name;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}




}
