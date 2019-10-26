package com.qhc.frye.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "k_order_view")
public class KOrderView
		implements Serializable {
	private static final long serialVersionUID = -6830633419653128937L;

	/* Column(sequence_number) - 序列号 */
	@Column(name="sequence_number",columnDefinition="CHAR",length=12)
	private String sequenceNumber = null;

	/* Column(order_type_code) - //dealer or keyaccount or bulk */
	@Column(name="order_type_code",columnDefinition="CHAR",length=4)
	private String orderTypeCode = null;

	/* Column(create_time) - 创建时间 */
	@Column(name="create_time")
	private Date createTime = null;

	/* Column(owner_domain_id) - creator or changed owner/sale code */
	@Column(name="owner_domain_id")
	private String ownerDomainId = null;

	/* Column(owner_name) - 创建人员姓名 */
	@Column(name="owner_name",columnDefinition="TEXT")
	private String ownerName = null;

	/* Column(sales_tel) - sales name */
	@Column(name="sales_tel",columnDefinition="CHAR",length=45)
	private String salesTel = null;

	/* Column(contractor_code) - contracter Code/ customer code */
	@Column(name="contractor_code",columnDefinition="CHAR",length=10)
	private String contractorCode = null;

	/* Column(contractor_name) - ContractorName */
	@Column(name="contractor_name",columnDefinition="TEXT")
	private String contractorName = null;

	/* Column(contractor_class_code) - sap customer class code: 01/02 */
	@Column(name="contractor_class_code",columnDefinition="CHAR",length=10)
	private String contractorClassCode = null;

	/* Column(contractor_class_name) - 经销商/直签 */
	@Column(name="contractor_class_name",columnDefinition="TEXT")
	private String contractorClassName = null;

	/* Column(sales_office_code) - 销售员所属区域 */
	@Column(name="sales_office_code",columnDefinition="CHAR",length=45)
	private String salesOfficeCode = null;

	/* Column(order_id) - OrderId */
	@Column(name="order_id",columnDefinition="CHAR",length=32)
	private String orderId = null;

	/* Column(version_id) - VersionId */
	@Id
	@Column(name="version_id",columnDefinition="CHAR",length=32)
	private String versionId = null;

	/* Column(version) - 版本名称 */
	@Column(name="version",columnDefinition="CHAR",length=45)
	private String version = null;

	/* Column(status) - 0:saved
1:draft:submit to headquater
2.approving:BPM
3.approved */
	@Column(name="status",columnDefinition="TINYINT",length=2)
	private Integer status = null;

	/* Column(version_create_time) - 版本创建时间 */
	@Column(name="version_create_time")
	private Date versionCreateTime = null;

	/* Column(opt_time) - submitDate */
	@Column(name="submit_date")
	private Date submitDate = null;

	/* Column(opt_time) - bpmSubmitTime */
	@Column(name="bpm_submit_time")
	private Date bpmSubmitTime = null;

	/* Column(opt_time) - OptTime */
	@Column(name="opt_time")
	private Date optTime = null;

	/* Column(order_info_id) - OrderInfoId */
	@Column(name="order_info_id",columnDefinition="CHAR",length=32)
	private String orderInfoId = null;

	/* Column(id) - Id of k_order_info */
	@Column(name="id",columnDefinition="char",length=32)
	private String id = null;

	/* Column(last_operator) - 最后操作人 */
	@Column(name="last_operator")
	private String lastOperator = null;

	/* Column(last_opt_time) - 最后操作时间 */
	@Column(name="last_opt_time")
	private Date lastOptTime = null;

	/* Column(customer_name) - //店名 customer name */
	@Column(name="customer_name",columnDefinition="TEXT")
	private String customerName = null;

	/* Column(is_reformed) - 是否是改造店 */
	@Column(name="is_reformed",columnDefinition ="BIT")
	private Integer	isReformed	;//	是否改造店

	/* Column(is_convenient_store) - 是否是便利店 */
	@Column(name="is_convenient_store",columnDefinition="BIT")
	private Integer isConvenientStore = null;

	/* Column(is_new) - 是不是新店 */
	@Column(name="is_new",columnDefinition ="BIT")
	private Integer isNew = null;

	/* Column(terminal_industry_code) - 终端店面的insustray code */
	@Column(name="terminal_industry_code")
	private String terminalIndustryCode = null;

	/* Column(terminal_industry_code_name) - 终端店面的insustray code的名字 */
	@Column(name="terminal_industry_code_name",columnDefinition="TEXT")
	private String terminalIndustryCodeName = null;

	/* Column(body_discount) - 柜体折扣 */
	@Column(name="body_discount")
	private Double bodyDiscount = null;

	/* Column(main_discount) - 机身折扣 */
	@Column(name="main_discount")
	private Double mainDiscount = null;

	/* Column(install_term_code) - 安装code */
	@Column(name="install_term_code")
	private String installTermCode = null;

	/* Column(install_term_name) - 安装方式名称 */
	@Column(name="install_term_name",columnDefinition="TEXT")
	private String installTermName = null;

	/* Column(receive_term_code) - 接货方式名称code */
	@Column(name="receive_term_code")
	private String receiveTermCode = null;

	/* Column(receive_term_name) - 接货方式名称 */
	@Column(name="receive_term_name",columnDefinition="TEXT")
	private String receiveTermName = null;

	/* Column(contactor_1_id) - 第一联系人身份证 */
	@Column(name="contactor_1_id",columnDefinition="VARCHAR",length=18)
	private String contactor1Id = null;

	/* Column(contactor_1_tel) - 第一联系人电话 */
	@Column(name="contactor_1_tel",columnDefinition="VARCHAR",length=16)
	private String contactor1Tel = null;

	/* Column(contactor_2_id) - 第二联系人身份证 */
	@Column(name="contactor_2_id",columnDefinition="VARCHAR",length=18)
	private String contactor2Id = null;

	/* Column(contactor_2_tel) - 第二联系人电话 */
	@Column(name="contactor_2_tel",columnDefinition="VARCHAR",length=16)
	private String contactor2Tel = null;

	/* Column(contactor_3_id) - 第三联系人身份证 */
	@Column(name="contactor_3_id",columnDefinition="VARCHAR",length=18)
	private String contactor3Id = null;

	/* Column(contactor_3_tel) - 第三联系人电话 */
	@Column(name="contactor_3_tel",columnDefinition="VARCHAR",length=16)
	private String contactor3Tel = null;

	/* Column(freight) - 运费合计 */
	@Column(name="freight")
	private BigDecimal freight = null;

	/* Column(warranty) - 保修周期 */
	@Column(name="warranty")
	private Integer warranty = null;

	/* Column(currency_code) - 外币code */
	@Column(name="currency_code",columnDefinition="VARCHAR",length=3)
	private String currencyCode = null;

	/* Column(currency_name) - 外币名称 */
	@Column(name="currency_name",columnDefinition="TEXT")
	private String currencyName = null;

	/* Column(exchange) - 汇率 */
	@Column(name="exchange")
	private Double exchange = null;

	/* Column(contract_amount) - 原合同金额 */
	@Column(name="contract_amount")
	private BigDecimal contractAmount = null;

	/* Column(contract_rmb_amount) - 合同人民币金额 */
	@Column(name="contract_rmb_amount")
	private BigDecimal contractRmbAmount = null;

	/* Column(sales_type) - 销售类型 */
	@Column(name="sales_type",columnDefinition="CHAR",length=2)
	private String salesType = null;

	/* Column(tax_rate) - 税率 */
	@Column(name="tax_rate")
	private Double taxRate = null;

	/* Column(incoterm_code) - 贸易条件code */
	@Column(name="incoterm_code")
	private String incotermCode = null;

	/* Column(incoterm_name) - 贸易条件名称 */
	@Column(name="incoterm_name",columnDefinition="TEXT")
	private String incotermName = null;

	/* Column(incoterm_contect) - 贸易条件 */
	@Column(name="incoterm_contect",columnDefinition="TEXT")
	private String incotermContect = null;

	/* Column(office_code) - 表单里的大区code */
	@Column(name="office_code",columnDefinition="CHAR",length=45)
	private String officeCode = null;

	/* Column(office_name) - 大区名称 */
	@Column(name="office_name",columnDefinition="TEXT")
	private String officeName = null;

	/* Column(group_code) - 中心code */
	@Column(name="group_code",columnDefinition="CHAR",length=45)
	private String groupCode = null;

	/* Column(group_name) - 中心名称 */
	@Column(name="group_name",columnDefinition="TEXT")
	private String groupName = null;

	/* Column(transfer_type_code) - 运输类型代码 */
	@Column(name="transfer_type_code",columnDefinition="CHAR",length=45)
	private String transferTypeCode = null;

	/* Column(transfer_type_name) - 运输类型名称 */
	@Column(name="transfer_type_name",columnDefinition="TEXT")
	private String transferTypeName = null;

	/* Column(is_term1) - 柜体控制阀门件是否甲供 */
	@Column(name="is_term1",columnDefinition="BIT")
	private Integer isTerm1 = null;

	/* Column(is_term2) - 分体柜是否远程监控 */
	@Column(name="is_term2",columnDefinition="BIT")
	private Integer isTerm2 = null;

	/* Column(is_term3) - 立体柜是否在地下室 */
	@Column(name="is_term3",columnDefinition="BIT")
	private Integer isTerm3 = null;

	/* Column(comments) - Comments */
	@Column(name="comments",columnDefinition="TEXT")
	private String comments = null;

	/* Column(form_id) - FormId */
	@Column(name="form_id",columnDefinition="CHAR",length=32)
	private String formId = null;

	/* Column(earliest_delivery_date) - 最早交付时间 */
	@Column(name="earliest_delivery_date")
	private Date earliestDeliveryDate = null;

	/* Column(earliest_product_date) - 最早生产时间 */
	@Column(name="earliest_product_date")
	private Date earliestProductDate = null;

	/* Column(form_comments) - 备注 */
	@Column(name="form_comments",columnDefinition="text")
	private String formComments = null;

	/* Column(form_operator) - 最后操作人 */
	@Column(name="form_operator")
	private String formOperator = null;

	/* Column(form_type) - 最后的操作类型
0:订单
1：工程
2：B2C */
	@Column(name="form_type",columnDefinition="tinyint",length=2)
	private Integer formType = null;

	/* Column(form_opt_time) - 最后操作时间 */
	@Column(name="form_opt_time")
	private Date formOptTime = null;

	/* Column(support_info_id) - SupportInfoId */
	@Column(name="support_info_id", columnDefinition="INTEGER", length=10)
	private Integer supportInfoId = null;

	/* Column(contract_number) - 合同号 */
	@Column(name="contract_number", columnDefinition="CHAR", length=45)
	private String contractNumber = null;

	/* Column(opterator_domain_id) - 支持经理id */
	@Column(name="opterator_domain_id", columnDefinition="CHAR")
	private String opteratorDomainId = null;

	/* Column(support_info_opt_time) - 最后操作时间 */
	@Column(name="support_info_opt_time", columnDefinition="TIMESTAMP")
	private Date supportInfoOptTime = null;


	public KOrderView(){
	}

	public String getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	 
	public String getOrderTypeCode() {
		return this.orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}
	 
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	 
	public String getOwnerDomainId() {
		return this.ownerDomainId;
	}

	public void setOwnerDomainId(String ownerDomainId) {
		this.ownerDomainId = ownerDomainId;
	}
	 
	public String getOwnerName() {
		return this.ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	 
	public String getSalesTel() {
		return this.salesTel;
	}

	public void setSalesTel(String salesTel) {
		this.salesTel = salesTel;
	}
	 
	public String getContractorCode() {
		return this.contractorCode;
	}

	public void setContractorCode(String contractorCode) {
		this.contractorCode = contractorCode;
	}
	 
	public String getContractorName() {
		return this.contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	 
	public String getContractorClassCode() {
		return this.contractorClassCode;
	}

	public void setContractorClassCode(String contractorClassCode) {
		this.contractorClassCode = contractorClassCode;
	}
	 
	public String getContractorClassName() {
		return this.contractorClassName;
	}

	public void setContractorClassName(String contractorClassName) {
		this.contractorClassName = contractorClassName;
	}
	 
	public String getSalesOfficeCode() {
		return this.salesOfficeCode;
	}

	public void setSalesOfficeCode(String salesOfficeCode) {
		this.salesOfficeCode = salesOfficeCode;
	}
	 
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	 
	public String getVersionId() {
		return this.versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	 
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	 
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	 
	public Date getVersionCreateTime() {
		return this.versionCreateTime;
	}

	public void setVersionCreateTime(Date versionCreateTime) {
		this.versionCreateTime = versionCreateTime;
	}
	 
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getBpmSubmitTime() {
		return bpmSubmitTime;
	}

	public void setBpmSubmitTime(Date bpmSubmitTime) {
		this.bpmSubmitTime = bpmSubmitTime;
	}

	public Date getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}
	 
	public String getOrderInfoId() {
		return this.orderInfoId;
	}

	public void setOrderInfoId(String orderInfoId) {
		this.orderInfoId = orderInfoId;
	}
	 
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	 
	public String getLastOperator() {
		return this.lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}
	 
	public Date getLastOptTime() {
		return this.lastOptTime;
	}

	public void setLastOptTime(Date lastOptTime) {
		this.lastOptTime = lastOptTime;
	}
	 
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	 
	public Integer getIsReformed() {
		return this.isReformed;
	}

	public void setIsReformed(Integer isReformed) {
		this.isReformed = isReformed;
	}
	 
	public Integer getIsConvenientStore() {
		return this.isConvenientStore;
	}

	public void setIsConvenientStore(Integer isConvenientStore) {
		this.isConvenientStore = isConvenientStore;
	}
	 
	public Integer getIsNew() {
		return this.isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	 
	public String getTerminalIndustryCode() {
		return this.terminalIndustryCode;
	}

	public void setTerminalIndustryCode(String terminalIndustryCode) {
		this.terminalIndustryCode = terminalIndustryCode;
	}
	 
	public String getTerminalIndustryCodeName() {
		return this.terminalIndustryCodeName;
	}

	public void setTerminalIndustryCodeName(String terminalIndustryCodeName) {
		this.terminalIndustryCodeName = terminalIndustryCodeName;
	}
	 
	public Double getBodyDiscount() {
		return this.bodyDiscount;
	}

	public void setBodyDiscount(Double bodyDiscount) {
		this.bodyDiscount = bodyDiscount;
	}
	 
	public Double getMainDiscount() {
		return this.mainDiscount;
	}

	public void setMainDiscount(Double mainDiscount) {
		this.mainDiscount = mainDiscount;
	}
	 
	public String getInstallTermCode() {
		return this.installTermCode;
	}

	public void setInstallTermCode(String installTermCode) {
		this.installTermCode = installTermCode;
	}
	 
	public String getInstallTermName() {
		return this.installTermName;
	}

	public void setInstallTermName(String installTermName) {
		this.installTermName = installTermName;
	}
	 
	public String getReceiveTermCode() {
		return this.receiveTermCode;
	}

	public void setReceiveTermCode(String receiveTermCode) {
		this.receiveTermCode = receiveTermCode;
	}
	 
	public String getReceiveTermName() {
		return this.receiveTermName;
	}

	public void setReceiveTermName(String receiveTermName) {
		this.receiveTermName = receiveTermName;
	}
	 
	public String getContactor1Id() {
		return this.contactor1Id;
	}

	public void setContactor1Id(String contactor1Id) {
		this.contactor1Id = contactor1Id;
	}
	 
	public String getContactor1Tel() {
		return this.contactor1Tel;
	}

	public void setContactor1Tel(String contactor1Tel) {
		this.contactor1Tel = contactor1Tel;
	}
	 
	public String getContactor2Id() {
		return this.contactor2Id;
	}

	public void setContactor2Id(String contactor2Id) {
		this.contactor2Id = contactor2Id;
	}
	 
	public String getContactor2Tel() {
		return this.contactor2Tel;
	}

	public void setContactor2Tel(String contactor2Tel) {
		this.contactor2Tel = contactor2Tel;
	}
	 
	public String getContactor3Id() {
		return this.contactor3Id;
	}

	public void setContactor3Id(String contactor3Id) {
		this.contactor3Id = contactor3Id;
	}
	 
	public String getContactor3Tel() {
		return this.contactor3Tel;
	}

	public void setContactor3Tel(String contactor3Tel) {
		this.contactor3Tel = contactor3Tel;
	}
	 
	public BigDecimal getFreight() {
		return this.freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	 
	public Integer getWarranty() {
		return this.warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}
	 
	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	 
	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	 
	public Double getExchange() {
		return this.exchange;
	}

	public void setExchange(Double exchange) {
		this.exchange = exchange;
	}
	 
	public BigDecimal getContractAmount() {
		return this.contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	 
	public BigDecimal getContractRmbAmount() {
		return this.contractRmbAmount;
	}

	public void setContractRmbAmount(BigDecimal contractRmbAmount) {
		this.contractRmbAmount = contractRmbAmount;
	}
	 
	public String getSalesType() {
		return this.salesType;
	}

	public void setSalesType(String salesType) {
		this.salesType = salesType;
	}
	 
	public Double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	 
	public String getIncotermCode() {
		return this.incotermCode;
	}

	public void setIncotermCode(String incotermCode) {
		this.incotermCode = incotermCode;
	}
	 
	public String getIncotermName() {
		return this.incotermName;
	}

	public void setIncotermName(String incotermName) {
		this.incotermName = incotermName;
	}
	 
	public String getIncotermContect() {
		return this.incotermContect;
	}

	public void setIncotermContect(String incotermContect) {
		this.incotermContect = incotermContect;
	}
	 
	public String getOfficeCode() {
		return this.officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	 
	public String getOfficeName() {
		return this.officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	 
	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	 
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	 
	public String getTransferTypeCode() {
		return this.transferTypeCode;
	}

	public void setTransferTypeCode(String transferTypeCode) {
		this.transferTypeCode = transferTypeCode;
	}
	 
	public String getTransferTypeName() {
		return this.transferTypeName;
	}

	public void setTransferTypeName(String transferTypeName) {
		this.transferTypeName = transferTypeName;
	}
	 
	public Integer getIsTerm1() {
		return this.isTerm1;
	}

	public void setIsTerm1(Integer isTerm1) {
		this.isTerm1 = isTerm1;
	}
	 
	public Integer getIsTerm2() {
		return this.isTerm2;
	}

	public void setIsTerm2(Integer isTerm2) {
		this.isTerm2 = isTerm2;
	}
	 
	public Integer getIsTerm3() {
		return this.isTerm3;
	}

	public void setIsTerm3(Integer isTerm3) {
		this.isTerm3 = isTerm3;
	}
	 
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	 
	public String getFormId() {
		return this.formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
	 
	public Date getEarliestDeliveryDate() {
		return this.earliestDeliveryDate;
	}

	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
		this.earliestDeliveryDate = earliestDeliveryDate;
	}
	 
	public Date getEarliestProductDate() {
		return this.earliestProductDate;
	}

	public void setEarliestProductDate(Date earliestProductDate) {
		this.earliestProductDate = earliestProductDate;
	}
	 
	public String getFormComments() {
		return this.formComments;
	}

	public void setFormComments(String formComments) {
		this.formComments = formComments;
	}
	 
	public String getFormOperator() {
		return this.formOperator;
	}

	public void setFormOperator(String formOperator) {
		this.formOperator = formOperator;
	}
	 
	public Integer getFormType() {
		return this.formType;
	}

	public void setFormType(Integer formType) {
		this.formType = formType;
	}
	 
	public Date getFormOptTime() {
		return this.formOptTime;
	}

	public void setFormOptTime(Date formOptTime) {
		this.formOptTime = formOptTime;
	}
	 
	public Integer getSupportInfoId() {
		return this.supportInfoId;
	}

	public void setSupportInfoId(Integer supportInfoId) {
		this.supportInfoId = supportInfoId;
	}
	 
	public String getContractNumber() {
		return this.contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	 
	public String getOpteratorDomainId() {
		return this.opteratorDomainId;
	}

	public void setOpteratorDomainId(String opteratorDomainId) {
		this.opteratorDomainId = opteratorDomainId;
	}
	 
	public Date getSupportInfoOptTime() {
		return this.supportInfoOptTime;
	}

	public void setSupportInfoOptTime(Date supportInfoOptTime) {
		this.supportInfoOptTime = supportInfoOptTime;
	}
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sequenceNumber == null) ? 0 : sequenceNumber.hashCode());
		result = prime * result + ((orderTypeCode == null) ? 0 : orderTypeCode.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((ownerDomainId == null) ? 0 : ownerDomainId.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((salesTel == null) ? 0 : salesTel.hashCode());
		result = prime * result + ((contractorCode == null) ? 0 : contractorCode.hashCode());
		result = prime * result + ((contractorName == null) ? 0 : contractorName.hashCode());
		result = prime * result + ((contractorClassCode == null) ? 0 : contractorClassCode.hashCode());
		result = prime * result + ((contractorClassName == null) ? 0 : contractorClassName.hashCode());
		result = prime * result + ((salesOfficeCode == null) ? 0 : salesOfficeCode.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((versionId == null) ? 0 : versionId.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((versionCreateTime == null) ? 0 : versionCreateTime.hashCode());
		result = prime * result + ((optTime == null) ? 0 : optTime.hashCode());
		result = prime * result + ((orderInfoId == null) ? 0 : orderInfoId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastOperator == null) ? 0 : lastOperator.hashCode());
		result = prime * result + ((lastOptTime == null) ? 0 : lastOptTime.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((isReformed == null) ? 0 : isReformed.hashCode());
		result = prime * result + ((isConvenientStore == null) ? 0 : isConvenientStore.hashCode());
		result = prime * result + ((isNew == null) ? 0 : isNew.hashCode());
		result = prime * result + ((terminalIndustryCode == null) ? 0 : terminalIndustryCode.hashCode());
		result = prime * result + ((terminalIndustryCodeName == null) ? 0 : terminalIndustryCodeName.hashCode());
		result = prime * result + ((bodyDiscount == null) ? 0 : bodyDiscount.hashCode());
		result = prime * result + ((mainDiscount == null) ? 0 : mainDiscount.hashCode());
		result = prime * result + ((installTermCode == null) ? 0 : installTermCode.hashCode());
		result = prime * result + ((installTermName == null) ? 0 : installTermName.hashCode());
		result = prime * result + ((receiveTermCode == null) ? 0 : receiveTermCode.hashCode());
		result = prime * result + ((receiveTermName == null) ? 0 : receiveTermName.hashCode());
		result = prime * result + ((contactor1Id == null) ? 0 : contactor1Id.hashCode());
		result = prime * result + ((contactor1Tel == null) ? 0 : contactor1Tel.hashCode());
		result = prime * result + ((contactor2Id == null) ? 0 : contactor2Id.hashCode());
		result = prime * result + ((contactor2Tel == null) ? 0 : contactor2Tel.hashCode());
		result = prime * result + ((contactor3Id == null) ? 0 : contactor3Id.hashCode());
		result = prime * result + ((contactor3Tel == null) ? 0 : contactor3Tel.hashCode());
		result = prime * result + ((freight == null) ? 0 : freight.hashCode());
		result = prime * result + ((warranty == null) ? 0 : warranty.hashCode());
		result = prime * result + ((currencyCode == null) ? 0 : currencyCode.hashCode());
		result = prime * result + ((currencyName == null) ? 0 : currencyName.hashCode());
		result = prime * result + ((exchange == null) ? 0 : exchange.hashCode());
		result = prime * result + ((contractAmount == null) ? 0 : contractAmount.hashCode());
		result = prime * result + ((contractRmbAmount == null) ? 0 : contractRmbAmount.hashCode());
		result = prime * result + ((salesType == null) ? 0 : salesType.hashCode());
		result = prime * result + ((taxRate == null) ? 0 : taxRate.hashCode());
		result = prime * result + ((incotermCode == null) ? 0 : incotermCode.hashCode());
		result = prime * result + ((incotermName == null) ? 0 : incotermName.hashCode());
		result = prime * result + ((incotermContect == null) ? 0 : incotermContect.hashCode());
		result = prime * result + ((officeCode == null) ? 0 : officeCode.hashCode());
		result = prime * result + ((officeName == null) ? 0 : officeName.hashCode());
		result = prime * result + ((groupCode == null) ? 0 : groupCode.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((transferTypeCode == null) ? 0 : transferTypeCode.hashCode());
		result = prime * result + ((transferTypeName == null) ? 0 : transferTypeName.hashCode());
		result = prime * result + ((isTerm1 == null) ? 0 : isTerm1.hashCode());
		result = prime * result + ((isTerm2 == null) ? 0 : isTerm2.hashCode());
		result = prime * result + ((isTerm3 == null) ? 0 : isTerm3.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result + ((earliestDeliveryDate == null) ? 0 : earliestDeliveryDate.hashCode());
		result = prime * result + ((earliestProductDate == null) ? 0 : earliestProductDate.hashCode());
		result = prime * result + ((formComments == null) ? 0 : formComments.hashCode());
		result = prime * result + ((formOperator == null) ? 0 : formOperator.hashCode());
		result = prime * result + ((formType == null) ? 0 : formType.hashCode());
		result = prime * result + ((formOptTime == null) ? 0 : formOptTime.hashCode());
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
		final KOrderView other = (KOrderView) obj;
		return (this.sequenceNumber == null ? other.sequenceNumber == null : this.sequenceNumber.equals(other.sequenceNumber))
			&&(this.orderTypeCode == null ? other.orderTypeCode == null : this.orderTypeCode.equals(other.orderTypeCode))
			&&(this.createTime == null ? other.createTime == null : this.createTime.equals(other.createTime))
			&&(this.ownerDomainId == null ? other.ownerDomainId == null : this.ownerDomainId.equals(other.ownerDomainId))
			&&(this.ownerName == null ? other.ownerName == null : this.ownerName.equals(other.ownerName))
			&&(this.salesTel == null ? other.salesTel == null : this.salesTel.equals(other.salesTel))
			&&(this.contractorCode == null ? other.contractorCode == null : this.contractorCode.equals(other.contractorCode))
			&&(this.contractorName == null ? other.contractorName == null : this.contractorName.equals(other.contractorName))
			&&(this.contractorClassCode == null ? other.contractorClassCode == null : this.contractorClassCode.equals(other.contractorClassCode))
			&&(this.contractorClassName == null ? other.contractorClassName == null : this.contractorClassName.equals(other.contractorClassName))
			&&(this.salesOfficeCode == null ? other.salesOfficeCode == null : this.salesOfficeCode.equals(other.salesOfficeCode))
			&&(this.orderId == null ? other.orderId == null : this.orderId.equals(other.orderId))
			&&(this.versionId == null ? other.versionId == null : this.versionId.equals(other.versionId))
			&&(this.version == null ? other.version == null : this.version.equals(other.version))
			&&(this.status == null ? other.status == null : this.status.equals(other.status))
			&&(this.versionCreateTime == null ? other.versionCreateTime == null : this.versionCreateTime.equals(other.versionCreateTime))
			&&(this.optTime == null ? other.optTime == null : this.optTime.equals(other.optTime))
			&&(this.orderInfoId == null ? other.orderInfoId == null : this.orderInfoId.equals(other.orderInfoId))
			&&(this.id == null ? other.id == null : this.id.equals(other.id))
			&&(this.lastOperator == null ? other.lastOperator == null : this.lastOperator.equals(other.lastOperator))
			&&(this.lastOptTime == null ? other.lastOptTime == null : this.lastOptTime.equals(other.lastOptTime))
			&&(this.customerName == null ? other.customerName == null : this.customerName.equals(other.customerName))
			&&(this.isReformed == null ? other.isReformed == null : this.isReformed.equals(other.isReformed))
			&&(this.isConvenientStore == null ? other.isConvenientStore == null : this.isConvenientStore.equals(other.isConvenientStore))
			&&(this.isNew == null ? other.isNew == null : this.isNew.equals(other.isNew))
			&&(this.terminalIndustryCode == null ? other.terminalIndustryCode == null : this.terminalIndustryCode.equals(other.terminalIndustryCode))
			&&(this.terminalIndustryCodeName == null ? other.terminalIndustryCodeName == null : this.terminalIndustryCodeName.equals(other.terminalIndustryCodeName))
			&&(this.bodyDiscount == null ? other.bodyDiscount == null : this.bodyDiscount.equals(other.bodyDiscount))
			&&(this.mainDiscount == null ? other.mainDiscount == null : this.mainDiscount.equals(other.mainDiscount))
			&&(this.installTermCode == null ? other.installTermCode == null : this.installTermCode.equals(other.installTermCode))
			&&(this.installTermName == null ? other.installTermName == null : this.installTermName.equals(other.installTermName))
			&&(this.receiveTermCode == null ? other.receiveTermCode == null : this.receiveTermCode.equals(other.receiveTermCode))
			&&(this.receiveTermName == null ? other.receiveTermName == null : this.receiveTermName.equals(other.receiveTermName))
			&&(this.contactor1Id == null ? other.contactor1Id == null : this.contactor1Id.equals(other.contactor1Id))
			&&(this.contactor1Tel == null ? other.contactor1Tel == null : this.contactor1Tel.equals(other.contactor1Tel))
			&&(this.contactor2Id == null ? other.contactor2Id == null : this.contactor2Id.equals(other.contactor2Id))
			&&(this.contactor2Tel == null ? other.contactor2Tel == null : this.contactor2Tel.equals(other.contactor2Tel))
			&&(this.contactor3Id == null ? other.contactor3Id == null : this.contactor3Id.equals(other.contactor3Id))
			&&(this.contactor3Tel == null ? other.contactor3Tel == null : this.contactor3Tel.equals(other.contactor3Tel))
			&&(this.freight == null ? other.freight == null : this.freight.equals(other.freight))
			&&(this.warranty == null ? other.warranty == null : this.warranty.equals(other.warranty))
			&&(this.currencyCode == null ? other.currencyCode == null : this.currencyCode.equals(other.currencyCode))
			&&(this.currencyName == null ? other.currencyName == null : this.currencyName.equals(other.currencyName))
			&&(this.exchange == null ? other.exchange == null : this.exchange.equals(other.exchange))
			&&(this.contractAmount == null ? other.contractAmount == null : this.contractAmount.equals(other.contractAmount))
			&&(this.contractRmbAmount == null ? other.contractRmbAmount == null : this.contractRmbAmount.equals(other.contractRmbAmount))
			&&(this.salesType == null ? other.salesType == null : this.salesType.equals(other.salesType))
			&&(this.taxRate == null ? other.taxRate == null : this.taxRate.equals(other.taxRate))
			&&(this.incotermCode == null ? other.incotermCode == null : this.incotermCode.equals(other.incotermCode))
			&&(this.incotermName == null ? other.incotermName == null : this.incotermName.equals(other.incotermName))
			&&(this.incotermContect == null ? other.incotermContect == null : this.incotermContect.equals(other.incotermContect))
			&&(this.officeCode == null ? other.officeCode == null : this.officeCode.equals(other.officeCode))
			&&(this.officeName == null ? other.officeName == null : this.officeName.equals(other.officeName))
			&&(this.groupCode == null ? other.groupCode == null : this.groupCode.equals(other.groupCode))
			&&(this.groupName == null ? other.groupName == null : this.groupName.equals(other.groupName))
			&&(this.transferTypeCode == null ? other.transferTypeCode == null : this.transferTypeCode.equals(other.transferTypeCode))
			&&(this.transferTypeName == null ? other.transferTypeName == null : this.transferTypeName.equals(other.transferTypeName))
			&&(this.isTerm1 == null ? other.isTerm1 == null : this.isTerm1.equals(other.isTerm1))
			&&(this.isTerm2 == null ? other.isTerm2 == null : this.isTerm2.equals(other.isTerm2))
			&&(this.isTerm3 == null ? other.isTerm3 == null : this.isTerm3.equals(other.isTerm3))
			&&(this.comments == null ? other.comments == null : this.comments.equals(other.comments))
			&&(this.formId == null ? other.formId == null : this.formId.equals(other.formId))
			&&(this.earliestDeliveryDate == null ? other.earliestDeliveryDate == null : this.earliestDeliveryDate.equals(other.earliestDeliveryDate))
			&&(this.earliestProductDate == null ? other.earliestProductDate == null : this.earliestProductDate.equals(other.earliestProductDate))
			&&(this.formComments == null ? other.formComments == null : this.formComments.equals(other.formComments))
			&&(this.formOperator == null ? other.formOperator == null : this.formOperator.equals(other.formOperator))
			&&(this.formType == null ? other.formType == null : this.formType.equals(other.formType))
			&&(this.formOptTime == null ? other.formOptTime == null : this.formOptTime.equals(other.formOptTime));
	}
	
	public String toString() {
	    final String tab = "  ";
	    String str = "";
	    str = "KOrderView ( "
	        + "sequenceNumber = " + this.sequenceNumber + tab
	        + "orderTypeCode = " + this.orderTypeCode + tab
	        + "createTime = " + this.createTime + tab
	        + "ownerDomainId = " + this.ownerDomainId + tab
	        + "ownerName = " + this.ownerName + tab
	        + "salesTel = " + this.salesTel + tab
	        + "contractorCode = " + this.contractorCode + tab
	        + "contractorName = " + this.contractorName + tab
	        + "contractorClassCode = " + this.contractorClassCode + tab
	        + "contractorClassName = " + this.contractorClassName + tab
	        + "salesOfficeCode = " + this.salesOfficeCode + tab
	        + "orderId = " + this.orderId + tab
	        + "versionId = " + this.versionId + tab
	        + "version = " + this.version + tab
	        + "status = " + this.status + tab
	        + "versionCreateTime = " + this.versionCreateTime + tab
	        + "optTime = " + this.optTime + tab
	        + "orderInfoId = " + this.orderInfoId + tab
	        + "id = " + this.id + tab
	        + "lastOperator = " + this.lastOperator + tab
	        + "lastOptTime = " + this.lastOptTime + tab
	        + "customerName = " + this.customerName + tab
	        + "isReformed = " + this.isReformed + tab
	        + "isConvenientStore = " + this.isConvenientStore + tab
	        + "isNew = " + this.isNew + tab
	        + "terminalIndustryCode = " + this.terminalIndustryCode + tab
	        + "terminalIndustryCodeName = " + this.terminalIndustryCodeName + tab
	        + "bodyDiscount = " + this.bodyDiscount + tab
	        + "mainDiscount = " + this.mainDiscount + tab
	        + "installTermCode = " + this.installTermCode + tab
	        + "installTermName = " + this.installTermName + tab
	        + "receiveTermCode = " + this.receiveTermCode + tab
	        + "receiveTermName = " + this.receiveTermName + tab
	        + "contactor1Id = " + this.contactor1Id + tab
	        + "contactor1Tel = " + this.contactor1Tel + tab
	        + "contactor2Id = " + this.contactor2Id + tab
	        + "contactor2Tel = " + this.contactor2Tel + tab
	        + "contactor3Id = " + this.contactor3Id + tab
	        + "contactor3Tel = " + this.contactor3Tel + tab
	        + "freight = " + this.freight + tab
	        + "warranty = " + this.warranty + tab
	        + "currencyCode = " + this.currencyCode + tab
	        + "currencyName = " + this.currencyName + tab
	        + "exchange = " + this.exchange + tab
	        + "contractAmount = " + this.contractAmount + tab
	        + "contractRmbAmount = " + this.contractRmbAmount + tab
	        + "salesType = " + this.salesType + tab
	        + "taxRate = " + this.taxRate + tab
	        + "incotermCode = " + this.incotermCode + tab
	        + "incotermName = " + this.incotermName + tab
	        + "incotermContect = " + this.incotermContect + tab
	        + "officeCode = " + this.officeCode + tab
	        + "officeName = " + this.officeName + tab
	        + "groupCode = " + this.groupCode + tab
	        + "groupName = " + this.groupName + tab
	        + "transferTypeCode = " + this.transferTypeCode + tab
	        + "transferTypeName = " + this.transferTypeName + tab
	        + "isTerm1 = " + this.isTerm1 + tab
	        + "isTerm2 = " + this.isTerm2 + tab
	        + "isTerm3 = " + this.isTerm3 + tab
	        + "comments = " + this.comments + tab
	        + "formId = " + this.formId + tab
	        + "earliestDeliveryDate = " + this.earliestDeliveryDate + tab
	        + "earliestProductDate = " + this.earliestProductDate + tab
	        + "formComments = " + this.formComments + tab
	        + "formOperator = " + this.formOperator + tab
	        + "formType = " + this.formType + tab
	        + "formOptTime = " + this.formOptTime + tab
	        + " )";
	
	    return str;
	}

}
