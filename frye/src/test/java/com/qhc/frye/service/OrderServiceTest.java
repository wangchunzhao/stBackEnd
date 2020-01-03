package com.qhc.frye.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.pagehelper.PageInfo;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderOption;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.OrderVersion;
import com.qhc.order.domain.sap.SapOrder;
import com.qhc.order.domain.sap.SapOrderCharacteristics;
import com.qhc.order.domain.sap.SapOrderHeader;
import com.qhc.order.domain.sap.SapOrderItem;
import com.qhc.order.domain.sap.SapOrderPlan;
import com.qhc.order.domain.sap.SapOrderPrice;
import com.qhc.order.mapper.OrderInfoMapper;
import com.qhc.order.mapper.OrderMapper;
import com.qhc.order.service.OrderService;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.system.domain.PageHelper;

@SpringBootTest
class OrderServiceTest {
	Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderInfoMapper orderInfoMapper;

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
		SapOrder sapCreationOrder = getSapCreationOrder();
		
		logger.info("Start test sap order creation.");
		
		String result = orderService.sendToSap(sapCreationOrder);
		
		logger.info("Result: {}" + result);
	}
	
	private SapOrder getSapCreationOrder() {
		SapOrder sapCreationOrder = new SapOrder();
		
		SapOrderHeader header = new SapOrderHeader();
		sapCreationOrder.setIsZhdr(header);
//		header.setAuart("ZH0T");
//		header.setVkorg("0842");
//		header.setVtweg("1");
//		header.setName2("1");
//		header.setSpart("10");
//		header.setVkbur("C001");
//		header.setVkgrp("C05");
//		header.setVbeln("1");
//		header.setKvgr1("1");
//		header.setKvgr2("1");
//		header.setKvgr3("1");
//		header.setBstzd("1");
//		header.setBstkdE("1");
//		header.setVsart("1");
//		header.setZterm("1");
//		header.setKunnr("3001");
//		header.setWaerk("1");
//		header.setInco1("EXW");
//		header.setInco2("EXW");
//		header.setVbbkz120("1");
//		header.setVbbkz121("1");
//		header.setVbbkz109("1");
//		header.setVbbkz108("1");
//		header.setVbbkz122("1");
		
		// Test data 2019/10/23
		header.setAuart("ZH0T");
		header.setVkorg("0842");
		header.setVtweg("10");
		header.setSpart("10");
		header.setVkbur("C001");
		header.setVkgrp("C05");
		header.setBstzd("1");
		header.setKunnr("3001");
		header.setInco1("EXW");
		header.setInco2("EXW");
		
		List<SapOrderItem> items = new ArrayList<>();
		SapOrderItem item = new SapOrderItem();
//		item.setPosnr(10);
//		item.setMatnr("1");
//		item.setZmeng(1);
//		item.setEdatu("20191101");
//		item.setPstyv("1");
//		item.setVkaus("1");
//		item.setStreet("Dalian");
//		item.setRegion("1");
//		item.setCity1("1");
//		item.setCity2("1");
//		item.setVbbp0006("1");
//		item.setVbbpz121("1");
//		item.setVbbpz117("1");
//		item.setVbbpz120("1");
//		item.setVbbp0007("1");
		
		// Test data 2019/10/23
		item.setPosnr(10);
		item.setMatnr("BG1AM200000");
		item.setZmeng(100);
		
		items.add(item);

		sapCreationOrder.setItZitem(items);
		
		List<SapOrderCharacteristics> characs = new ArrayList<>();
		SapOrderCharacteristics charac = null;
		// Test data 2019/10/23
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F105");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F106");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F107");
		charac.setAtwrt("4");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F108");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F109");
		charac.setAtwrt("4");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F110");
		charac.setAtwrt("4");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F221");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F101");
		charac.setAtwrt("1");
		characs.add(charac);
		sapCreationOrder.setItZcharc(characs);
		
		List<SapOrderPrice> prices = new ArrayList<SapOrderPrice>();
		SapOrderPrice price = null;
		price = new SapOrderPrice();
		price.setPosnr(10);
		price.setKschl("ZH05");
		price.setKbetr(new BigDecimal("2"));
		prices.add(price);
		sapCreationOrder.setItZcond(prices);
		
		List<SapOrderPlan> plans = new ArrayList<SapOrderPlan>();
//		SapOrderPlan plan = null;
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q001");
//		plan.setFkdat("20191010");
//		plan.setFakwr(BigDecimal.valueOf(3));
//		plan.setZterm("Q005");
//		plans.add(plan);
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q002");
//		plan.setFkdat("20191110");
//		plan.setFakwr(BigDecimal.valueOf(4));
//		plan.setZterm("Q010");
//		plans.add(plan);
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q003");
//		plan.setFkdat("20191209");
//		plan.setFakwr(BigDecimal.valueOf(5));
//		plan.setZterm("Q015");
//		plans.add(plan);
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q004");
//		plan.setFkdat("20200109");
//		plan.setFakwr(BigDecimal.valueOf(6));
//		plan.setZterm("Q020");
//		plans.add(plan);
		sapCreationOrder.setItZplan(plans);
		
		return sapCreationOrder;
	}
	
	@Test
	void testFindOrdersObject() throws Exception {
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
		query.setOrderId(1);
		query.setId(1);
		query.setOrderType("ZH01");
		query.setSalesCode("1");
		query.setSalesName("1");
		query.setSequenceNumber("1");
		query.setSpecialDiscount("1");
		query.setVersion("1");
		query.setVersionId("1");
		PageInfo<OrderDto> result = orderService.findOrders(query);
		System.out.println(result);
		
		List<OrderDto> orders = orderInfoMapper.findOrderViewByParams(query);
		System.out.println(orders);
	}
	
	@Test
	void testCalcGrossProfitObject() throws Exception {
		OrderQuery query = new OrderQuery();
		query.setSequenceNumber("123");
		query.setVersion("1-1");
		query.setIncludeDetail(true);
		PageInfo<OrderDto> result = orderService.findOrders(query);
		OrderDto order = result.getList().get(0);
		List<MaterialGroups> groups = orderService.calculateGrossProfit(order);
		System.out.println(groups);
	}

}
