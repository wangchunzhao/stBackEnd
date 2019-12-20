package com.qhc.order.domain.bpm;

import java.util.List;

public class BpmOrder {
	private Order order;
	private List<OrderItem> items;
	private List<OrderMargin> margin;
	private List<OrderMargin> wtwMargin;

	@Override
	public String toString() {
		return "BpmOrder [order=" + order + ", items=" + items + ", margin=" + margin + ", wtwMargin=" + wtwMargin
				+ "]";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
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
}
