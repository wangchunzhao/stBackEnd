package com.qhc.frye.rest.controller.qhc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.SpecialDelivery;
import com.qhc.frye.service.SpecialDeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * @author lizuoshan
 */
@RestController
@RequestMapping("specialDelivery")
@Api(value = "SpecialDelivery", description = "SpecialDelivery info")
public class SpecialController {
	
	@Autowired
	SpecialDeliveryService specialDeliveryService;

	
	@ApiOperation(value="Modify the SpecialDelivery status or create new one ", notes="Modify the SpecialDelivery status or create new one")
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
    public SpecialDelivery updateRoleOperations(@RequestBody SpecialDelivery sd) throws Exception
    {	

		return specialDeliveryService.saveOrUpdate(sd);
		
    }
	
	@ApiOperation(value="Find the SpecialDelivery by orderId ", notes="Find the SpecialDelivery by orderId")
	@GetMapping("/order/{ordersId}")
    @ResponseStatus(HttpStatus.OK)
    public List<SpecialDelivery> findByOrdersId(@PathVariable Integer ordersId) throws Exception
    {	

		return specialDeliveryService.findByOrdersId(ordersId);
		
    }
	
	@ApiOperation(value="Find the SpecialDelivery by id ", notes="Find the SpecialDelivery by id")
	@GetMapping("/{applyId}")
    @ResponseStatus(HttpStatus.OK)
    public SpecialDelivery findById(@PathVariable Integer applyId) throws Exception
    {	

		return specialDeliveryService.findById(applyId);
		
    }
	
	
	
}
