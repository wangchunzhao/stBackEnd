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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.Role;
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
@RequestMapping("role")
@Api(value = "Role", description = "Role info")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RelationService relationService;
	
	
	@ApiOperation(value=" Find all role info", notes="Find all role info")
	@RequestMapping(value = "/findAll")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Role> findAll() throws Exception{
		return roleService.findAll();
    }
	
	@ApiOperation(value=" Find role by id", notes="Find role by id")
	@GetMapping(value = "/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Object findById(@PathVariable("id") Integer id) throws Exception
    {	
		return roleService.findById(id);
    }
	
	@ApiOperation(value="Add role ", notes="Add role")
	@PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Role add(@RequestBody(required=true) @Valid Role role) throws Exception
    {	
		return roleService.createOrUpdateRole(role);
		
    }
	
	@ApiOperation(value="Update role", notes="Update role")
	@PostMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Role update(@RequestBody(required=true) Role role) throws Exception
    {	
		return roleService.createOrUpdateRole(role);
		
    }
	
	
	@ApiOperation(value="Modify the permissions of roles ", notes="Modify the permissions of roles")
	@PostMapping(value = "/updateRoleOperations")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Role updateRoleOperations(@RequestBody(required=true) Role role) throws Exception
    {	
		//得到权限ids
		String operations = role.getName();
		String[] operationArr = operations.split(",");
		List list = new ArrayList();
		//删除所有权限关系
		relationService.remove(role.getId());
		if(operationArr.length>0) {
			list = relationService.saveRelation(role.getId(),operationArr);
		}
		
		if(list.isEmpty()) {
			role = new Role();
		}
		
		return role;
		
    }
	
	@ApiOperation(value="Delete role", notes="Delete role")
	@GetMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public String delete(@PathVariable("id") Integer id) throws Exception
    {	
//		List list = relationService.findByRoleId(id);
//		
//		if(list!=null&&list.size()>0) {
//			return "false";
//		}else {
//			roleService.remove(id);
//			return "success";
//		}
		return "false";
    }

}
