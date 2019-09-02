/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Order;
import com.qhc.frye.service.MaterialService;
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
    private MaterialService configService;
	
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="Submit Order by sales", notes="Save&Submit to headQuanter by sales")
    @GetMapping(value = "submit")
    @ResponseStatus(HttpStatus.OK)
    public void submit(@RequestBody(required=true) @Valid Order order) throws Exception
    {	
		
		
    }
	
	@ApiOperation(value="Save order as draft", notes="Looking fro the newest order from cloud")
    @GetMapping(value = "save")
    @ResponseStatus(HttpStatus.OK)
    public void  save(@RequestBody(required=true) @Valid Order order) throws Exception
    {	
		
		orderService.findNewestOrder();
    }
	
	@ApiOperation(value="Looking for the newest order", notes="Looking fro the newest order from cloud")
    @GetMapping(value = "update")
    @ResponseStatus(HttpStatus.OK)
    public void  update(@RequestBody(required=true) @Valid Order order) throws Exception
    {	
		
		
    }
	

}
