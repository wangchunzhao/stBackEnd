package com.qhc.order.domain.sap;

/**
 * SAP Characteristics value input
 * @author zsu4
 *
 */
public class SapOrderCharacteristics {
	// Item/行项目编号	VBAP-POSNR -- Selling tool 行号
	private Integer posnr;
	// Characteristic/特性 -- Selling tool 配置
	private String atnam;
	// Char. value/特性值 -- Selling tool 配置值
	private String atwrt;
	
	public Integer getPosnr() {
		return posnr;
	}
	public void setPosnr(Integer posnr) {
		this.posnr = posnr;
	}
	public String getAtnam() {
		return atnam;
	}
	public void setAtnam(String atnam) {
		this.atnam = atnam;
	}
	public String getAtwrt() {
		return atwrt;
	}
	public void setAtwrt(String atwrt) {
		this.atwrt = atwrt;
	}
}
