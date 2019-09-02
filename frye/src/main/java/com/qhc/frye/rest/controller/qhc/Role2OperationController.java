package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@ApiOperation(value=" Find role Operations by role id", notes="Find role Operations by role id")
	@GetMapping(value = "/findByRoleId")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<Operation2role> findByRoleId(@RequestParam("roleId") Integer roleId) throws Exception
    {	
		return relationService.findByRoleId(roleId, 0);
    }
	
	@ApiOperation(value=" Find relationShip by Operations id", notes="Find realationShip by Operations id")
	@GetMapping(value = "/findByOperationId")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public List<Operation2role> findByOperationId(@RequestParam("operationId") String operationId) throws Exception
    {	
		return relationService.findByOperationId(operationId, 0);
    }
	
	@ApiOperation(value=" Delete relationShip by relation id", notes="Delete relationShip by relation id")
	@GetMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public Operation2role delete(@RequestParam("id") int id) throws Exception
    {	
		Operation2role or = new Operation2role();
		or.setId(id);
		relationService.delete(id);
		return or;
		
    }

}
