package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.BCity;
import com.qhc.frye.domain.BProvince;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.service.BAreaService;
import com.qhc.frye.service.BCityService;
import com.qhc.frye.service.BProvinceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("freight")
@Api(value = "BArea", description = "三级地区及运费")
public class FreightController {
	
	@Autowired
	private BAreaService bAreaService;
	
	@Autowired
	private BCityService bCityService;
	
	@Autowired
	private BProvinceService bProvinceService;
	
	
	@ApiOperation(value="添加或修改三级地区及运费", notes="添加或修改三级地区及运费")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BArea add(@RequestBody(required=true) BProvince bProvince,BCity bCity,BArea bArea) throws Exception
    {	
		bProvinceService.add(bProvince);
		bCityService.add(bCity);
		return bAreaService.add(bArea);
    }
	
	@ApiOperation(value = "省市区运费")
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void put(@RequestBody(required = true) @Valid List<List<String>> freight) throws Exception {
		
		bAreaService.saveFreight(freight);
	}
	
	@ApiOperation(value="查询省市区运费", notes="Find all kOrderInfo paging info")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
	public  PageHelper findPagingList(
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("name") String name
			) throws Exception{
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		PageHelper pageHelper = new PageHelper();
		
		Page<List<Object>> page = bAreaService.getList(name, pageable);
		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }

}
