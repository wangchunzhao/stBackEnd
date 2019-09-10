package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.Operation2role;
import com.qhc.frye.domain.Operations;
import com.qhc.frye.domain.Role;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.domain.User;
import com.qhc.frye.rest.controller.entity.RestPage;
import com.qhc.frye.service.ApplicationOfRolechangeService;
import com.qhc.frye.service.OperationService;
import com.qhc.frye.service.RelationService;
import com.qhc.frye.service.RoleService;
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
@Api(value = "User", description = "User info")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired 
	private ApplicationOfRolechangeService appService;
	@Autowired
	private RelationService relationService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private SapSalesOfficeService officeService;
	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value=" Find all user info ", notes="Find all user info")
	@GetMapping(value="/paging")
    @ResponseStatus(HttpStatus.OK)
    public RestPage findAll(
    		@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
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
        return new RestPage(page);
    }
	
	@ApiOperation(value=" Find user by multiple conditions", notes="Find user by multiple conditions")
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
	
	
	@ApiOperation(value=" Find user by UserIdentity", notes="Find user by UserIdentity")
	@GetMapping(value = "/{userIdentity}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUserIdentity(@PathVariable String userIdentity) throws Exception{	
		User user = userService.findByUserIdentity(userIdentity);
		List<ApplicationOfRolechange> appList = appService.findByBUsersId(user.getId());
		Set<Operations> operationSet = new HashSet<Operations>();
		List<Role> roleList = new ArrayList<Role>();
		String region ="";
		if(appList!=null&&appList.size()>0) {
			for(ApplicationOfRolechange app:appList) {
				region = app.getAttachedCode();
				Role role = roleService.findById(app.getbRolesId());
				roleList.add(role);
				List<Operation2role> relations = relationService.findByRoleId(app.getbRolesId(), 1);
				for(Operation2role r:relations) {
					String operationId = r.getOperationId();
					Operations op = operationService.findById(operationId);
					if(op!=null) {
						operationSet.add(op);
					}
				}
			}
			SapSalesOffice office = officeService.findByCode(region);
			user.setRegion(office);
			user.setRoles(roleList);
			user.setOperations(new ArrayList<Operations>(operationSet));
		}
		return user;
    }
	
	@ApiOperation(value="Add user", notes="Add user")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@Transactional
    public User add(@RequestBody(required=true) User user) throws Exception
    {	
		appService.addOrUpdateApp(user);
		user = userService.createOrUpdateUser(user);
		return user;
    }
	
	@ApiOperation(value="Update user", notes="Update user")
	@PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody User user) throws Exception
    {	
		return userService.createOrUpdateUser(user);
    }
	


}
