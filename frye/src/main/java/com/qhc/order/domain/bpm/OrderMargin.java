package com.qhc.order.domain.bpm;

import java.math.BigDecimal;

public class OrderMargin {
	/* 状态 */
	private String status;
	/* 更新日期 */
	private String updateTime;
	/* 更新人 */
	private String updateBy;
	/* 名称 */
	private String name;
	/* 金额 */
	private BigDecimal amount;
	/* 成本 */
	private BigDecimal cost;
	/* 毛利 */
	private BigDecimal grossProfit;
	/* 不含税金额 */
	private BigDecimal excludingTaxAmount;
	/* 毛利率 */
	private Double margin;
	/* 序号 */
	private String code;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public BigDecimal getExcludingTaxAmount() {
		return excludingTaxAmount;
	}

	public void setExcludingTaxAmount(BigDecimal excludingTaxAmount) {
		this.excludingTaxAmount = excludingTaxAmount;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "OrderMargin [status=" + status + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", name="
				+ name + ", amount=" + amount + ", cost=" + cost + ", grossProfit=" + grossProfit
				+ ", excludingTaxAmount=" + excludingTaxAmount + ", margin=" + margin + ", code=" + code + "]";
	}

}
