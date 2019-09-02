package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.Role;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.service.SapSalesOfficeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("sapSalesOffice")
@Api(value = "SapSalesOffice", description = "SapSalesOffice info")
public class SapSalesOfficeController {

	@Autowired
	private SapSalesOfficeService sapSalesOfficeService;
	
	@ApiOperation(value=" Find all sapSalesOfficeService info", notes="Find all sapSalesOfficeService info")
	@RequestMapping(value = "/findAll")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SapSalesOffice> findAll() throws Exception{
		return sapSalesOfficeService.findAll();
    }
	
}
