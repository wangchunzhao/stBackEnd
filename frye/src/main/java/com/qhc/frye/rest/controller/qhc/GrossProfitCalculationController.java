package com.qhc.frye.rest.controller.qhc;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(value = "GrossProfitCalculation", description = "毛利计算接口")
public class GrossProfitCalculationController {
	
	
	@ApiOperation(value="根据入参计算毛利 ", notes="根据入参计算毛利")
	@GetMapping(value="grossProfitCalculation/{afterTaxAmount}/{cost}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> findByConditions(
    		@PathVariable Double afterTaxAmount,
    		@PathVariable Double cost) throws Exception
	{
		Map<String,Object> map = new HashMap();
		Double result = (afterTaxAmount-cost)/cost;
		Double finalResult = (double) Math.round(result * 10000) / 10000;
		map.put("result", finalResult);
        return map;
    }
	


}
