package com.qhc.frye.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderOption;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.OrderVersionDto;
import com.qhc.order.entity.OrderView;
import com.qhc.order.mapper.OrderMapper;
import com.qhc.order.service.OrderService;
import com.qhc.sap.entity.DMaterialGroups;
import com.qhc.system.domain.PageHelper;

@SpringBootTest
class OrderServiceTest {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderMapper orderMapper;

	@Test
	void testGetOrderOption() {
		OrderOption orderOption = orderService.getOrderOption();
		System.out.println(orderOption);
	}
	
	@Test
	void testFindOrderVersionsByOrderIdString() {
		List<OrderVersionDto> versions = orderService.findOrderVersions("123");
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
		query.setCreateTime("2019-10-01 - 2019-12-31");
		query.setSubmitTime("2019-10-01 - 2019-12-31");
		query.setContracterName("1");
		query.setContractNumber("1");
		query.setDominSalesCode("a");
		query.setDominStatusList(Arrays.asList(new String[] {"1","2"}));
		query.setIncludeDetail(true);
		query.setOfficeCode("1");
		query.setOrderId("1");
		query.setOrderType("ZH01");
		query.setSalesCode("1");
		query.setSalesName("1");
		query.setSequenceNumber("1");
		query.setSpecialDiscount("1");
		query.setVersion("1");
		query.setVersionId("1");
		PageHelper<OrderDto> result = orderService.findOrders(query);
		System.out.println(result);
		
		List<OrderView> orders = orderMapper.findOrderViewByParams(query);
		System.out.println(orders);
	}
	
	@Test
	void testCalcGrossProfitObject() {
		OrderQuery query = new OrderQuery();
		query.setSequenceNumber("123");
		query.setVersion("1-1");
		query.setIncludeDetail(true);
		PageHelper<OrderDto> result = orderService.findOrders(query);
		OrderDto order = result.getRows().get(0);
		List<DMaterialGroups> groups = orderService.calcGrossProfit(order);
		System.out.println(groups);
	}

}
