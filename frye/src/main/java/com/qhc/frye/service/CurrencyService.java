package com.qhc.frye.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.dao.IncotermRepository;
import com.qhc.frye.dao.PriceRepository;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.DIncoterm;
import com.qhc.frye.domain.DPrice;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.Incoterm;
import com.qhc.frye.rest.controller.entity.Price;

/**
 * 
 * @author wang@dxc.com
 *
 */
@Service
public class CurrencyService {
	@Autowired
	private CurrencyRepository currencyRepo;
	@Autowired
	private IncotermRepository incotermRepo;
	
	@Autowired
	private PriceRepository priceRepo;
	/**
	 * 
	 * @param currency
	 */
	public void saveCurrency(List<Currency> currency) {
		Set<DCurrency> dcs = new HashSet<DCurrency>();
		for(Currency cur:currency) {
			DCurrency dc = new DCurrency();
			dc.setCode(cur.getCode());
			dc.setName(cur.getName());
			dc.setRate(cur.getRate());
			
			dcs.add(dc);
		}
		currencyRepo.saveAll(dcs);
		
	}
	/**
	 * 
	 * @return
	 */
	public List<Currency> getCurrency(){
		List<Currency> cs = new ArrayList<Currency>();
		List<DCurrency> dcs=currencyRepo.findAll();
		for(DCurrency cur:dcs) {
			Currency dc = new Currency();
			dc.setCode(cur.getCode());
			dc.setName(cur.getName());
			dc.setRate(cur.getRate());
			cs.add(dc);
		}
		return cs;
	}
	/**
	 * 
	 * @param incoterm
	 */
	public void saveIncoterm(List<Incoterm> incoterm) {
		Set<DIncoterm> incos = new HashSet<DIncoterm>();
		for(Incoterm inco:incoterm) {
			DIncoterm temp = new DIncoterm();
			temp.setCode(inco.getCode());
			temp.setName(inco.getName());
			temp.setSapSalesTypeCode(inco.getSapSalesTypeCode());
			incos.add(temp);
		}
		incotermRepo.saveAll(incos);
	}
	/**
	 * 
	 * @return international trade
	 */
	public Map<String,String> getIncoterms(){
		Map<String,String> incos = new HashMap<String,String>();
		List<DIncoterm> dincos = incotermRepo.findAll();
		for(DIncoterm di:dincos) {
			incos.put(di.getCode(), di.getName());
		}
		return incos;
	}
	
	public void savePrice(List<Price> price) {
		Set<DPrice> dps = new HashSet<DPrice>();
		for(Price pri:price) {
			DPrice temp = new DPrice();
			/**
			 * 
			 */
			dps.add(temp);
		}
		priceRepo.saveAll(dps);
	}
}
