package com.qhc.order.service;

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
import com.qhc.order.mapper.ReportMapper;
import com.qhc.sap.entity.Currency;
import com.qhc.sap.entity.MaterialGroups;

@Service
public class ReportService {
	
	@Autowired
	private ReportMapper reportMapper;
	
	@Autowired
	private ConstantService constantService;
	
	public List<Map<String, Object>> findPurchaseSalesReport(Map<String, Object> params) {
		String createTime = (String)params.get("createTime");
		if (StringUtils.isNoneEmpty(createTime)) {
			String[] times = createTime.split(" - ");
			params.put("startTime", times[0]);
			params.put("endTime", times[1]);
		}
		
		List<Map<String, Object>> list = reportMapper.findPurchaseAndSalesReport(params);
		
		return list;
	}
	
	public PageInfo<Map<String, Object>>  findBiddingReport(Map<String, Object> params) throws JsonMappingException, JsonProcessingException {
		String createTime = (String)params.get("createTime");
//		if (StringUtils.isNoneEmpty(createTime)) {
//			String[] times = createTime.split(" - ");
//			params.put("startTime", times[0]);
//			params.put("endTime", times[1]);
//		}
		
		// 设置分页信息
	    int pageNo = params.get("pageNo") == null ? 0 : Integer.valueOf(params.get("pageNo").toString());
	    int pageSize = params.get("pageSize") == null ? 0 : Integer.valueOf(params.get("pageSize").toString());

	    com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
		List<Map<String, Object>> list = reportMapper.findBiddingReport(params);
		for (Map<String, Object> map : list) {
			Object quoteStatus = map.get("quoteStatus");
			if (quoteStatus != null && quoteStatus.equals("01")) {
				map.put("quoteStatusDesc", "是");
			} else {
				map.put("quoteStatusDesc", "否");
			}
			Object saleType = map.get("saleType");
			if (saleType.equals("10")) {
				map.put("saleTypeDesc", "内销");
			}
			if (saleType.equals("20")) {
				map.put("saleTypeDesc", "出口");
			}
			if (saleType.equals("30")) {
				map.put("saleTypeDesc", "冷库");
			}
			Object status = map.get("status");
			if (status.equals("05")) {
				map.put("statusDesc", "BPM审批通过");
			}
//			if (saleType.equals("01")) {
//				map.put("statusDesc", "冷库");
//			}
//			if (saleType.equals("30")) {
//				map.put("statusDesc", "冷库");
//			}
//			if (saleType.equals("30")) {
//				map.put("statusDesc", "冷库");
//			}
//			if (saleType.equals("30")) {
//				map.put("statusDesc", "冷库");
//			}
//			if (saleType.equals("30")) {
//				map.put("statusDesc", "冷库");
//			}
			List<MaterialGroups> margins = new ObjectMapper().readValue(map.get("gross_profit_margin").toString(), new TypeReference<List<MaterialGroups>>() {});
			map.put("margin", margins.get(margins.size() - 1).getGrossProfitMargin());
		}
		
		return new PageInfo<>(list);
	}
	
	public List<Map<String, Object>>  findOrderSummaryReport(Map<String, Object> params) {
		String createTime = (String)params.get("createTime");
		if (StringUtils.isNoneEmpty(createTime)) {
			String[] times = createTime.split(" - ");
			params.put("startTime", times[0]);
			params.put("endTime", times[1]);
		}
		
		List<Map<String, Object>> list = reportMapper.findOrderSummaryReport(params);
		
		for (Map<String, Object> map : list) {
			String currency = (String)map.get("currency");
			if (StringUtils.isNotEmpty(currency)) {
				Currency c = constantService.findAllCurrency().get(currency);
				if (c != null) {
					map.put("currency", c.getName());
				}
			}
		}
		
		return list;
	}
}
