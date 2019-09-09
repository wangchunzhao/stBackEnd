package com.qhc.frye.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author lizuoshan
 * 主键策略UUid
 *
 */
@Entity
@Table(name = "b_operations")
public class Operations implements Serializable{
	
	@Id
    @NotNull
//    @GeneratedValue(strategy= GenerationType.AUTO, generator="uuid")
    @Column(name="id",columnDefinition="CHAR",length=32)
    private String id;
	
	@NotNull
	@Column(name="name",columnDefinition="TEXT",length = 64)
    private String name;
	
	@Transient
	public boolean selected;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}




}
