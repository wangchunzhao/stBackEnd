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
	
	@Column(name="contract_no",columnDefinition="CHAR")
	public String contractNo;
	
	@Column(name="b2c",length = 10)
	public int b2c;
	
	@Column(name="special_discount",length = 10)
	public int specialDiscount;
	
	@Column(name="create_time",columnDefinition="DATETIME")
	public Date createTime;
	
	@Column(name="sequence_number",columnDefinition="CHAR")
	private String sequenceNumber;//流水号
	
	@Column(name="shop_name",columnDefinition="TEXT")
	private String	shopName	;//	店名
	
	@Column(name="is_reformedShop",columnDefinition ="TINYINT",length=1)
	private Integer	isReformShop	;//	是否改造店
	
	@Column(name="is_newCustomer",columnDefinition ="TINYINT",length=1)
	private Integer	isNewCustomer	;//是否新客户
	
	@Column(name="terminal_industry_code")
	private String	terminalIndustryCode;
	
	@Column(name="terminal_industry_code_name",columnDefinition="TEXT")
	private String	terminalIndustryCodeName	;//	终端客户性质
	
	@Column(name="dicount",columnDefinition="BOUBLE")
	private Double	discount	;//	折扣
	
	@Column(name="main_discount",columnDefinition="BOUBLE")
	private Double	mainDiscount	;//	
	
	@Column(name="body_discount",columnDefinition="BOUBLE")
	private Double	bodyDiscount	;//	
	
	
	
	@Column(name="province_code")
	private String	provinceCode	;//
	
	@Column(name="province_name",columnDefinition="TEXT")
	private String	provinceName	;//
	
	@Column(name="city_code")
	private String	cityCode	;//	
	
	@Column(name="city_name",columnDefinition="TEXT")
	private String	cityName	;//	
	
	@Column(name="distince_code")
	private String	distinceCode	;//
	
	@Column(name="distince_name",columnDefinition="TEXT")
	private String	distinceName	;//
	
	@Column(name="address",columnDefinition="TEXT")
	private String	address	;//	到货地址
	
	
	
	@Column(name="install_term_code")
	private String	installTermCode	;//	
	
	@Column(name="install_term_name",columnDefinition="TEXT")
	private String	installTermName	;//	
	
	@Column(name="receive_term_code")
	private String	receiveTermCode	;//
	
	@Column(name="receive_term_name",columnDefinition="TEXT")
	private String	receiveTermName	;//	

	
	
	@Column(name="contactor_1_id")
	private String	contactor1Id	;//	授权人及身份证号
	@Column(name="contactor_1_tel")
	private String	contactor1Tel	;//
	
	@Column(name="contactor_2_id")
	private String	contactor2Id	;//	授权人及身份证号
	@Column(name="contactor_2_tel")
	private String	contactor2Tel	;//
	
	@Column(name="contactor_3_id")
	private String	contactor3Id	;//	授权人及身份证号
	@Column(name="contactor_3_tel")
	private String	contactor3Tel	;//
	
	
	
	@Column(name="freight",columnDefinition = "DOUBLE")
	private Double	freight	;//运费
	
	@Column(name="warranty")
	private int	warranty	;//保修期限（年）
	
	
	@Column(name="currency_code")
	private Integer	currencyCode	;//币别
	
	@Column(name="currency_name",columnDefinition = "TEXT")
	private Integer	currencyName	;//币别
	
	@Column(name="exchange",columnDefinition = "DOUBLE")
	private Double	exchange	;//汇率
	
	
	@Column(name="contract_amount",columnDefinition = "DOUBLE")
	private Double	contractAmount	;//原币合同金额
	
	@Column(name="contract_rmb_amount",columnDefinition = "DOUBLE")
	private Double	contractRmbAmount	;//
	
	@Column(name="k_order_version_id",length = 10)
	public int kOrderVersionId;
	
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


	public String getContractNo() {
		return contractNo;
	}


	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}


	public int getB2c() {
		return b2c;
	}


	public void setB2c(int b2c) {
		this.b2c = b2c;
	}


	public int getSpecialDiscount() {
		return specialDiscount;
	}


	public void setSpecialDiscount(int specialDiscount) {
		this.specialDiscount = specialDiscount;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getSequenceNumber() {
		return sequenceNumber;
	}


	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}


	public String getShopName() {
		return shopName;
	}


	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


	public Integer getIsReformShop() {
		return isReformShop;
	}


	public void setIsReformShop(Integer isReformShop) {
		this.isReformShop = isReformShop;
	}


	public Integer getIsNewCustomer() {
		return isNewCustomer;
	}


	public void setIsNewCustomer(Integer isNewCustomer) {
		this.isNewCustomer = isNewCustomer;
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


	public Double getDiscount() {
		return discount;
	}


	public void setDiscount(Double discount) {
		this.discount = discount;
	}


	public Double getMainDiscount() {
		return mainDiscount;
	}


	public void setMainDiscount(Double mainDiscount) {
		this.mainDiscount = mainDiscount;
	}


	public Double getBodyDiscount() {
		return bodyDiscount;
	}


	public void setBodyDiscount(Double bodyDiscount) {
		this.bodyDiscount = bodyDiscount;
	}


	public String getProvinceCode() {
		return provinceCode;
	}


	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}


	public String getProvinceName() {
		return provinceName;
	}


	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


	public String getCityCode() {
		return cityCode;
	}


	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getDistinceCode() {
		return distinceCode;
	}


	public void setDistinceCode(String distinceCode) {
		this.distinceCode = distinceCode;
	}


	public String getDistinceName() {
		return distinceName;
	}


	public void setDistinceName(String distinceName) {
		this.distinceName = distinceName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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


	public Integer getCurrencyCode() {
		return currencyCode;
	}


	public void setCurrencyCode(Integer currencyCode) {
		this.currencyCode = currencyCode;
	}


	public Integer getCurrencyName() {
		return currencyName;
	}


	public void setCurrencyName(Integer currencyName) {
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


	public int getkOrderVersionId() {
		return kOrderVersionId;
	}


	public void setkOrderVersionId(int kOrderVersionId) {
		this.kOrderVersionId = kOrderVersionId;
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
