package com.qhc.order.controller;

import java.util.List;

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

import com.qhc.order.domain.SpecialDeliveryVo;
import com.qhc.order.entity.SpecialOrderApplication;
import com.qhc.order.service.SpecialDeliveryService;
import com.qhc.system.domain.PageHelper;

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
	@PostMapping(value = "")
	@ResponseStatus(HttpStatus.OK)
	public SpecialOrderApplication updateRoleOperations(@RequestBody SpecialOrderApplication sd) throws Exception {
		return specialDeliveryService.saveOrUpdate(sd);
	}

	@ApiOperation(value = "根据申请ID查询 ", notes = "根据申请ID查询")
	@GetMapping("{applyId}")
	@ResponseStatus(HttpStatus.OK)
	public SpecialOrderApplication findById(@PathVariable Integer applyId) throws Exception {
		return specialDeliveryService.findById(applyId);
	}

	@ApiOperation(value = "分页查询特批发货列表", notes = "分页查询特批发货列表")
	@GetMapping(value = "specialDeliveryVoInfo/{pageNo}/{pageSize}")
	@ResponseStatus(HttpStatus.OK)
	public PageHelper<SpecialDeliveryVo> findPagingList(@PathVariable int pageNo, @PathVariable int pageSize,
			@RequestParam("sequenceNumber") String sequenceNumber, @RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime, @RequestParam("ownerDomainId") String ownerDomainId,
			@RequestParam("officeCode") String officeCode, @RequestParam("orderTypeCode") String orderTypeCode)
			throws Exception {
		PageHelper<SpecialDeliveryVo> pageHelper = new PageHelper<SpecialDeliveryVo>();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		SpecialDeliveryVo sdv = new SpecialDeliveryVo();
		sdv.setSequenceNumber(sequenceNumber);
//		sdv.setStartTime(startTime);
//		sdv.setEndTime(endTime);
//		sdv.setOrderTypeCode(orderTypeCode);
//		sdv.setOwnerDomainId(ownerDomainId);
//		sdv.setOfficeCode(officeCode);
		Page<SpecialDeliveryVo> page = null; // specialDeliveryService.getInfoByConditions(sdv, pageable);

		pageHelper.setTotal(Integer.valueOf(page.getTotalElements() + ""));
		pageHelper.setRows(page.getContent());
		return pageHelper;
	}

}
