/**
 * 
 */
package com.qhc.sap.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qhc.order.service.ConstantService;
import com.qhc.sap.domain.MaterialBom;
import com.qhc.sap.domain.Characteristic;
import com.qhc.sap.domain.CharacteristicValueDto;
import com.qhc.sap.domain.Clazz;
import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.domain.DefaultCharacteristicsDto;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.service.CharacteristicService;
import com.qhc.sap.service.CustomerService;
import com.qhc.sap.service.MaterialService;
import com.qhc.system.domain.PageHelper;
import com.qhc.system.domain.Result;
import com.qhc.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Material Management in Frye", description = "物料管理")
@Slf4j
public class MaterialController {
	

	public final static String MATERIAL_NAME = "name";
	public final static String MATERIAL_PAGENO = "pageNo";
	public final static String MATERIAL_PAGESIZE = "pageSize";

	@Autowired
	private MaterialService materialSer;

	@Autowired
	private CustomerService cu;

	@Autowired
	private CharacteristicService characteristicSer;

	@ApiOperation(value = "查询物料lastUpdateDate")
	@GetMapping(value = "material/lastUpdateDate")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String getLastUpdatedDate() throws Exception {
		Date date = cu.getLastUpdated(MaterialDto.MATERIAL_CODE);
		return DateUtil.convert2String(date, "yyyyMMddHHmmss");
	}

	@ApiOperation(value = "新增物料信息")
	@PutMapping(value = "material", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void postMaterial(@RequestBody(required = true) @Valid List<MaterialDto> materials) throws Exception {

		materialSer.saveMaterials(materials);
	}

	@ApiOperation(value = "查找增物料信息")
	@PostMapping(value = "material")
	@ResponseStatus(HttpStatus.OK)
	public Result findMaterialsByName(@RequestBody(required = true) Map<String, String> pars) throws Exception {
		Result result = null;
		try {
			String name = pars.get("name");
			String industryCode = pars.get("industryCode");
			Integer pageNo = Integer.parseInt(pars.get(MATERIAL_PAGENO));
			Integer pageSize = Integer.parseInt(pars.get(MATERIAL_PAGESIZE));
			PageInfo<MaterialDto> page = materialSer.findMaterialsByName(name, industryCode, pageNo, pageSize);
			PageHelper<MaterialDto> materials = new PageHelper<MaterialDto>(page);
			result = Result.ok(materials);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "通过code查找具体增物料信息")
	@GetMapping(value = "material/{code}/{industryCode}", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public MaterialDto getMaterialById(@PathVariable(required = true) String code,
			@PathVariable(required = false) String industryCode) throws Exception {
		if (code.equals("null")) {
			code = null;
		}
		MaterialDto m = materialSer.getMaterialsById(code, industryCode);
		return m;
	}

	@ApiOperation(value = "根据物料分类代码查找characteristic和value列表及default value")
	@GetMapping(value = "material/configurations/{clazzCode},{materialCode}")
	@ResponseStatus(HttpStatus.OK)
	public Result findCharacteristic(@PathVariable(required = true) String clazzCode,
			@PathVariable(required = true) String materialCode) throws Exception {
		Result result = null;
		try {
			List<Characteristic> chars = materialSer.getCharactersByClazzCode(clazzCode, materialCode);
			result = Result.ok(chars);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "根据BOM配置获取新的Characteristic和value")
	@PostMapping(value = "material/optionalbom")
	@ResponseStatus(HttpStatus.OK)
	public Result findBOMWithPrice(@RequestBody(required = true) Map<String, String> pars) throws Exception {
		Result result;
		try {
			MaterialBom bom = materialSer.findBomPrice(pars);
			result = Result.ok(bom);
		} catch (Exception e) {
			this.log.error("查询产品BOM失败", e);
			result = Result.error("查询产品BOM失败！");
		}
		
		return result;
	}
	
	/**
	 * SAP 同步接口
	 * @param clazz
	 * @throws Exception
	 */

	@ApiOperation(value = "保存或者修改MaterialClazz")
	@PutMapping(value = "material/materialclass")
	@ResponseStatus(HttpStatus.OK)
	public void putClass(@RequestBody(required = true) @Valid List<Clazz> clazz) throws Exception {
		characteristicSer.saveClass(clazz);
	}

	@ApiOperation(value = "保存或者修改CharacteristicValue")
	@PutMapping(value = "material/characteristic")
	@ResponseStatus(HttpStatus.OK)
	public void putcharacteristicValue(@RequestBody(required = true) @Valid List<CharacteristicValueDto> chaValues)
			throws Exception {
		characteristicSer.saveCharacteristicValue(chaValues);
	}

	@ApiOperation(value = "保存或者修改默认特征")
	@PutMapping(value = "material/default")
	@ResponseStatus(HttpStatus.OK)
	public void putcharacteristicDefault(
			@RequestBody(required = true) @Valid List<DefaultCharacteristicsDto> defaultChavalue) throws Exception {
		characteristicSer.saveCharacteristicDefault(defaultChavalue);
	}

}
