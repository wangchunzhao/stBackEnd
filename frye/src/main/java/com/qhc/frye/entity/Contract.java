package com.qhc.frye.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;


/**
 * 
 *  <br> 
 *
 * @author walker
 */
@Entity
@Table(name = "k_contract")
public class Contract
		implements Serializable {
	private static final long serialVersionUID = -1218924565991811880L;
	
	// Primary key
	// Column(id) - Id	
	@Id
	@Column(name="id", columnDefinition="INTEGER", length=10)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id = null;

	/* Column(sequence_number) - SequenceNumber */
	@NotNull
	@Column(name="sequence_number", columnDefinition="CHAR", length=12)
	private String sequenceNumber = null;

	/* Column(PartyA_code) - PartyaCode */
	@NotNull
	@Column(name="PartyA_code", columnDefinition="CHAR", length=10)
	private String partyaCode = null;

	/* Column(PartyA_name) - PartyaName */
	@NotNull
	@Column(name="PartyA_name", columnDefinition="TEXT")
	private String partyaName = null;

	/* Column(partyA_mail) - PartyaMail */
	@Column(name="partyA_mail", columnDefinition="TEXT")
	private String partyaMail = null;

	/* Column(amount_on_contract) - AmountOnContract */
	@NotNull
	@Column(name="amount_on_contract", columnDefinition="DECIMAL", length=10)
	private BigDecimal amountOnContract = null;

	/* Column(delivery_days_after_prepay) - DeliveryDaysAfterPrepay */
	@Column(name="delivery_days_after_prepay", columnDefinition="SMALLINT", length=5)
	private Short deliveryDaysAfterPrepay = null;

	/* Column(client_name) - ClientName */
	@NotNull
	@Column(name="client_name", columnDefinition="TEXT")
	private String clientName = null;

	/* Column(install_location) - InstallLocation */
	@NotNull
	@Column(name="install_location", columnDefinition="TEXT")
	private String installLocation = null;

	/* Column(quality_stand) - QualityStand */
	@Column(name="quality_stand", columnDefinition="VARCHAR", length=45)
	private String qualityStand = null;

	/* Column(settlement) - Settlement */
	@Column(name="settlement", columnDefinition="TEXT")
	private String settlement = null;

	/* Column(paryA_address) - ParyaAddress */
	@Column(name="partyA_address", columnDefinition="TEXT")
	private String partyaAddress = null;

	/* Column(invoice_address) - InvoiceAddress */
	@Column(name="invoice_address", columnDefinition="TEXT")
	private String invoiceAddress = null;

	/* Column(broker) - Broker */
	@Column(name="broker", columnDefinition="TEXT")
	private String broker = null;

	/* Column(invoice_receiver) - InvoiceReceiver */
	@Column(name="invoice_receiver", columnDefinition="TEXT")
	private String invoiceReceiver = null;

	/* Column(invoice_tel) - InvoiceTel */
	@Column(name="invoice_tel", columnDefinition="TEXT")
	private String invoiceTel = null;

	/* Column(invoice_post_code) - InvoicePostCode */
	@Column(name="invoice_post_code", columnDefinition="CHAR", length=6)
	private String invoicePostCode = null;

	/* Column(company_tel) - CompanyTel */
	@Column(name="company_tel", columnDefinition="TEXT")
	private String companyTel = null;

	/* Column(bank_name) - BankName */
	@Column(name="bank_name", columnDefinition="TEXT")
	private String bankName = null;

	/* Column(account_number) - AccountNumber */
	@Column(name="account_number", columnDefinition="TEXT")
	private String accountNumber = null;

	/* Column(k_order_version_id) - KOrderVersionId */
	/* Foreign Column(id)) Reference from (k_order_version) */ 
	@NotNull
	@Column(name="k_order_version_id", columnDefinition="CHAR", length=32)
	private String orderVersionId = null;

	/* Column(receive_terms_code) - ReceiveTermsCode */
	@NotNull
	@Column(name="receive_terms_code", columnDefinition="VARCHAR", length=45)
	private String receiveTermsCode = null;

	/* Column(receive_terms_name) - ReceiveTermsName */
	@NotNull
	@Column(name="receive_terms_name", columnDefinition="VARCHAR", length=45)
	private String receiveTermsName = null;

	/* Column(k_acceptance_criteria_code) - KAcceptanceCriteriaCode */
	/* Foreign Column(code)) Reference from (k_acceptance_criteria) */ 
	@NotNull
	@Column(name="k_acceptance_criteria_code", columnDefinition="CHAR", length=4)
	private String acceptanceCriteriaCode = null;

	/* Column(mail) - Mail */
//	@NotNull
//	@Column(name="mail", columnDefinition="TEXT")
//	private String mail = null;

	/* Column(contractor_1_id) - Contractor1Id */
	@Column(name="contractor_1_id", columnDefinition="VARCHAR", length=45)
	private String contractor1Id = null;

	/* Column(contractor_1_tel) - Contractor1Tel */
	@Column(name="contractor_1_tel", columnDefinition="VARCHAR", length=45)
	private String contractor1Tel = null;

	/* Column(contractor_2_id) - Contractor2Id */
	@Column(name="contractor_2_id", columnDefinition="VARCHAR", length=45)
	private String contractor2Id = null;

	/* Column(contractor_2_tel) - Contractor2Tel */
	@Column(name="contractor_2_tel", columnDefinition="VARCHAR", length=45)
	private String contractor2Tel = null;

	/* Column(contractor_3_id) - Contractor3Id */
	@Column(name="contractor_3_id", columnDefinition="VARCHAR", length=45)
	private String contractor3Id = null;

	/* Column(contractor_3_tel) - Contractor3Tel */
	@Column(name="contractor_3_tel", columnDefinition="VARCHAR", length=45)
	private String contractor3Tel = null;

	/* 合同制作时间 */
	@NotNull
	@Column(name="production_Time", columnDefinition="DATETIME")
	private Date productionTime = null;

	/* 合同发送时间 */
	@Column(name="send_time", columnDefinition="DATETIME")
	private Date sendTime = null;

	/* 合同状态：1 未发送 2 已发送 */
	@Column(name="contract_status", columnDefinition="INTEGER")
	private Integer status = null;
	
	@Size(max = 256)
	@ApiModelProperty("文档Hash值")
	@Column(name = "file_hashcode", length = 256)
	private String fileHashCode;
	@Size(max = 64)
	@ApiModelProperty("电子签约中合同Id")
	@Column(name = "sign_contractid", length = 64)
	private String signContractId;


	public Contract(){
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	 
	public String getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	 
	public String getPartyaCode() {
		return this.partyaCode;
	}

	public void setPartyaCode(String partyaCode) {
		this.partyaCode = partyaCode;
	}
	 
	public String getPartyaName() {
		return this.partyaName;
	}

	public void setPartyaName(String partyaName) {
		this.partyaName = partyaName;
	}
	 
	public String getPartyaMail() {
		return this.partyaMail;
	}

	public void setPartyaMail(String partyaMail) {
		this.partyaMail = partyaMail;
	}
	 
	public BigDecimal getAmountOnContract() {
		return this.amountOnContract;
	}

	public void setAmountOnContract(BigDecimal amountOnContract) {
		this.amountOnContract = amountOnContract;
	}
	 
	public Short getDeliveryDaysAfterPrepay() {
		return this.deliveryDaysAfterPrepay;
	}

	public void setDeliveryDaysAfterPrepay(Short deliveryDaysAfterPrepay) {
		this.deliveryDaysAfterPrepay = deliveryDaysAfterPrepay;
	}
	 
	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	 
	public String getInstallLocation() {
		return this.installLocation;
	}

	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}
	 
	public String getQualityStand() {
		return this.qualityStand;
	}

	public void setQualityStand(String qualityStand) {
		this.qualityStand = qualityStand;
	}
	 
	public String getSettlement() {
		return this.settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}
	 
	public String getParyaAddress() {
		return this.partyaAddress;
	}

	public void setParyaAddress(String partyaAddress) {
		this.partyaAddress = partyaAddress;
	}
	 
	public String getInvoiceAddress() {
		return this.invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}
	 
	public String getBroker() {
		return this.broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}
	 
	public String getInvoiceReceiver() {
		return this.invoiceReceiver;
	}

	public void setInvoiceReceiver(String invoiceReceiver) {
		this.invoiceReceiver = invoiceReceiver;
	}
	 
	public String getInvoiceTel() {
		return this.invoiceTel;
	}

	public void setInvoiceTel(String invoiceTel) {
		this.invoiceTel = invoiceTel;
	}
	 
	public String getInvoicePostCode() {
		return this.invoicePostCode;
	}

	public void setInvoicePostCode(String invoicePostCode) {
		this.invoicePostCode = invoicePostCode;
	}
	 
	public String getCompanyTel() {
		return this.companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}
	 
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	 
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	 
	public String getReceiveTermsCode() {
		return this.receiveTermsCode;
	}

	public void setReceiveTermsCode(String receiveTermsCode) {
		this.receiveTermsCode = receiveTermsCode;
	}
	 
	public String getReceiveTermsName() {
		return this.receiveTermsName;
	}

	public void setReceiveTermsName(String receiveTermsName) {
		this.receiveTermsName = receiveTermsName;
	}
	public String getOrderVersionId() {
		return orderVersionId;
	}

	public void setOrderVersionId(String orderVersionId) {
		this.orderVersionId = orderVersionId;
	}

	public String getAcceptanceCriteriaCode() {
		return acceptanceCriteriaCode;
	}

	public void setAcceptanceCriteriaCode(String acceptanceCriteriaCode) {
		this.acceptanceCriteriaCode = acceptanceCriteriaCode;
	}

//	public String getMail() {
//		return this.mail;
//	}
//
//	public void setMail(String mail) {
//		this.mail = mail;
//	}
	 
	public String getContractor1Id() {
		return this.contractor1Id;
	}

	public void setContractor1Id(String contractor1Id) {
		this.contractor1Id = contractor1Id;
	}
	 
	public String getContractor1Tel() {
		return this.contractor1Tel;
	}

	public void setContractor1Tel(String contractor1Tel) {
		this.contractor1Tel = contractor1Tel;
	}
	 
	public String getContractor2Id() {
		return this.contractor2Id;
	}

	public void setContractor2Id(String contractor2Id) {
		this.contractor2Id = contractor2Id;
	}
	 
	public String getContractor2Tel() {
		return this.contractor2Tel;
	}

	public void setContractor2Tel(String contractor2Tel) {
		this.contractor2Tel = contractor2Tel;
	}
	 
	public String getContractor3Id() {
		return this.contractor3Id;
	}

	public void setContractor3Id(String contractor3Id) {
		this.contractor3Id = contractor3Id;
	}
	 
	public String getContractor3Tel() {
		return this.contractor3Tel;
	}

	public void setContractor3Tel(String contractor3Tel) {
		this.contractor3Tel = contractor3Tel;
	}
	 
	public Date getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(Date productionTime) {
		this.productionTime = productionTime;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPartyaAddress() {
		return partyaAddress;
	}

	public void setPartyaAddress(String partyaAddress) {
		this.partyaAddress = partyaAddress;
	}

	public String getFileHashCode() {
		return fileHashCode;
	}

	public void setFileHashCode(String fileHashCode) {
		this.fileHashCode = fileHashCode;
	}

	public String getSignContractId() {
		return signContractId;
	}

	public void setSignContractId(String signContractId) {
		this.signContractId = signContractId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Contract other = (Contract) obj;
		return (this.id == null ? other.id == null : this.id.equals(other.id));
	}
	
	public String toString() {
	    final String tab = "  ";
	    String str = "";
	    str = "KContract ( "
	        + "id = " + this.id + tab
	        + "sequenceNumber = " + this.sequenceNumber + tab
	        + "partyaCode = " + this.partyaCode + tab
	        + "partyaName = " + this.partyaName + tab
	        + "partyaMail = " + this.partyaMail + tab
	        + "amountOnContract = " + this.amountOnContract + tab
	        + "deliveryDaysAfterPrepay = " + this.deliveryDaysAfterPrepay + tab
	        + "clientName = " + this.clientName + tab
	        + "installLocation = " + this.installLocation + tab
	        + "qualityStand = " + this.qualityStand + tab
	        + "settlement = " + this.settlement + tab
	        + "paryaAddress = " + this.partyaAddress + tab
	        + "invoiceAddress = " + this.invoiceAddress + tab
	        + "broker = " + this.broker + tab
	        + "invoiceReceiver = " + this.invoiceReceiver + tab
	        + "invoiceTel = " + this.invoiceTel + tab
	        + "invoicePostCode = " + this.invoicePostCode + tab
	        + "companyTel = " + this.companyTel + tab
	        + "bankName = " + this.bankName + tab
	        + "accountNumber = " + this.accountNumber + tab
	        + "kOrderVersionId = " + this.orderVersionId + tab
	        + "receiveTermsCode = " + this.receiveTermsCode + tab
	        + "receiveTermsName = " + this.receiveTermsName + tab
	        + "kAcceptanceCriteriaCode = " + this.acceptanceCriteriaCode + tab
//	        + "mail = " + this.mail + tab
	        + "contractor1Id = " + this.contractor1Id + tab
	        + "contractor1Tel = " + this.contractor1Tel + tab
	        + "contractor2Id = " + this.contractor2Id + tab
	        + "contractor2Tel = " + this.contractor2Tel + tab
	        + "contractor3Id = " + this.contractor3Id + tab
	        + "contractor3Tel = " + this.contractor3Tel + tab
	        + " )";
	
	    return str;
	}

}