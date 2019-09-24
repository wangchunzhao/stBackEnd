package com.qhc.frye.rest.controller.qhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.BCityFreight;
import com.qhc.frye.domain.BProvince;
import com.qhc.frye.service.BProvinceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("bProvince")
@Api(value = "BProvince", description = "BProvince info")
public class BProvinceController {
	
	@Autowired
	private BProvinceService bProvinceService;
	
	@ApiOperation(value="Add bProvince", notes="Add bProvince")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BProvince add(@RequestBody(required=true) BProvince bProvince) throws Exception
    {	
		return bProvinceService.add(bProvince);
    }

}
