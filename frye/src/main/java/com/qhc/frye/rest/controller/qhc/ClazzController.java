/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Clazz;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.service.ClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Clazz Management in Frye")
public class ClazzController {
	
	@Autowired
	private ClassService classService;
	
	@ApiOperation(value = "update class and characteristic data to DB.")
	@PutMapping(value = "class")
	@ResponseStatus(HttpStatus.OK)
	public void putClassAndCharacteristic(@RequestBody(required = true) @Valid List<Clazz> clazz) {
		classService.saveClassAndCharacteristic(clazz);
	}

}
