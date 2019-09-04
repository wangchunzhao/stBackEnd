/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Customer;
import com.qhc.frye.rest.controller.entity.SalesGroup;
import com.qhc.frye.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@RequestMapping("customer")
@Api(value = "Customer Management in Frye")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@ApiOperation(value = "update customers data to DB.")
	@GetMapping(value = "lastUpdateDate")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Date getLastUpdatedDate() throws Exception {
		Date date = customerService.getLastUpdated(Customer.CODE_CUSTOMER);
		System.out.println(date.toString());
		return date;
	}
	
	

}
