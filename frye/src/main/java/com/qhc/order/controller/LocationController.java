/**
 * 
 */
package com.qhc.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.sap.domain.SalesGroup;
import com.qhc.sap.entity.SapSalesOffice;
import com.qhc.sap.service.LocationService;
import com.qhc.sap.service.SapSalesOfficeService;
import com.qhc.system.domain.PageHelper;
import com.qhc.system.service.AreaService;
import com.qhc.system.service.CityService;
import com.qhc.system.service.ProvinceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Location Management in Frye", description = "地址及中心大区管理")
public class LocationController {
	
	@Autowired
	private LocationService localService;
	
	@Autowired
	private AreaService bAreaService;
	
	@Autowired
	private CityService bCityService;
	
	@Autowired
	private ProvinceService bProvinceService;
	
	@Autowired
	private SapSalesOfficeService sapSalesOfficeService;

	@ApiOperation(value = "保存或者修改销售大区和中心")
	@PutMapping(value = "location/salesOffice", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void uploadSalesOffice(@RequestBody(required = true) @Valid List<SalesGroup> salesGroups) throws Exception {
//		localService.clean();
		localService.put(salesGroups);
	}
	
	@ApiOperation(value = "获取目的地计算运费")
	@GetMapping(value = "location/destination", produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void getDestination() throws Exception {
		
	}
	
	@ApiOperation(value = "添加省市区运费")
	@PutMapping(value="location/freight")
	@ResponseStatus(HttpStatus.OK)
	public void put(@RequestBody(required = true) @Valid List<List<String>> freight) throws Exception {
		
		bAreaService.saveFreight(freight);
	}
	
	@ApiOperation(value="查询省市区运费", notes="分页查询全部省市区运费")
	@GetMapping(value="location/freight/{pageNo}/{pageSize}")
    @ResponseStatus(HttpStatus.OK)
	public  PageHelper findPagingList(
			@PathVariable int pageNo,
			@PathVariable int pageSize
			) throws Exception{
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		PageHelper pageHelper = new PageHelper();
		
		Page<List<Object>> page = bAreaService.getList(pageable);
		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }
	
	@ApiOperation(value="查询所有大区", notes="查询所有大区")
	@GetMapping(value="location/sapSalesOffice")
    @ResponseStatus(HttpStatus.OK)
	public List<SapSalesOffice> findAll() throws Exception{
		return sapSalesOfficeService.findAll();
    }

}
