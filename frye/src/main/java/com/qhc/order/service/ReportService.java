package com.qhc.order.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.order.mapper.ReportMapper;
import com.qhc.sap.entity.Currency;

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
	
	public List<Map<String, Object>>  findBiddingReport(Map<String, Object> params) {
		String createTime = (String)params.get("createTime");
		if (StringUtils.isNoneEmpty(createTime)) {
			String[] times = createTime.split(" - ");
			params.put("startTime", times[0]);
			params.put("endTime", times[1]);
		}
		
		List<Map<String, Object>> list = reportMapper.findBiddingReport(params);
		
		return list;
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
