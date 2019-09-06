package com.qhc.frye.rest.controller.qhc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.Operations;
import com.qhc.frye.domain.User;
import com.qhc.frye.service.ApplicationOfRolechangeService;
import com.qhc.frye.service.OperationService;
import com.qhc.frye.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@Api(value = "Operations", description = "Operations info")
public class OperationController {
	
	@Autowired
	private OperationService operationService;
	@Autowired 
	private ApplicationOfRolechangeService appService;
	@Autowired 
	private UserService userService;
	
	@ApiOperation(value="Query operations", notes="Query operations")
	@GetMapping(value = "/operations")
    @ResponseStatus(HttpStatus.OK)
    public List<Operations> findAll() throws Exception
    {	
		return operationService.findAll();
    }
	
	@ApiOperation(value=" Query operations by id", notes="Query operations by id")
	@GetMapping(value = "/operations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Operations findById(@PathVariable("id") String id) throws Exception
    {	
		return operationService.findById(id);
    }
	
	@ApiOperation(value=" Query operations by user id", notes="Query operations by user id")
	@GetMapping(value = "/operations/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User findById(@PathVariable("id") int id) throws Exception
	{	
		//得到application信息
			List<ApplicationOfRolechange> apps = appService.findByBUsersId(id);
			
			User user = userService.findById(id);
			boolean flag =false;
			Set<ApplicationOfRolechange> set = new HashSet<ApplicationOfRolechange>();
			if(apps!=null&&apps.size()>0) {
				for(ApplicationOfRolechange app :apps) {
					set.add(app);
				}
				
			}
			user.setApps(set);
			return user;
	}

	

}
