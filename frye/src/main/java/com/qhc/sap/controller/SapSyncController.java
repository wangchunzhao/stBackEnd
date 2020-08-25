package com.qhc.sap.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.sap.SapItemStatus;
import com.qhc.order.domain.sap.SapOrderStatus;
import com.qhc.order.entity.OrderInfo;
import com.qhc.order.mapper.OrderInfoMapper;
import com.qhc.order.mapper.ItemMapper;
import com.qhc.order.service.OrderService;
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
import com.qhc.sap.domain.QueryOrderStatusDto;
import com.qhc.sap.domain.QueryOrdersStatusBody;
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
	
	@Autowired
	OrderInfoMapper orderInfoMapper;
	
	@Autowired
	ItemMapper itemMapper;
	
	
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
	
	@Scheduled(cron = "0 10 12 * * ?")
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
	
	//每天凌晨5点执行一次
	@Scheduled(cron = "0 0 5 * * ?")
	@ApiOperation(value = "从SAP同步订单开工日期入库日期交货日期定时任务")
	@GetMapping(value = "UpdateSellingToolOrderStatus")
	@ResponseStatus(HttpStatus.OK)
	public void sapOrderStatus() {
		logger.info("每日自动同步sap订单状态开始==============================================");
		logger.info("读取订单数据【订单-最早生产时间 为空 或者 行项目-生产开始时间/入库时间/最早交货时间 为空】");
		List<Map<String, Object>> contractNumberList = orderInfoMapper.findForUpdateSapStatus();
		logger.info("订单数据： {}", contractNumberList);
		for (Map<String, Object> map : contractNumberList) {
			try {
				Integer orderInfoId = (Integer)map.get("id");
				String contractNumber = (String)map.get("contract_number");
				logger.info("读取SAP订单状态：ContractNumber： {}", contractNumber);
				SapOrderStatus sapOrderStatus = sapService.getOrderStatus(contractNumber);
				logger.info("SAP订单状态：{}", sapOrderStatus);
				List<SapItemStatus> itemStatusList = sapOrderStatus.getItems();
				String firstDeliveryDate = null; // 最早开工日期
				for (SapItemStatus itemStatus : itemStatusList) {
					Integer rowNum = Integer.valueOf(itemStatus.getRowNum());
					String receiptDate = StringUtils.trimToEmpty(itemStatus.getReceiptDate()); // 入库日期
					String productionStartdate = StringUtils.trimToEmpty(itemStatus.getProductionStartdate()); // 开工日期
					String firstIssueDate = receiptDate; // StringUtils.trimToEmpty(itemStatus.getFirstIssueDate()); // 首次GI日期
					
					if (productionStartdate.length() > 0) {
						if (firstDeliveryDate == null) {
							firstDeliveryDate = firstIssueDate;
						}
						if (firstDeliveryDate.compareTo(firstIssueDate) > 0) {
							firstDeliveryDate = firstIssueDate;
						}
					}
					
					// update item
					if (productionStartdate.length() > 0 || receiptDate.length() >0 || firstIssueDate.length() > 0) {
						Date onStoreDate = strToDate(receiptDate);
						Date produceDate = strToDate(productionStartdate);
						// Date deliveryDate = strToDate(firstIssueDate);
						Map<String, Object> itemData = new HashMap<>();
						itemData.put("orderInfoId", orderInfoId);
						itemData.put("rowNum", rowNum);
						itemData.put("onStoreDate", onStoreDate);
						itemData.put("produceDate", produceDate);
						 itemData.put("deliveryDate", onStoreDate);

						logger.info("更新行项目状态：{}", itemData);
						itemMapper.updateSapStatus(itemData);
					}
				}
				
				// update order
				if (firstDeliveryDate != null) {
					Date earliestDeliveryDate = strToDate(firstDeliveryDate);
					Map<String, Object> orderData = new HashMap<>();
					orderData.put("id", orderInfoId);
					orderData.put("earliestDeliveryDate", earliestDeliveryDate);
					
					logger.info("更新订单状态：{}", orderData);
					orderInfoMapper.updateSapStatus(orderData);
				}
			} catch (ParseException e) {
				logger.error("", e);
			}
		}
	}
    
    //每天8 - 19点每10分鐘执行一次
    @Scheduled(cron = "0 0/10 8-23 * * ?")
    @ApiOperation(value = "从SAP同步订单下推后处理状态")
    @GetMapping(value = "syncSendSapOrderStatus")
    @ResponseStatus(HttpStatus.OK)
    public void sapTempOrderStatus() {
        logger.info("自动同步下推sap订单处理状态开始==============================================");
        logger.info("读取已下推sap未处理订单数据【订单状态为07/08，并且为激活状态】");
        List<OrderInfo> toSapTempOrders = orderInfoMapper.findSapTempOrder();
        logger.info("订单数据： {}", toSapTempOrders);
        
        List<QueryOrdersStatusBody> queryOrders = new ArrayList<>();
        Map<String, Integer> contractNumberMap = new HashMap<>();
        for (OrderInfo orderInfo : toSapTempOrders) {
          QueryOrdersStatusBody queryOrder = new QueryOrdersStatusBody();
          queryOrder.setVBELN(orderInfo.getContractNumber()); //订单合同号
          String status = orderInfo.getStatus();
          if (OrderDto.ORDER_STATUS_SAP_TEMP.equals(status)) {
            queryOrder.setUPDKZ("I"); //状态   U是订单变更，I是新建订单
          } else {
            queryOrder.setUPDKZ("U"); //状态   U是订单变更，I是新建订单
          }
          queryOrders.add(queryOrder);
          // 存储合同号与orderinfo id映射关系
          contractNumberMap.put(queryOrder.getVBELN(), orderInfo.getId());
        }
        logger.info("查询下推sap订单状态参数： {}", queryOrders);
        List<QueryOrderStatusDto>orderStatusList = sapService.queryOrderStatus(queryOrders);
        logger.info("查询下推sap订单状态结果： {}", orderStatusList);
        for (QueryOrderStatusDto queryOrderStatusDto : orderStatusList) {
          String contractNumber = queryOrderStatusDto.getVbeln(); //订单号 vbeln
          String status = StringUtils.trimToEmpty(queryOrderStatusDto.getSubrc()); //订单创建状态   subrc  S/E
          String updated = StringUtils.trimToEmpty(queryOrderStatusDto.getUpdkz()); //是否订单变更    updkz U/I
          String comments = queryOrderStatusDto.getMessage(); //订单创建message   message 
          
          Integer id = contractNumberMap.get(contractNumber);
          if (id == null) {
            logger.error("SAP下推订单状态查询错误，合同号【{}】没有找到对应主键ID", contractNumber);
            continue;
          }
          Map<String, Object> params = new HashMap<>();
          params.put("id", id);
          if (status.equals("")) {
        	  continue;
          } else if (status.equals("S")) {
            params.put("status", OrderDto.ORDER_STATUS_SAP);
          } else {
            if (updated.equals("I")) {
              params.put("status", OrderDto.ORDER_STATUS_APPROVED);
            } else {
              params.put("status", OrderDto.ORDER_STATUS_APPROVED_UPDATE);
            }
            params.put("comments", comments);
          }
          
          orderInfoMapper.updateSapTempOrderStatus(params);
        }
    }

	private Date strToDate(String strDate)
			throws ParseException {
		Date date = null;
		if (strDate.length() > 0) {
			if (strDate.startsWith("0")) {
				date = null;
			} else {
				date = new SimpleDateFormat("yyyyMMdd").parse(strDate);
			}
		}
		return date;
	}
	
}
