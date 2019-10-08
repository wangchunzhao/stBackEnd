package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.service.SapSalesOfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "SapSalesOffice", description = "大区")
public class SapSalesOfficeController {
	
	@Autowired
	private SapSalesOfficeService sapSalesOfficeService;
	
	@ApiOperation(value="查询所有大区", notes="查询所有大区")
	@GetMapping(value="sapSalesOffice")
    @ResponseStatus(HttpStatus.OK)
	public List<SapSalesOffice> findAll() throws Exception{
		return sapSalesOfficeService.findAll();
    }
}
