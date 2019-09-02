package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.User;
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
	
	@ApiOperation(value=" Find all user info ", notes="Find all user info")
	@RequestMapping(value = "/findAll")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<User> findAll(@RequestParam("userIdentity") String userIdentity,@RequestParam("userMail") String userMail,@RequestParam("isActive") String isActive) throws Exception
    {	
		List<User> list = new ArrayList<User>();
		if("flag".equals(userIdentity)) {
			if(!"".equals(userMail)&&null!=userMail) {
				if(isActive!=null) {
					if(!"2".equals(isActive)) {
						list = userService.findByUserMailAndIsActive("%"+userMail+"%",Integer.valueOf(isActive));
					}else {
						list = userService.findByUserMail("%"+userMail+"%");
					}
				}else {
					list = userService.findByUserMail("%"+userMail+"%");
				}
			}else{
				if(!"2".equals(isActive)) {
					list = userService.findByIsActive(Integer.valueOf(isActive));
				}else {
					list = userService.findAll();
				}
			}
		} else {
			list = userService.findAll();
		}
		
		/*
		if(list!=null&&list.size()>0){
			for(User user:list) {
				List<ApplicationOfRolechange> apps = applicationService.findByBUsersId(user.getId());
				String roles="";
				if(apps!=null&&apps.size()>0) {
					for(ApplicationOfRolechange app:apps) {
						roles = roles+app.getRole().getName()+",";
					}
				}
				if(!"".equals(roles)) {
					roles = roles.substring(0, roles.length()-1);
				}
				user.set
				user.setRegion(region);
			}
		}
		*/
		return list;
    }
	
	@ApiOperation(value=" Find user by multiple conditions", notes="Find user by multiple conditions")
	@RequestMapping(value = "/findByMultipleConditions")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<User> findByMultipleConditions(@RequestParam("userName") String userName,@RequestParam("rolesName") String rolesName,@RequestParam("userMail") String userMail,@RequestParam("isActive") String isActive) throws Exception
    {	
		List<User> list =  userService.findByMultipleConditions(Integer.valueOf(isActive),userName,userMail,"");
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
	
	@ApiOperation(value=" Find user by id", notes="Find user by id")
	@GetMapping(value = "/findById")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public User findById(@RequestParam("id") Integer id) throws Exception
    {	
		return userService.findById(id);
    }
	
	@ApiOperation(value=" Find user by UserIdentity", notes="Find user by UserIdentity")
	@GetMapping(value = "/findByUserIdentity")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public User findByUserIdentity(@RequestParam("userIdentity") String userIdentity) throws Exception
    {	
		return userService.findByUserIdentity(userIdentity);
    }
	
	@ApiOperation(value="Add user", notes="Add user")
	@PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public User add(@RequestBody(required=true) User user) throws Exception
    {	
		return userService.createOrUpdateUser(user);
		
    }
	
	@ApiOperation(value="Delete user", notes="Delete user")
	@RequestMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public String delete(@RequestParam("id") Integer id) throws Exception
    {	
		User u = userService.notAvailable(id);
		if(u!=null&&u.getIsActive()==1) {
			return "success";
		}else {
			return "false";
		}
    }

}
