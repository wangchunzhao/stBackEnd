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
@Table(name = "k_engining_cost")
public class EnginingCost {
	@Id
	@Column(name="id", columnDefinition="INTEGER", length=10)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	

	@Column(name="cost", columnDefinition="DECIMAL")
    private double cost;
	

	@Column(name="k_engining_term_code", columnDefinition="CHAR")
    private String materialCode;
	

	@Column(name="k_order_info_id", columnDefinition="CHAR")
    private String orderId;
	
	@Column(name="opt_time", columnDefinition="DATE")
	private Date optTime;
	
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


	public String getMaterialCode() {
		return materialCode;
	}


	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	
}
