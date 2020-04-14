package com.qhc.order.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.SpecialDeliveryDto;
import com.qhc.order.entity.SpecialOrderApplication;
import com.qhc.order.service.SpecialDeliveryService;
import com.qhc.system.domain.PageHelper;
import com.qhc.system.domain.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lizuoshan
 */
@RestController
@Api(value = "SpecialDelivery", description = "特批发货")
@RequestMapping("specialdelivery")
public class SpecialDeliveryController {

	@Autowired
	SpecialDeliveryService specialDeliveryService;

	@ApiOperation(value = "修改或者新增特批发货 ", notes = "修改或者新增特批发货")
	@PostMapping(value = "{user}")
	@ResponseStatus(HttpStatus.OK)
	public Result save(@PathVariable("user") String user, @RequestBody SpecialDeliveryDto sd) {
		Result result = null;
		try {
			SpecialDeliveryDto dto = specialDeliveryService.save(user, sd);
			result = Result.ok(dto);
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "根据申请ID查询 ", notes = "根据申请ID查询")
	@GetMapping("{applyId}")
	@ResponseStatus(HttpStatus.OK)
	public Result findById(@PathVariable Integer applyId) throws Exception {
		Result result = null;
		try {
			SpecialDeliveryDto dto = specialDeliveryService.findById(applyId);
			result = Result.ok(dto);
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "分页查询特批发货列表", notes = "分页查询特批发货列表")
	@GetMapping(value = "")
	@ResponseStatus(HttpStatus.OK)
//	public PageHelper<SpecialDeliveryDto> findPagingList(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize,
//			@RequestParam("sequenceNumber") String sequenceNumber, @RequestParam("orderInfoId") String orderInfoId, @RequestParam("startTime") String startTime,
//			@RequestParam("endTime") String endTime, @RequestParam("ownerDomainId") String ownerDomainId,
//			@RequestParam("officeCode") String officeCode, @RequestParam("orderTypeCode") String orderTypeCode)
	public Result find(Map params) {
		Result result = null;
		try {
			PageHelper<SpecialDeliveryDto> page = new PageHelper<SpecialDeliveryDto>(
					specialDeliveryService.find(params));
			result = Result.ok(page);
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "提交特批发货 ", notes = "提交特批发货")
	@PostMapping(value = "submit/{user}")
	@ResponseStatus(HttpStatus.OK)
	public Result submit(@PathVariable("user") String user, @RequestBody SpecialDeliveryDto sd) {
		Result result = null;
		try {
			specialDeliveryService.submit(user, sd);
			result = Result.ok("");
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

}
