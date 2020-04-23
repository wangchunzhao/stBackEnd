package com.qhc.order.domain.sap;

/**
 * SAP Header input
 * @author zsu4
 *
 */
public class SapOrderHeader {
	// Sales order type/订单类型 -- Selling tool 需求类型
	private String auart;
	// Sales org./销售组织 Fixed value/固定为 0841
	private String vkorg = "0841";
	// DC/分销渠道 -- Selling tool 性质分类
	private String vtweg;
	// Store name/店名 -- Selling tool 店名
	private String name2;
	// Division/产品组 -- Selling tool 销售类型
	private String spart;
	// Sales office/销售办公室 -- Selling tool 大区
	private String vkbur;
	// Sales group/销售组 -- Selling tool 中心
	private String vkgrp;
	// SO number/销售订单编号 -- Selling tool 合同号
	private String vbeln;
	// Customer grp.1/客户组1 -- Selling tool 是否便利店
	private String kvgr1;
	// Customer grp.2/客户组2 -- Selling tool 是否新客户
	private String kvgr2;
	// Customer grp.3/客户组3 -- Selling tool 是否改造店
	private String kvgr3;
	// Additional/附加的 -- Selling tool 保修年限
	private String bstzd;
	// Ship-to PO/送达方-采购订单编号 -- Selling tool 项目报备编号
	private String bstkdE;
	// Shipping type/装运类型 -- Selling tool 运输类型
	private String vsart;
	// Payment terms/付款条款 -- Selling tool 结算方式
	private String zterm;
	// Payment terms/付款条款 -- Selling tool 结算方式，直签订单
	private String vbbkz114;
	// Sold-to party/售达方 -- Selling tool 签约单位
	private String kunnr;
	// Sold-to party/开票方 -- Selling tool 签约单位
	private String kunnr_re;
	// Sold-to party/付款方 -- Selling tool 签约单位
	private String kunnr_rg;
	// Sold-to party/送达方 -- Selling tool 签约单位
	private String kunnr_we;
	// Currency/币别 -- Selling tool 币别
	private String waerk;
	// Incoterms/国际贸易条款 -- Selling tool 国际贸易条件
	private String inco1;
	// Incoterms2/国际贸易条款2 -- Selling tool 国际贸易条件2
	private String inco2;
	// 折扣 ：规定为 ZH03
	// Contract amount/合同金额 -- Selling tool 合同金额
	private String vbbkz120;
	// Sale rep./签约人 -- Selling tool 客户经理
	private String vbbkz121;
	// Order clerk/合同管理员 -- Selling tool 合同管理员
	private String vbbkz109;
	// Contact info./授权人信息	Text(VBBK-Z108) -- Selling tool 授权人信息6个字段
	private String vbbkz108;
	// Survey info. for header /调研表相关内容 -- Selling tool 调研表相关内容3个字段
	private String vbbkz122;
	// Receiving method /收货方式 -- Selling tool 收货方式
	private String vbbkz106;
	// Ship-to address/送达方地址 -- Selling tool 到货地址
	private String street;
	// Province/省 -- Selling tool 省
	private String region;
	// City/市 -- Selling tool 市
	private String city1;
	// District/区 -- Selling tool 区
	private String city2;
	// 交货日期 -- 要求发货日期
	private String vdatu;
	// 变更状态
	private String updateflag;
	
	public String getAuart() {
		return auart;
	}
	public void setAuart(String auart) {
		this.auart = auart;
	}
	public String getVkorg() {
		return vkorg;
	}
	public void setVkorg(String vkorg) {
		this.vkorg = vkorg;
	}
	public String getVtweg() {
		return vtweg;
	}
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getVkbur() {
		return vkbur;
	}
	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}
	public String getVkgrp() {
		return vkgrp;
	}
	public void setVkgrp(String vkgrp) {
		this.vkgrp = vkgrp;
	}
	public String getVbeln() {
		return vbeln;
	}
	public void setVbeln(String vbeln) {
		this.vbeln = vbeln;
	}
	public String getKvgr1() {
		return kvgr1;
	}
	public void setKvgr1(String kvgr1) {
		this.kvgr1 = kvgr1;
	}
	public String getKvgr2() {
		return kvgr2;
	}
	public void setKvgr2(String kvgr2) {
		this.kvgr2 = kvgr2;
	}
	public String getKvgr3() {
		return kvgr3;
	}
	public void setKvgr3(String kvgr3) {
		this.kvgr3 = kvgr3;
	}
	public String getBstzd() {
		return bstzd;
	}
	public void setBstzd(String bstzd) {
		this.bstzd = bstzd;
	}
	public String getBstkdE() {
		return bstkdE;
	}
	public void setBstkdE(String bstkdE) {
		this.bstkdE = bstkdE;
	}
	public String getVsart() {
		return vsart;
	}
	public void setVsart(String vsart) {
		this.vsart = vsart;
	}
	public String getZterm() {
		return zterm;
	}
	public void setZterm(String zterm) {
		this.zterm = zterm;
	}
	public String getVbbkz114() {
    return vbbkz114;
  }
  public void setVbbkz114(String vbbkz114) {
    this.vbbkz114 = vbbkz114;
  }
  public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getKunnr_re() {
		return kunnr_re;
	}
	public void setKunnr_re(String kunnr_re) {
		this.kunnr_re = kunnr_re;
	}
	public String getKunnr_rg() {
		return kunnr_rg;
	}
	public void setKunnr_rg(String kunnr_rg) {
		this.kunnr_rg = kunnr_rg;
	}
	public String getKunnr_we() {
		return kunnr_we;
	}
	public void setKunnr_we(String kunnr_we) {
		this.kunnr_we = kunnr_we;
	}
	public String getWaerk() {
		return waerk;
	}
	public void setWaerk(String waerk) {
		this.waerk = waerk;
	}
	public String getInco1() {
		return inco1;
	}
	public void setInco1(String inco1) {
		this.inco1 = inco1;
	}
	public String getInco2() {
		return inco2;
	}
	public void setInco2(String inco2) {
		this.inco2 = inco2;
	}
	public String getVbbkz120() {
		return vbbkz120;
	}
	public void setVbbkz120(String vbbkz120) {
		this.vbbkz120 = vbbkz120;
	}
	public String getVbbkz121() {
		return vbbkz121;
	}
	public void setVbbkz121(String vbbkz121) {
		this.vbbkz121 = vbbkz121;
	}
	public String getVbbkz109() {
		return vbbkz109;
	}
	public void setVbbkz109(String vbbkz109) {
		this.vbbkz109 = vbbkz109;
	}
	public String getVbbkz108() {
		return vbbkz108;
	}
	public void setVbbkz108(String vbbkz108) {
		this.vbbkz108 = vbbkz108;
	}
	public String getVbbkz122() {
		return vbbkz122;
	}
	public void setVbbkz122(String vbbkz122) {
		this.vbbkz122 = vbbkz122;
	}
	public String getVbbkz106() {
		return vbbkz106;
	}
	public void setVbbkz106(String vbbkz106) {
		this.vbbkz106 = vbbkz106;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getCity2() {
		return city2;
	}
	public void setCity2(String city2) {
		this.city2 = city2;
	}
	public String getVdatu() {
		return vdatu;
	}
	public void setVdatu(String vdatu) {
		this.vdatu = vdatu;
	}
	public String getUpdateflag() {
		return updateflag;
	}
	public void setUpdateflag(String updateflag) {
		this.updateflag = updateflag;
	}
}
