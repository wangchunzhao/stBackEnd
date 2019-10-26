package com.qhc.frye.rest.controller.entity;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.ItemsForm;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.OrderSupportInfo;

/**
 * @author wang@dxc.com
 *
 */
public abstract class AbsOrder{
//	private final static String EXCEPTION_TOO_LONG = "too long";
//	private final static String EXCEPTION_MUST_HAVE = "must have";
//	
//	
//	private String orderType;//dealer or keyaccount or bulk
//	/**
//	 * 客户基本信息 Basic information
//	 */
//	
//	private String contracterCode;//签约单位 Contract unit
//	
//	private String customerName;//店名 customer name
//	private int isConvenientStore;//是否便利店 convenience store
//	
//	private String salesCode;//客户经理 Customer manager
//	private String salesName;//客户经理 Customer manager
//	private String salesTelnumber;//客户经理电话 Customer manager Tel
//	
//	private int isNew;//是否新客户 new customer
//	/**
//	 * 合同详细信息 Contract details
//	 */
//	
//	private String sequenceNumber;//流水号 code
//	private String contractNumber;//合同号 contract no
//	private String saleType;//销售类型 Sales type
//	private double taxRate;//税率 Rate
//	private String incoterm;//国际贸易条件 international trade
//	private String incotermContect;//国际贸易条件 international trade
//	private double contractValue;//原币合同金额 Contract amount
//	private double contractRMBValue;//合同金额 Contract amount
//	private String currency;//币种 currency
//	private double currencyExchange;//汇率 exchange rate
//	//public abstract double getItemsAmount();//购销明细金额合计 Aggregate amount
//	/*
//	 * 合同详细信息 Contract details
//	 */
//	private String officeCode;//大区 area
//	private String groupCode;//中心 center
//	private int warrenty;//保修年限
//	private String installCode;//安装方式 installation
//	private String provinceCode; //地区 Region,到货地址 Address
//	private String cityCode; //地区 Region,到货地址 Address
//	private String distinctCode; //地区 Region,到货地址 Address
//	private String address; //地区 Region,到货地址 Address
//	private String contactor1Id;//授权人1及身份证号
//	private String contactor1Tel;//授权人1及身份证号
//	private String contactor2Id;//授权人2及身份证号
//	private String contactor2Tel;//授权人2及身份证号
//	private String contactor3Id;//授权人3及身份证号
//	private String contactor3Tel;//授权人3及身份证号
//	private String confirmTypeCode;//收货方式 Receiving way
//	private String transferTypeCode;//运输类型 Type of transportation
//	private double freight;//运费
//	//private int isAllinBulk;//是否全部为散件
//	/**
//	 * 结算方式 Method of payment
//	 */
////	private List<AbsSettlement> settles;
//	/**
//	 * 调研表相关内容 Research table related content
//	 */
//	private int isTerm1;//柜体控制阀件是否甲供
//	private int isTerm2;//分体柜是否远程监控
//	private int isTerm3;//立柜柜体是否在地下室
//	/**
//	 * 购销明细 Purchase and sale subsidiar
//	 */
////	private List<ProductItemForm> items;//购销明细
//	private ItemsForm itemsForm;//购销明细表单
//	private String comments;//备注
//	/**
//	 * 附件信息 Attachment information
//	 */
//	private List<String> attachedFileName;
//	
//	/**
//	 * from session
//	 */
//	
//	private String currentUser;//当前session用户
//	
//	
//	private int submitType;//1.save 2.submit 3.margin 4.wtw margin
//	//
//	private String recordCode;// 项目报备编号 report number
//	
//	private String customerNatureCode;//终端客户性质 customer nature
//	/**
//	 * 
//	 */
//	private double discount;//合并折扣
//	
//	private double bodyDiscount;//柜体折扣 standard discount
//	
//	private double mainDiscount;//机组折扣 standard discount
//	
//	private int isLongterm;//是否为长期折扣
//	
//	private double approvedDicount;//批准的折扣、标准折扣
//	//
//	
//	public AbsOrder () {
//		System.out.println("here");
//	}
//	public AbsOrder (String Json) {
//		System.out.println("here");
//	}
//	
//	public String getCurrentUser() {
//		return currentUser;
//	}
//	public void setCurrentUser(String currentUser) {
//		this.currentUser = currentUser;
//	}
//
//	public int getSubmitType() {
//		return submitType;
//	}
//	public void setSubmitType(int submitType) {
//		this.submitType = submitType;
//	}
//	//
//	public String getContracterCode() {
//		return contracterCode;
//	}
//	public void setContracterCode(String contracterCode) {
//		this.contracterCode = contracterCode;
//	}
//	public String getCustomerName() {
//		return customerName;
//	}
//	public void setCustomerName(String customerName) {
//		this.customerName = customerName;
//	}
//	public int getIsConvenientStore() {
//		return isConvenientStore;
//	}
//	public void setIsConvenientStore(int isConvenientStore) {
//		this.isConvenientStore = isConvenientStore;
//	}
//	public String getSalesTelnumber() {
//		return salesTelnumber;
//	}
//	public void setSalesTelnumber(String salesTelnumber) {
//		this.salesTelnumber = salesTelnumber;
//	}
//	public int getIsNew() {
//		return isNew;
//	}
//	public void setIsNew(int isNew) {
//		this.isNew = isNew;
//	}
//	public String getSequenceNumber() {
//		return sequenceNumber;
//	}
//	public void setSequenceNumber(String sequenceNumber) {
//		this.sequenceNumber = sequenceNumber;
//	}
//	public String getContractNumber() {
//		return contractNumber;
//	}
//	public void setContractNumber(String contractNumber) {
//		this.contractNumber = contractNumber;
//	}
//	public String getSaleType() {
//		return saleType;
//	}
//	public void setSaleType(String saleType) {
//		this.saleType = saleType;
//	}
//	public double getTaxRate() {
//		return taxRate;
//	}
//	public void setTaxRate(double taxRate) {
//		this.taxRate = taxRate;
//	}
//	public String getIncoterm() {
//		return incoterm;
//	}
//	public void setIncoterm(String incoterm) {
//		this.incoterm = incoterm;
//	}
//	public String getIncotermContect() {
//		return incotermContect;
//	}
//	public void setIncotermContect(String incotermContect) {
//		this.incotermContect = incotermContect;
//	}
//	public double getContractValue() {
//		return contractValue;
//	}
//	public void setContractValue(double contractValue) {
//		this.contractValue = contractValue;
//	}
//	public double getContractRMBValue() {
//		return contractRMBValue;
//	}
//	public void setContractRMBValue(double contractRMBValue) {
//		this.contractRMBValue = contractRMBValue;
//	}
//	public String getCurrency() {
//		return currency;
//	}
//	public void setCurrency(String currency) {
//		this.currency = currency;
//	}
//	public double getCurrencyRate() {
//		return currencyExchange;
//	}
//	public void setCurrencyRate(double currencyRate) {
//		this.currencyExchange = currencyRate;
//	}
//	public String getOfficeCode() {
//		return officeCode;
//	}
//	public void setOfficeCode(String officeCode) {
//		this.officeCode = officeCode;
//	}
//	public String getGroupCode() {
//		return groupCode;
//	}
//	public void setGroupCode(String groupCode) {
//		this.groupCode = groupCode;
//	}
//	public int getWarrenty() {
//		return warrenty;
//	}
//	public void setWarrenty(int warrenty) {
//		this.warrenty = warrenty;
//	}
//	
//	public String getRecordCode() {
//		return recordCode;
//	}
//	public void setRecordCode(String recordCode) {
//		this.recordCode = recordCode;
//	}
//	public String getCustomerNatureCode() {
//		return customerNatureCode;
//	}
//	public void setCustomerNatureCode(String customerNatureCode) {
//		this.customerNatureCode = customerNatureCode;
//	}
//	public double getDiscount() {
//		return discount;
//	}
//	public void setDiscount(double discount) {
//		this.discount = discount;
//	}
//	public double getBodyDiscount() {
//		return bodyDiscount;
//	}
//	public void setBodyDiscount(double bodyDiscount) {
//		this.bodyDiscount = bodyDiscount;
//	}
//	public double getMainDiscount() {
//		return mainDiscount;
//	}
//	public void setMainDiscount(double mainDiscount) {
//		this.mainDiscount = mainDiscount;
//	}
//	public int getIsLongterm() {
//		return isLongterm;
//	}
//	public void setIsLongterm(int isLongterm) {
//		this.isLongterm = isLongterm;
//	}
//	public double getApprovedDicount() {
//		return approvedDicount;
//	}
//	public void setApprovedDicount(double approvedDicount) {
//		this.approvedDicount = approvedDicount;
//	}
//	public String getContactor1Id() {
//		return contactor1Id;
//	}
//	public void setContactor1Id(String contactor1Id) {
//		this.contactor1Id = contactor1Id;
//	}
//	public String getContactor1Tel() {
//		return contactor1Tel;
//	}
//	public void setContactor1Tel(String contactor1Tel) {
//		this.contactor1Tel = contactor1Tel;
//	}
//	public String getContactor2Id() {
//		return contactor2Id;
//	}
//	public void setContactor2Id(String contactor2Id) {
//		this.contactor2Id = contactor2Id;
//	}
//	public String getContactor2Tel() {
//		return contactor2Tel;
//	}
//	public void setContactor2Tel(String contactor2Tel) {
//		this.contactor2Tel = contactor2Tel;
//	}
//	public String getContactor3Id() {
//		return contactor3Id;
//	}
//	public void setContactor3Id(String contactor3Id) {
//		this.contactor3Id = contactor3Id;
//	}
//	public String getContactor3Tel() {
//		return contactor3Tel;
//	}
//	public void setContactor3Tel(String contactor3Tel) {
//		this.contactor3Tel = contactor3Tel;
//	}
//	public String getConfirmTypeCode() {
//		return confirmTypeCode;
//	}
//	public void setConfirmTypeCode(String confirmTypeCode) {
//		this.confirmTypeCode = confirmTypeCode;
//	}
//	public String getTransferTypeCode() {
//		return transferTypeCode;
//	}
//	public void setTransferTypeCode(String transferTypeCode) {
//		this.transferTypeCode = transferTypeCode;
//	}
//
//	public int getIsTerm1() {
//		return isTerm1;
//	}
//	public void setIsTerm1(int isTerm1) {
//		this.isTerm1 = isTerm1;
//	}
//	public int getIsTerm2() {
//		return isTerm2;
//	}
//	public void setIsTerm2(int isTerm2) {
//		this.isTerm2 = isTerm2;
//	}
//	public int getIsTerm3() {
//		return isTerm3;
//	}
//	public void setIsTerm3(int isTerm3) {
//		this.isTerm3 = isTerm3;
//	}
//	public String getComments() {
//		return comments;
//	}
//	public void setComments(String comments) {
//		this.comments = comments;
//	}
//	public List<String> getAttachedFileName() {
//		return attachedFileName;
//	}
//	public void setAttachedFileName(List<String> attachedFileName) {
//		this.attachedFileName = attachedFileName;
//	}
//	public String getInstallCode() {
//		return installCode;
//	}
//	public void setInstallCode(String installCode) {
//		this.installCode = installCode;
//	}
//	public String getProvinceCode() {
//		return provinceCode;
//	}
//	public void setProvinceCode(String provinceCode) {
//		this.provinceCode = provinceCode;
//	}
//	public String getCityCode() {
//		return cityCode;
//	}
//	public void setCityCode(String cityCode) {
//		this.cityCode = cityCode;
//	}
//	public String getDistinctCode() {
//		return distinctCode;
//	}
//	public void setDistinctCode(String distinctCode) {
//		this.distinctCode = distinctCode;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public double getFreight() {
//		return freight;
//	}
//	public void setFreight(double freight) {
//		this.freight = freight;
//	}
//	public String getOrderType() {
//		return orderType;
//	}
//	public void setOrderType(String orderType) {
//		this.orderType = orderType;
//	}
//	public String getSalesCode() {
//		return salesCode;
//	}
//	public void setSalesCode(String salesCode) {
//		this.salesCode = salesCode;
//	}
//	public String getSalesName() {
//		return salesName;
//	}
//	public void setSalesName(String salesName) {
//		this.salesName = salesName;
//	}
//	public double getCurrencyExchange() {
//		return currencyExchange;
//	}
//	public void setCurrencyExchange(double currencyExchange) {
//		this.currencyExchange = currencyExchange;
//	}
//	
//	public ItemsForm getItemsForm() {
//		return itemsForm;
//	}
//	public void setItemsForm(ItemsForm itemsForm) {
//		this.itemsForm = itemsForm;
//	}
//	//
//	public DOrder getDorder() {
//		DOrder dorder = new DOrder();
//		//
//		dorder.setSequenceNumber(this.getSequenceNumber());//序列号
//		dorder.setOrderTypeCode(this.getOrderType());
//		dorder.setCreateTime(new Date());
//		dorder.setOwnerDomainId(this.getSalesCode());
//		dorder.setOwnerName(this.getSalesName());
//		dorder.setSalesTel(this.getSalesTelnumber());
//		dorder.setContractorCode(this.getContracterCode());
////		dorder.setContractorName();//name
////		dorder.setContractorClassCode();//customer class
////		dorder.setContractorClassName();//customer class name
//		dorder.setOfficeCode(this.getOfficeCode());
//		//
//		/*
//		 * test code
//		 */
//		dorder.setSequenceNumber("seq12345");
//		dorder.setOrderTypeCode("Z001");
//		dorder.setCreateTime(new Date());
//		dorder.setOwnerDomainId("w0123");
//		dorder.setOwnerName("test name");
//		dorder.setContractorCode("Con123");
//		dorder.setContractorName("Cont123");
//		dorder.setContractorClassCode("01");
//		dorder.setContractorClassName("conclass123");
//		dorder.setOfficeCode("10");
//		return dorder;
//	}
//	
//	public OrderSupportInfo getSupportInforOfOrder() {
//		OrderSupportInfo osi = new OrderSupportInfo();
//		osi.setContractNumber(this.getContractNumber());
//		osi.setOperationTime(new Date());
//		osi.setSupportorId(this.getCurrentUser());
//		/*
//		 * test code
//		 */
//		osi.setContractNumber("ContractNumber");
//		return osi;
//	}
//	
//	public KOrderVersion getOrderVersion() {
//		KOrderVersion orderVer = new KOrderVersion();
//		orderVer.setCreateTime(new Date());
//		return orderVer;
//	}
		
}
