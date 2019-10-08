/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.dao.SalesGroupRepository;
import com.qhc.frye.domain.CustomerClass;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.GrossProfitDTO;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.rest.controller.entity.AbsOrder;
import com.qhc.frye.rest.controller.entity.SalesOrder;
import com.qhc.frye.service.MaterialService;
import com.qhc.frye.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Order management in Frye", description = "订单管理")
public class OrderController {
	
	@Autowired
    private MaterialService configService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SalesGroupRepository salesGroupRepository;
	
	@ApiOperation(value="保存订单信息", notes="保存订单信息")
    @PostMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public void submitOrder(@RequestBody(required=true) @Valid AbsOrder absOrder) throws Exception
    {	
		System.out.println("submitOrder");
		orderService.save(absOrder);
    }

	@ApiOperation(value="Looking for the newest order", notes="Looking fro the newest order from cloud")
    @PutMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public void  updateOrder(@RequestBody(required=true) @Valid AbsOrder absOrder) throws Exception
    {	
		System.out.println("updateOrder");
		orderService.save(absOrder);
    }
	
	@ApiOperation(value="查询订单类型", notes="查询订单类型")
    @GetMapping(value = "order/salesType")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getOrderType() throws Exception
    {	
		Map<String,String> saleTypes = new HashMap<String,String>();
		List<DSalesType> dsl =  orderService.getSalesTypes();
		for(DSalesType ds:dsl) {
			saleTypes.put(ds.getCode(), ds.getName());
		}
		return saleTypes;
		
    }
	
	
    
    @PostMapping(value = "order/salesOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<SapSalesGroup> getGrossProfit(@RequestBody SalesOrder salesOrder) throws Exception
    {	
    	GrossProfitDTO grossProfitDTO = new GrossProfitDTO();
    	grossProfitDTO.setSalesOrder(salesOrder);
    	grossProfitDTO.setSapSalesGroupList(salesGroupRepository.findAll());
    	
		return this.getGrossProfitDetail(grossProfitDTO);
    }
    
    @PostMapping(value = "order/grossProfitDTO")
    @ResponseStatus(HttpStatus.OK)
    public List<SapSalesGroup> getGrossProfitDetail(@RequestBody GrossProfitDTO grossProfitDTO) throws Exception
    {	
    	
		return orderService.findGrossProfitBySalesOrder(grossProfitDTO.getSalesOrder(), grossProfitDTO.getSapSalesGroupList());
    }
    
	

}
