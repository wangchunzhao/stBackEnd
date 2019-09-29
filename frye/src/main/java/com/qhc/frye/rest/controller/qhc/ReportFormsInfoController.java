package com.qhc.frye.rest.controller.qhc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.domain.ReportFormsInfo;
//import com.qhc.frye.domain.ReportFormsInfo;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.service.ReportFormsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("reportFormsInfo")
@Api(value = "ReportFormsInfo", description = "report forms Info")
public class ReportFormsInfoController {
	
	@Autowired
	private ReportFormsInfoService reportFormsInfoService;
	
	@ApiOperation(value=" Find all report forms info ", notes="Find all report forms info")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageHelper<ReportFormsInfo> findByConditions(
    		@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sequenceNumber") String sequenceNumber,
			@RequestParam("contractorCode") String contractorCode,
			@RequestParam("contractUnit") String contractUnit,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("contractorClassCode") String contractorClassCode,
			@RequestParam("isSpecialDiscount") int isSpecialDiscount,
			@RequestParam("orderTypeCode") String orderTypeCode,
			@RequestParam("officeCode") String officeCode) throws Exception
    
    
	{
		PageHelper<ReportFormsInfo> pageHelper = new PageHelper<ReportFormsInfo>();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		ReportFormsInfo reportFormsInfo = new ReportFormsInfo();
		reportFormsInfo.setSequenceNumber(sequenceNumber);
		reportFormsInfo.setContractorCode(contractorCode);
		reportFormsInfo.setContractUnit(contractUnit);
		reportFormsInfo.setStartTime(startTime);
		reportFormsInfo.setEndTime(endTime);
		reportFormsInfo.setContractorClassCode(contractorClassCode);
		reportFormsInfo.setIsSpecialDiscount(isSpecialDiscount);
		reportFormsInfo.setOrderTypeCode(orderTypeCode);
		reportFormsInfo.setOfficeCode(officeCode);
		
		Page<ReportFormsInfo> page = reportFormsInfoService.getReportFormsInfoByConditions(reportFormsInfo, pageable);
		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }
	

}
