package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.Role;
import com.qhc.frye.rest.controller.entity.Order;
import com.qhc.frye.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("role")
@Api(value = "Role", description = "角色信息")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value=" 查询所有用户信息", notes="查询所有用户信息")
	@GetMapping(value = "/findAll")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<Role> findAll() throws Exception
    {	
		return roleService.getRoleList();
    }
	
	@ApiOperation(value=" 根据id查询角色信息", notes="根据id查询角色信息")
	@GetMapping(value = "/findById")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Object findById(@PathVariable("id") Integer id) throws Exception
    {	
		return roleService.getRole(id);
    }
	
	@ApiOperation(value="新增角色", notes="新增角色")
	@PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Role add(@RequestBody(required=true) @Valid Role role) throws Exception
    {	
		return roleService.createOrUpdateRole(role);
		
    }
	
	@ApiOperation(value="修改角色", notes="修改角色")
	@PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Role update(@RequestBody(required=true) Role role) throws Exception
    {	
		return roleService.createOrUpdateRole(role);
		
    }

}
