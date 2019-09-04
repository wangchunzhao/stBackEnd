package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.User;
import com.qhc.frye.service.ApplicationOfRolechangeService;
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
@Api(value = "User", description = "User info")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired 
	private ApplicationOfRolechangeService appService;
	
	@ApiOperation(value=" Find all user info ", notes="Find all user info")
	@GetMapping(value="/paging")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findAll() throws Exception
    {	
		return userService.findAll();
    }
	
	@ApiOperation(value=" Find user by multiple conditions", notes="Find user by multiple conditions")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByMultipleConditions(@RequestParam("userIdentity") String userIdentity,@RequestParam("rolesName") String rolesName,@RequestParam("userMail") String userMail,@RequestParam("isActive") String isActive) throws Exception
    {	
		List<User> list =  userService.findByMultipleConditions(Integer.valueOf(isActive),userIdentity,userMail,"");
		List<User> newList = new ArrayList<User>();
		if(rolesName!=null) {
			if(list!=null&&list.size()>0) {
				for(User user : list) {
					boolean flag = true;
					Set<ApplicationOfRolechange> apps = user.getApps();
					if(!apps.isEmpty()) {
						for(ApplicationOfRolechange app:apps) {
							if(app.getAttachedCode().equals(rolesName)) {
								flag = true;
							}
						}
					}
					if(flag) {
						newList.add(user);
					}
				}
			}
		}else {
			newList = list;
		}
		return newList;
    }
	
	
	@ApiOperation(value=" Find user by UserIdentity", notes="Find user by UserIdentity")
	@GetMapping(value = "/{userIdentity}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUserIdentity(@PathVariable String userIdentity) throws Exception{	
		
		return userService.findByUserIdentity(userIdentity);
    }
	
	@ApiOperation(value="Add user", notes="Add user")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@Transactional
    public User add(@RequestBody(required=true) User user) throws Exception
    {	
		//得到application信息
		List<ApplicationOfRolechange> apps = appService.findByBUsersId(user.getId());
		//得到角色id
		String rolesName = user.getRolesName();
		int roleId = Integer.valueOf(rolesName);
		ApplicationOfRolechange applicationOfRolechange = new ApplicationOfRolechange();
		boolean flag =false;
		Set<ApplicationOfRolechange> set = new HashSet<ApplicationOfRolechange>();
		if(apps!=null&&apps.size()>0) {
			applicationOfRolechange = apps.get(0);
			for(ApplicationOfRolechange app :apps) {
				set.add(applicationOfRolechange);
				int busersId = app.getbRolesId();
				if(busersId==roleId) {
					flag = true;
				}
			}
			if(!flag) {
				user = userService.createOrUpdateUser(user);
				applicationOfRolechange.setbRolesId(roleId);
				ApplicationOfRolechange ap =appService.add(applicationOfRolechange);
				set.add(ap);
				user.setApps(set);
			}else {
				user = userService.createOrUpdateUser(user);
			}
		}
		
		return user;
    }
	
	@ApiOperation(value="Update user", notes="Update user")
	@PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String update(@RequestBody User user) throws Exception
    {	
		User u = userService.notAvailable(user.getId());
		if(u!=null&&u.getIsActive()==1) {
			return "success";
		}else {
			return "false";
		}
    }
	
	@ApiOperation(value="Update user isActive status", notes="Update user isActive status")
	@PutMapping(value="/isActive")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@RequestBody User user) throws Exception
    {	
		User u = userService.notAvailable(user.getId());
		if(u!=null&&u.getIsActive()==1) {
			return "success";
		}else {
			return "false";
		}
    }

}
