package com.qhc.order.domain.sap;

import java.util.List;

/**
 * SAP Order 
 * @author zsu4
 *
 */
public class SapOrder {
	// Header input
	private SapOrderHeader isZhdr;
	// Item input
	private List<SapOrderItem> itZitem;
	// Characteristics value input
	private List<SapOrderCharacteristics> itZcharc;
	// Price/condition record input
	private List<SapOrderPrice> itZcond;
	// Billing plan
	private List<SapOrderPlan> itZplan;
	
	public SapOrderHeader getIsZhdr() {
		return isZhdr;
	}
	public void setIsZhdr(SapOrderHeader isZhdr) {
		this.isZhdr = isZhdr;
	}
	public List<SapOrderCharacteristics> getItZcharc() {
		return itZcharc;
	}
	public void setItZcharc(List<SapOrderCharacteristics> itZcharc) {
		this.itZcharc = itZcharc;
	}
	public List<SapOrderPrice> getItZcond() {
		return itZcond;
	}
	public void setItZcond(List<SapOrderPrice> itZcond) {
		this.itZcond = itZcond;
	}
	public List<SapOrderItem> getItZitem() {
		return itZitem;
	}
	public void setItZitem(List<SapOrderItem> itZitem) {
		this.itZitem = itZitem;
	}
	public List<SapOrderPlan> getItZplan() {
		return itZplan;
	}
	public void setItZplan(List<SapOrderPlan> itZplan) {
		this.itZplan = itZplan;
	}
}
