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
import com.qhc.frye.domain.User;
import com.qhc.frye.rest.controller.entity.Order;
import com.qhc.frye.service.RoleService;
import com.qhc.frye.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("user")
@Api(value = "User", description = "user info")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@ApiOperation(value=" looking for user info by id", notes="looking for user info by id")
	@GetMapping(value = "/userList/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getUsers(@PathVariable("id") String strid) throws Exception
    {	
		List<User> list = new ArrayList<User>();
		int id = Integer.valueOf(strid);
		if(id==0) {
			list = userService.getUserList();
		}else {
			list.add(userService.getUser(id));
		}
		return list;
    }
	
	
	@ApiOperation(value=" add user", notes="add user")
	@RequestMapping(value = "/addUser")
    @ResponseStatus(HttpStatus.OK)
    public Object addUser(@RequestBody(required=true) @Valid User user) throws Exception
    {	
		return userService.createOrUpdateRole(user);
		
    }
	
	@ApiOperation(value=" add user", notes="add user")
	@RequestMapping(value = "/editUser")
    @ResponseStatus(HttpStatus.OK)
    public Object editUser(@RequestBody(required=true) @Valid User user) throws Exception
    {	
		return userService.createOrUpdateRole(user);
		
    }

}
