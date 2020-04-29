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
import org.springframework.transaction.annotation.Transactional;

import com.qhc.sap.dao.CurrencyRepository;
import com.qhc.sap.dao.IncotermRepository;
import com.qhc.sap.dao.PaymentTermRepository;
import com.qhc.sap.dao.PriceRepository;
import com.qhc.sap.dao.SapCurrencySaleTypeRepository;
import com.qhc.sap.dao.SapLastUpdatedRepository;
import com.qhc.sap.domain.CurrencyDto;
import com.qhc.sap.domain.IncotermDto;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.domain.PaymentPlanDto;
import com.qhc.sap.domain.PriceDto;
import com.qhc.sap.entity.Currency;
import com.qhc.sap.entity.Incoterm;
import com.qhc.sap.entity.MaterialPrice;
import com.qhc.sap.entity.PaymentTerm;
import com.qhc.sap.entity.Industry;
import com.qhc.sap.entity.LastUpdated;
import com.qhc.sap.entity.SapCurrencySaleType;
import com.qhc.utils.DateUtil;

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
	private PaymentTermRepository PaymentTermRepo;
	
	@Autowired
	private SapLastUpdatedRepository lastUpdate;
	/**
	 * 
	 * @param currency
	 */
	@Transactional
	public void saveCurrency(List<CurrencyDto> currency) {
		Set<Currency> dcs = new HashSet<Currency>();
		Set<SapCurrencySaleType> sc = new HashSet<SapCurrencySaleType>();
		for(CurrencyDto cur:currency) {
			List<Object> objs = cur.toDaos();
			for(Object obj:objs) {
				if(obj instanceof Currency) {
					dcs.add((Currency) obj);
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
	public List<CurrencyDto> getCurrency(){
		List<CurrencyDto> cs = new ArrayList<CurrencyDto>();
		List<Currency> dcs=currencyRepo.findAll();
		for(Currency cur:dcs) {
			CurrencyDto dc = new CurrencyDto();
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
	@Transactional
	public void saveIncoterm(List<IncotermDto> incoterm) {
		Set<Incoterm> incos = new HashSet<Incoterm>();
		for(IncotermDto inco:incoterm) {
			Incoterm temp = new Incoterm();
			temp.setCode(inco.getCode());
			temp.setName(inco.getName());
			temp.setSapSalesTypeCode(inco.getSapSalesTypeCode());
			incos.add(temp);
		}
		incotermRepo.deleteAll();
		incotermRepo.saveAll(incos);
	}
	/**
	 * 
	 * @return international trade
	 */
	public Map<String,String> getIncoterms(){
		Map<String,String> incos = new HashMap<String,String>();
		List<Incoterm> dincos = incotermRepo.findAll();
		for(Incoterm di:dincos) {
			incos.put(di.getCode(), di.getName());
		}
		return incos;
	}

	@Transactional
	public void savePrice(List<PriceDto> price) {
		Set<MaterialPrice> dps = new HashSet<MaterialPrice>();
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(PriceDto.PRICE_CHANGE_CODE);
		lastUpdated.setName("priceChange");
		//
		for(PriceDto pri:price) {
			MaterialPrice temp = new MaterialPrice();
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
	
	@Transactional
	public void savePriceDate(String date) {
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(PriceDto.PRICE_CODE);
		lastUpdated.setName("price");
		//将日期增加一天
		lastUpdated.setLastUpdate(DateUtil.addDays(DateUtil.convert2Date(date, "yyyyMMdd"), 1));
		
		lastUpdatedRepo.save(lastUpdated);
	}
	
	@Transactional
	public void savePriceADate(String date) {
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(PriceDto.PRICEA_CODE);
		lastUpdated.setName("priceA");
		//将日期增加一天
		lastUpdated.setLastUpdate(DateUtil.addDays(DateUtil.convert2Date(date, "yyyyMMdd"), 1));
		
		lastUpdatedRepo.save(lastUpdated);
	}

	@Transactional
	public void savePriceA(List<PriceDto> price) {
		Set<MaterialPrice> dps = new HashSet<MaterialPrice>();
		//
		LastUpdated lastUpdated = new LastUpdated();
		lastUpdated.setCode(PriceDto.PRICEA_CODE);
		lastUpdated.setName("priceA");
		//
		for(PriceDto pri:price) {
			MaterialPrice temp = new MaterialPrice();
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

	@Transactional
	public void savePaymentPlan(List<PaymentPlanDto> paymentPlan) {
		Set<PaymentTerm> incos = new HashSet<PaymentTerm>();
		for (PaymentPlanDto inco : paymentPlan) {
			PaymentTerm temp = new PaymentTerm();
			temp.setCode(inco.getCode());
			temp.setName(inco.getName());
			temp.setIsPaymentTerm(inco.getPaymentTerm());
			incos.add(temp);
		}
		PaymentTermRepo.deleteAll();
		PaymentTermRepo.saveAll(incos);
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
