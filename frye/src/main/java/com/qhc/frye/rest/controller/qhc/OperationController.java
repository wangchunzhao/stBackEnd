package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.Operations;
import com.qhc.frye.service.OperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("operation")
@Api(value = "Operation", description = "operation info")
public class OperationController {
	
	@Autowired
	private OperationService operationService;
	
	@ApiOperation(value=" looking for role operation by id", notes="looking for operation info by id")
    @GetMapping(value = "/operationList/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getMaterial(@PathVariable("id") int id) throws Exception
    {	
		List<Operations> list = new ArrayList<Operations>();
		if(id==0) {
			list.add(operationService.getOperations(id));
		}else {
			list = operationService.getOperationsList();
		}
		return list;
    }
	

}
