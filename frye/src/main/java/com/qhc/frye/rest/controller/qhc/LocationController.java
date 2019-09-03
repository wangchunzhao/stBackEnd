/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.rest.controller.entity.SalesGroup;
import com.qhc.frye.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@RequestMapping("location")
@Api(value = "Location Management in Frye")
public class LocationController {
	@Autowired
	private LocationService localService;

	@ApiOperation(value = "update a sales group with sales office to DB.")
	@PutMapping(value = "salesOffices", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void uploadSalesOffice(@RequestBody(required = true) @Valid List<SalesGroup> salesGroups) throws Exception {
		localService.clean();
		localService.put(salesGroups);
	}

	@ApiOperation(value = "test")
	@GetMapping(value = "test")
	@ResponseStatus(HttpStatus.OK)
	public void getup(@RequestParam("test") String test) throws Exception {

		System.out.println(test);

	}
}
