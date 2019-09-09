package com.qhc.frye.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.dao.IncotermRepository;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.DIncoterm;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.Incoterm;

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
	
	public void saveIncoterm(List<Incoterm> incoterm) {
		Set<DIncoterm> incos = new HashSet<DIncoterm>();
		for(Incoterm inco:incoterm) {
			DIncoterm temp = new DIncoterm();
			temp.setCode(inco.getCode());
			temp.setName(inco.getName());
			incos.add(temp);
		}
		incotermRepo.saveAll(incos);
	}
}
