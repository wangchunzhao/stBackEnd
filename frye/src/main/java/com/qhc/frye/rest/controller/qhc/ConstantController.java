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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.DCustomer;
import com.qhc.frye.rest.controller.entity.Customer;
import com.qhc.frye.service.ConstantService;

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
	public Map<String,String> putCustomers() throws Exception {
		
		Map<String,String> ccMap =  constService.findAllCustomerClazz();
		return ccMap;
	}

}
