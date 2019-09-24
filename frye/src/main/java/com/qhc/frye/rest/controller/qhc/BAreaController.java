package com.qhc.frye.rest.controller.qhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.BCity;
import com.qhc.frye.service.BAreaService;
import com.qhc.frye.service.BCityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("bArea")
@Api(value = "BArea", description = "BArea info")
public class BAreaController {
	
	@Autowired
	private BAreaService bAreaService;
	
	@ApiOperation(value="Add bCity", notes="Add bCity")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BArea add(@RequestBody(required=true) BArea bArea) throws Exception
    {	
		return bAreaService.add(bArea);
    }

}
