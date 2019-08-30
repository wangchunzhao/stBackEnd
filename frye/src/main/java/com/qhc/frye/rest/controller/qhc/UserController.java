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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.User;
import com.qhc.frye.service.ApplicationOfRolechangeService;
import com.qhc.frye.service.SapSalesOfficeService;
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
@Api(value = "User", description = "用户信息user info")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value=" 查询所有用户信息", notes="查询所有用户信息")
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
	
	@ApiOperation(value=" 根据id查询用户信息", notes="根据id查询用户信息")
	@GetMapping(value = "/findById")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public User findById(@RequestParam("id") Integer id) throws Exception
    {	
		return userService.findById(id);
    }
	
	@ApiOperation(value=" 根据UserIdentity查询角色信息", notes="根据UserIdentity查询角色信息")
	@GetMapping(value = "/findByUserIdentity/{userIdentity}")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public User findByUserName(@PathVariable("userIdentity") String userIdentity) throws Exception
    {	
		return userService.findByUserName(userIdentity);
    }
	
	@ApiOperation(value="新增用户", notes="新增用户")
	@PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public User add(@RequestBody(required=true) @Valid User user) throws Exception
    {	
		return userService.createOrUpdateUser(user);
		
    }
	
	@ApiOperation(value="删除用户", notes="删除用户")
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
