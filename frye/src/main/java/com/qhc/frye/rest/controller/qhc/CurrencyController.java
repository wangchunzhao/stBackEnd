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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.Incoterm;
import com.qhc.frye.rest.controller.entity.Price;
import com.qhc.frye.service.CurrencyService;
import com.qhc.frye.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Currency and Incoterm management in Frye")
public class CurrencyController {
	

	@Autowired
	private CurrencyService currencyService;
	

	@ApiOperation(value = "update currency data to DB.")
	@PutMapping(value = "currency")
	@ResponseStatus(HttpStatus.OK)
	public void putCurrency(@RequestBody(required = true) @Valid List<Currency> currency) {
		currencyService.saveCurrency(currency);
	}
	
	@ApiOperation(value = "get currency map from DB.")
	@GetMapping(value = "currency")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> getCurrency() {
		Map<String,String> currencies = new HashMap<String,String>();
		List<Currency> curList = currencyService.getCurrency();
		for(Currency cur:curList) {
			currencies.put(cur.getCode(),cur.getName());
		}
		return currencies;
	}
	
	@ApiOperation(value = "update incoterms data to DB.")
	@PutMapping(value = "incoterms")
	@ResponseStatus(HttpStatus.OK)
	public void putIncoterm(@RequestBody(required = true) @Valid List<Incoterm> incoterm) {
		currencyService.saveIncoterm(incoterm);
	}
	@ApiOperation(value = "retrieve incoterms from DB.")
	@GetMapping(value = "incoterms")
	@ResponseStatus(HttpStatus.OK)
	public Map<String,String> getIncoterms() {
		return currencyService.getIncoterms();
	}
	
	@ApiOperation(value = "update price data to DB.")
	@PutMapping(value = "price")
	@ResponseStatus(HttpStatus.OK)
	public void putPrice(@RequestBody(required = true) @Valid List<Price> price) {
		currencyService.savePrice(price);
	}

}
