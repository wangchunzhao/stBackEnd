/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
@Api(value = "Location Management in Frye", description = "Location管理")
public class LocationController {
	
	@Autowired
	private LocationService localService;

	@ApiOperation(value = "update sales groups with sales offices to DB.")
	@PutMapping(value = "salesOffice", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void uploadSalesOffice(@RequestBody(required = true) @Valid List<SalesGroup> salesGroups) throws Exception {
		localService.clean();
		localService.put(salesGroups);
	}
	
	@ApiOperation(value = "获取目的地计算运费")
	@GetMapping(value = "destination", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void getDestination() throws Exception {
		
	}

}
