package com.qhc.frye.rest.controller.qhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
			@RequestParam("b2c") int b2c) throws Exception{
		
		PageHelper<KOrderInfo> pageHelper = new PageHelper<KOrderInfo>();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		KOrderInfo kOrderInfo = new KOrderInfo();
		kOrderInfo.setContractNo(contractNo);
		kOrderInfo.setB2c(b2c);
		kOrderInfo.setContractUnit(contractUnit);
		Page<KOrderInfo> page = kOrderInfoService.getKOrdersByConditions(kOrderInfo, pageable);

		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }

}
