/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Clazz;
import com.qhc.frye.rest.controller.entity.Configurable;
import com.qhc.frye.rest.controller.entity.Material;
import com.qhc.frye.service.MaterialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@RequestMapping("material")
@Api(value = "Material Management in Frye")
public class MaterialController {

	@Autowired
	private MaterialService configService;

	@ApiOperation(value = "update a FERT material list to DB.")
	@PostMapping(value = "/updateFERTMaterials", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void updateFERTMaterials(@RequestBody(required = true) @Valid List<Material> material) throws Exception {

	}

	@ApiOperation(value = "update a Configurable and their values into DB. update data in table only. the data will be ingored if there is not")
	@PostMapping(value = "/updateConfigs", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void updateConfigurableValue(@RequestBody(required = true) @Valid List<Configurable> configs)
			throws Exception {

	}

	@ApiOperation(value = "update the relationship and each Configurable and Class to DB. Delete whole talbe and insert data with batch.")
	@PostMapping(value = "/updateConfigAndClazz", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void relateConfigAndClazz(@RequestBody(required = true) @Valid Map<Clazz, List<Configurable>> configs)
			throws Exception {

	}

}
