/**
 * 
 */
package com.qhc.sap.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qhc.order.domain.InterEntityToEntity;
import com.qhc.sap.entity.CustomerAffiliation;
import com.qhc.sap.entity.Customer;
import com.qhc.sap.entity.Industry;
import com.qhc.sap.entity.identity.CustomerIndustryIdentity;

/**
 * @author wang@dxc.com
 *
 */
public class CustomerDto implements InterEntityToEntity,Serializable {
	private static final long serialVersionUID = -9169262959918008183L;
	public final static String CODE_CUSTOMER = "59870645008146f9938f7e8818031778";
	public final static String NAME_CUSTOMER = "Customer master date";

	private String code;
	private String name;
	private Date changedDate;
	private String address;
	//private String groupCode;
	private String clazzCode;
	private String clazzName;
	private String levelCode;
	private String affiliationCode;
	private String affiliationName;
	private String industryCodeCode;
	
	public CustomerDto() {
		
	}
	public CustomerDto(String code,String name,String clazzCode,String clazzName) {
		this.code = code;
		this.name= name;
		this.clazzCode = clazzCode;
		this.clazzName =  clazzName;
	}
	
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
//	public String getGroupCode() {
//		return groupCode;
//	}
//	public void setGroupCode(String groupCode) {
//		this.groupCode = groupCode;
//	}
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
	
		
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getAffiliationCode() {
		return affiliationCode;
	}
	public void setAffiliationCode(String affiliationCode) {
		this.affiliationCode = affiliationCode;
	}
	public String getAffiliationName() {
		return affiliationName;
	}
	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}
	public String getIndustryCodeCode() {
		return industryCodeCode;
	}
	public void setIndustryCodeCode(String industryCodeCode) {
		this.industryCodeCode = industryCodeCode;
	}
	@Override
	public List<Object> toEntity() {
		List<Object> objs = new ArrayList<Object>();
		Customer dc = new Customer();
		dc.setCode(this.getCode());
		dc.setName(this.getName());
		dc.setAddress(this.getAddress());
		dc.setChangedDate(this.getChangedDate());
		dc.setClazzCode(this.getClazzCode());
		dc.setIndustryCodeCode(this.getIndustryCodeCode());
//		dc.setGroupCode(this.getGroupCode());
//		dc.setLevelCode(this.getLevelCode());
		objs.add(dc);
		//
		if(this.getAffiliationCode()!=null && !this.getAffiliationCode().isEmpty()) {
			Industry indu = new Industry();
			indu.setCode(this.getAffiliationCode());
			indu.setName(this.getAffiliationName());
			//sap同步的数据设置默认值
			indu.setReserved(false);
			objs.add(indu);
			//
			CustomerAffiliation ca= new CustomerAffiliation();
			CustomerIndustryIdentity cii = new CustomerIndustryIdentity();
			cii.setCustomerCode(this.getCode());
			cii.setIndustryCode(this.getAffiliationCode());
			ca.setCii(cii);
			//
			objs.add(ca);
		}
		return objs;
	}
}