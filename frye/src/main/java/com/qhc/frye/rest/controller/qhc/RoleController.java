package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.Role;
import com.qhc.frye.rest.controller.entity.RestPage;
import com.qhc.frye.service.RelationService;
import com.qhc.frye.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@Api(value = "Role", description = "角色管理")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RelationService relationService;
	
	
	@ApiOperation(value="带条件分页查询角色", notes="带条件分页查询角色")
	@GetMapping(value="role/{pageNo}/{pageSize}/{isActive}")
    @ResponseStatus(HttpStatus.OK)
	public RestPage<Role> findPagingList(
			@PathVariable int pageNo,
			@PathVariable int pageSize,
			@PathVariable int isActive) throws Exception{
		
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Role role = new Role();
		role.setIsActive(isActive);
		Page<Role> page = roleService.getByConditions(role,pageable);
        return new RestPage(page);
    }
/*	
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
				if(isActive==r.getIsActive()) {
					result.add(r);
				}
			}
		}
		return result;
    }
*/	
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
