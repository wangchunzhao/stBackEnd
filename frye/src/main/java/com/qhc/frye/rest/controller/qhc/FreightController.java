package com.qhc.frye.rest.controller.qhc;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.BArea;
import com.qhc.frye.domain.BCity;
import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.service.BAreaService;
import com.qhc.frye.service.BCityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("bArea")
@Api(value = "BArea", description = "BArea info")
public class BAreaController {
	
	@Autowired
	private BAreaService bAreaService;
	
	@ApiOperation(value="Add bArea", notes="Add bArea")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
    public BArea add(@RequestBody(required=true) BArea bArea) throws Exception
    {	
		return bAreaService.add(bArea);
    }
	
	@ApiOperation(value=" Find all kOrderInfo paging info", notes="Find all kOrderInfo paging info")
	@GetMapping("/paging")
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
