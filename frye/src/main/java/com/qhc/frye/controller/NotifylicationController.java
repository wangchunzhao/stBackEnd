package com.qhc.frye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.entity.ApplicationOfRolechange;
import com.qhc.frye.entity.BNotifyInfor;
import com.qhc.frye.service.BNotifyInforService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("bNotifyInfor")
@Api(value = "BNotifyInfor", description = "邮件发送记录")
public class NotifylicationController {

	@Autowired
	private BNotifyInforService bNotifyInforService;
	
	@ApiOperation(value="新增邮件发送记录", notes="新增邮件发送记录")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BNotifyInfor add(@RequestBody(required=true) BNotifyInfor bNotifyInfor) throws Exception
    {	
		return bNotifyInforService.add(bNotifyInfor);
		
    }
	
	
	
	
	
}
