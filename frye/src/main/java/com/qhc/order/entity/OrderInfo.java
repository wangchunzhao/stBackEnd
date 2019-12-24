package com.qhc.order.entity;import java.io.Serializable;import java.util.Date;/** *  * Function: Data transfer object. <br>  * * @author walker */public class OrderInfo		implements Serializable {	private static final long serialVersionUID = -6624630629915607479L;		/* Id */	private Integer id = null;	/* OrderId */	private Integer orderId = null;	/* 版本名称 */	private String version = null;	/* 版本序号，每次累加1 */	private Integer versionNum = null;	/* 订单状态            00   草稿            01   客户经理提交，待B2C审批            02   客户经理提交，待工程人员审批            03   支持经理提交，提交到BPM            04   BPM审批通过            05   订单变更BPM审批通过            06   不用            07   不用            08   不用            09   已下推SAP            10   Selling Tool驳回            11   BPM驳回 */	private String status = null;	/* 最新版本，新的版本生成后旧的版本变为0， 新的版本为1            0 非最新，1 最新 */	private Integer isActive = null;	/**	 * 终端客户性质	 * 01 连锁百强	 * 02 国际大连锁	 * 03 散户	 */	private String terminalType = null;	/* 店名 shop name */	private String shopName = null;	/* 项目报备编号 */	private String recordCode = null;	/* 客户（销售）经理电话 */	private String salesTel = null;	/* 是否是便利店 */	private Integer isConvenientStore = null;	/* 是否是改造店 */	private Integer isReformed = null;	/* 是不是新店 */	private Integer isNew = null;	/* 合同号，结算号 */	private String contractNumber = null;	/* 销售类型 */	private String saleType = null;	/* 税率 */	private Double taxRate = null;	/* 国际贸易条件code */	private String incoterm = null;	/* 国际贸易条件内容 */	private String incotermContect = null;	/* 原合同金额 */	private Double contractValue = null;	/* 合同人民币金额 */	private Double contractRmbValue = null;	/* 外币code */	private String currency = null;	/* 汇率 */	private Double currencyExchange = null;	/* 购销明细金额合计 */	private Double itemsAmount = null;	/* 支持经理，合同管理员 */	private String contractManager = null;	/* 表单里的大区code */	private String officeCode = null;	/* 中心code */	private String groupCode = null;	/* 保修年限 */	private Integer warranty = null;	/* 安装code */	private String installType = null;	/* 收货方式 code */	private String receiveType = null;	/* 运输类型代码code */	private String transferType = null;	/* 运费合计 */	private Double freight = null;	/* 第一联系人身份证 */	private String contactor1Id = null;	/* 第一联系人电话 */	private String contactor1Tel = null;	/* 第二联系人身份证 */	private String contactor2Id = null;	/* 第二联系人电话 */	private String contactor2Tel = null;	/* 第三联系人身份证 */	private String contactor3Id = null;	/* 第三联系人电话 */	private String contactor3Tel = null;	/* 柜体折扣 */	private Double bodyDiscount = null;	/* 批准的柜体折扣 */	private Double approvedBodyDiscount = null;	/* 机组折扣 */	private Double mainDiscount = null;	/* 批准的机组折扣 */	private Double approvedMainDiscount = null;	/* 合并折扣 */	private Double discount = null;	/* 是否为长期折扣 */	private Integer isLongterm = null;	/* 是否特批折扣/非标折扣，1 非标折扣， 0 */	private Integer isSpecial = null;	/* 结算方式，经销商 */	private String paymentType = null;	/* 柜体控制阀门件是否甲供 */	private Integer isTerm1 = null;	/* 分体柜是否远程监控 */	private Integer isTerm2 = null;	/* 立体柜是否在地下室 */	private Integer isTerm3 = null;	/* 工程安装费 */	private Double installFee = null;	/* 工程材料费 */	private Double materialFee = null;	/* 工程电气费 */	private Double electricalFee = null;	/* 工程冷库费 */	private Double refrigeratoryFee = null;	/* 工程维保费 */	private Double maintenanceFee = null;	/* 要求发货时间,最早交付时间 */	private Date earliestDeliveryDate = null;	/* 工厂最早交货时间,最早生产时间 */	private Date earliestProductDate = null;	/* 是否有B2C备注 */	private Integer isB2c = null;	/* 毛利率，每次修改保存时自动计算订单毛利率并保存到此字段 */	private String grossProfitMargin = null;	/* 备注 Remark */	private String comments = null;	/* 最后一次提交时间，会有被驳回然后提交的时间 */	private Date submitTime = null;	/* 提交bpm审批的时间，会有被驳回然后提交的时间 */	private Date bpmSubmitTime = null;	/* 创建人 */	private String creater = null;	/* 创建时间 */	private Date createTime = null;	/* 最后操作人 */	private String updater = null;	/* 最后操作时间 */	private Date updateTime = null;	public OrderInfo(){	}	public Integer getId() {		return this.id;	}	public void setId(Integer id) {		this.id = id;	}	 	public Integer getOrderId() {		return this.orderId;	}	public void setOrderId(Integer orderId) {		this.orderId = orderId;	}	 	public String getVersion() {		return this.version;	}	public void setVersion(String version) {		this.version = version;	}	 	public Integer getVersionNum() {		return this.versionNum;	}	public void setVersionNum(Integer versionNum) {		this.versionNum = versionNum;	}	 	public String getStatus() {		return this.status;	}	public void setStatus(String status) {		this.status = status;	}	 	public Integer getIsActive() {		return this.isActive;	}	public void setIsActive(Integer isActive) {		this.isActive = isActive;	}	 	public String getTerminalType() {		return this.terminalType;	}	public void setTerminalType(String terminalType) {		this.terminalType = terminalType;	}	 	public String getShopName() {		return this.shopName;	}	public void setShopName(String shopName) {		this.shopName = shopName;	}	 	public String getRecordCode() {		return this.recordCode;	}	public void setRecordCode(String recordCode) {		this.recordCode = recordCode;	}	 	public String getSalesTel() {		return this.salesTel;	}	public void setSalesTel(String salesTel) {		this.salesTel = salesTel;	}	 	public Integer getIsConvenientStore() {		return this.isConvenientStore;	}	public void setIsConvenientStore(Integer isConvenientStore) {		this.isConvenientStore = isConvenientStore;	}	 	public Integer getIsReformed() {		return this.isReformed;	}	public void setIsReformed(Integer isReformed) {		this.isReformed = isReformed;	}	 	public Integer getIsNew() {		return this.isNew;	}	public void setIsNew(Integer isNew) {		this.isNew = isNew;	}	 	public String getContractNumber() {		return this.contractNumber;	}	public void setContractNumber(String contractNumber) {		this.contractNumber = contractNumber;	}	 	public String getSaleType() {		return this.saleType;	}	public void setSaleType(String saleType) {		this.saleType = saleType;	}	 	public Double getTaxRate() {		return this.taxRate;	}	public void setTaxRate(Double taxRate) {		this.taxRate = taxRate;	}	 	public String getIncoterm() {		return this.incoterm;	}	public void setIncoterm(String incoterm) {		this.incoterm = incoterm;	}	 	public String getIncotermContect() {		return this.incotermContect;	}	public void setIncotermContect(String incotermContect) {		this.incotermContect = incotermContect;	}	 	public Double getContractValue() {		return this.contractValue;	}	public void setContractValue(Double contractValue) {		this.contractValue = contractValue;	}	 	public Double getContractRmbValue() {		return this.contractRmbValue;	}	public void setContractRmbValue(Double contractRmbValue) {		this.contractRmbValue = contractRmbValue;	}	 	public String getCurrency() {		return this.currency;	}	public void setCurrency(String currency) {		this.currency = currency;	}	 	public Double getCurrencyExchange() {		return this.currencyExchange;	}	public void setCurrencyExchange(Double currencyExchange) {		this.currencyExchange = currencyExchange;	}	 	public Double getItemsAmount() {		return this.itemsAmount;	}	public void setItemsAmount(Double itemsAmount) {		this.itemsAmount = itemsAmount;	}	 	public String getContractManager() {		return this.contractManager;	}	public void setContractManager(String contractManager) {		this.contractManager = contractManager;	}	 	public String getOfficeCode() {		return this.officeCode;	}	public void setOfficeCode(String officeCode) {		this.officeCode = officeCode;	}	 	public String getGroupCode() {		return this.groupCode;	}	public void setGroupCode(String groupCode) {		this.groupCode = groupCode;	}	 	public Integer getWarranty() {		return this.warranty;	}	public void setWarranty(Integer warranty) {		this.warranty = warranty;	}	 	public String getInstallType() {		return this.installType;	}	public void setInstallType(String installType) {		this.installType = installType;	}	 	public String getReceiveType() {		return this.receiveType;	}	public void setReceiveType(String receiveType) {		this.receiveType = receiveType;	}	 	public String getTransferType() {		return this.transferType;	}	public void setTransferType(String transferType) {		this.transferType = transferType;	}	 	public Double getFreight() {		return this.freight;	}	public void setFreight(Double freight) {		this.freight = freight;	}	 	public String getContactor1Id() {		return this.contactor1Id;	}	public void setContactor1Id(String contactor1Id) {		this.contactor1Id = contactor1Id;	}	 	public String getContactor1Tel() {		return this.contactor1Tel;	}	public void setContactor1Tel(String contactor1Tel) {		this.contactor1Tel = contactor1Tel;	}	 	public String getContactor2Id() {		return this.contactor2Id;	}	public void setContactor2Id(String contactor2Id) {		this.contactor2Id = contactor2Id;	}	 	public String getContactor2Tel() {		return this.contactor2Tel;	}	public void setContactor2Tel(String contactor2Tel) {		this.contactor2Tel = contactor2Tel;	}	 	public String getContactor3Id() {		return this.contactor3Id;	}	public void setContactor3Id(String contactor3Id) {		this.contactor3Id = contactor3Id;	}	 	public String getContactor3Tel() {		return this.contactor3Tel;	}	public void setContactor3Tel(String contactor3Tel) {		this.contactor3Tel = contactor3Tel;	}	 	public Double getBodyDiscount() {		return this.bodyDiscount;	}	public void setBodyDiscount(Double bodyDiscount) {		this.bodyDiscount = bodyDiscount;	}	 	public Double getApprovedBodyDiscount() {		return this.approvedBodyDiscount;	}	public void setApprovedBodyDiscount(Double approvedBodyDiscount) {		this.approvedBodyDiscount = approvedBodyDiscount;	}	 	public Double getMainDiscount() {		return this.mainDiscount;	}	public void setMainDiscount(Double mainDiscount) {		this.mainDiscount = mainDiscount;	}	 	public Double getApprovedMainDiscount() {		return this.approvedMainDiscount;	}	public void setApprovedMainDiscount(Double approvedMainDiscount) {		this.approvedMainDiscount = approvedMainDiscount;	}	 	public Double getDiscount() {		return this.discount;	}	public void setDiscount(Double discount) {		this.discount = discount;	}	 	public Integer getIsLongterm() {		return this.isLongterm;	}	public void setIsLongterm(Integer isLongterm) {		this.isLongterm = isLongterm;	}	 	public Integer getIsSpecial() {		return this.isSpecial;	}	public void setIsSpecial(Integer isSpecial) {		this.isSpecial = isSpecial;	}	 	public String getPaymentType() {		return this.paymentType;	}	public void setPaymentType(String paymentType) {		this.paymentType = paymentType;	}	 	public Integer getIsTerm1() {		return this.isTerm1;	}	public void setIsTerm1(Integer isTerm1) {		this.isTerm1 = isTerm1;	}	 	public Integer getIsTerm2() {		return this.isTerm2;	}	public void setIsTerm2(Integer isTerm2) {		this.isTerm2 = isTerm2;	}	 	public Integer getIsTerm3() {		return this.isTerm3;	}	public void setIsTerm3(Integer isTerm3) {		this.isTerm3 = isTerm3;	}	 	public Double getInstallFee() {		return this.installFee;	}	public void setInstallFee(Double installFee) {		this.installFee = installFee;	}	 	public Double getMaterialFee() {		return this.materialFee;	}	public void setMaterialFee(Double materialFee) {		this.materialFee = materialFee;	}	 	public Double getElectricalFee() {		return this.electricalFee;	}	public void setElectricalFee(Double electricalFee) {		this.electricalFee = electricalFee;	}	 	public Double getRefrigeratoryFee() {		return this.refrigeratoryFee;	}	public void setRefrigeratoryFee(Double refrigeratoryFee) {		this.refrigeratoryFee = refrigeratoryFee;	}	 	public Double getMaintenanceFee() {		return this.maintenanceFee;	}	public void setMaintenanceFee(Double maintenanceFee) {		this.maintenanceFee = maintenanceFee;	}	 	public Date getEarliestDeliveryDate() {		return this.earliestDeliveryDate;	}	public void setEarliestDeliveryDate(Date earliestDeliveryDate) {		this.earliestDeliveryDate = earliestDeliveryDate;	}	 	public Date getEarliestProductDate() {		return this.earliestProductDate;	}	public void setEarliestProductDate(Date earliestProductDate) {		this.earliestProductDate = earliestProductDate;	}	 	public Integer getIsB2c() {		return this.isB2c;	}	public void setIsB2c(Integer isB2c) {		this.isB2c = isB2c;	}	 	public String getGrossProfitMargin() {		return this.grossProfitMargin;	}	public void setGrossProfitMargin(String grossProfitMargin) {		this.grossProfitMargin = grossProfitMargin;	}	 	public String getComments() {		return this.comments;	}	public void setComments(String comments) {		this.comments = comments;	}	 	public Date getSubmitTime() {		return this.submitTime;	}	public void setSubmitTime(Date submitTime) {		this.submitTime = submitTime;	}	 	public Date getBpmSubmitTime() {		return this.bpmSubmitTime;	}	public void setBpmSubmitTime(Date bpmSubmitTime) {		this.bpmSubmitTime = bpmSubmitTime;	}	 	public String getCreater() {		return this.creater;	}	public void setCreater(String creater) {		this.creater = creater;	}	 	public Date getCreateTime() {		return this.createTime;	}	public void setCreateTime(Date createTime) {		this.createTime = createTime;	}	 	public String getUpdater() {		return this.updater;	}	public void setUpdater(String updater) {		this.updater = updater;	}	 	public Date getUpdateTime() {		return this.updateTime;	}	public void setUpdateTime(Date updateTime) {		this.updateTime = updateTime;	}	 	@Override	public int hashCode() {		final int prime = 31;		int result = 1;		result = prime * result + ((id == null) ? 0 : id.hashCode());		return result;	}	@Override	public boolean equals(Object obj) {		if (this == obj) {			return true;		}		if (obj == null) {			return false;		}		if (getClass() != obj.getClass()) {			return false;		}		final OrderInfo other = (OrderInfo) obj;		return (this.id == null ? other.id == null : this.id.equals(other.id));	}		public String toString() {	    final String tab = "  ";	    String str = "";	    str = "OrderInfo ( "	        + "id = " + this.id + tab	        + "orderId = " + this.orderId + tab	        + "version = " + this.version + tab	        + "versionNum = " + this.versionNum + tab	        + "status = " + this.status + tab	        + "isActive = " + this.isActive + tab	        + "terminalType = " + this.terminalType + tab	        + "shopName = " + this.shopName + tab	        + "recordCode = " + this.recordCode + tab	        + "salesTel = " + this.salesTel + tab	        + "isConvenientStore = " + this.isConvenientStore + tab	        + "isReformed = " + this.isReformed + tab	        + "isNew = " + this.isNew + tab	        + "contractNumber = " + this.contractNumber + tab	        + "saleType = " + this.saleType + tab	        + "taxRate = " + this.taxRate + tab	        + "incoterm = " + this.incoterm + tab	        + "incotermContect = " + this.incotermContect + tab	        + "contractValue = " + this.contractValue + tab	        + "contractRmbValue = " + this.contractRmbValue + tab	        + "currency = " + this.currency + tab	        + "currencyExchange = " + this.currencyExchange + tab	        + "itemsAmount = " + this.itemsAmount + tab	        + "contractManager = " + this.contractManager + tab	        + "officeCode = " + this.officeCode + tab	        + "groupCode = " + this.groupCode + tab	        + "warranty = " + this.warranty + tab	        + "installType = " + this.installType + tab	        + "receiveType = " + this.receiveType + tab	        + "transferType = " + this.transferType + tab	        + "freight = " + this.freight + tab	        + "contactor1Id = " + this.contactor1Id + tab	        + "contactor1Tel = " + this.contactor1Tel + tab	        + "contactor2Id = " + this.contactor2Id + tab	        + "contactor2Tel = " + this.contactor2Tel + tab	        + "contactor3Id = " + this.contactor3Id + tab	        + "contactor3Tel = " + this.contactor3Tel + tab	        + "bodyDiscount = " + this.bodyDiscount + tab	        + "approvedBodyDiscount = " + this.approvedBodyDiscount + tab	        + "mainDiscount = " + this.mainDiscount + tab	        + "approvedMainDiscount = " + this.approvedMainDiscount + tab	        + "discount = " + this.discount + tab	        + "isLongterm = " + this.isLongterm + tab	        + "isSpecial = " + this.isSpecial + tab	        + "paymentType = " + this.paymentType + tab	        + "isTerm1 = " + this.isTerm1 + tab	        + "isTerm2 = " + this.isTerm2 + tab	        + "isTerm3 = " + this.isTerm3 + tab	        + "installFee = " + this.installFee + tab	        + "materialFee = " + this.materialFee + tab	        + "electricalFee = " + this.electricalFee + tab	        + "refrigeratoryFee = " + this.refrigeratoryFee + tab	        + "maintenanceFee = " + this.maintenanceFee + tab	        + "earliestDeliveryDate = " + this.earliestDeliveryDate + tab	        + "earliestProductDate = " + this.earliestProductDate + tab	        + "isB2c = " + this.isB2c + tab	        + "grossProfitMargin = " + this.grossProfitMargin + tab	        + "comments = " + this.comments + tab	        + "submitTime = " + this.submitTime + tab	        + "bpmSubmitTime = " + this.bpmSubmitTime + tab	        + "creater = " + this.creater + tab	        + "createTime = " + this.createTime + tab	        + "updater = " + this.updater + tab	        + "updateTime = " + this.updateTime + tab	        + " )";		    return str;	}}