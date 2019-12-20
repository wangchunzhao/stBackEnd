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
	public Map<String,String> getCustomerClazz() throws Exception {
		
		Map<String,String> ccMap =  constService.findAllCustomerClazz();
		return ccMap;
	}
	
		
	@ApiOperation(value = "列出所有订单类型代码")
	@GetMapping(value = "orderType")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> getOrderTypes() throws Exception {
		
		Map<String,String> ccMap =  constService.getOrderTypes();
		return ccMap;
	}
	
		
	@ApiOperation(value = "列出所有For Dealer的终端客户性质")
	@GetMapping(value = "dealerIndustryCode")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> getFordealerIndustryCodes() throws Exception {
		Map<String,String> map =  constService.findFordealerIndustryCodes();
		return map;
	}
	

}
