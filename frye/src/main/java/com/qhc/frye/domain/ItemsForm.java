package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "k_forms")
public class ItemsForm implements Serializable{
	
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
	public int id;
	
	@NotNull
	@Column(name = "k_form_number")
	public Date kFormNumber;//
	
	@NotNull
	@Column(name = "earliest_product_date")
	public Date earliestProductDate;//最早交货时间
	
	@NotNull
	@Column(name = "earliest_delivery_date")
	public Date earliestDeliveryDate;//最早发货时间
	
	@Column(name = "comments",columnDefinition ="TEXT")
	public String comments;
	
	@Column(name = "k_order_info_id")
	public int kOrderInfoId;


	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public Date getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}


	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
	}


	public Date getkFormNumber() {
		return kFormNumber;
	}


	public void setkFormNumber(Date kFormNumber) {
		this.kFormNumber = kFormNumber;
	}


	public Date getEarliestProductDate() {
		return earliestProductDate;
	}


	public void setEarliestProductDate(Date earliestProductDate) {
		this.earliestProductDate = earliestProductDate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public int getkOrderInfoId() {
		return kOrderInfoId;
	}


	public void setkOrderInfoId(int kOrderInfoId) {
		this.kOrderInfoId = kOrderInfoId;
	}






}
