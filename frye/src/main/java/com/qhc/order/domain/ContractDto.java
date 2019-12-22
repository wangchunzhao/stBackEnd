package com.qhc.order.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * Function: Data transfer object. <br>
 *
 * @author walker
 */
public class ContractDto implements Serializable {
	private static final long serialVersionUID = -7870996928236577566L;

	/* Id */
	private Integer id = null;

	/* OrderInfoId */
	private Integer orderInfoId = null;

	private String version = null;

	/* SequenceNumber */
	private String sequenceNumber = null;
	
	private String contractNumber = null;

	private String contractManager = null;

	private String customerCode;// 签约单位 Contract unit
	private String customerName;// 签约单位 Contract Name

	/* PartyaMail */
	private String customerEmail = null;

	/* AmountOnContract */
	private Double contractRmbValue = null;

	/* DeliveryDaysAfterPrepay */
	private Short deliveryDays = null;

	/* ClientName */
	private String clientName = null;

	/* InstallLocation */
	private String installLocation = null;

	/* QualityStand */
	private String qualityStand = null;

	/* Settlement */
	private String settlement = null;

	/* partyaAddress */
	private String partyaAddress = null;

	/* InvoiceAddress */
	private String invoiceAddress = null;

	/* Broker */
	private String broker = null;

	/* InvoiceReceiver */
	private String invoiceReceiver = null;

	/* InvoiceTel */
	private String invoiceTel = null;

	/* InvoicePostCode */
	private String invoicePostCode = null;

	/* CompanyTel */
	private String companyTel = null;

	/* BankName */
	private String bankName = null;

	/* AccountNumber */
	private String accountNumber = null;

	/* OrderVersionId */
	private String orderVersionId = null;

	/* ReceiveTermsCode */
	private String receiveTermsCode = null;

	/* ReceiveTermsName */
	private String receiveTermsName = null;

	/* KAcceptanceCriteriaCode */
	private String acceptanceCriteriaCode = null;

//	/* Mail */
//	private String mail = null;

	private String contactor1Id;// 授权人1及身份证号
	private String contactor1Tel;// 授权人1电话
	private String contactor2Id;// 授权人2及身份证号
	private String contactor2Tel;// 授权人2电话
	private String contactor3Id;// 授权人3及身份证号
	private String contactor3Tel;// 授权人3电话

	/* 合同制作时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime = null;

	/* 合同发送时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime = null;

	/* 合同发送时间 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date signTime = null;

	/* 合同状态：1 未发送 2 已发送 */
	private String status = null;

	/* 性质分类 Classification */
	private String contractorClassCode = null;

	/* 性质分类 Classification */
	private String contractorClassName = null;

	@ApiModelProperty("文档Hash值")
	private String fileHashcode;

	@ApiModelProperty("电子签约中合同Id")
	private String signContractid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderInfoId() {
		return orderInfoId;
	}

	public void setOrderInfoId(Integer orderInfoId) {
		this.orderInfoId = orderInfoId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractManager() {
		return contractManager;
	}

	public void setContractManager(String contractManager) {
		this.contractManager = contractManager;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Double getContractRmbValue() {
		return contractRmbValue;
	}

	public void setContractRmbValue(Double contractRmbValue) {
		this.contractRmbValue = contractRmbValue;
	}

	public Short getDeliveryDays() {
		return deliveryDays;
	}

	public void setDeliveryDays(Short deliveryDays) {
		this.deliveryDays = deliveryDays;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getInstallLocation() {
		return installLocation;
	}

	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

	public String getQualityStand() {
		return qualityStand;
	}

	public void setQualityStand(String qualityStand) {
		this.qualityStand = qualityStand;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getPartyaAddress() {
		return partyaAddress;
	}

	public void setPartyaAddress(String partyaAddress) {
		this.partyaAddress = partyaAddress;
	}

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getInvoiceReceiver() {
		return invoiceReceiver;
	}

	public void setInvoiceReceiver(String invoiceReceiver) {
		this.invoiceReceiver = invoiceReceiver;
	}

	public String getInvoiceTel() {
		return invoiceTel;
	}

	public void setInvoiceTel(String invoiceTel) {
		this.invoiceTel = invoiceTel;
	}

	public String getInvoicePostCode() {
		return invoicePostCode;
	}

	public void setInvoicePostCode(String invoicePostCode) {
		this.invoicePostCode = invoicePostCode;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOrderVersionId() {
		return orderVersionId;
	}

	public void setOrderVersionId(String orderVersionId) {
		this.orderVersionId = orderVersionId;
	}

	public String getReceiveTermsCode() {
		return receiveTermsCode;
	}

	public void setReceiveTermsCode(String receiveTermsCode) {
		this.receiveTermsCode = receiveTermsCode;
	}

	public String getReceiveTermsName() {
		return receiveTermsName;
	}

	public void setReceiveTermsName(String receiveTermsName) {
		this.receiveTermsName = receiveTermsName;
	}

	public String getAcceptanceCriteriaCode() {
		return acceptanceCriteriaCode;
	}

	public void setAcceptanceCriteriaCode(String acceptanceCriteriaCode) {
		this.acceptanceCriteriaCode = acceptanceCriteriaCode;
	}

	public String getContactor1Id() {
		return contactor1Id;
	}

	public void setContactor1Id(String contactor1Id) {
		this.contactor1Id = contactor1Id;
	}

	public String getContactor1Tel() {
		return contactor1Tel;
	}

	public void setContactor1Tel(String contactor1Tel) {
		this.contactor1Tel = contactor1Tel;
	}

	public String getContactor2Id() {
		return contactor2Id;
	}

	public void setContactor2Id(String contactor2Id) {
		this.contactor2Id = contactor2Id;
	}

	public String getContactor2Tel() {
		return contactor2Tel;
	}

	public void setContactor2Tel(String contactor2Tel) {
		this.contactor2Tel = contactor2Tel;
	}

	public String getContactor3Id() {
		return contactor3Id;
	}

	public void setContactor3Id(String contactor3Id) {
		this.contactor3Id = contactor3Id;
	}

	public String getContactor3Tel() {
		return contactor3Tel;
	}

	public void setContactor3Tel(String contactor3Tel) {
		this.contactor3Tel = contactor3Tel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContractorClassCode() {
		return contractorClassCode;
	}

	public void setContractorClassCode(String contractorClassCode) {
		this.contractorClassCode = contractorClassCode;
	}

	public String getContractorClassName() {
		return contractorClassName;
	}

	public void setContractorClassName(String contractorClassName) {
		this.contractorClassName = contractorClassName;
	}

	public String getFileHashcode() {
		return fileHashcode;
	}

	public void setFileHashcode(String fileHashcode) {
		this.fileHashcode = fileHashcode;
	}

	public String getSignContractid() {
		return signContractid;
	}

	public void setSignContractid(String signContractid) {
		this.signContractid = signContractid;
	}


}
