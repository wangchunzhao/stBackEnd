/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.Contract;
import com.qhc.frye.rest.controller.entity.ContractView;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.rest.controller.entity.Result;
import com.qhc.frye.service.ContractService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Walker
 *
 */
@Controller
@Api(value = "Contract management in Frye", description = "合同管理")
@RequestMapping(path = "contract")
public class ContractController {
	Logger logger = LoggerFactory.getLogger(ContractController.class);

	@Autowired
	private ContractService contractService;
	
	@ApiOperation(value = "查询合同列表")
	@GetMapping(value = "")
	@ResponseBody
	public Result<? extends Object> find(@RequestParam(required = false) Map<String, Object> params) throws Exception {
		Result<? extends Object> result = null;
		try {
			PageHelper<ContractView> page = contractService.find(params);
			result = Result.ok(page);
		} catch (Exception e) {
			logger.error("查询合同", e);
			result = Result.error(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "保存或修改合同",notes="保存或修改合同")
	@PostMapping(value = "")
	@ResponseBody
	public Result<? extends Object> save(@RequestBody(required = true) ContractView view ) {
		Result<? extends Object> result = null;
		try {
			Contract contract = new Contract();
			BeanUtils.copyProperties(view, contract);
			contract = contractService.save(contract);
			view.setId(contract.getId());
			result = Result.ok(view);
		} catch (Exception e) {
			logger.error("保存或修改合同", e);
			result = Result.error(e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "获取合同详情",notes="获取合同详情")
	@GetMapping(value = "{id}")
	@ResponseBody
	public Result<? extends Object> getContract(@PathVariable("id") Integer contractId) {
		Result<? extends Object> result = null;
		try {
			ContractView c = contractService.findById(contractId);
			result = Result.ok(c);
		} catch (Exception e) {
			String msg = "查询合同详情，合同ID=" + contractId;
			logger.error(msg, e);
			result = Result.error(e.getMessage());
		}
		return result;
	}

}
