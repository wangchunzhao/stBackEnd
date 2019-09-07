package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.Parameter;
import com.qhc.frye.service.ParameterSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("parameterSettings")
@Api(value = "ParameterSettings ", description = "ParameterSettings info")
public class ParameterSettingsController {
	
	@Autowired
	private ParameterSettingsService settingService;

	
	@ApiOperation(value=" Find all parameter settings info ", notes="Find all parameter settings info")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Parameter> findAll() throws Exception
    {	
		return settingService.findDistinctInfo();
    }
	
	
	
	@ApiOperation(value="Update parameters info and add a new value", notes="Update parameters info and add a new value")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Parameter update(@RequestBody Parameter p)
    {	
		return settingService.updateParameter(p);
    }
	
	

}
