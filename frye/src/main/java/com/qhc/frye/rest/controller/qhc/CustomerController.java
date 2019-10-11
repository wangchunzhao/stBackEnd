/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.rest.controller.entity.Customer;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.service.ConstantService;
import com.qhc.frye.service.CustomerService;

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
/*	
	@ApiOperation(value = "获取客户lastUpdateDate")
	@PutMapping(value = "customer/lastUpdateDate")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Date getLastUpdatedDate() throws Exception {
		Date date = customerService.getLastUpdated(Customer.CODE_CUSTOMER);
		return date;
	}*/
	
	@ApiOperation(value = "新增客户信息")
	@PostMapping(value = "customer")
	@ResponseStatus(HttpStatus.OK)
	public void putCustomers(@RequestBody(required = true) @Valid List<Customer> customers) throws Exception {
		
		customerService.saveCustomers(customers);
	}
	@ApiOperation(value = "根据名称查询客户信息")
	@GetMapping(value = "customer/{clazzCode},{name},{pageNo}", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public PageHelper<DCustomer> findCustomers(@PathVariable(required = true) String name,@PathVariable String clazzCode,@PathVariable int pageNo) throws Exception {
		
		Page<DCustomer> dcs=customerService.searchCustomers(clazzCode,name,pageNo);
		PageHelper ph = new PageHelper(dcs);
		return ph;
	}
	
	

}
