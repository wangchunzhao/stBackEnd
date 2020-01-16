package com.qhc.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.qhc.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qhc.system.entity.Operation;
import com.qhc.system.entity.Role;
import com.qhc.system.entity.User;
import com.qhc.system.service.OperationService;
import com.qhc.system.service.RelationService;
import com.qhc.system.service.RoleService;
import com.qhc.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lizuoshan
 */
@RestController
@Api(value = "User", description = "用户管理")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private RoleService roleService;

	@ApiOperation(value = "带条件分页查询用户", notes = "带条件分页查询用户")
	@GetMapping(value = "user/{pageNo}/{pageSize}")
	@ResponseStatus(HttpStatus.OK)
	public RestPage findAll(@PathVariable int pageNo, @PathVariable int pageSize,
			@RequestParam("isActive") int isActive, @RequestParam("userIdentity") String userIdentity,
			@RequestParam("userMail") String userMail) throws Exception {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		User user = new User();
		user.setIsActive(isActive);
		user.setUserIdentity(userIdentity);
		user.setUserMail(userMail);
		PageInfo<User> page = userService.getByConditions(user, pageable);
		return new RestPage(page);
	}

	@ApiOperation(value = "根据域账号查询用户", notes = "根据域账号查询用户")
	@GetMapping(value = "user/{userIdentity}")
	@ResponseStatus(HttpStatus.OK)
	public UserDto findByUserIdentity(@PathVariable String userIdentity) throws Exception {
		UserDto user = userService.findByUserIdentity(userIdentity);
		return user;
	}

	@ApiOperation(value = "新增用户", notes = "新增用户")
	@PostMapping(value = "user")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public UserDto add(@RequestBody(required = true) UserDto user) throws Exception {
		user.setName(user.getUserName());
		user = userService.createOrUpdateUser(user);
		return user;
	}

	@ApiOperation(value = "查询所有权限", notes = "查询所有权限")
	@GetMapping(value = "operations")
	@ResponseStatus(HttpStatus.OK)
	public List<Operation> findAll() throws Exception {
		return operationService.findAll();
	}

	@ApiOperation(value = " 根据identity查询权限", notes = "根据identity查询权限")
	@GetMapping(value = "operations/{user}")
	@ResponseStatus(HttpStatus.OK)
	public List<OperationDto> findById(@PathVariable("user") String user) throws Exception {
		return operationService.findByUser(user);
	}

	@ApiOperation(value = "根据域账号查询视图", notes = "根据域账号查询视图")
	@GetMapping(value = "userOperationInfo/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<UserOperationInfo> findByUserId(@PathVariable("userId") Integer userId) throws Exception {
		UserDto user = this.userService.findById(userId);
		List<OperationDto> operations = this.operationService.findByUser(user.getUserIdentity());
		
		List<UserOperationInfo> list = new ArrayList<UserOperationInfo>();
		for (OperationDto operation : operations) {
			UserOperationInfo opInfo = new UserOperationInfo();
			opInfo.setAttachedCode(null);
			opInfo.setAttachedName(null);
			opInfo.setOperationId(operation.getId());
			opInfo.setOperationName(operation.getName());
			opInfo.setRoleId(null);
			opInfo.setRoleName(null);
			opInfo.setUserId(user.getId());
			opInfo.setUserIdentity(user.getUserIdentity());
			opInfo.setUserIsActive(user.getIsActive());
			opInfo.setUserMail(user.getUserMail());
			
			list.add(opInfo);
		}
		
		return list;
	}

	@ApiOperation(value = "带条件分页查询角色", notes = "带条件分页查询角色")
	@GetMapping(value = "role/{pageNo}/{pageSize}")
	@ResponseStatus(HttpStatus.OK)
	public RestPage<Role> findPagingList(@PathVariable int pageNo, @PathVariable int pageSize,
			@RequestParam("isActive") int isActive) throws Exception {

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Role role = new Role();
		PageInfo<Role> page = roleService.getByConditions(role, pageable);
		return new RestPage(page);
	}

	@ApiOperation(value = " Find all role info", notes = "Find all role info")
	@GetMapping(value = "role")
	@ResponseStatus(HttpStatus.OK)
	public List<RoleDto> findAll(@RequestParam Integer isActive) throws Exception {
		List<RoleDto> list = roleService.findAll();
		return list;
	}

	@ApiOperation(value = "根据id查找角色", notes = "根据id查找角色")
	@GetMapping(value = "role/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Object findById(@PathVariable("id") Integer id) throws Exception {
		return roleService.findById(id);
	}

	/**
	 * 根据角色名查找角色
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据角色名查找角色", notes = "根据角色名查找角色")
	@GetMapping(value = "role/searchByName/{name}")
	@ResponseStatus(HttpStatus.OK)
	public Object findByName(@PathVariable String name) throws Exception {
		List<Role> list = new ArrayList<>();
		if (roleService.findByName(name) != null) {
			list.add(roleService.findByName(name));
		}
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		return new RestPage(pageInfo);
	}

	@ApiOperation(value="Create role", notes="Create role")
	@PostMapping(value = "role")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto addRole(@RequestBody(required=true) RoleDto role) throws Exception
    {
		return roleService.createOrUpdateRole(role);
    }

	@ApiOperation(value = "根据Role对象更新角色 ", notes = "根据Role对象更新角色")
	@PutMapping(value = "role")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public RoleDto updateRoleOperations(@RequestBody(required = true) @Valid RoleDto role) throws Exception {
		roleService.createOrUpdateRole(role);
		relationService.saveRelation(role);
		return role;
	}

	@ApiOperation(value = "查询用户所有菜单", notes = "查询用户所有菜单")
	@GetMapping(value = "menus/{userIdentity}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,MenusDto> queryAllAserMenus(@PathVariable String userIdentity) throws Exception {
		Map<String,MenusDto> nap = operationService.findMenus(userIdentity);
		return nap;
	}

	@ApiOperation(value = "根据id删除角色", notes = "根据id删除角色")
	@PostMapping(value = "deleteRole")
	@ResponseStatus(HttpStatus.OK)
	public Role deleteRole(@RequestBody(required=true) Role role) throws Exception {
		return roleService.deleteRole(role);
	}
}
