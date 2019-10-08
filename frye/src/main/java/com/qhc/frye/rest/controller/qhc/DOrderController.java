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
@Api(value = "DOrder management in Frye", description = "省市区三级地区及运费")
public class DOrderController {
	
	
	@Autowired
	private OrderService orderService;
	

	
	@ApiOperation(value="根据id查询订单", notes="根据id查询订单")
    @GetMapping(value = "dOrder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DOrder findByKOrderVersionId(@PathVariable String id) throws Exception
    {	
		
		return orderService.findByKOrderVersionId(id);
		
    }
	

}
