/**
 * 
 */
package com.qhc.frye.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.BMaterialGroupOrderRepository;
import com.qhc.frye.dao.CurrencyRepository;
import com.qhc.frye.dao.CustomerClassRepository;
import com.qhc.frye.dao.DIndustryCodeRepository;
import com.qhc.frye.dao.DReceiveTermsRepository;
import com.qhc.frye.dao.DShippingTypeRepository;
import com.qhc.frye.dao.IncotermRepository;
import com.qhc.frye.dao.InstallationTermsRepository;
import com.qhc.frye.dao.MeasurementUnitRepository;
import com.qhc.frye.dao.OrderTypeRepository;
import com.qhc.frye.dao.SalesGroupRepository;
import com.qhc.frye.dao.SalesOfficeRepository;
import com.qhc.frye.dao.SapMaterialGroupsRepository;
import com.qhc.frye.domain.BMaterialGroupOrder;
import com.qhc.frye.domain.CustomerClass;
import com.qhc.frye.domain.DCurrency;
import com.qhc.frye.domain.DIncoterm;
import com.qhc.frye.domain.DIndustryCode;
import com.qhc.frye.domain.DInstallationTerms;
import com.qhc.frye.domain.DMaterialGroups;
import com.qhc.frye.domain.DReceiveTerms;
import com.qhc.frye.domain.DShippingType;
import com.qhc.frye.domain.DUnit;
import com.qhc.frye.domain.OrderTypeCustomerClass;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.domain.SapSalesOffice;
import com.qhc.frye.rest.controller.entity.Currency;

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
	private MeasurementUnitRepository measurementUnitRepository;

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
	
	public static Map<String, Currency> currencies = null;
	
	public static Map<String, Map<String, String>> installationTerms = null;
	
	public static Map<String, String> measurementUnit = null;

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
			List<BMaterialGroupOrder> list = materialGroupOrderRepository.findAll(Sort.by(Order.asc("code")));
			materialGroupOrders = new HashMap<String, String>();

			for (BMaterialGroupOrder bMaterialGroupOrder : list) {
				materialGroupOrders.put(bMaterialGroupOrder.getCode(), bMaterialGroupOrder.getName());
			}
		}

		return materialGroupOrders;
	}

	public Map<String, String> findMaterialGroups() {
		if (materialGroups == null || materialGroups.isEmpty()) {
			List<DMaterialGroups> list = materialGroupsRepository.findAll(Sort.by(Order.asc("code")));
			materialGroups = new HashMap<String, String>();
			materialGroupMapGroupOrders = new HashMap<String, String>();

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
			salesOffices = new HashMap<String, Map<String, String>>();

			for (SapSalesOffice sapSalesOffice : list) {
				String typeCode = sapSalesOffice.getTypeCode();
				Map<String, String> office = salesOffices.get(typeCode);
				if (office == null) {
					office = new HashMap<>();
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
			salesGroups = new HashMap<String, Map<String, String>>();

			for (SapSalesGroup sapSalesGroup : list) {
				String officeCode = sapSalesGroup.getOfficeCode();
				Map<String, String> group = salesGroups.get(officeCode);
				if (group == null) {
					group = new HashMap<>();
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
			shippingTypes = new HashMap<String, String>();

			for (DShippingType dShippingType : list) {
				shippingTypes.put(dShippingType.getCode(), dShippingType.getName());
			}
		}

		return shippingTypes;
	}

	public Map<String, String> findReceiveTerms() {
		if (receiveTerms == null || receiveTerms.isEmpty()) {
			List<DReceiveTerms> list = receiveTermsRepository.findAll(Sort.by(Order.asc("code")));
			receiveTerms = new HashMap<String, String>();

			for (DReceiveTerms dReceiveTerms : list) {
				receiveTerms.put(dReceiveTerms.getCode(), dReceiveTerms.getName());
			}
		}

		return receiveTerms;
	}
	
	public Map<String, String> findIncoterms() {
		if (incoterms == null || incoterms.isEmpty()) {
			List<DIncoterm> list = incotermRepository.findAll(Sort.by(Order.asc("code")));
			incoterms = new HashMap<String, String>();

			for (DIncoterm dIncoterm : list) {
				incoterms.put(dIncoterm.getCode(), dIncoterm.getName());
			}
		}

		return incoterms;
	}
	
	public Map<String, Currency> findCurrencies() {
		if (currencies == null || currencies.isEmpty()) {
			List<DCurrency> list = currencyRepository.findAll(Sort.by(Order.asc("code")));
			currencies = new HashMap<String, Currency>();

			for (DCurrency dCurrency : list) {
				Currency currency = new Currency();
				BeanUtils.copyProperties(dCurrency, currency);
				currencies.put(dCurrency.getCode(), currency);
			}
		}

		return currencies;
	}
	
	public Map<String, Map<String, String>> findInstallationTerms() {
		if (installationTerms == null || installationTerms.isEmpty()) {
			List<DInstallationTerms> list = installationTermsRepository.findAll(Sort.by(Order.asc("code")));
			installationTerms = new HashMap<String, Map<String, String>>();

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
			measurementUnit = new HashMap<String, String>();

			for (DUnit unit : list) {
				measurementUnit.put(unit.getCode(), unit.getName());
			}
		}

		return measurementUnit;
	}

}
