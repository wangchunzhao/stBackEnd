/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("dOrder")
@Api(value = "DOrder management in Frye")
public class DOrderController {
	
	
	@Autowired
	private OrderService orderService;
	

	
	@ApiOperation(value="Get DOrder by id", notes="Get DOrder by id")
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DOrder findByKOrderVersionId(@PathVariable Integer id) throws Exception
    {	
		
		return orderService.findByKOrderVersionId(id);
		
    }
	

}
