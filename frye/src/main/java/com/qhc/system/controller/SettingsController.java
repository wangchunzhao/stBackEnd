package com.qhc.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.system.entity.Settings;
import com.qhc.system.service.SettingsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@Api(value = "ParameterSettings ", description = "系统参数管理")
public class SettingsController {

	@Autowired
	private SettingsService settingService;

	@ApiOperation(value = "查询所有系统参数 ", notes = "查询所有系统参数")
	@GetMapping(value = "parameterSettings")
	@ResponseStatus(HttpStatus.OK)
	public List<Settings> findAll() throws Exception {
		return settingService.findDistinctInfo();
	}

	@ApiOperation(value = "查询所有系统参数 ", notes = "查询所有系统参数")
	@GetMapping(value = "parameterSettings/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Settings findById(@PathVariable Integer id) throws Exception {
		return settingService.findById(id);
	}

	@ApiOperation(value = "新增修改参数", notes = "新增修改参数")
	@PostMapping(value = "parameterSettings")
	@ResponseStatus(HttpStatus.OK)
	public Settings update(@RequestBody Settings p) {
		return settingService.updateParameter(p);
	}
}
