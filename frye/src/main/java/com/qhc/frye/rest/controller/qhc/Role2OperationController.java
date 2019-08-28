package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.Operation2role;
import com.qhc.frye.service.RelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("operation2role")
@Api(value = "Operation2role", description = "角色权限关系")
public class Role2OperationController {
	
	@Autowired
	private RelationService relationService;
	
	@ApiOperation(value=" 根据权角色id查询所拥有的权限", notes="根据权角色id查询所拥有的权限")
	@GetMapping(value = "/findByRoleId")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<Operation2role> findByRoleId(@PathVariable("roleId") Integer roleId) throws Exception
    {	
		return relationService.findByRoleId(roleId, 1);
    }
	
	@ApiOperation(value=" 根据权限id查询所属角色", notes="根据权限id查询所属角色")
	@GetMapping(value = "/findByOperationId")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<Operation2role> findByOperationId(@PathVariable("operationId") String operationId) throws Exception
    {	
		return relationService.findByOperationId(operationId, 1);
    }
	
	

}
