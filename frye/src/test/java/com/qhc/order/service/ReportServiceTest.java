package com.qhc.order.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReportServiceTest {
	
	@Autowired
	ReportService reportService;

	@Test
	void testFindPurchaseSalesReport() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list = reportService.findPurchaseSalesReport(params);
		System.out.println(list);
	}

	@Test
	void testFindBiddingReport() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list = reportService.findBiddingReport(params);
		System.out.println(list);
	}

	@Test
	void testFindOrderSummaryReport() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list = reportService.findOrderSummaryReport(params);
		System.out.println(list);
	}

}
