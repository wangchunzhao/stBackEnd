package com.qhc.order.domain.sap;

/**
 * SAP订单行项目实时状态
 * 
 * @author zsu4
 *
 */
public class SapItemStatus {
	// SO item/订单编号项目
	private String rowNum;
	// Material Number/物料编码
	private String materialCode;
	// Order quantity/订单数量
	private double quantity;
	// Unit/销售单位
	private String unitCode;
	/**
	 * Rejected code/拒绝原因
	 * 
	 * 空值： 正常使用
	 * Z2:  取消
	 * Z7: 订单关闭
	 */
	private String rejectedCode;
	/**
	 * Item status/行项目状态
	 * 
	 * A： 没有处理
	 * B：部分处理
	 * C：完全地处理
	 */
	private String status;
	/**
	 * Procurement type/采购类型
	 * 
	 * F: 外购
	 * E: 自制
	 */
	private String purchaseType;
	/**
	 * Valuation class/评估类
	 * 物料分组
	 */
	private String groupCode;
	/**
	 * Planned order/计划订单
	 * 计划订单转成生产订单后为空值
	 */
	private String plannedOrder;
	/**
	 * Production order/生产工单
	 */
	private String productionOrder;
	/**
	 * Start date of production/开工日期
	 */
	private String productionStartdate;
	/**
	 * Receipt date/入库日期
	 */
	private String receiptDate;
	/**
	 * Receipt quantity/入库数量
	 */
	private double receiptQuantity;
	/**
	 * DN quantity/DN 数量
	 * 计划交货数量
	 */
	private double plannedIssueQuantity;
	/**
	 * Goods issue qty/GI数量	实际交货数量
	 */
	private double IssuedQuantity;
	/**
	 * First GI date/首次GI日期
	 */
	private double firstIssueDate;
	/**
	 * Finally GI date/完全GI日期
	 */
	private double finallyIssueDate;
	
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getRejectedCode() {
		return rejectedCode;
	}
	public void setRejectedCode(String rejectedCode) {
		this.rejectedCode = rejectedCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getPlannedOrder() {
		return plannedOrder;
	}
	public void setPlannedOrder(String plannedOrder) {
		this.plannedOrder = plannedOrder;
	}
	public String getProductionOrder() {
		return productionOrder;
	}
	public void setProductionOrder(String productionOrder) {
		this.productionOrder = productionOrder;
	}
	public String getProductionStartdate() {
		return productionStartdate;
	}
	public void setProductionStartdate(String productionStartdate) {
		this.productionStartdate = productionStartdate;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	public double getReceiptQuantity() {
		return receiptQuantity;
	}
	public void setReceiptQuantity(double receiptQuantity) {
		this.receiptQuantity = receiptQuantity;
	}
	public double getPlannedIssueQuantity() {
		return plannedIssueQuantity;
	}
	public void setPlannedIssueQuantity(double plannedIssueQuantity) {
		this.plannedIssueQuantity = plannedIssueQuantity;
	}
	public double getIssuedQuantity() {
		return IssuedQuantity;
	}
	public void setIssuedQuantity(double issuedQuantity) {
		IssuedQuantity = issuedQuantity;
	}
	public double getFirstIssueDate() {
		return firstIssueDate;
	}
	public void setFirstIssueDate(double firstIssueDate) {
		this.firstIssueDate = firstIssueDate;
	}
	public double getFinallyIssueDate() {
		return finallyIssueDate;
	}
	public void setFinallyIssueDate(double finallyIssueDate) {
		this.finallyIssueDate = finallyIssueDate;
	}
	
}
