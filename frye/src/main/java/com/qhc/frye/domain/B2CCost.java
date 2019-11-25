/**
 * 
 */
package com.qhc.frye.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "k_item_b2c")
public class B2CCost {
	@Id
	@Column(name="id", columnDefinition="INTEGER", length=10)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private String id;
	private double cost;
	private Date optTime;
	private String itemId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getOptTime() {
		return optTime;
	}
	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	

}
