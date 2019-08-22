/**
 * 
 */
package com.qhc.frye.rest.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.User;
import com.qhc.frye.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 *
*@RestController
*@RequestMapping("/rws/User")
*#@Api(value = "UserManagerController", description = "User Management")
**/
public class UserRWS {
	
//	@Autowired
//    private UserService userService;
//
//	@ApiOperation(value="get all users")
//    @GetMapping(value = "/users")
//    @ResponseStatus(HttpStatus.OK)
//    public List<User> getUserList()
//    {
//
//        return userService.getUserList();
//    }
//	
//	@ApiOperation(value="get user by mail", notes="get user by mail")
//    @GetMapping(value = "/user/{mail}")
//    @ResponseStatus(HttpStatus.OK)
//    public Object getUser(@PathVariable("mail") String mail) throws Exception
//    {	
//		
//		return userService.getUser(mail);
//    }
	

}
