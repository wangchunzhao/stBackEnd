package com.qhc.order.domain.sap;

/**
 * SAP Item input
 * @author zsu4
 *
 */
public class SapOrderItem {
	// Ship-to PO item/送达方-采购订单编号项目 -- Selling tool 行号
	private Integer posnr;
	// Material Number/物料编码 -- Selling tool 专用号
	private String matnr;
	// Target quantity/数量 -- Selling tool 数量
	private Integer zmeng;
	// Req.dlv.date/请求发货日期 -- Selling tool 要求发货日期
	// 格式：20191101
	private String edatu;
	// Item category/行项目类别	 -- Selling tool 行项目类别
	private String pstyv;
	// Item usage/项目用途 -- Selling tool 行项目用途
	private String vkaus;
	// Ship-to address/送达方地址 -- Selling tool 到货地址
	private String street;
	// Province/省 -- Selling tool 省
	private String region;
	// City/市 -- Selling tool 市
	private String city1;
	// District/区 -- Selling tool 区
	private String city2;
	// B2C note/B2C备注 -- Selling tool B2C备注
	private String vbbp0006;
	// Survey info. for item /调研表基本信息 -- Selling tool 调研表基本信息：商标要求，木包装要求，铭牌要求，电路图要求。
	private String vbbpz121;
	// Special note/特殊备注 -- Selling tool 特殊备注
	private String vbbpz117;
	// Color option/颜色可选项 -- Selling tool 颜色可选项信息
	private String vbbpz120;
	// Customer special request/客户物料说明 -- Selling tool 备注
	private String vbbp0007;
	// Color Note/颜色备注
	private String vbbpz118;
	// 拼接方式
	private String vbbpz119;
	// 变更状态
	private String updateflag;
	
	public Integer getPosnr() {
		return posnr;
	}
	public void setPosnr(Integer posnr) {
		this.posnr = posnr;
	}
	public String getMatnr() {
		return matnr;
	}
	public void setMatnr(String matnr) {
		this.matnr = matnr;
	}
	public Integer getZmeng() {
		return zmeng;
	}
	public void setZmeng(Integer zmeng) {
		this.zmeng = zmeng;
	}
	public String getEdatu() {
		return edatu;
	}
	public void setEdatu(String edatu) {
		this.edatu = edatu;
	}
	public String getPstyv() {
		return pstyv;
	}
	public void setPstyv(String pstyv) {
		this.pstyv = pstyv;
	}
	public String getVkaus() {
		return vkaus;
	}
	public void setVkaus(String vkaus) {
		this.vkaus = vkaus;
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
	public String getVbbp0006() {
		return vbbp0006;
	}
	public void setVbbp0006(String vbbp0006) {
		this.vbbp0006 = vbbp0006;
	}
	public String getVbbpz121() {
		return vbbpz121;
	}
	public void setVbbpz121(String vbbpz121) {
		this.vbbpz121 = vbbpz121;
	}
	public String getVbbpz117() {
		return vbbpz117;
	}
	public void setVbbpz117(String vbbpz117) {
		this.vbbpz117 = vbbpz117;
	}
	public String getVbbpz120() {
		return vbbpz120;
	}
	public void setVbbpz120(String vbbpz120) {
		this.vbbpz120 = vbbpz120;
	}
	public String getVbbp0007() {
		return vbbp0007;
	}
	public void setVbbp0007(String vbbp0007) {
		this.vbbp0007 = vbbp0007;
	}
	public String getVbbpz118() {
		return vbbpz118;
	}
	public void setVbbpz118(String vbbpz118) {
		this.vbbpz118 = vbbpz118;
	}
  public String getVbbpz119() {
    return vbbpz119;
  }
  public void setVbbpz119(String vbbpz119) {
    this.vbbpz119 = vbbpz119;
  }
  public String getUpdateflag() {
		return updateflag;
	}
	public void setUpdateflag(String updateflag) {
		this.updateflag = updateflag;
	}
}
