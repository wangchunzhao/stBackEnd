package com.qhc.sap.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.order.service.SapOrderService;
import com.qhc.sap.domain.CharacteristicValueDto;
import com.qhc.sap.domain.Clazz;
import com.qhc.sap.domain.ColorDto;
import com.qhc.sap.domain.CurrencyDto;
import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.domain.DefaultCharacteristicsDto;
import com.qhc.sap.domain.IncotermDto;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.domain.PaymentPlanDto;
import com.qhc.sap.domain.PriceDto;
import com.qhc.sap.domain.SalesGroup;
import com.qhc.sap.service.CharacteristicService;
import com.qhc.sap.service.CurrencyService;
import com.qhc.sap.service.CustomerService;
import com.qhc.sap.service.LocationService;
import com.qhc.sap.service.MaterialService;
import com.qhc.sap.service.SapService;
import com.qhc.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "同步sap数据进入销售工具", description = "同步sap数据进入销售工具")
public class SapSyncController {
	
	private static Logger logger = LoggerFactory.getLogger(SapSyncController.class);
	
	@Autowired
	SapService sapService;
	
	@Autowired
	CurrencyService currencyService;
	
	@Autowired
	CharacteristicService characteristicService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	MaterialService materialService;
	
	
	@ApiOperation(value = "同步sap的币种信息并写入销售工具数据库")
	@GetMapping(value = "sycCurrency")
	@ResponseStatus(HttpStatus.OK)
	public void sycCurrency() throws Exception {
		List<CurrencyDto> temp = sapService.getCurrencyFromSap();
		currencyService.saveCurrency(temp);
	}
	
	@ApiOperation(value = "同步sap的信息并写入销售工具数据库")
	@GetMapping(value = "sycIncoterm")
	@ResponseStatus(HttpStatus.OK)
	public void sycIncoterm() throws Exception {
		List<IncotermDto> temp = sapService.getIncotermFromSap();
		currencyService.saveIncoterm(temp);
	}
	
	@ApiOperation(value = "同步sap的class信息并写入销售工具数据库")
	@GetMapping(value = "sycClass")
	@ResponseStatus(HttpStatus.OK)
	public void sycClasses() throws Exception {
		List<Clazz> clazz = sapService.getClassesFromSap();
		characteristicService.saveClass(clazz);
	}
	
	@ApiOperation(value = "同步sap的Customer信息并写入销售工具数据库")
	@GetMapping(value = "sycCustomer")
	@ResponseStatus(HttpStatus.OK)
	public void sycCustomers() throws Exception {
		List<CustomerDto> temp = sapService.getCustomersFromSap();
		customerService.saveCustomers(temp);
	}
	
	@ApiOperation(value = "同步sap的大区和中心信息并写入销售工具数据库")
	@GetMapping(value = "sycOffices")
	@ResponseStatus(HttpStatus.OK)
	public void sycSalesOffices() throws Exception {
		List<SalesGroup> temp = sapService.getSalesgroupFromSAP();
		locationService.put(temp);
	}
	
	@ApiOperation(value = "同步sap的结算方式信息并写入销售工具数据库")
	@GetMapping(value = "sycPaymentPlan")
	@ResponseStatus(HttpStatus.OK)
	public void sycPaymentPlan() throws Exception {
		List<PaymentPlanDto> lp = sapService.getPaymentFromSAP();
		currencyService.savePaymentPlan(lp);
	}
	
	@ApiOperation(value = "同步sap的CharacteristicValue信息并写入销售工具数据库")
	@GetMapping(value = "sycCharacteristicValue")
	@ResponseStatus(HttpStatus.OK)
	public void sycCharacteristic() throws Exception {
		List<CharacteristicValueDto>  values = sapService.getClassesAndCharacteristicValueFromSap();
		characteristicService.saveCharacteristicValue(values);
	}
	
	@ApiOperation(value="同步sap的material信息并写入销售工具数据库")
	@GetMapping(value = "sycMaterial",produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void sycMaterials() throws Exception{
		for (int i = 0; i < 1000; i++) {
			List<MaterialDto> matList = sapService.getNewestMaterialsFromSap();
			if (matList.size() > 0) {
				materialService.saveMaterials(matList);
			} else {
				System.out.println("物料数据抽取完毕");
				break;
			}
		}
	}
	
	@ApiOperation(value = "同步sap的price信息并写入销售工具数据库")
	@GetMapping(value = "sycPrice")
	@ResponseStatus(HttpStatus.OK)
	public void sycPrices() throws Exception {
		for (int i = 0; i < 1000; i++) {
			if(i == 0) {
				String date = DateUtil.convert2String(currencyService.getLastUpdated(PriceDto.PRICE_CODE),"yyyyMMddHHmmss");
				List<PriceDto> temp = sapService.getPriceFromSap(date,"");
				if (temp.size() > 0) {
					currencyService.savePrice(temp);
				}else {
					currencyService.savePriceDate(date.substring(0, 8));
					System.out.println("price数据抽取完毕");
					break;
				}
			}else {
				String date = DateUtil.convert2String(currencyService.getLastUpdated(PriceDto.PRICE_CODE),"yyyyMMddHHmmss");
				String updateDate = DateUtil.convert2String(currencyService.getLastUpdated(PriceDto.PRICE_CHANGE_CODE),"yyyyMMddHHmmss");
				List<PriceDto> temp = sapService.getPriceFromSap(date,updateDate);
				if (temp.size() > 0) {
					currencyService.savePrice(temp);
				}else {
					currencyService.savePriceDate(date.substring(0, 8));
					System.out.println("price数据抽取完毕");
					break;
				}
			}
		}
	}
	
	@ApiOperation(value = "同步sap的priceA信息并写入销售工具数据库")
	@GetMapping(value = "sycPriceA")
	@ResponseStatus(HttpStatus.OK)
	public void sycPricesA() throws Exception {
		for (int i = 0; i < 1000; i++) {
			if(i == 0) {
				String date = DateUtil.convert2String(currencyService.getLastUpdated(PriceDto.PRICEA_CODE),"yyyyMMddHHmmss");
				List<PriceDto> temp = sapService.getPriceAFromSap(date,"");
				if (temp.size() > 0) {
					currencyService.savePriceA(temp);
				} else {
					currencyService.savePriceADate(date.substring(0, 8));
					System.out.println("priceA数据抽取完毕");
					break;
				}
			}else {
				String date = DateUtil.convert2String(currencyService.getLastUpdated(PriceDto.PRICEA_CODE),"yyyyMMddHHmmss");
				String updateDate = DateUtil.convert2String(currencyService.getLastUpdated(PriceDto.PRICEA_CHANGE_CODE),"yyyyMMddHHmmss");
				List<PriceDto> temp = sapService.getPriceAFromSap(date,updateDate);
				if (temp.size() > 0) {
					currencyService.savePriceA(temp);
				} else {
					currencyService.savePriceADate(date.substring(0, 8));
					System.out.println("priceA数据抽取完毕");
					break;
				}
			}
			
		}
	}
	
	//每天12点执行一次
	@Scheduled(cron = "0 0 12 * * ?")
	@ApiOperation(value = "同步sap的默认特征信息并写入销售工具数据库")
	@GetMapping(value = "sycDefaultCharacteristic")
	@ResponseStatus(HttpStatus.OK)
	public void sycDefaultCharacteristic() throws Exception {
		List<DefaultCharacteristicsDto> defaultList = sapService.getDefaultCharacteristics();
		characteristicService.saveCharacteristicDefault(defaultList);
	}
	
	@ApiOperation(value = "同步sap的颜色可选项信息并写入销售工具数据库")
	@GetMapping(value = "sycColor")
	@ResponseStatus(HttpStatus.OK)
	public void sycColor() throws Exception {
		List<ColorDto> prclassList = sapService.getColorPrclass();
		characteristicService.savePrclassColor(prclassList);
		List<ColorDto> coclassList = sapService.getColorCoclass();
		characteristicService.saveCoclassColor(coclassList);
		List<ColorDto> paclassList = sapService.getColorPaclass();
		characteristicService.savePaclassColor(paclassList);
		List<ColorDto> pamappList = sapService.getColorPamapp();
		characteristicService.savePamappColor(pamappList);
	}
	
//	@ApiOperation(value = "同步")
//	@GetMapping(value = "syc")
//	@ResponseStatus(HttpStatus.OK)
//	public void sycorder() throws Exception {
//		SapOrderStatus defaultList = sapService.getOrderStatus("QHCAA11130");
//		System.out.println("1111111");
//	}
	
	
	/*	
	1.currency
	2.incoterm
	3.class
	4.customer
	5.offices
	6.paymentPlan
	7.CharacteristicValue
	8.materials
	9.price
	10.priceA
	11.defaultCharacteristic 
	12.color
	*/
	//每天凌晨1点执行一次
	@Scheduled(cron = "0 0 1 * * ?")
	@ApiOperation(value = "同步sap数据进入销售工具")
	@GetMapping(value = "SapToSellingTool")
	@ResponseStatus(HttpStatus.OK)
	public void sapToSellingTool() {
		try {
			logger.info("每日自动同步sap数据开始==============================================");
			logger.info("同步currency数据");
			//currency
			List<CurrencyDto> temp = sapService.getCurrencyFromSap();
			currencyService.saveCurrency(temp);
			logger.info("同步incoterm数据");
			//incoterm
			List<IncotermDto> incotermtTemp = sapService.getIncotermFromSap();
			currencyService.saveIncoterm(incotermtTemp);
			logger.info("同步class数据");
			//class
			List<Clazz> clazz = sapService.getClassesFromSap();
			characteristicService.saveClass(clazz);
			logger.info("同步customer数据");
			//customer
			List<CustomerDto> customerTemp = sapService.getCustomersFromSap();
			customerService.saveCustomers(customerTemp);
			logger.info("同步offices数据");
			//offices
			List<SalesGroup> SalesGroupTemp = sapService.getSalesgroupFromSAP();
			locationService.put(SalesGroupTemp);
			logger.info("同步paymentPlan数据");
			//paymentPlan
			List<PaymentPlanDto> lp = sapService.getPaymentFromSAP();
			currencyService.savePaymentPlan(lp);
			logger.info("同步CharacteristicValue数据");
			//CharacteristicValue
			List<CharacteristicValueDto>  values = sapService.getClassesAndCharacteristicValueFromSap();
			characteristicService.saveCharacteristicValue(values);
			logger.info("同步materials数据");
			//materials
			this.sycMaterials();
			logger.info("同步price数据");
			//price
			this.sycPrices();
			logger.info("同步priceA数据");
			//priceA
			this.sycPricesA();
			logger.info("同步defaultCharacteristic数据");
			//defaultCharacteristic
			List<DefaultCharacteristicsDto> defaultList = sapService.getDefaultCharacteristics();
			characteristicService.saveCharacteristicDefault(defaultList);
			logger.info("同步color数据");
			//color
			this.sycColor();
			logger.info("每日自动同步sap数据结束=============================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
