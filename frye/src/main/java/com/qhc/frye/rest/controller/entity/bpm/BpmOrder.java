package com.qhc.frye.rest.controller.entity.bpm;

import java.util.List;

public class BpmOrder {
	private Order order;
	private List<OrderItem> items;
	private OrderMargin margin;
	private OrderMargin wtwMargin;

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

	public OrderMargin getMargin() {
		return margin;
	}

	public void setMargin(OrderMargin margin) {
		this.margin = margin;
	}

	public OrderMargin getWtwMargin() {
		return wtwMargin;
	}

	public void setWtwMargin(OrderMargin wtwMargin) {
		this.wtwMargin = wtwMargin;
	}
}
