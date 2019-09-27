/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;


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
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.rest.controller.entity.SpecialDeliveryVoInfo;
import com.qhc.frye.service.SpecialDeliveryVoInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author lizuoshan
 *
 */
@RestController
@RequestMapping("specialDeliveryVoInfo")
@Api(value = "SpecialDeliveryVoInfo List in frye", description = "SpecialDeliveryVoInfo info")
public class SpecialVoInfoController {
	
	@Autowired
	private SpecialDeliveryVoInfoService specialDeliveryVoInfoService;
	

	@ApiOperation(value=" Find all SpecialDeliveryVo paging info for specialDelivery ", notes="Find all SpecialDeliveryVo paging info for specialDelivery")
	@GetMapping
    @ResponseStatus(HttpStatus.OK)
	public PageHelper<SpecialDeliveryVoInfo> findPagingList(
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize,
			@RequestParam("sequenceNumber") String sequenceNumber,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("ownerDomainId") String ownerDomainId,
			@RequestParam("officeCode") String officeCode,
			@RequestParam("orderTypeCode") String orderTypeCode) throws Exception{
		
		PageHelper<SpecialDeliveryVoInfo> pageHelper = new PageHelper<SpecialDeliveryVoInfo>();
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		SpecialDeliveryVoInfo sdv = new SpecialDeliveryVoInfo();
		sdv.setSequenceNumber(sequenceNumber);
		sdv.setStartTime(startTime);
		sdv.setEndTime(endTime);
		sdv.setOrderTypeCode(orderTypeCode);
		sdv.setOwnerDomainId(ownerDomainId);
		sdv.setOfficeCode(officeCode);
		Page<SpecialDeliveryVoInfo> page = specialDeliveryVoInfoService.getInfoByConditions(sdv, pageable);

		pageHelper.setTotal(Integer.valueOf(page.getTotalElements()+""));
		pageHelper.setRows(page.getContent());
        return pageHelper;
    }
	
}
