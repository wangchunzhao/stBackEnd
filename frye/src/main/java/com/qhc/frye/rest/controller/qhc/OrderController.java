/**
 * 
 */
package com.qhc.frye.rest.controller.qhc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.dao.BAreaRepository;
import com.qhc.frye.dao.BCityRepository;
import com.qhc.frye.dao.BProvinceRepository;
import com.qhc.frye.dao.SalesGroupRepository;
import com.qhc.frye.domain.BProvince;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.GrossProfitDTO;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.rest.controller.entity.Currency;
import com.qhc.frye.rest.controller.entity.OrderForm;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.OrderVersion;
import com.qhc.frye.rest.controller.entity.PaymentPlan;
import com.qhc.frye.rest.controller.entity.SalesOrder;
import com.qhc.frye.service.BAreaService;
import com.qhc.frye.service.BCityService;
import com.qhc.frye.service.BProvinceService;
import com.qhc.frye.service.MaterialService;
import com.qhc.frye.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Order management in Frye", description = "订单管理")
public class OrderController {
	

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private SalesGroupRepository salesGroupRepository;
	
	@ApiOperation(value = "保存或修改结算方式",notes="保存或修改结算方式")
	@PutMapping(value = "order/paymentPlan")
	@ResponseStatus(HttpStatus.OK)
	public void putCurrency(@RequestBody(required = true) @Valid List<PaymentPlan> paymentPlan) {
		orderService.savePaymentPlan(paymentPlan);
	}
	
	@ApiOperation(value="保存订单信息", notes="保存订单信息")
    @PostMapping(value = "order")
    @ResponseStatus(HttpStatus.OK)
    public void submitOrder(@RequestBody(required=true)  OrderForm order) throws Exception
    {	
		//	
		orderService.save(order);
    }

	
	@ApiOperation(value="查询订单类型", notes="查询订单类型")
    @GetMapping(value = "order/salesType")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getOrderType() throws Exception
    {	
		Map<String,String> saleTypes = new HashMap<String,String>();
		List<DSalesType> dsl =  orderService.getSalesTypes();
		for(DSalesType ds:dsl) {
			saleTypes.put(ds.getCode(), ds.getName());
		}
		return saleTypes;
		
    }
	
	
	@ApiOperation(value="计算毛利", notes="计算毛利")
    @PostMapping(value = "order/salesOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<SapSalesGroup> getGrossProfit(@RequestBody SalesOrder salesOrder) throws Exception
    {	
    	GrossProfitDTO grossProfitDTO = new GrossProfitDTO();
    	grossProfitDTO.setSalesOrder(salesOrder);
    	grossProfitDTO.setSapSalesGroupList(salesGroupRepository.findAll());
    	
		return this.getGrossProfitDetail(grossProfitDTO);
    }
    
	@ApiOperation(value="计算毛利", notes="计算毛利")
    @PostMapping(value = "order/grossProfitDTO")
    @ResponseStatus(HttpStatus.OK)
    public List<SapSalesGroup> getGrossProfitDetail(@RequestBody GrossProfitDTO grossProfitDTO) throws Exception
    {	
    	
		return orderService.findGrossProfitBySalesOrder(grossProfitDTO.getSalesOrder(), grossProfitDTO.getSapSalesGroupList());
    }
    
    @ApiOperation(value="根据id查询订单", notes="根据id查询订单")
    @GetMapping(value = "order/dOrder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DOrder findByKOrderVersionId(@PathVariable String id) throws Exception
    {	
		
		return orderService.findByKOrderVersionId(id);
		
    }
    
    @ApiOperation(value="订单的公共可选择", notes="所有订单共享的可选项")
    @GetMapping(value = "order/option")
    @ResponseStatus(HttpStatus.OK)
    public OrderOption getOption() throws Exception
    {	
    	
		return orderService.getOrderOption();
		
    }
    
    /**
     * 根据流水号获取销售订单详情并同步SAP
     * @param sequenceNumber
     * @return
     * @throws Exception
     */
    @ApiOperation(value="根据sequenceNumber组装订单并同步SAP", notes="根据sequenceNumber组装订单并同步SAP")
    @GetMapping(value = "order/creationSap/{sequenceNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String orderCreationForSAP(@PathVariable String sequenceNumber) throws Exception {	
    	return orderService.orderCreationForSAP(sequenceNumber);
    }

    
    /**
     * 查询订单版本历史
     * @param orderId
     * @return
     * @throws Exception
     */
    @ApiOperation(value="根据orderId查询订单版本历史", notes="根据orderId查询订单版本历史")
    @GetMapping(value = "order/{orderId}/version")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderVersion> orderVersions(@PathVariable String orderId) throws Exception {	
    	return orderService.findOrderVersionsByOrderId(orderId);
    }

}
