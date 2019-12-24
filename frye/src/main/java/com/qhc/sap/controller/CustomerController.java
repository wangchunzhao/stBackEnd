/**
 * 
 */
package com.qhc.sap.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.service.ConstantService;
import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.entity.Customer;
import com.qhc.sap.service.CustomerService;
import com.qhc.system.domain.PageHelper;
import com.qhc.system.domain.Result;
import com.qhc.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController

@Api(value = "Customer Management in Frye", description = "客户管理")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ConstantService constService;
	
	@ApiOperation(value = "获取客户lastUpdateDate")
	@GetMapping(value = "customer/lastUpdateDate")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String getLastUpdatedDate() throws Exception {
		Date date = customerService.getLastUpdated(CustomerDto.CODE_CUSTOMER);
		return DateUtil.convert2String(date, "yyyyMMddHHmmss");
	}
	
	@ApiOperation(value = "新增客户信息")
	@PostMapping(value = "customer")
	@ResponseStatus(HttpStatus.OK)
	public void putCustomers(@RequestBody(required = true) @Valid List<CustomerDto> customers) throws Exception {
		
		customerService.saveCustomers(customers);
	}
	@ApiOperation(value = "根据名称查询客户信息")
	@GetMapping(value = "customer/{clazzCode},{name},{pageNo},{pageSize}", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public Result findCustomers(@PathVariable String name,@PathVariable String clazzCode,@PathVariable int pageNo,@PathVariable int pageSize) throws Exception {
		Result result = null;
		try {
			PageInfo<CustomerDto> dcs=customerService.searchCustomers(clazzCode,name,pageNo,pageSize);
			PageHelper<CustomerDto> ph = new PageHelper<CustomerDto>(dcs);
			result = Result.ok(ph);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}
	
	

}
