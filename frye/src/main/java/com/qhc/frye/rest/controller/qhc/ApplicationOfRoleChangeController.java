package com.qhc.frye.rest.controller.qhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.User;
import com.qhc.frye.service.ApplicationOfRolechangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("applicationOfRolechange")
@Api(value = "ApplicationOfRolechange", description = "ApplicationOfRolechange info")
public class ApplicationOfRoleChangeController {

	@Autowired
	private ApplicationOfRolechangeService applicationOfRolechangeService;
	
	@ApiOperation(value="Add applicationOfRolechange", notes="Add applicationOfRolechange")
	@PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public ApplicationOfRolechange add(@RequestBody(required=true) ApplicationOfRolechange applicationOfRolechange) throws Exception
    {	
		return applicationOfRolechangeService.add(applicationOfRolechange);
		
    }
	
	
	
}
