package com.qhc.frye.rest.controller.qhc;

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
@Api(value = "User", description = "用户信息user info")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value=" 查询所有用户信息", notes="查询所有用户信息")
	@GetMapping(value = "/findAll")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<User> findAll() throws Exception
    {	
		return userService.findAll();
    }
	
	@ApiOperation(value=" 根据id查询用户信息", notes="根据id查询用户信息")
	@GetMapping(value = "/findById")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Object findById(@PathVariable("id") Integer id) throws Exception
    {	
		return userService.findById(id);
    }
	
	@ApiOperation(value="新增用户", notes="新增用户")
	@PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public User add(@RequestBody(required=true) @Valid User user) throws Exception
    {	
		return userService.createOrUpdateUser(user);
		
    }

}
