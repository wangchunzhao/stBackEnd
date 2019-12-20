package com.qhc.frye.entity;

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
	@Column(name="name",columnDefinition="TEXT")
    private String name;
	
	@Column(name="description",columnDefinition="TEXT")
	private String description;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}




}
