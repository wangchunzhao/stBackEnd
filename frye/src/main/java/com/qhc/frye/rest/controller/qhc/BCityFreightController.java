package com.qhc.frye.rest.controller.qhc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.ApplicationOfRolechange;
import com.qhc.frye.domain.BCityFreight;
import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.service.BCityFreightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("bCityFreight")
@Api(value = "BCityFreight", description = "BCityFreight info")
public class BCityFreightController {

	@Autowired
	private BCityFreightService bCityFreightService;
	
	@ApiOperation(value="Add bCityFreight", notes="Add bCityFreight")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BCityFreight add(@RequestBody(required=true) BCityFreight bCityFreight) throws Exception
    {	
		return bCityFreightService.add(bCityFreight);
		
    }
	
	@ApiOperation(value=" Find all bCityFreight paging info", notes="Find all bCityFreight paging info")
	@GetMapping("/paging")
    @ResponseStatus(HttpStatus.OK)
	public PageHelper<BCityFreight> findPagingList(
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("countyName") String countyName) throws Exception{
		
		PageHelper<BCityFreight> pageHelper = new PageHelper<BCityFreight>();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		BCityFreight bCityFreight = new BCityFreight();
		bCityFreight.setCountyName(countyName);
		
		Page<BCityFreight> page = bCityFreightService.getListByConditions(bCityFreight, pageable);

		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }
	
	
}
