/**
 * 
 */
package com.qhc.frye.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wang@dxc.com
 *
 */
@Entity
@Table(name = "k_attachment")
public class KAttachment {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="file_name",columnDefinition="TEXT")
	private String fileName;
	
	@Column(name="k_order_info_id",columnDefinition="CHAR")
	private String orderInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	
	

}
