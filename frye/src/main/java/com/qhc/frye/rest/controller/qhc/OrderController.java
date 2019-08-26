/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Order;
import com.qhc.frye.service.ConfirugrableService;
import com.qhc.frye.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@RequestMapping("order")
@Api(value = "Order management in Frye")
public class OrderController {
	
	@Autowired
    private ConfirugrableService configService;
	
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="Get material by configurable", notes="Get material by configurable Json string")
    @GetMapping(value = "/Materinal/{configurable}")
    @ResponseStatus(HttpStatus.OK)
    public void submit(@PathVariable("configurable") List<String> configurable) throws Exception
    {	
		
		configService.findMaterial(configurable);
    }
	
	@ApiOperation(value="Looking for the newest order", notes="Looking fro the newest order from cloud")
    @GetMapping(value = "/order/{configurable}")
    @ResponseStatus(HttpStatus.OK)
    public void  save() throws Exception
    {	
		
		orderService.findNewestOrder();
    }
	@ApiOperation(value="Looking for the newest order", notes="Looking fro the newest order from cloud")
    @GetMapping(value = "/order/{configurable}")
    @ResponseStatus(HttpStatus.OK)
    public Order getByCode() throws Exception
    {	
		
		return new Order();
    }
	

}
