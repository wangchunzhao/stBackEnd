package com.qhc.order.domain.sap;

import java.util.List;

/**
 * SAP订单实时状态
 * 
 * @author zsu4
 *
 */
public class SapOrderStatus {
	// SO number/销售订单编号
	private String contractNumber;
	/**
	 * Block status/冻结状态
	 * 
	 * 空值：无关
	 * A： 没有处理
	 * B：部分处理
	 * C：完全地处理
	 */
	private String blockStatus;
	
	/**
	 * Overview status/总览状态
	 * 
	 * 空值：无关
	 * A： 没有处理
	 * B：部分处理
	 * C：完全地处理
	 */
	private String overviewStatus;
	
	/**
	 * Release status/释放状
	 * 
	 * Z1: 初始状态
	 * Z3: 财务审批
	 */
	private String releaseStatus;
	
	// 行项目状态列表
	private List<SapItemStatus> items;

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public String getOverviewStatus() {
		return overviewStatus;
	}

	public void setOverviewStatus(String overviewStatus) {
		this.overviewStatus = overviewStatus;
	}

	public String getReleaseStatus() {
		return releaseStatus;
	}

	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}

	public List<SapItemStatus> getItems() {
		return items;
	}

	public void setItems(List<SapItemStatus> items) {
		this.items = items;
	}
}
