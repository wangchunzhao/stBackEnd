package com.qhc.frye.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.rest.controller.entity.Currency;

/**
 * 
 * @author wang@dxc.com
 *
 */
@Service
public class CurrencyService {
	@Autowired
	private CurrencyRepository currencyRepo;
	
	public void saveCurrency(List<Currency> currency) {
		Set<DCurrency> dcs = new HashSet<DCurrency>();
		for(Currency cur:currency) {
			DCurrency dc = new DCurrency();
			dc.setCode(cur.getCode());
			dc.setName(cur.getName());
			dc.setRate(cur.getRate()*100);
			dcs.add(dc);
		}
		currencyRepo.saveAll(dcs);
		
	}
}
