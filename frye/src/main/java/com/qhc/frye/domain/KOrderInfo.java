package com.qhc.frye.domain;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "k_order_info")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class KOrderInfo {
	
	@Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	public int id;
	
	@Column(name="last_operator")
	public String lastOperator;
	
	@Column(name="last_opt_time",columnDefinition="datetime")
	public Date lastOptTime;
	
	@Column(name="customer_name")
	public String customerName;//店名
	
	@Column(name="is_reformed",columnDefinition ="TINYINT",length=1)
	private Integer	isReformed	;//	是否改造店
	
	@Column(name="is_convenient_store",columnDefinition ="TINYINT",length=1)
	private Integer	isConvenientStore	;//	是否便利店
	
	@Column(name="is_new",columnDefinition ="TINYINT",length=1)
	private Integer	isNew	;//	是不是新店
	
	@Column(name="terminal_industry_code")
	private String	terminalIndustryCode;
	
	@Column(name="terminal_industry_code_name",columnDefinition="TEXT")
	private String	terminalIndustryCodeName	;//	终端客户性质
	
	
	@Column(name="body_discount",columnDefinition="BOUBLE")
	private Double	bodyDiscount;//	
	
	@Column(name="main_discount",columnDefinition="BOUBLE")
	private Double	mainDiscount;//	
	
	@Column(name="install_term_code")
	private String	installTermCode;//	
	
	@Column(name="install_term_name",columnDefinition="TEXT")
	private String	installTermName	;//	
	
	@Column(name="receive_term_code")
	private String	receiveTermCode	;//
	
	@Column(name="receive_term_name",columnDefinition="TEXT")
	private String	receiveTermName	;//	

	@Column(name="contactor_1_id")
	private String	contactor1Id;//	授权人及身份证号
	@Column(name="contactor_1_tel")
	private String	contactor1Tel;//
	
	@Column(name="contactor_2_id")
	private String	contactor2Id;//	授权人及身份证号
	@Column(name="contactor_2_tel")
	private String	contactor2Tel;//
	
	@Column(name="contactor_3_id")
	private String	contactor3Id;//	授权人及身份证号
	@Column(name="contactor_3_tel")
	private String	contactor3Tel;//
	
	@Column(name="freight",columnDefinition = "DOUBLE")
	private Double	freight	;//运费
	
	@Column(name="warranty")
	private int	warranty;//保修期限（年）
	
	
	@Column(name="currency_code")
	private String	currencyCode;//币别
	
	@Column(name="currency_name",columnDefinition = "TEXT")
	private String	currencyName;//币别
	
	@Column(name="exchange",columnDefinition = "DOUBLE")
	private Double	exchange;//汇率
	
	
	@Column(name="contract_amount",columnDefinition = "DOUBLE")
	private Double	contractAmount	;//原币合同金额
	
	@Column(name="contract_rmb_amount",columnDefinition = "DOUBLE")
	private Double	contractRmbAmount;//
	
	
	@Column(name="sales_type",columnDefinition = "char")
	private String	salesType;//
	
	
	@Column(name="tax_rate",columnDefinition = "DOUBLE")
	private Double	taxRate	;//
	
	
	
	@Column(name="incoterm_code")
	private String incotermCode;//
	
	@Column(name="incoterm_name",columnDefinition="TEXT")
	private String	incotermName;//	
	
	@Column(name="incoterm_contect",columnDefinition="TEXT")
	private String	incotermContect;//	
	
	
	@Column(name="office_code")
	private String officeCode;//
	
	@Column(name="office_name",columnDefinition="TEXT")
	private String	officeName;//	
	
	@Column(name="group_code")
	private String groupCode;//
	
	@Column(name="group_name",columnDefinition="TEXT")
	private String	groupName;//	
	
	@Column(name="transfer_type_code")
	private String transferTypeCode;//
	
	@Column(name="transfer_type_name",columnDefinition="TEXT")
	private String	transferTypeName;//	
	
	@Column(name="is_term1",columnDefinition ="TINYINT",length=1)
	private Integer	isTerm1	;//
	
	@Column(name="is_term2",columnDefinition ="TINYINT",length=1)
	private Integer	isTerm2	;//
	
	@Column(name="is_term3",columnDefinition ="TINYINT",length=1)
	private Integer	isTerm3	;//
	
	@Column(name="comments",columnDefinition="TEXT")
	private String	comments;
	
	
	@Transient
	public String startTime;
	
	@Transient
	public String endTime;
	
	@Transient
	public List<SpecialDelivery> applyList;
	
	
	
	
	
	
	
	
	
	
	

	public KOrderInfo() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastOperator() {
		return lastOperator;
	}


	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}


	public Date getLastOptTime() {
		return lastOptTime;
	}


	public void setLastOptTime(Date lastOptTime) {
		this.lastOptTime = lastOptTime;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Integer getIsReformed() {
		return isReformed;
	}


	public void setIsReformed(Integer isReformed) {
		this.isReformed = isReformed;
	}


	public Integer getIsConvenientStore() {
		return isConvenientStore;
	}


	public void setIsConvenientStore(Integer isConvenientStore) {
		this.isConvenientStore = isConvenientStore;
	}


	public Integer getIsNew() {
		return isNew;
	}


	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}


	public String getTerminalIndustryCode() {
		return terminalIndustryCode;
	}


	public void setTerminalIndustryCode(String terminalIndustryCode) {
		this.terminalIndustryCode = terminalIndustryCode;
	}


	public String getTerminalIndustryCodeName() {
		return terminalIndustryCodeName;
	}


	public void setTerminalIndustryCodeName(String terminalIndustryCodeName) {
		this.terminalIndustryCodeName = terminalIndustryCodeName;
	}


	public Double getBodyDiscount() {
		return bodyDiscount;
	}


	public void setBodyDiscount(Double bodyDiscount) {
		this.bodyDiscount = bodyDiscount;
	}


	public Double getMainDiscount() {
		return mainDiscount;
	}


	public void setMainDiscount(Double mainDiscount) {
		this.mainDiscount = mainDiscount;
	}


	public String getInstallTermCode() {
		return installTermCode;
	}


	public void setInstallTermCode(String installTermCode) {
		this.installTermCode = installTermCode;
	}


	public String getInstallTermName() {
		return installTermName;
	}


	public void setInstallTermName(String installTermName) {
		this.installTermName = installTermName;
	}


	public String getReceiveTermCode() {
		return receiveTermCode;
	}


	public void setReceiveTermCode(String receiveTermCode) {
		this.receiveTermCode = receiveTermCode;
	}


	public String getReceiveTermName() {
		return receiveTermName;
	}


	public void setReceiveTermName(String receiveTermName) {
		this.receiveTermName = receiveTermName;
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


	public Double getFreight() {
		return freight;
	}


	public void setFreight(Double freight) {
		this.freight = freight;
	}


	public int getWarranty() {
		return warranty;
	}


	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}


	public String getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}


	public String getCurrencyName() {
		return currencyName;
	}


	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}


	public Double getExchange() {
		return exchange;
	}


	public void setExchange(Double exchange) {
		this.exchange = exchange;
	}


	public Double getContractAmount() {
		return contractAmount;
	}


	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}


	public Double getContractRmbAmount() {
		return contractRmbAmount;
	}


	public void setContractRmbAmount(Double contractRmbAmount) {
		this.contractRmbAmount = contractRmbAmount;
	}


	public String getSalesType() {
		return salesType;
	}


	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}


	public Double getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}


	public String getIncotermCode() {
		return incotermCode;
	}


	public void setIncotermCode(String incotermCode) {
		this.incotermCode = incotermCode;
	}


	public String getIncotermName() {
		return incotermName;
	}


	public void setIncotermName(String incotermName) {
		this.incotermName = incotermName;
	}


	public String getIncotermContect() {
		return incotermContect;
	}


	public void setIncotermContect(String incotermContect) {
		this.incotermContect = incotermContect;
	}


	public String getOfficeCode() {
		return officeCode;
	}


	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}


	public String getOfficeName() {
		return officeName;
	}


	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}


	public String getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getTransferTypeCode() {
		return transferTypeCode;
	}


	public void setTransferTypeCode(String transferTypeCode) {
		this.transferTypeCode = transferTypeCode;
	}


	public String getTransferTypeName() {
		return transferTypeName;
	}


	public void setTransferTypeName(String transferTypeName) {
		this.transferTypeName = transferTypeName;
	}


	public Integer getIsTerm1() {
		return isTerm1;
	}


	public void setIsTerm1(Integer isTerm1) {
		this.isTerm1 = isTerm1;
	}


	public Integer getIsTerm2() {
		return isTerm2;
	}


	public void setIsTerm2(Integer isTerm2) {
		this.isTerm2 = isTerm2;
	}


	public Integer getIsTerm3() {
		return isTerm3;
	}


	public void setIsTerm3(Integer isTerm3) {
		this.isTerm3 = isTerm3;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public List<SpecialDelivery> getApplyList() {
		return applyList;
	}


	public void setApplyList(List<SpecialDelivery> applyList) {
		this.applyList = applyList;
	}


	
	
	
}
