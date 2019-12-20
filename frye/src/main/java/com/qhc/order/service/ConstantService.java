/**
 * 
 */
package com.qhc.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.qhc.order.dao.AcceptanceCriteriaRepository;
import com.qhc.order.domain.Currency;
import com.qhc.order.entity.AcceptanceCriteria;
import com.qhc.order.entity.MaterialGroupOrder;
import com.qhc.sap.dao.BMaterialGroupOrderRepository;
import com.qhc.sap.dao.CurrencyRepository;
import com.qhc.sap.dao.CustomerClassRepository;
import com.qhc.sap.dao.DIndustryCodeRepository;
import com.qhc.sap.dao.DReceiveTermsRepository;
import com.qhc.sap.dao.DShippingTypeRepository;
import com.qhc.sap.dao.IncotermRepository;
import com.qhc.sap.dao.InstallationTermsRepository;
import com.qhc.sap.dao.MeasurementUnitRepository;
import com.qhc.sap.dao.OrderTypeRepository;
import com.qhc.sap.dao.SalesGroupRepository;
import com.qhc.sap.dao.SalesOfficeRepository;
import com.qhc.sap.dao.SapCurrencySaleTypeRepository;
import com.qhc.sap.dao.SapMaterialGroupsRepository;
import com.qhc.sap.entity.CustomerClass;
import com.qhc.sap.entity.DCurrency;
import com.qhc.sap.entity.DIncoterm;
import com.qhc.sap.entity.DIndustryCode;
import com.qhc.sap.entity.DInstallationTerms;
import com.qhc.sap.entity.DMaterialGroups;
import com.qhc.sap.entity.DReceiveTerms;
import com.qhc.sap.entity.DShippingType;
import com.qhc.sap.entity.DUnit;
import com.qhc.sap.entity.OrderTypeCustomerClass;
import com.qhc.sap.entity.SapCurrencySaleType;
import com.qhc.sap.entity.SapSalesGroup;
import com.qhc.sap.entity.SapSalesOffice;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class ConstantService {

	private final static String KEY = "00";

	@Autowired
	private CustomerClassRepository customerClassRepo;

	@Autowired
	private OrderTypeRepository orderTypeRepository;

	@Autowired
	private DIndustryCodeRepository industryCodeRepository;

	@Autowired
	private BMaterialGroupOrderRepository materialGroupOrderRepository;

	@Autowired
	private SapMaterialGroupsRepository materialGroupsRepository;

	@Autowired
	private SalesOfficeRepository saleOfficeRepository;

	@Autowired
	private SalesGroupRepository salesGroupRepository;

	@Autowired
	private DShippingTypeRepository shippingTypeRepository;

	@Autowired
	private DReceiveTermsRepository receiveTermsRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private IncotermRepository incotermRepository;
	
	@Autowired
	private InstallationTermsRepository installationTermsRepository;
	
	@Autowired
	private SapCurrencySaleTypeRepository sapCurrencySaleTypeRepo;
	
	@Autowired
	private MeasurementUnitRepository measurementUnitRepository;
	
	@Autowired
	private AcceptanceCriteriaRepository acceptanceCriteriaRepository;

	public static Map<String, String> customerClazz;

	public static Map<String, String> orderType;// Map<customerClassCode,List<ordertype code>>

	public static Map<String, String> dealerIndustryCodes = null;

	public static Map<String, String> materialGroupOrders = null;

	public static Map<String, String> materialGroups = null;

	public static Map<String, String> materialGroupMapGroupOrders = null;

	public static Map<String, Map<String, String>> salesOffices = null;

	public static Map<String, Map<String, String>> salesGroups = null;

	public static Map<String, String> shippingTypes = null;

	public static Map<String, String> receiveTerms = null;
	
	public static Map<String, String> incoterms = null;
	
	public static Map<String, List<Currency>> currencies = null;
	
	public static Map<String, Map<String, String>> installationTerms = null;
	
	public static Map<String, String> measurementUnit = null;
	
	//k_acceptance_Criteria
	public static Map<String, String> acceptanceCriteria = null;

	public Map<String, String> findAllCustomerClazz() {
		if (customerClazz == null || customerClazz.isEmpty()) {
			customerClazz = new HashMap<String, String>();
			List<CustomerClass> cucList = customerClassRepo.findAll();
			for (CustomerClass cc : cucList) {
				customerClazz.put(cc.getCode(), cc.getName());
			}
		}
		return customerClazz;
	}

	public String findCustomerClazzByCode(String code) {

		return this.findAllCustomerClazz().get(code);

	}

	public Map<String, String> getOrderTypes() {
		if (orderType == null || orderType.isEmpty()) {
			orderType = new HashMap<String, String>();
			List<OrderTypeCustomerClass> ods = orderTypeRepository.findAll();

			for (OrderTypeCustomerClass od : ods) {
				if (orderType.putIfAbsent(od.getOtcci().getClazzCode(), od.getOtcci().getTypeCode()) != null) {
					orderType.put(KEY, od.getOtcci().getTypeCode());
				}
			}
		}
		return orderType;

	}

	public DIndustryCode findIndustryCodeByCode(String code) {
		return industryCodeRepository.findByCode(code);
	}

	public Map<String, String> findFordealerIndustryCodes() {
		if (dealerIndustryCodes == null || dealerIndustryCodes.isEmpty()) {
			List<DIndustryCode> industryCodeList = industryCodeRepository.findAllFordealer();
			dealerIndustryCodes = new HashMap<String, String>();

			for (DIndustryCode dIndustryCode : industryCodeList) {
				dealerIndustryCodes.put(dIndustryCode.getCode(), dIndustryCode.getName());
			}
		}

		return dealerIndustryCodes;
	}

	public Map<String, String> findMaterialGroupOrders() {
		if (materialGroupOrders == null || materialGroupOrders.isEmpty()) {
			List<MaterialGroupOrder> list = materialGroupOrderRepository.findAll(Sort.by(Order.asc("code")));
			materialGroupOrders = new LinkedHashMap<String, String>();

			for (MaterialGroupOrder bMaterialGroupOrder : list) {
				materialGroupOrders.put(bMaterialGroupOrder.getCode(), bMaterialGroupOrder.getName());
			}
		}

		return materialGroupOrders;
	}

	public Map<String, String> findMaterialGroups() {
		if (materialGroups == null || materialGroups.isEmpty()) {
			List<DMaterialGroups> list = materialGroupsRepository.findAll(Sort.by(Order.asc("code")));
			materialGroups = new LinkedHashMap<String, String>();
			materialGroupMapGroupOrders = new LinkedHashMap<String, String>();

			for (DMaterialGroups dMaterialGroups : list) {
				String groupCode = dMaterialGroups.getCode();
				materialGroups.put(groupCode, dMaterialGroups.getName());

				materialGroupMapGroupOrders.put(groupCode, dMaterialGroups.getMaterialGroupOrderCode());
			}
		}

		return materialGroups;
	}

	public Map<String, String> findMaterialGroupMapGroupOrders() {
		findMaterialGroups();
		return materialGroupMapGroupOrders;
	}

	public Map<String, Map<String, String>> findSalesOffices() {
		if (salesOffices == null || salesOffices.isEmpty()) {
			List<SapSalesOffice> list = saleOfficeRepository.findAll(Sort.by(Order.asc("code")));
			salesOffices = new LinkedHashMap<String, Map<String, String>>();

			for (SapSalesOffice sapSalesOffice : list) {
				String typeCode = sapSalesOffice.getTypeCode();
				Map<String, String> office = salesOffices.get(typeCode);
				if (office == null) {
					office = new LinkedHashMap<String, String>();
					salesOffices.put(typeCode, office);
				}
				office.put(sapSalesOffice.getCode(), sapSalesOffice.getName());
			}
		}

		return salesOffices;
	}

	public Map<String, Map<String, String>> findSalesGroups() {
		if (salesGroups == null || salesGroups.isEmpty()) {
			List<SapSalesGroup> list = salesGroupRepository.findAll(Sort.by(Order.asc("code")));
			salesGroups = new LinkedHashMap<String, Map<String, String>>();

			for (SapSalesGroup sapSalesGroup : list) {
				String officeCode = sapSalesGroup.getOfficeCode();
				Map<String, String> group = salesGroups.get(officeCode);
				if (group == null) {
					group = new LinkedHashMap<String, String>();
					salesGroups.put(officeCode, group);
				}
				group.put(sapSalesGroup.getCode(), sapSalesGroup.getName());
			}
		}

		return salesGroups;
	}

	public Map<String, String> findShippingTypes() {
		if (shippingTypes == null || shippingTypes.isEmpty()) {
			List<DShippingType> list = shippingTypeRepository.findAll(Sort.by(Order.asc("code")));
			shippingTypes = new LinkedHashMap<String, String>();

			for (DShippingType dShippingType : list) {
				shippingTypes.put(dShippingType.getCode(), dShippingType.getName());
			}
		}

		return shippingTypes;
	}

	public Map<String, String> findReceiveTerms() {
		if (receiveTerms == null || receiveTerms.isEmpty()) {
			List<DReceiveTerms> list = receiveTermsRepository.findAll(Sort.by(Order.asc("code")));
			receiveTerms = new LinkedHashMap<String, String>();

			for (DReceiveTerms dReceiveTerms : list) {
				receiveTerms.put(dReceiveTerms.getCode(), dReceiveTerms.getName());
			}
		}

		return receiveTerms;
	}
	
	public Map<String, String> findIncoterms() {
		if (incoterms == null || incoterms.isEmpty()) {
			List<DIncoterm> list = incotermRepository.findAll(Sort.by(Order.asc("code")));
			incoterms = new LinkedHashMap<String, String>();

			for (DIncoterm dIncoterm : list) {
				incoterms.put(dIncoterm.getCode(), dIncoterm.getName());
			}
		}

		return incoterms;
	}
	
	public Map<String, List<Currency>> findCurrencies() {
		if (currencies == null || currencies.isEmpty()) {
			currencies = new HashMap<String, List<Currency>>();
			List<DCurrency> list = currencyRepository.findAll(Sort.by(Order.asc("code")));
			Map<String, Currency> cmap = new LinkedHashMap<String, Currency>();
			list.forEach(dCurrency -> {
				Currency currency = new Currency();
				currency.setCode(dCurrency.getCode());
				currency.setName(dCurrency.getName());
				currency.setRate(dCurrency.getRate());
				cmap.put(currency.getCode(), currency);
			});
			List<SapCurrencySaleType> saleTypes = sapCurrencySaleTypeRepo.findAll();
			saleTypes.forEach(cs -> {
				String salesType = cs.getCii().getSaleTypeCode();
				String currencyCode = cs.getCii().getCurrencyCode();
				List<Currency> currencyList = currencies.get(salesType);
				if (currencyList == null) {
					currencyList = new ArrayList<Currency>();
					currencies.put(salesType, currencyList);
				}
				currencyList.add(cmap.get(currencyCode));
			});
		}

		return currencies;
	}
	
	public Map<String, Map<String, String>> findInstallationTerms() {
		if (installationTerms == null || installationTerms.isEmpty()) {
			List<DInstallationTerms> list = installationTermsRepository.findAll(Sort.by(Order.asc("code")));
			installationTerms = new LinkedHashMap<String, Map<String, String>>();

			for (DInstallationTerms term : list) {
				String classCode = term.getCustomerClassCode();
				Map<String, String> terms = installationTerms.get(classCode);
				if (terms == null) {
					terms = new HashMap<String, String>();
					installationTerms.put(classCode, terms);
				}
				terms.put(term.getCode(), term.getName());
			}
		}

		return installationTerms;
	}
	
	public Map<String, String> findMeasurementUnits() {
		if (measurementUnit == null || measurementUnit.isEmpty()) {
			List<DUnit> list = measurementUnitRepository.findAll(Sort.by(Order.asc("code")));
			measurementUnit = new LinkedHashMap<String, String>();

			for (DUnit unit : list) {
				measurementUnit.put(unit.getCode(), unit.getName());
			}
		}

		return measurementUnit;
	}
	
	public Map<String, String> findAcceptanceCriteriaes() {
		if (acceptanceCriteria == null || acceptanceCriteria.isEmpty()) {
			List<AcceptanceCriteria> list = acceptanceCriteriaRepository.findAll(Sort.by(Order.asc("code")));
			acceptanceCriteria = new LinkedHashMap<String, String>();

			for (AcceptanceCriteria unit : list) {
				acceptanceCriteria.put(unit.getCode(), unit.getName());
			}
		}

		return acceptanceCriteria;
	}

}
