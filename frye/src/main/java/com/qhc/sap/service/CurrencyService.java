package com.qhc.sap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.order.domain.Currency;
import com.qhc.order.domain.DateUtil;
import com.qhc.order.domain.Incoterm;
import com.qhc.order.domain.Material;
import com.qhc.order.domain.Price;
import com.qhc.sap.dao.CurrencyRepository;
import com.qhc.sap.dao.IncotermRepository;
import com.qhc.sap.dao.PriceRepository;
import com.qhc.sap.dao.SapCurrencySaleTypeRepository;
import com.qhc.sap.dao.SapLastUpdatedRepository;
import com.qhc.sap.entity.DCurrency;
import com.qhc.sap.entity.DIncoterm;
import com.qhc.sap.entity.DPrice;
import com.qhc.sap.entity.Industry;
import com.qhc.sap.entity.LastUpdated;
import com.qhc.sap.entity.SapCurrencySaleType;

/**
 * 
 * @author wang@dxc.com
 *
 */
@Service
public class CurrencyService {
	
	public final static long DEFAULT_DATE = 1008005271098L;
	
	@Autowired
	private CurrencyRepository currencyRepo;
	
	@Autowired
	private SapCurrencySaleTypeRepository sapCurrencySaleTypeRepo;
	
	@Autowired
	private IncotermRepository incotermRepo;
	
	@Autowired
	private SapLastUpdatedRepository lastUpdatedRepo;
	
	@Autowired
	private PriceRepository priceRepo;
	
	@Autowired
	private SapLastUpdatedRepository lastUpdate;
	/**
	 * 
	 * @param currency
	 */
	public void saveCurrency(List<Currency> currency) {
		Set<DCurrency> dcs = new HashSet<DCurrency>();
		Set<SapCurrencySaleType> sc = new HashSet<SapCurrencySaleType>();
		for(Currency cur:currency) {
			List<Object> objs = cur.toDaos();
			for(Object obj:objs) {
				if(obj instanceof DCurrency) {
					dcs.add((DCurrency) obj);
				}else if(obj instanceof SapCurrencySaleType){
					sc.add((SapCurrencySaleType) obj);
				}
			}
		}
		currencyRepo.saveAll(dcs);
		sapCurrencySaleTypeRepo.saveAll(sc);
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
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(Price.PRICE_CODE);
		lastUpdated.setName("price");
		//
		for(Price pri:price) {
			DPrice temp = new DPrice();
			temp.setPrice(pri.getPrice());
			temp.setType(pri.getType());
			temp.setMaterialCode(pri.getMaterialCode());
			temp.setIndustryCode(pri.getIndustryCode());
			dps.add(temp);
			lastUpdated.setLastUpdate(DateUtil.convert2Date(pri.getLastDate(), "yyyyMMddhhmmss"));
		}
		priceRepo.saveAll(dps);
		lastUpdatedRepo.save(lastUpdated);
	}
	
	public void savePriceA(List<Price> price) {
		Set<DPrice> dps = new HashSet<DPrice>();
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(Price.PRICEA_CODE);
		lastUpdated.setName("priceA");
		//
		for(Price pri:price) {
			DPrice temp = new DPrice();
			temp.setPrice(pri.getPrice());
			temp.setType(pri.getType());
			temp.setMaterialCode(pri.getMaterialCode());
			temp.setIndustryCode(pri.getIndustryCode());
			dps.add(temp);
			lastUpdated.setLastUpdate(DateUtil.convert2Date(pri.getLastDate(), "yyyyMMddHHmmss"));
		}
		priceRepo.saveAll(dps);
		lastUpdatedRepo.save(lastUpdated);
	}
	
	public Date getLastUpdated(String code) {
		Optional<LastUpdated> lu = lastUpdate.findById(code);
		if(lu.isPresent()) {
			return lu.get().getLastUpdate();
		}
		Date d = new Date(DEFAULT_DATE);

		return d;
	}
}
