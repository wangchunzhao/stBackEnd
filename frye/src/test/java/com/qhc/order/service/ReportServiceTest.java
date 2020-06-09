package com.qhc.order.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.pagehelper.PageInfo;

@SpringBootTest
class ReportServiceTest {
	
	@Autowired
	ReportService reportService;

	@Test
	void testFindPurchaseSalesReport() throws JsonMappingException, JsonProcessingException {
		Map<String, Object> params = new HashMap<String, Object>();
		PageInfo<Map<String, Object>> list = reportService.findPurchaseSalesReport(params);
		System.out.println(list);
	}

	@Test
	void testFindBiddingReport() throws JsonMappingException, JsonProcessingException {
		Map<String, Object> params = new HashMap<String, Object>();
		PageInfo<Map<String, Object>> list = reportService.findBiddingReport(params);
		System.out.println(list);
	}

	@Test
	void testFindOrderSummaryReport() throws JsonMappingException, JsonProcessingException {
		Map<String, Object> params = new HashMap<String, Object>();
		PageInfo<Map<String, Object>> list = reportService.findOrderSummaryReport(params);
		System.out.println(list);
	}

}
