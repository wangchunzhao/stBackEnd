package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.Operations;
import com.qhc.frye.domain.Operations;
import com.qhc.frye.service.OperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("operations")
@Api(value = "Operations", description = "权限信息")
public class OperationController {
	
	@Autowired
	private OperationService operationService;
	
	@ApiOperation(value=" 查询所有权限信息", notes="查询所有权限信息")
	@GetMapping(value = "/findAll")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<Operations> findAll() throws Exception
    {	
		return operationService.findAll();
    }
	
	@ApiOperation(value=" 根据id查询权限信息", notes="根据id查询权限信息")
	@GetMapping(value = "/findById")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Operations findById(@PathVariable("id") Integer id) throws Exception
    {	
		return operationService.findById(id);
    }
	
	@ApiOperation(value="新增权限", notes="新增权限")
	@PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Operations add(@RequestBody(required=true) @Valid Operations Operations) throws Exception
    {	
		return operationService.createOrUpdateOperations(Operations);
		
    }
	

}
