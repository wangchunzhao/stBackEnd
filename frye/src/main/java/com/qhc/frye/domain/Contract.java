package com.qhc.frye.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "k_contract")
public class Contract implements Serializable{
	
	private static final long serialVersionUID = -1429807827446950753L;

	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)  
	public int id;
	
	@NotNull
	@Column(name = "sequence_number",columnDefinition="char")
	private String		sequenceNumber;
	
	@NotNull
	@Column(name = "Partya_code",columnDefinition="char")
	private String		PartyaCode;
	
	@NotNull
	@Column(name = "Partya_name",columnDefinition="TEXT")
	private String		PartyaName;
	
	@Column(name = "partya_mail",columnDefinition="TEXT")
	private String		partyaMail;
	
	@NotNull
	@Column(name = "amount_on_contract",columnDefinition="DECIMAL")
	private BigDecimal	amountOnContract;
	
	@Column(name = "delivery_days_after_prepay",columnDefinition="SMALLINT")
	private Integer		deliveryDaysAfterPrepay;
	
	@NotNull
	@Column(name = "client_name",columnDefinition="TEXT")
	private String		clientName;
	
	@NotNull
	@Column(name = "install_location",columnDefinition="TEXT")
	private String		installLocation;
	
	@Column(name = "quality_stand")
	private String		qualityStand;
	
	@Column(name = "k_receive_confirm_id",columnDefinition="CHAR")
	private String		kReceiveConfirmId;
	
	@Column(name = "k_acceptance_approch_id",columnDefinition="CHAR")
	private String		kAcceptanceApprochId;
	
	@Column(name = "settlement",columnDefinition="TEXT")
	private String		settlement;
	
	@Column(name = "paryA_address",columnDefinition="TEXT")
	private String		paryaAddress;
	
	@Column(name = "invoice_address",columnDefinition="TEXT")
	private String		invoiceAddress;
	
	@Column(name = "broker",columnDefinition="TEXT")
	private String		broker;
	
	@Column(name = "invoice_receiver",columnDefinition="TEXT")
	private String		invoiceReceiver;
	
	@Column(name = "invoice_tel",columnDefinition="TEXT")
	private String		invoiceTel;
	
	@Column(name = "invoice_post_code",columnDefinition="char")
	private String		invoicePostCode;
	
	@Column(name = "company_tel",columnDefinition="TEXT")
	private String		companyTel;
	
	@Column(name = "bank_name",columnDefinition="TEXT")
	private String		bankName;
	
	@Column(name = "account_number",columnDefinition="TEXT")
	private String		accountNumber;
	
	@Column(name = "k_order_version_id")
	private Integer		kOrdersId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getPartyaCode() {
		return PartyaCode;
	}

	public void setPartyaCode(String partyaCode) {
		PartyaCode = partyaCode;
	}

	public String getPartyaName() {
		return PartyaName;
	}

	public void setPartyaName(String partyaName) {
		PartyaName = partyaName;
	}

	public String getPartyaMail() {
		return partyaMail;
	}

	public void setPartyaMail(String partyaMail) {
		this.partyaMail = partyaMail;
	}

	public BigDecimal getAmountOnContract() {
		return amountOnContract;
	}

	public void setAmountOnContract(BigDecimal amountOnContract) {
		this.amountOnContract = amountOnContract;
	}

	public Integer getDeliveryDaysAfterPrepay() {
		return deliveryDaysAfterPrepay;
	}

	public void setDeliveryDaysAfterPrepay(Integer deliveryDaysAfterPrepay) {
		this.deliveryDaysAfterPrepay = deliveryDaysAfterPrepay;
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

	public String getkReceiveConfirmId() {
		return kReceiveConfirmId;
	}

	public void setkReceiveConfirmId(String kReceiveConfirmId) {
		this.kReceiveConfirmId = kReceiveConfirmId;
	}

	public String getkAcceptanceApprochId() {
		return kAcceptanceApprochId;
	}

	public void setkAcceptanceApprochId(String kAcceptanceApprochId) {
		this.kAcceptanceApprochId = kAcceptanceApprochId;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public String getParyaAddress() {
		return paryaAddress;
	}

	public void setParyaAddress(String paryaAddress) {
		this.paryaAddress = paryaAddress;
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

	public Integer getkOrdersId() {
		return kOrdersId;
	}

	public void setkOrdersId(Integer kOrdersId) {
		this.kOrdersId = kOrdersId;
	}





}
