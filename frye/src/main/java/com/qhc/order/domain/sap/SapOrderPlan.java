package com.qhc.order.domain.sap;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * SAP Billing plan
 * @author zsu4
 *
 */
public class SapOrderPlan {
	// Date category/日期原因 -- Selling tool 回款类型
	private String tetxt;
	// Settlement date/结算日期 -- Selling tool 回款起始时间
	// 格式：20191018
	private String fkdat;
	// Value to be billed/金额 -- Selling tool 预算回款金额
	// 保留两位小数
	private BigDecimal fakwr;
	// Payment terms/付款条款 -- Selling tool 付款条款（New/新增）
	private String zterm;
	public String getTetxt() {
		return tetxt;
	}
	public void setTetxt(String tetxt) {
		this.tetxt = tetxt;
	}
	public String getFkdat() {
		return fkdat;
	}
	public void setFkdat(String fkdat) {
		this.fkdat = fkdat;
	}
	public BigDecimal getFakwr() {
		return fakwr;
	}
	public void setFakwr(BigDecimal fakwr) {
		this.fakwr = fakwr;
		if (this.fakwr != null) {
			this.fakwr = this.fakwr.setScale(2, RoundingMode.HALF_UP);
		}
	}
	public String getZterm() {
		return zterm;
	}
	public void setZterm(String zterm) {
		this.zterm = zterm;
	}
}
