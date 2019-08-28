package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.Operation2role;
import com.qhc.frye.domain.Role;
import com.qhc.frye.rest.controller.entity.Order;
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
@Api(value = "Role", description = "role info")
public class Role2OperationController {
	
	@Autowired
	private RelationService relationService;
	
	@ResponseBody
	@ApiOperation(value=" looking for role info by id", notes="looking for role info by id")
	@GetMapping(value = "/roleList/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object queryRealation(@PathVariable("id") String id,@PathVariable("type") String type) throws Exception
    {	
		List<Operation2role> list = new ArrayList<Operation2role>();
		//角色
		if("role".equals(type)) {
			list = relationService.getByRoleId(Integer.valueOf(id),1);//1表示已经激活的
		}else {
			list = relationService.getByOperationId(id,1);//1表示已经激活的
		}
		return list;
    }
	
	

}
