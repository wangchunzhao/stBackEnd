package com.qhc.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.order.entity.OrderInfo;
import com.qhc.order.entity.ReportFormsInfo;
import com.qhc.order.service.ReportService;
import com.qhc.system.domain.PageHelper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author lizuoshan
 *
 */
@RestController
@Api(value = "Report", description = "报表管理")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
//	@ApiOperation(value="带条件查询报表所有信息", notes="带条件查询报表所有信息")
//	@GetMapping(value="reportFormsInfo/{pageNo}/{pageSize}")
//    @ResponseStatus(HttpStatus.OK)
//    public PageHelper<ReportFormsInfo> findByConditions(
//    		@PathVariable int pageNo,
//    		@PathVariable int pageSize,
//    		@RequestParam("sequenceNumber") String sequenceNumber,
//    		@RequestParam("contractorCode") String contractorCode,
//    		@RequestParam("contractUnit") String contractUnit,
//    		@RequestParam("startTime") String startTime,
//    		@RequestParam("endTime") String endTime,
//    		@RequestParam("contractorClassCode") String contractorClassCode,
//    		@RequestParam("isSpecialDiscount") int isSpecialDiscount,
//    		@RequestParam("orderTypeCode") String orderTypeCode,
//    		@RequestParam("officeCode") String officeCode) throws Exception
//    
//    
//	{
//		PageHelper<ReportFormsInfo> pageHelper = new PageHelper<ReportFormsInfo>();
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		ReportFormsInfo reportFormsInfo = new ReportFormsInfo();
//		reportFormsInfo.setSequenceNumber(sequenceNumber);
//		reportFormsInfo.setContractorCode(contractorCode);
//		reportFormsInfo.setContractUnit(contractUnit);
//		reportFormsInfo.setStartTime(startTime);
//		reportFormsInfo.setEndTime(endTime);
//		reportFormsInfo.setContractorClassCode(contractorClassCode);
//		reportFormsInfo.setIsSpecialDiscount(isSpecialDiscount);
//		reportFormsInfo.setOrderTypeCode(orderTypeCode);
//		reportFormsInfo.setOfficeCode(officeCode);
//		
//		Page<ReportFormsInfo> page = reportService.getReportFormsInfoByConditions(reportFormsInfo, pageable);
//		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
//		pageHelper.setRows(page.getContent());
//        return pageHelper;
//    }
	

}
