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
@Table(name="sap_characteristic_value")
public class DCharacteristicValue {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
    @Column(name="id",length=11)
	private Integer id;
	
    @NotNull
    @Column(name="code",length=30)
	private String code;
	
	@NotNull
    @Column(name="name",columnDefinition="TEXT")
	private String name;
	
	@NotNull
    @Column(name="sap_characteristic_code",length=30)
	private String character;
	
	
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
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
		

}
