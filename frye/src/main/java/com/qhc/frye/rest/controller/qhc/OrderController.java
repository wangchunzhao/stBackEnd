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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.frye.dao.SalesGroupRepository;
import com.qhc.frye.domain.DMaterialGroups;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.GrossProfitDTO;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.rest.controller.entity.form.AbsOrder;
import com.qhc.frye.rest.controller.entity.form.BaseOrder;
import com.qhc.frye.rest.controller.entity.form.DealerOrder;
import com.qhc.frye.rest.controller.entity.B2CComments;
import com.qhc.frye.rest.controller.entity.OrderOption;
import com.qhc.frye.rest.controller.entity.OrderQuery;
import com.qhc.frye.rest.controller.entity.OrderVersion;
import com.qhc.frye.rest.controller.entity.PageHelper;
import com.qhc.frye.rest.controller.entity.PaymentPlan;
import com.qhc.frye.rest.controller.entity.SalesOrder;
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

	@ApiOperation(value = "保存或修改结算方式", notes = "保存或修改结算方式")
	@PutMapping(value = "order/paymentPlan")
	@ResponseStatus(HttpStatus.OK)
	public void putCurrency(@RequestBody(required = true) @Valid List<PaymentPlan> paymentPlan) {
		orderService.savePaymentPlan(paymentPlan);
	}

	@ApiOperation(value = "保存订单信息", notes = "保存订单信息")
	@PostMapping(value = "order")
	@ResponseStatus(HttpStatus.OK)
	public void submitOrder(@RequestBody(required = true) DealerOrder order) throws Exception {
		//
		switch(order.getSubmitType()) {
			case 1:
				//submit order
				orderService.save(order);
				break;
			case 2:
				//save order
				orderService.save(order);
				break;
			default:
				throw new Exception();
		}
		
	}
	
	@ApiOperation(value = "B2c审核订单", notes = "B2c审核订单")
	@PostMapping(value = "order/b2c")
	@ResponseStatus(HttpStatus.OK)
	public void approvedByB2C(@RequestParam int isApproved,@RequestParam String seqnum,@RequestParam String version,@RequestBody List<B2CComments> b2cs) throws Exception{
		boolean isPro = false;
		if(isApproved!=0)
			isPro = true;
		orderService.b2cCost(isPro, seqnum, version, b2cs);
	}
	
	
	@ApiOperation(value = "工程经理审核", notes = "工程经理审核订单")
	@PostMapping(value = "order/engineering")
	@ResponseStatus(HttpStatus.OK)
	public void approvedByEngineering(@RequestParam int isApproved,@RequestParam String seqnum,@RequestParam String version,@RequestParam double cost,@RequestParam double cost2,@RequestParam double cost3,@RequestParam double cost4) throws Exception{
		
	}
	
	@ApiOperation(value = "BPM送审", notes = "BPM送审")
	@PostMapping(value = "order/bpm")
	@ResponseStatus(HttpStatus.OK)
	public void toBPM() throws Exception{
		
	}

	@ApiOperation(value = "查询订单类型", notes = "查询订单类型")
	@GetMapping(value = "order/salesType")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, String> getOrderType() throws Exception {
		Map<String, String> saleTypes = new HashMap<String, String>();
		List<DSalesType> dsl = orderService.getSalesTypes();
		for (DSalesType ds : dsl) {
			saleTypes.put(ds.getCode(), ds.getName());
		}
		return saleTypes;

	}

//	@ApiOperation(value = "计算毛利", notes = "计算毛利")
//	@PostMapping(value = "order/salesOrder")
//	@ResponseStatus(HttpStatus.OK)
//	public List<SapSalesGroup> getGrossProfit(@RequestBody SalesOrder salesOrder) throws Exception {
//		GrossProfitDTO grossProfitDTO = new GrossProfitDTO();
//		grossProfitDTO.setSalesOrder(salesOrder);
//		grossProfitDTO.setSapSalesGroupList(salesGroupRepository.findAll());
//
//		return this.getGrossProfitDetail(grossProfitDTO);
//	}
//
//	@ApiOperation(value = "计算毛利", notes = "计算毛利")
//	@PostMapping(value = "order/grossProfitDTO")
//	@ResponseStatus(HttpStatus.OK)
//	public List<SapSalesGroup> getGrossProfitDetail(@RequestBody GrossProfitDTO grossProfitDTO) throws Exception {
//
//		return orderService.findGrossProfitBySalesOrder(grossProfitDTO.getSalesOrder(),
//				grossProfitDTO.getSapSalesGroupList());
//	}

	@ApiOperation(value = "计算毛利", notes = "计算毛利")
	@PostMapping(value = "order/grossprofit")
	@ResponseStatus(HttpStatus.OK)
	public List<DMaterialGroups> calcGrossProfit(@RequestBody BaseOrder order) throws Exception {
		return orderService.calcGrossProfit(order);
	}

	@ApiOperation(value = "计算毛利", notes = "计算毛利")
	@PostMapping(value = "order/{sequenceNumber}/{version}/wtwgrossprofit")
	@ResponseStatus(HttpStatus.OK)
	public List<DMaterialGroups> calcWtwGrossProfit(@PathVariable String sequenceNumber, @PathVariable String version) throws Exception {
		return orderService.calcWtwGrossProfit(sequenceNumber, version);
	}

	@ApiOperation(value = "计算毛利", notes = "计算毛利")
	@PostMapping(value = "order/{sequenceNumber}/{version}/grossprofit")
	@ResponseStatus(HttpStatus.OK)
	public List<DMaterialGroups> calcGrossProfit(@PathVariable String sequenceNumber, @PathVariable String version) throws Exception {
		return orderService.calcGrossProfit(sequenceNumber, version);
	}

	@ApiOperation(value = "根据id查询订单", notes = "根据id查询订单")
	@GetMapping(value = "order/dOrder/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DOrder findByKOrderVersionId(@PathVariable String id) throws Exception {

		return orderService.findByKOrderVersionId(id);

	}

	@ApiOperation(value = "订单的公共可选择", notes = "所有订单共享的可选项")
	@GetMapping(value = "order/option")
	@ResponseStatus(HttpStatus.OK)
	public OrderOption getOption() throws Exception {
		OrderOption option = new OrderOption();
		try {
			option = orderService.getOrderOption();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return option;
	}
		    
    /**
     * 根据流水号获取销售订单详情并同步SAP
     * @param sequenceNumber
     * @return
     * @throws Exception
     */
    @ApiOperation(value="根据sequenceNumber组装订单并同步SAP", notes="根据sequenceNumber组装订单并同步SAP")
    @PostMapping(value = "order/sap")
    @ResponseStatus(HttpStatus.OK)
    public String orderCreationForSAP(@RequestParam String sequenceNumber, @RequestParam String version) throws Exception {	
    	return orderService.orderCreationForSAP(sequenceNumber, version);
    }
    
    /**
     * 查询订单版本历史
     * @param orderId
     * @return
     * @throws Exception
     */
    @ApiOperation(value="根据sequenceNumber查询订单版本历史", notes="根据sequenceNumber查询订单版本历史")
    @GetMapping(value = "order/{sequenceNumber}/version")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderVersion> orderVersions(@PathVariable String sequenceNumber) throws Exception {	
    	return orderService.findOrderVersions(sequenceNumber);
    }
    
    /**
     * 查询订单
     * @param orderId
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查询订单列表", notes="查询订单列表")
    @PostMapping(value = "order/query")
    @ResponseStatus(HttpStatus.OK)
    public PageHelper<AbsOrder> findOrders(@RequestBody OrderQuery query) throws Exception {	
    	return orderService.findOrders(query);
    }
    
    /**
     * 查询订单
     * @param orderId
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查询订单详情", notes="查询订单详情")
    @GetMapping(value = "order/detail")
    @ResponseStatus(HttpStatus.OK)
    public AbsOrder getOrder(@RequestParam String sequenceNumber, @RequestParam String version) throws Exception {	
    	return orderService.findOrder(sequenceNumber, version);
    }
    
    /**
     * 查询订单
     * @param orderId
     * @return
     * @throws Exception
     */
    @ApiOperation(value="查询订单类型", notes="查询订单类型")
    @GetMapping(value = "order/type")
    @ResponseStatus(HttpStatus.OK)
    public String getDealerOrder(@RequestParam String sequenceNumber) throws Exception {	
    	return orderService.getOrderType(sequenceNumber);
    }

}
