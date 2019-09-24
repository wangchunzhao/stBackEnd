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
@Table(name = "k_items_form")
public class ItemsForm implements Serializable{
	
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
	public int id;
	
	@NotNull
	@Column(name = "k_details_id")
	public Integer kDetailsId;
	
	@NotNull
	@Column(name = "earliest_delivery_date",columnDefinition ="datetime")
	public Date earliestDeliveryDate;//最早交货时间
	
	@NotNull
	@Column(name = "earliest_send_date",columnDefinition ="datetime")
	public Date earliestSendDate;//最早发货时间
	
	@Column(name = "remark",columnDefinition ="TEXT")
	public String remark;


	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Integer getkDetailsId() {
		return kDetailsId;
	}


	public void setkDetailsId(Integer kDetailsId) {
		this.kDetailsId = kDetailsId;
	}


	public Date getEarliestDeliveryDate() {
		return earliestDeliveryDate;
	}


	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
	}


	public Date getEarliestSendDate() {
		return earliestSendDate;
	}


	public void setEarliestSendDate(Date earliestSendDate) {
		this.earliestSendDate = earliestSendDate;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
  



}
