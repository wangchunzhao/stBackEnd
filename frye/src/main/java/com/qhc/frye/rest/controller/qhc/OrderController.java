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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.CustomerClass;
import com.qhc.frye.domain.DSalesType;
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
@Api(value = "Order management in Frye")
public class OrderController {
	
	@Autowired
    private MaterialService configService;
	
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="Submit Order by sales", notes="Save&Submit to headQuanter by sales")
    @PostMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public void submitOrder(@RequestBody(required=true) @Valid Order order) throws Exception
    {	
		System.out.println("submitOrder");
		orderService.save(order);
    }

	@ApiOperation(value="Looking for the newest order", notes="Looking fro the newest order from cloud")
    @PutMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public void  updateOrder(@RequestBody(required=true) @Valid Order order) throws Exception
    {	
		System.out.println("updateOrder");
		orderService.update(order);
    }
	
	@ApiOperation(value="get salesType", notes="get the list olf sales type for order")
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
	

}
