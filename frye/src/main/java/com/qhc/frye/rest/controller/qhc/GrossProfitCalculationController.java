package com.qhc.frye.rest.controller.qhc;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("grossProfitCalculation")
@Api(value = "GrossProfitCalculation", description = "Gross profit calculation interface")
public class GrossProfitCalculationController {
	
	
	@ApiOperation(value=" Gross profit calculation by parameter ", notes="Gross profit calculation by parameter")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> findByConditions(
    		@RequestParam("afterTaxAmount") Double afterTaxAmount,
			@RequestParam("cost") Double cost) throws Exception
	{
		Map<String,Object> map = new HashMap();
		Double result = (afterTaxAmount-cost)/cost;
		Double finalResult = (double) Math.round(result * 10000) / 10000;
		map.put("result", finalResult);
        return map;
    }
	


}
