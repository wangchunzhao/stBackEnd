package com.qhc.frye.rest.controller.entity.bpm;

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
	private String amount;
	/* 成本 */
	private String cost;
	/* 毛利 */
	private String grossProfit;
	/* 不含税金额 */
	private String excludingTaxAmount;
	/* 毛利率 */
	private String margin;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getExcludingTaxAmount() {
		return excludingTaxAmount;
	}

	public void setExcludingTaxAmount(String excludingTaxAmount) {
		this.excludingTaxAmount = excludingTaxAmount;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
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
