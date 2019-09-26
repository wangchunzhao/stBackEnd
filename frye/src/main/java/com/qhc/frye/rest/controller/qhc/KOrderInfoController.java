package com.qhc.frye.rest.controller.qhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.service.KOrderInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("kOrderInfo")
@Api(value = "KOrderInfo", description = "KOrderInfo info")
public class KOrderInfoController {
	
	@Autowired
	private KOrderInfoService kOrderInfoService;
	
	@ApiOperation(value=" Find all kOrderInfo paging info", notes="Find all kOrderInfo paging info")
	@GetMapping("/paging")
    @ResponseStatus(HttpStatus.OK)
	public PageHelper<KOrderInfo> findPagingList(
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("contractNo") String contractNo,
			@RequestParam("contractUnit") String contractUnit,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("b2c") int b2c,
			@RequestParam("createId") int createId,
			@RequestParam("area") int area,
			@RequestParam("orderType") int orderType,
			@RequestParam("specialDiscount") int specialDiscount,
			@RequestParam("status") int status) throws Exception{
		
		PageHelper<KOrderInfo> pageHelper = new PageHelper<KOrderInfo>();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		KOrderInfo kOrderInfo = new KOrderInfo();
/*		kOrderInfo.setContractNo(contractNo);
		kOrderInfo.setB2c(b2c);
		kOrderInfo.setContractUnit(contractUnit);
		kOrderInfo.setCreateId(createId);
		kOrderInfo.setArea(area);
		kOrderInfo.setStartTime(startTime);
		kOrderInfo.setEndTime(endTime);
		kOrderInfo.setOrderType(1);
		kOrderInfo.setSpecialDiscount(specialDiscount);
		kOrderInfo.setStatus(status);*/
		Page<KOrderInfo> page = kOrderInfoService.getKOrdersByConditions(kOrderInfo, pageable);

		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }
	
	@ApiOperation(value=" Find  kOrderInfo by id", notes="Find  kOrderInfo by id")
	@GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
	public KOrderInfo findPagingList(@PathVariable Integer orderId) throws Exception{
		
        return kOrderInfoService.findById(orderId);
    }
	
	
	@ApiOperation(value=" Find all kOrderInfo paging info for specialDelivery ", notes="Find all kOrderInfo paging info for specialDelivery")
	@GetMapping("/pagingForSpecial")
    @ResponseStatus(HttpStatus.OK)
	public PageHelper<KOrderInfo> findPagingList(
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("contractNo") String contractNo,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("status") int status) throws Exception{
		
		PageHelper<KOrderInfo> pageHelper = new PageHelper<KOrderInfo>();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		KOrderInfo kOrderInfo = new KOrderInfo();
/*		kOrderInfo.setContractNo(contractNo);
		kOrderInfo.setStartTime(startTime);
		kOrderInfo.setEndTime(endTime);
		kOrderInfo.setStatus(status);*/
		Page<KOrderInfo> page = kOrderInfoService.getKOrdersByConditions2(kOrderInfo, pageable);

		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }
	

}
