package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.Role;
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
@Api(value = "Role", description = "role info")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@ResponseBody
	@ApiOperation(value=" looking for role info by id", notes="looking for role info by id")
	@GetMapping(value = "/roleList/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getRoles(@PathVariable("id") String strid) throws Exception
    {	
		List<Role> list = new ArrayList<Role>();
		int id = Integer.valueOf(strid);
		if(id==0) {
			list = roleService.getRoleList();
		}else {
			list.add(roleService.getRole(id));
		}
		return list;
    }
	
	
	@ApiOperation(value=" add role", notes="add role")
	@RequestMapping(value = "/addRole")
    @ResponseStatus(HttpStatus.OK)
    public Object addRole(HttpServletRequest request) throws Exception
    {	
		Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
		Role role = new Role();
		role.setId(id);
		role.setName(name);
		return roleService.createOrUpdateRole(role);
		
    }

}
