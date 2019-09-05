/**
 * 
 */
package com.qhc.frye.rest.controller.entity;

import java.util.Date;

import com.qhc.frye.domain.DCustomer;

/**
 * @author wang@dxc.com
 *
 */
public class Customer implements InterEntityToDao {
	private static final long serialVersionUID = -9169262959918008183L;
	public final static String CODE_CUSTOMER = "59870645008146f9938f7e8818031778";
	public final static String NAME_CUSTOMER = "Customer master date";

	private String code;
	private String name;
	private Date changedDate;
	private String address;
	private String groupCode;
	private String clazzCode;
	private String levelCode;
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getClazzCode() {
		return clazzCode;
	}
	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}
	
	
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
	@Override
	public DCustomer toDao() {
		DCustomer dc = new DCustomer();
		dc.setCode(this.getCode());
		dc.setName(this.getName());
		dc.setAddress(this.getAddress());
		dc.setChangedDate(this.getChangedDate());
		dc.setClazzCode(this.getClazzCode());
		dc.setGroupCode(this.getGroupCode());
		dc.setLevelCode(this.getLevelCode());
		return dc;
	}
}
