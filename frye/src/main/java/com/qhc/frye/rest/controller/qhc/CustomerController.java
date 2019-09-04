/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Customer;
import com.qhc.frye.rest.controller.entity.SalesGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Customer Management in Frye")
public class CustomerController {
	
	@ApiOperation(value = "update customers data to DB.")
	@PutMapping(value = "customer", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void uploadSalesOffice(@RequestBody(required = true) @Valid List<Customer> customers) throws Exception {
		System.out.println("customer");
	}

}
