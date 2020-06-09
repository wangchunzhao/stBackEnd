package com.qhc.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.order.service.ReportService;
import com.qhc.system.domain.PageHelper;
import com.qhc.system.domain.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author lizuoshan
 *
 */
@RestController
@Api(value = "Report", description = "报表管理")
@RequestMapping("report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;

	@ApiOperation(value = "投标跟踪表")
	@GetMapping(value = "bidding")
	@ResponseStatus(HttpStatus.OK)
	public Result getBiddingReport(@RequestParam(required = false) Map<String, Object> params) throws Exception {
		Result result = null;
		try {
			PageHelper<Map<String, Object>> page = new PageHelper<>(reportService.findBiddingReport(params));
			result = Result.ok(page);
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "购销明细报表")
	@GetMapping(value = "orderdetail")
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, Object>> getOrderReport(@RequestParam(required = false) Map<String, Object> params) throws Exception {
		return reportService.findPurchaseSalesReport(params);
	}

	@ApiOperation(value = "订单汇总报表，按订单查询")
	@GetMapping(value = "ordersummary")
	@ResponseStatus(HttpStatus.OK)
	public List<Map<String, Object>> getOrderSummaryReport(@RequestParam(required = false) Map<String, Object> params) throws Exception {
		return reportService.findOrderSummaryReport(params);
	}
	

}
