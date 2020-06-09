/**
 * 
 */
package com.qhc.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.order.service.ConstantService;
import com.qhc.sap.entity.IndustryCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "constant data")
@RequestMapping("constant")
public class ConstantController {
	@Autowired
	private ConstantService constService;

	@ApiOperation(value = "列出所有客户的级别信息")
	@GetMapping(value = "customerClazz")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getCustomerClazz() throws Exception {

		Map<String, String> ccMap = constService.findAllCustomerClazz();
		return ccMap;
	}

	@ApiOperation(value = "列出所有订单类型代码")
	@GetMapping(value = "orderType")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getOrderTypes() throws Exception {

		Map<String, String> ccMap = constService.getOrderTypes();
		return ccMap;
	}

	@ApiOperation(value = "列出所有For Dealer的客户分类")
	@GetMapping(value = "dealerIndustryCode")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getFordealerIndustryCodes() throws Exception {
		Map<String, String> map = constService.findFordealerIndustryCodes();
		return map;
	}

	@ApiOperation(value = "列出所有客户分类")
	@GetMapping(value = "industryCode")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getIndustryCodes() throws Exception {
		Map<String, String> map = constService.findIndustryCodes();
		return map;
	}

	@ApiOperation(value = "列出所有大客户")
	@GetMapping(value = "industry")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getIndustrys() throws Exception {
		Map<String, String> map = constService.findIndustrys();
		return map;
	}

	@ApiOperation(value = "列出所有销售类型")
	@GetMapping(value = "saleType")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getSaleTypes() throws Exception {
		Map<String, String> map = constService.findSaleTypes();
		return map;
	}

	@ApiOperation(value = "列出所有大区")
	@GetMapping(value = "office")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getOffices() throws Exception {
		Map<String, String> map = constService.findOffices();
		return map;
	}

}
