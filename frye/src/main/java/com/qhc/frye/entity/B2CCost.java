/**
 * 
 */
package com.qhc.frye.entity;

import java.util.Date;

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
@Table(name = "k_item_b2c")
public class B2CCost {
	@Id
	@Column(name="id", columnDefinition="INTEGER", length=10)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name="cost", columnDefinition="DECIMAL")
    private double cost;
	
	@NotNull
	@Column(name="opt_time", columnDefinition="Date")
	private Date optTime;
	
	@NotNull
	@Column(name="k_item_details_id", columnDefinition="CHAR")
	private String itemId;
	
	@NotNull
	@Column(name="operator", columnDefinition="CHAR")
	private String operator;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	

}
