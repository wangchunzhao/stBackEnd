package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(value = "Operations", description = "Operations info")
public class OperationController {
	
	@Autowired
	private OperationService operationService;
	
	@ApiOperation(value="Query operations", notes="Query operations")
	@GetMapping(value = "/operations")
    @ResponseStatus(HttpStatus.OK)
    public List<Operations> findAll() throws Exception
    {	
		return operationService.findAll();
    }
	
	@ApiOperation(value=" Query operations by id", notes="Query operations by id")
	@GetMapping(value = "/operations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Operations findById(@PathVariable("id") String id) throws Exception
    {	
		return operationService.findById(id);
    }

	

}
