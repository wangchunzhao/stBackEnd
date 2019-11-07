package com.qhc.frye.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.frye.domain.DMaterialGroups;
import com.qhc.frye.domain.KOrderView;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.OrderQuery;
import com.qhc.frye.rest.controller.entity.OrderVersion;
import com.qhc.frye.rest.controller.entity.PageHelper;
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
		List<OrderVersion> versions = orderService.findOrderVersions("123");
		System.out.println(versions);
	}
	
	@Test
	void testOrderCreationForSAP() {
		String result = orderService.orderCreationForSAP("123", "1-1");
		System.out.println(result);
	}
	
	@Test
	void testFindOrdersObject() {
		OrderQuery query = new OrderQuery();
		query.setLast(true);
		query.setPageNo(1);
		query.setPageSize(10);
		query.setB2c("1");
		query.setStatus("1");
		query.setStatusList(Arrays.asList(new String[] {"1", "2"}));
		query.setIncludeDetail(true);
		PageHelper<AbsOrder> result = orderService.findOrders(query);
		System.out.println(result);
	}
	
	@Test
	void testCalcGrossProfitObject() {
		OrderQuery query = new OrderQuery();
		query.setSequenceNumber("123");
		query.setVersion("1-1");
		query.setIncludeDetail(true);
		PageHelper<AbsOrder> result = orderService.findOrders(query);
		AbsOrder order = result.getRows().get(0);
		List<DMaterialGroups> groups = orderService.calcGrossProfit(order);
		System.out.println(groups);
	}

}
