package com.qhc.frye.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "k_forms")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ItemsForm implements Serializable{
	
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull
    @Column(name = "id",columnDefinition ="CHAR",length=32)
    @GeneratedValue(generator = "jpa-uuid")
	public String id;
	
	@NotNull
	@Column(name = "earliest_product_date")
	public Date earliestProductDate;//最早交货时间
	
	@NotNull
	@Column(name = "earliest_delivery_date")
	public Date earliestDeliveryDate;//最早发货时间
	
	@Column(name = "comments",columnDefinition ="TEXT")
	public String comments;
	

	@Column(name = "k_order_info_id",columnDefinition ="CHAR",length=32)
	public String kOrderInfoId;

	@NotNull
	@Column(name = "operator")
	public String operator;

	@NotNull
	@Column(name = "type",columnDefinition ="tinyint")
	public int type;
	
	@NotNull
	@Column(name="opt_time",columnDefinition="datetime")
	public Date optTime;
	
	@NotNull
	@Column(name = "is_ready",columnDefinition ="BIT")
	public int isReady;
	
	
	
	@Transient
	public List<ItemDetails> detailsList;
	
	
	
	

	






	public List<ItemDetails> getDetailsList() {
		return detailsList;
	}


	public void setDetailsList(List<ItemDetails> detailsList) {
		this.detailsList = detailsList;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Date getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}


	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
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

	public String getkOrderInfoId() {
		return kOrderInfoId;
	}


	public void setkOrderInfoId(String kOrderInfoId) {
		this.kOrderInfoId = kOrderInfoId;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public Date getOptTime() {
		return optTime;
	}


	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}


	public int getIsReady() {
		return isReady;
	}


	public void setIsReady(int isReady) {
		this.isReady = isReady;
	}






}
