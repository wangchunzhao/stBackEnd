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

import com.qhc.order.entity.MaterialGroupOrder;
import com.qhc.order.mapper.MaterialGroupOrderMapper;
import com.qhc.sap.dao.CurrencyRepository;
import com.qhc.sap.dao.CustomerClassRepository;
import com.qhc.sap.dao.IndustryCodeRepository;
import com.qhc.sap.dao.ReceiveTermsRepository;
import com.qhc.sap.dao.ShippingTypeRepository;
import com.qhc.sap.dao.IncotermRepository;
import com.qhc.sap.dao.InstallationTermsRepository;
import com.qhc.sap.dao.MeasurementUnitRepository;
import com.qhc.sap.dao.OrderTypeRepository;
import com.qhc.sap.dao.PaymentTermRepository;
import com.qhc.sap.dao.SalesGroupRepository;
import com.qhc.sap.dao.SalesOfficeRepository;
import com.qhc.sap.dao.SapCurrencySaleTypeRepository;
import com.qhc.sap.dao.SapMaterialGroupsRepository;
import com.qhc.sap.domain.CurrencyDto;
import com.qhc.sap.entity.CustomerClass;
import com.qhc.sap.entity.Currency;
import com.qhc.sap.entity.Incoterm;
import com.qhc.sap.entity.IndustryCode;
import com.qhc.sap.entity.InstallationTerms;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.sap.entity.ReceiveTerms;
import com.qhc.sap.entity.ShippingType;
import com.qhc.sap.entity.Unit;
import com.qhc.sap.entity.OrderTypeCustomerClass;
import com.qhc.sap.entity.PaymentTerm;
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
	private IndustryCodeRepository industryCodeRepository;

	@Autowired
	private MaterialGroupOrderMapper materialGroupOrderMapper;

	@Autowired
	private SapMaterialGroupsRepository materialGroupsRepository;

	@Autowired
	private SalesOfficeRepository saleOfficeRepository;

	@Autowired
	private SalesGroupRepository salesGroupRepository;

	@Autowired
	private ShippingTypeRepository shippingTypeRepository;

	@Autowired
	private ReceiveTermsRepository receiveTermsRepository;
	
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
	private PaymentTermRepository paymentTermRepository;
	
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
	
	public static Map<String, List<CurrencyDto>> currencies = null;
	
	public static Map<String, Currency> sapCurrencies = null;
	
	public static Map<String, String> installationTerms = null;
	
	public static Map<String, String> measurementUnit = null;
	
	//k_acceptance_Criteria
	public static Map<String, String> acceptanceCriteria = null;

	public static Map<String, String> dealerPaymentTerms = null;

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

	public IndustryCode findIndustryCodeByCode(String code) {
		return industryCodeRepository.findByCode(code);
	}

	public Map<String, String> findFordealerIndustryCodes() {
		if (dealerIndustryCodes == null || dealerIndustryCodes.isEmpty()) {
			List<IndustryCode> industryCodeList = industryCodeRepository.findAllFordealer();
			dealerIndustryCodes = new HashMap<String, String>();

			for (IndustryCode dIndustryCode : industryCodeList) {
				dealerIndustryCodes.put(dIndustryCode.getCode(), dIndustryCode.getName());
			}
		}

		return dealerIndustryCodes;
	}

	public Map<String, String> findMaterialGroupOrders() {
		if (materialGroupOrders == null || materialGroupOrders.isEmpty()) {
			List<MaterialGroupOrder> list = materialGroupOrderMapper.findByParams(null);
			materialGroupOrders = new LinkedHashMap<String, String>();

			for (MaterialGroupOrder bMaterialGroupOrder : list) {
				materialGroupOrders.put(bMaterialGroupOrder.getCode(), bMaterialGroupOrder.getName());
			}
		}

		return materialGroupOrders;
	}

	public Map<String, String> findMaterialGroups() {
		if (materialGroups == null || materialGroups.isEmpty()) {
			List<MaterialGroups> list = materialGroupsRepository.findAll(Sort.by(Order.asc("code")));
			materialGroups = new LinkedHashMap<String, String>();
			materialGroupMapGroupOrders = new LinkedHashMap<String, String>();

			for (MaterialGroups dMaterialGroups : list) {
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
			List<ShippingType> list = shippingTypeRepository.findAll(Sort.by(Order.asc("code")));
			shippingTypes = new LinkedHashMap<String, String>();

			for (ShippingType dShippingType : list) {
				shippingTypes.put(dShippingType.getCode(), dShippingType.getName());
			}
		}

		return shippingTypes;
	}

	public Map<String, String> findReceiveTerms() {
		if (receiveTerms == null || receiveTerms.isEmpty()) {
			List<ReceiveTerms> list = receiveTermsRepository.findAll(Sort.by(Order.asc("code")));
			receiveTerms = new LinkedHashMap<String, String>();

			for (ReceiveTerms dReceiveTerms : list) {
				receiveTerms.put(dReceiveTerms.getCode(), dReceiveTerms.getName());
			}
		}

		return receiveTerms;
	}
	
	public Map<String, String> findIncoterms() {
		if (incoterms == null || incoterms.isEmpty()) {
			List<Incoterm> list = incotermRepository.findAll(Sort.by(Order.asc("code")));
			incoterms = new LinkedHashMap<String, String>();

			for (Incoterm dIncoterm : list) {
				incoterms.put(dIncoterm.getCode(), dIncoterm.getName());
			}
		}

		return incoterms;
	}
	
	public Map<String, Currency> findAllCurrency() {
		if (sapCurrencies == null || sapCurrencies.isEmpty()) {
			sapCurrencies = new HashMap<String, Currency>();
			List<Currency> list = currencyRepository.findAll();
			for (Currency currency : list) {
				sapCurrencies.put(currency.getCode(), currency);
			}
		}
		
		return sapCurrencies;
	}
	
	public Map<String, List<CurrencyDto>> findCurrencies() {
		if (currencies == null || currencies.isEmpty()) {
			currencies = new HashMap<String, List<CurrencyDto>>();
			List<Currency> list = currencyRepository.findAll(Sort.by(Order.asc("code")));
			Map<String, CurrencyDto> cmap = new LinkedHashMap<String, CurrencyDto>();
			list.forEach(dCurrency -> {
				CurrencyDto currency = new CurrencyDto();
				currency.setCode(dCurrency.getCode());
				currency.setName(dCurrency.getName());
				currency.setRate(dCurrency.getRate());
				cmap.put(currency.getCode(), currency);
			});
			List<SapCurrencySaleType> saleTypes = sapCurrencySaleTypeRepo.findAll();
			saleTypes.forEach(cs -> {
				String salesType = cs.getCii().getSaleTypeCode();
				String currencyCode = cs.getCii().getCurrencyCode();
				List<CurrencyDto> currencyList = currencies.get(salesType);
				if (currencyList == null) {
					currencyList = new ArrayList<CurrencyDto>();
					currencies.put(salesType, currencyList);
				}
				currencyList.add(cmap.get(currencyCode));
			});
		}

		return currencies;
	}
	
	public Map<String, String> findInstallationTerms() {
		if (installationTerms == null || installationTerms.isEmpty()) {
			installationTerms = new LinkedHashMap<String, String>();
			installationTerms.put("01", "招标");
			installationTerms.put("02", "自安自保");
		}

		return installationTerms;
	}
	
	public Map<String, String> findMeasurementUnits() {
		if (measurementUnit == null || measurementUnit.isEmpty()) {
			List<Unit> list = measurementUnitRepository.findAll(Sort.by(Order.asc("code")));
			measurementUnit = new LinkedHashMap<String, String>();

			for (Unit unit : list) {
				measurementUnit.put(unit.getCode(), unit.getName());
			}
		}

		return measurementUnit;
	}
	
	public Map<String, String> findAcceptanceCriteriaes() {
		if (acceptanceCriteria == null || acceptanceCriteria.isEmpty()) {
//			List<AcceptanceCriteria> list = acceptanceCriteriaRepository.findAll(Sort.by(Order.asc("code")));
			acceptanceCriteria = new LinkedHashMap<String, String>();

//			for (AcceptanceCriteria unit : list) {
//				acceptanceCriteria.put(unit.getCode(), unit.getName());
//			}
//			1001	需方负责安装调试
//			1002	供方负责安装调试
			acceptanceCriteria.put("1001", "需方负责安装调试");
			acceptanceCriteria.put("1002", "供方负责安装调试");
		}

		return acceptanceCriteria;
	}
	
	public Map<String, String> findDealerPaymentTerms() {
		if (dealerPaymentTerms == null || dealerPaymentTerms.isEmpty()) {
			List<PaymentTerm> list = paymentTermRepository.findByCodeLike("QA%", Sort.by(Order.asc("code")));
			dealerPaymentTerms = new LinkedHashMap<String, String>();

			for (PaymentTerm paymentTerm : list) {
				dealerPaymentTerms.put(paymentTerm.getCode(), paymentTerm.getName());
			}
		}

		return dealerPaymentTerms;
	}

}
