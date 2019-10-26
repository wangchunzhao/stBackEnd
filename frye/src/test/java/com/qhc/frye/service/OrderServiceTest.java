package com.qhc.frye.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.frye.domain.KOrderView;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.OrderQuery;
import com.qhc.frye.rest.controller.entity.OrderVersion;
import com.qhc.frye.rest.controller.entity.form.AbsOrder;

@SpringBootTest
class OrderServiceTest {
	@Autowired
	OrderService orderService;

	@Test
	void testGetOrderOption() {
		OrderOption orderOption = orderService.getOrderOption();
		System.out.println(orderOption);
	}
	
	@Test
	void testFindOrderVersionsByOrderIdString() {
		List<OrderVersion> versions = orderService.findOrderVersionsByOrderId("123");
		System.out.println(versions);
	}
	
	@Test
	void testOrderCreationForSAP() {
		String result = orderService.orderCreationForSAP("123");
		System.out.println(result);
	}
	
	@Test
	void testFindOrdersObject() {
		OrderQuery query = new OrderQuery();
		query.setLast(true);
		query.setPageSize(10);
		query.setIncludeDetail(true);
		List<AbsOrder> result = orderService.findOrders(query);
		System.out.println(result);
	}

}
