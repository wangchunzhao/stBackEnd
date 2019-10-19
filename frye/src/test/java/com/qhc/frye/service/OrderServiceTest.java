package com.qhc.frye.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.frye.rest.controller.entity.OrderOption;

@SpringBootTest
class OrderServiceTest {
	@Autowired
	OrderService orderService;

	@Test
	void testGetOrderOption() {
		OrderOption orderOption = orderService.getOrderOption();
		System.out.println(orderOption);
	}

}
