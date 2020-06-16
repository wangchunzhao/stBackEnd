package com.qhc.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.qhc.Constant;
import com.qhc.order.domain.DeliveryAddressDto;
import com.qhc.order.entity.DeliveryAddress;
import com.qhc.order.mapper.DeliveryAddressMapper;
import com.qhc.order.mapper.ReportMapper;
import com.qhc.sap.entity.Currency;
import com.qhc.sap.entity.MaterialGroups;

@Service
public class ReportService {
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private ConstantService constantService;
	
	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;
	
	public PageInfo<Map<String, Object>> findPurchaseSalesReport(Map<String, Object> params) throws JsonMappingException, JsonProcessingException {
		// 设置分页信息
	    int pageNo = params.get("pageNo") == null ? 0 : Integer.valueOf(params.get("pageNo").toString());
	    int pageSize = params.get("pageSize") == null ? 0 : Integer.valueOf(params.get("pageSize").toString());

	    com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
		List<Map<String, Object>> list = reportMapper.findPurchaseAndSalesReport(params);
		translateFieldValue(list);
		
		return new PageInfo<>(list);
	}
	
	public PageInfo<Map<String, Object>>  findBiddingReport(Map<String, Object> params) throws JsonMappingException, JsonProcessingException {
		// 设置分页信息
	    int pageNo = params.get("pageNo") == null ? 0 : Integer.valueOf(params.get("pageNo").toString());
	    int pageSize = params.get("pageSize") == null ? 0 : Integer.valueOf(params.get("pageSize").toString());

	    com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
		List<Map<String, Object>> list = reportMapper.findBiddingReport(params);
		translateFieldValue(list);
		
		return new PageInfo<>(list);
	}
	
	public PageInfo<Map<String, Object>>  findOrderSummaryReport(Map<String, Object> params) throws JsonMappingException, JsonProcessingException {
		// 设置分页信息
	    int pageNo = params.get("pageNo") == null ? 0 : Integer.valueOf(params.get("pageNo").toString());
	    int pageSize = params.get("pageSize") == null ? 0 : Integer.valueOf(params.get("pageSize").toString());

	    com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
		List<Map<String, Object>> list = reportMapper.findOrderSummaryReport(params);
		translateFieldValue(list);
		
		return new PageInfo<>(list);
	}

	private void translateFieldValue(List<Map<String, Object>> list)
			throws JsonProcessingException, JsonMappingException {
		for (Map<String, Object> map : list) {
			Object quoteStatus = map.get("quoteStatus");
			if (quoteStatus != null) {
				if (quoteStatus.equals("01") || quoteStatus.equals("02")) {
					map.put("quoteStatusDesc", "是");
				} else {
					map.put("quoteStatusDesc", "否");
				}
				if (quoteStatus.equals("02")) {
					map.put("makingOrder", "是");
				} else {
					map.put("makingOrder", "否");
				}
			}
			Object saleType = map.get("saleType");
			if (saleType != null) {
				map.put("saleTypeDesc", Constant.saleTypeMap.get(saleType));
			}
			Object status = map.get("status");
			if (status != null) {
				map.put("statusDesc", Constant.statusMap.get(status));
			}
			
			String stOrderType = (String)map.get("stOrderType");
			if (StringUtils.isNotEmpty(stOrderType)) {
				map.put("stOrderTypeDesc", Constant.stOrderTypeMap.get(stOrderType));
			}
			
			String currency = (String)map.get("currency");
			if (StringUtils.isNotEmpty(currency)) {
				Currency c = constantService.findAllCurrency().get(currency);
				if (c != null) {
					map.put("currency", c.getName());
				}
			}
			
			String industryCodeCode = (String)map.get("industryCodeName");
			if (StringUtils.isNotEmpty(industryCodeCode)) {
				String name = constantService.findIndustryCodes().get(industryCodeCode);
				if (name != null) {
					map.put("industryCodeName", name);
				}
			}
			
			String installType = (String)map.get("installType");
			if (StringUtils.isNotEmpty(installType)) {
				String name = constantService.findInstallationTerms().get(installType);
				if (name != null) {
					map.put("installType", name);
				}
			}
			
			String transferType = (String)map.get("transferType");
			if (StringUtils.isNotEmpty(transferType)) {
				String name = constantService.findShippingTypes().get(transferType);
				if (name != null) {
					map.put("transferType", name);
				}
			}
			
			String paymentType = (String)map.get("paymentType");
			if (StringUtils.isNotEmpty(paymentType)) {
				String name = constantService.findDealerPaymentTerms().get(paymentType);
				if (name != null) {
					map.put("paymentType", name);
				}
			}
			
			Integer id = Integer.valueOf(map.get("id").toString());
			List<DeliveryAddressDto> addresses = deliveryAddressMapper.findByOrderInfoId(id);
			DeliveryAddressDto deliveryAddressDto = addresses != null && addresses.size() > 0 ? addresses.get(0) : null;
			if (deliveryAddressDto != null) {
				// 省市區
				map.put("city", StringUtils.trimToEmpty(deliveryAddressDto.getProvinceName()) + StringUtils.trimToEmpty(deliveryAddressDto.getDistrictName()) + StringUtils.trimToEmpty(deliveryAddressDto.getCityName()));
				// 地址
				map.put("address", deliveryAddressDto.getAddress());
			}
			
			// TODO 是否年采客户
			
			// 行项目状态
			String itemStatus = (String)map.get("itemStatus");
			if (StringUtils.isNotEmpty(itemStatus)) {
				map.put("itemStatus", Constant.itemStatusMap.get(itemStatus));
			}
			// 行项目需求计划
			String itemRequirementPlan = (String)map.get("itemRequirementPlan");
			if (StringUtils.isNotEmpty(itemRequirementPlan)) {
				map.put("itemRequirementPlan", Constant.itemRequirementPlanMap.get(itemRequirementPlan));
			}
			// 行项目物料分组
			String materialGroupCode = (String)map.get("materialGroupCode");
			if (StringUtils.isNotEmpty(materialGroupCode)) {
				String name = constantService.findMaterialGroups().get(materialGroupCode);
				if (name != null) {
					map.put("materialGroupCode", name);
				}
			}
			// 行项目物料计量单位
			String unitCode = (String)map.get("unitCode");
			if (StringUtils.isNotEmpty(unitCode)) {
				String name = constantService.findMeasurementUnits().get(unitCode);
				if (name != null) {
					map.put("unitCode", name);
				}
			}
			// 行项目类别
			String itemCategory = (String)map.get("itemCategory");
			if (StringUtils.isNotEmpty(itemCategory)) {
				map.put("itemCategory", Constant.itemCategoryMap.get(itemCategory));
			}
			
			// 行项目物料属性
			Boolean isPurchased = (Boolean)map.get("isPurchased");
			if (isPurchased != null) {
				String name = isPurchased ? "生产" : "采购"; 
				map.put("isPurchased", name);
			}
			
			List<MaterialGroups> margins = new ObjectMapper().readValue(map.get("gross_profit_margin").toString(), new TypeReference<List<MaterialGroups>>() {});
			String margin = BigDecimal.valueOf(margins.get(margins.size() - 1).getGrossProfitMargin() * 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			map.put("margin", margin);
		}
	}
}
