/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.ArrayList;
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

import com.qhc.frye.domain.DMaterial;
import com.qhc.frye.rest.controller.entity.Bom;
import com.qhc.frye.rest.controller.entity.BomExplosion;
import com.qhc.frye.rest.controller.entity.Characteristic;
import com.qhc.frye.rest.controller.entity.CharacteristicValue;
import com.qhc.frye.rest.controller.entity.Clazz;
import com.qhc.frye.rest.controller.entity.DateUtil;
import com.qhc.frye.rest.controller.entity.DefaultCharacteristics;
import com.qhc.frye.rest.controller.entity.Material;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.service.BayernService;
import com.qhc.frye.service.CharacteristicService;
import com.qhc.frye.service.CustomerService;
import com.qhc.frye.service.MaterialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Material Management in Frye", description = "物料管理")
public class MaterialController {
	
	public final static String MATERIAL_NAME ="name";
	public final static String MATERIAL_PAGENO ="pageNo";
	public final static String MATERIAL_BOM_CODE ="bom_code";
	

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
		Date date = cu.getLastUpdated(Material.MATERIAL_CODE);
		return DateUtil.convert2String(date, "yyyyMMddHHmmss");
	}

	@ApiOperation(value = "新增物料信息")
	@PutMapping(value = "material", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void postMaterial(@RequestBody(required = true) @Valid List<Material> materials) throws Exception {

		materialSer.saveMaterials(materials);
	}

	@ApiOperation(value = "查找增物料信息")
	@PostMapping(value = "material")
	@ResponseStatus(HttpStatus.OK)
	public PageHelper<Material> findMaterialsByName(@RequestBody(required = true) Map<String, String> pars)
			throws Exception {
		if (pars.containsKey(MATERIAL_NAME) && pars.containsKey(MATERIAL_PAGENO)) {
			PageHelper<Material> ms = new PageHelper<Material>();
			PageHelper<DMaterial> dms = materialSer.findMaterialsByName(pars.get(MATERIAL_NAME),
					Integer.parseInt(pars.get(MATERIAL_PAGENO)));
			List<Material> ml = new ArrayList<Material>();
			List<DMaterial> dml = dms.getRows();
			for (DMaterial dm : dml) {
				Material temp = new Material();
				temp.setCode(dm.getCode());
				temp.setDescription(dm.getDescription());
				temp.setConfigurable(dm.isConfigurable());
				temp.setPurchased(dm.isPurchased());
				temp.setStandardPrice(dm.getPrice());
				ml.add(temp);
			}
			ms.setRows(ml);
			ms.setTotal(dms.getTotal());
			return ms;
		}
		return null;
	}

	@ApiOperation(value = "通过code查找具体增物料信息")
	@GetMapping(value = "material/{code}", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public Material getMaterialById(@PathVariable(required = true) String code) throws Exception {
		if (code.equals("null")) {
			code = null;
		}
		Material m = materialSer.getMaterialsById(code);
		return m;
	}

	@ApiOperation(value = "保存或者修改MaterialClazz")
	@PutMapping(value = "material/materialclass")
	@ResponseStatus(HttpStatus.OK)
	public void putClass(@RequestBody(required = true) @Valid List<Clazz> clazz)  throws Exception{
		characteristicSer.saveClass(clazz);
	}

	@ApiOperation(value = "保存或者修改CharacteristicValue")
	@PutMapping(value = "material/characteristic")
	@ResponseStatus(HttpStatus.OK)
	public void putcharacteristicValue(@RequestBody(required = true) @Valid List<CharacteristicValue> chaValues) throws Exception {
		characteristicSer.saveCharacteristicValue(chaValues);
	}
	
	@ApiOperation(value = "保存或者修改默认特征")
	@PutMapping(value = "material/default")
	@ResponseStatus(HttpStatus.OK)
	public void putcharacteristicDefault(@RequestBody(required = true) @Valid List<DefaultCharacteristics> defaultChavalue) throws Exception {
		characteristicSer.saveCharacteristicDefault(defaultChavalue);
	}
	
	@ApiOperation(value = "根据物料分类代码查找haracteristic和value列表及default value")
	@GetMapping(value = "material/configurations/{clazzCode},{materialCode}")
	@ResponseStatus(HttpStatus.OK)
	public List<Characteristic> findCharacteristic(@PathVariable(required = true) String clazzCode,@PathVariable(required = true) String materialCode)  throws Exception{
		return materialSer.getCharactersByClazzCode(clazzCode,materialCode);
		
	}
	
	@ApiOperation(value = "根据BOM配置获取新的Characteristic和value")
	@PostMapping(value = "material/configuration")
	@ResponseStatus(HttpStatus.OK)
	public BomExplosion findBOMWithPrice(@RequestBody(required = true) Map<String,String> pars)  throws Exception{
		if(pars !=null &&pars.containsKey(MATERIAL_BOM_CODE)) {
			return materialSer.findBOMWithPrice(pars);
		}
		return null;
		
	}


}
