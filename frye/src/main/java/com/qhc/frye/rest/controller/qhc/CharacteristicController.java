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

import com.qhc.frye.rest.controller.entity.CharacteristicValue;
import com.qhc.frye.rest.controller.entity.Clazz;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.service.CharacteristicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Clazz, Characteristic and its value Management in Frye")
public class CharacteristicController {
	
	@Autowired
	private CharacteristicService characteristicService;
	
	@ApiOperation(value = "update class to DB.")
	@PutMapping(value = "class")
	@ResponseStatus(HttpStatus.OK)
	public void putClass(@RequestBody(required = true) @Valid List<Clazz> clazz) {
		characteristicService.saveClass(clazz);
	}

	@ApiOperation(value = "update characteristic and corresponding data to DB.")
	@PutMapping(value = "class")
	@ResponseStatus(HttpStatus.OK)
	public void putcharacteristicValue(@RequestBody(required = true) @Valid List<CharacteristicValue> chaValues) {
		characteristicService.saveCharacteristicValue(chaValues);
	}

}
