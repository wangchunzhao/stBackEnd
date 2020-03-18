package com.qhc.order.domain.bpm;

import java.util.List;

public class BpmOrder {
	private OrderHeader order;
	private List<OrderItem> items;
	private List<OrderMargin> margin;
	private List<OrderMargin> wtwMargin;
	private List<OrderAttachment> attachments;

	public OrderHeader getOrder() {
		return order;
	}

	public void setOrder(OrderHeader order) {
		this.order = order;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public List<OrderMargin> getMargin() {
		return margin;
	}

	public void setMargin(List<OrderMargin> margin) {
		this.margin = margin;
	}

	public List<OrderMargin> getWtwMargin() {
		return wtwMargin;
	}

	public void setWtwMargin(List<OrderMargin> wtwMargin) {
		this.wtwMargin = wtwMargin;
	}

	public List<OrderAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<OrderAttachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "BpmOrder [order=" + order + ", items=" + items + ", margin=" + margin + ", wtwMargin=" + wtwMargin
				+ ", attachments=" + attachments + "]";
	}
}
