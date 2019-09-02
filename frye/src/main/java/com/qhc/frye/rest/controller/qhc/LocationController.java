/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Material;
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
	@PostMapping(value = "uploadSalesOffice", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void uploadSalesOffice(@RequestBody(required = true) @Valid List<SalesGroup> groups) throws Exception {
			for(SalesGroup sg :groups) {
				System.out.println(sg.getCode());
			}
	}

}
