/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.Customer;
import com.qhc.frye.service.CurrencyService;
import com.qhc.frye.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Currency Management in Frye")
public class CurrencyController {
	

	@Autowired
	private CurrencyService currencyService;
	
	@ApiOperation(value = "update currency data to DB.")
	@PutMapping(value = "currency")
	@ResponseStatus(HttpStatus.OK)
	public void putCurrency(@RequestBody(required = true) @Valid List<Currency> currency) {
		currencyService.saveCurrency(currency);
	}

}
