package com.qhc.system.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.sap.entity.SapSalesOffice;
import com.qhc.sap.service.SapSalesOfficeService;
import com.qhc.system.entity.UserRole;
import com.qhc.system.entity.RoleOperation;
import com.qhc.system.dao.UserOperationInfoRepository;
import com.qhc.system.domain.RestPage;
import com.qhc.system.entity.Operation;
import com.qhc.system.entity.Role;
import com.qhc.system.entity.User;
import com.qhc.system.entity.UserOperationView;
import com.qhc.system.service.UserRoleService;
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
	private UserRoleService appService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private SapSalesOfficeService officeService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService applicationOfRolechangeService;
	@Autowired
	private UserOperationInfoRepository userOperationInfoRepository;
	
	
	@ApiOperation(value="带条件分页查询用户", notes="带条件分页查询用户")
	@GetMapping(value="user/{pageNo}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
    public RestPage findAll(
    		@PathVariable int pageNo,
    		@PathVariable int pageSize,
    		@RequestParam("isActive") int isActive,
    		@RequestParam("userIdentity") String userIdentity,
    		@RequestParam("userMail") String userMail) throws Exception
    {	
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		User user = new User();
		user.setIsActive(isActive);
		user.setUserIdentity(userIdentity);
		user.setUserMail(userMail);
		Page<User> page = userService.getByConditions(user,pageable);
		return new RestPage(refushPageUser(page));
    }
	
/*	@ApiOperation(value=" Find user by multiple conditions", notes="Find user by multiple conditions")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByMultipleConditions(@RequestParam("userIdentity") String userIdentity,@RequestParam("userMail") String userMail,@RequestParam("isActive") String isActive) throws Exception
    {	
		List<User> users = userService.findByMultipleConditions(Integer.valueOf(isActive),userIdentity,userMail,"");
		List<User> userList = new ArrayList<User>();
		if(users!=null&&users.size()>0) {
			for(User u:users) {
				User user = findByUserIdentity(u.getUserIdentity());
				userList.add(user);
			}
		}
		return userList;
    }
	
*/	
	@ApiOperation(value="根据域账号查询用户", notes="根据域账号查询用户")
	@GetMapping(value = "user/{userIdentity}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUserIdentity(@PathVariable String userIdentity) throws Exception{	
		User user = userService.findByUserIdentity(userIdentity);
		if(user == null) {
			return null;
		}
		List<UserRole> appList = appService.findByBUsersId(user.getId());
		Set<Operation> operationSet = new HashSet<Operation>();
		List<Role> roleList = new ArrayList<Role>();
		String region ="";
		if(appList!=null&&appList.size()>0) {
			for(UserRole app:appList) {
				app.setCreateTime(null);
				app.setApprovalTime(null);
				region = app.getAttachedCode();
				Role role = roleService.findById(app.getbRolesId());
				roleList.add(role);
				List<RoleOperation> relations = relationService.findByRoleId(app.getbRolesId(), 1);
				for(RoleOperation r:relations) {
					String operationId = r.getOperationId();
					Operation op = operationService.findById(operationId);
					if(op!=null) {
						operationSet.add(op);
					}
				}
			}
			SapSalesOffice office = officeService.findByCode(region);
			user.setRegion(office);
			user.setRoles(roleList);
			user.setOperations(new ArrayList<Operation>(operationSet));
		}
		return user;
    }
	
	@ApiOperation(value="新增用户", notes="新增用户")
	@PostMapping(value="user")
    @ResponseStatus(HttpStatus.OK)
	@Transactional
    public User add(@RequestBody(required=true) User user) throws Exception
    {	
		appService.addOrUpdateApp(user);
		user = userService.createOrUpdateUser(user);
		return user;
    }
	
	@ApiOperation(value="新增用户角色关系", notes="新增用户角色关系")
	@PostMapping(value="applicationOfRolechange")
    @ResponseStatus(HttpStatus.OK)
	@Transactional
    public UserRole add(@RequestBody(required=true) UserRole applicationOfRolechange) throws Exception
    {	
		return applicationOfRolechangeService.add(applicationOfRolechange);
		
    }
	
	@ApiOperation(value="查询所有权限", notes="查询所有权限")
	@GetMapping(value = "operations")
    @ResponseStatus(HttpStatus.OK)
    public List<Operation> findAll() throws Exception
    {	
		return operationService.findAll();
    }
	
	@ApiOperation(value=" 根据id查询权限", notes="根据id查询权限")
	@GetMapping(value = "operations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Operation findById(@PathVariable("id") String id) throws Exception
    {	
		return operationService.findById(id);
    }
	
/*	
	@ApiOperation(value="Update user", notes="Update user")
	@PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user) throws Exception
    {	
		return userService.createOrUpdateUser(user);
    }
*/	
	public Page<User> refushPageUser( Page<User> pu) throws Exception {
		if(pu.getContent()!=null&&pu.getContent().size()>0) {
			for(User u :pu.getContent()) {
				u = this.findByUserIdentity(u.getUserIdentity());
			}
		}
		return pu;
	}
	
	@ApiOperation(value="查询整个视图", notes="查询整个视图")
	@GetMapping(value = "userOperationInfo")
    @ResponseStatus(HttpStatus.OK)
    public List<UserOperationView> findAllOperation() throws Exception
    {	
        return userOperationInfoRepository.findAll();
    }
	
	@ApiOperation(value="根据域账号查询视图", notes="根据域账号查询视图")
	@GetMapping(value="userOperationInfo/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserOperationView> findByUserId(@PathVariable("userId") Integer userId) throws Exception
    {	
		return userOperationInfoRepository.findByUserId(userId);
    }
	
	@ApiOperation(value="带条件分页查询角色", notes="带条件分页查询角色")
	@GetMapping(value="role/{pageNo}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
	public RestPage<Role> findPagingList(
			@PathVariable int pageNo,
			@PathVariable int pageSize,
			@RequestParam("isActive") int isActive) throws Exception{
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Role role = new Role();
		role.setIsActive(isActive);
		Page<Role> page = roleService.getByConditions(role,pageable);
        return new RestPage(page);
    }
	
	@ApiOperation(value=" Find all role info", notes="Find all role info")
	@GetMapping(value="role")
    @ResponseStatus(HttpStatus.OK)
	public List<Role> findAll(@RequestParam Integer isActive) throws Exception{
		
		List<Role> list = roleService.findAll();
		List<Role> result = new ArrayList<Role>();
		if(isActive==2) {
			result = list;
		}else {
			for(Role r :list) {
				Set<UserRole> ars = r.getApps();
				for(UserRole ar:ars) {
					ar.setApprovalTime(null);
					ar.setCreateTime(null);
				}
				if(isActive==r.getIsActive()) {
					result.add(r);
				}
			}
		}
		return result;
    }
	
	@ApiOperation(value="根据id查找角色", notes="根据id查找角色")
	@GetMapping(value = "role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object findById(@PathVariable("id") Integer id) throws Exception {	
		
		return roleService.findById(id);
    }
	
	
//	@ApiOperation(value="Create role", notes="Create role")
//	@PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Role update(@RequestBody(required=true) Role role) throws Exception
//    {	
//		return roleService.createOrUpdateRole(role);	
//    }
	
	
	@ApiOperation(value="根据Role对象更新角色 ", notes="根据Role对象更新角色")
	@PutMapping(value="role")
    @ResponseStatus(HttpStatus.OK)
	@Transactional
    public Role updateRoleOperations(@RequestBody(required=true)@Valid Role role) throws Exception
    {	
		if(role.getIsActive()==1) {
			relationService.saveRelation(role);
		}
		return roleService.createOrUpdateRole(role);
		
    }

	

}
