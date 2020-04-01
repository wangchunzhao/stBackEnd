package com.qhc.order.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderOption;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.OrderVersion;
import com.qhc.order.service.GrossProfitMarginService;
import com.qhc.order.service.OrderService;
import com.qhc.sap.entity.MaterialGroups;
import com.qhc.system.domain.PageHelper;
import com.qhc.system.domain.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Walker
 *
 */
@RestController
@Api(value = "Order management in qhc", description = "订单管理")
public class OrderController {
	
	Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private GrossProfitMarginService grossProfitMarginService;

	/**
	 * 查询订单
	 * 
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	@ApiOperation(value = "查询订单详情", notes = "查询订单详情")
	@GetMapping(value = "order/{orderInfoId}")
	@ResponseStatus(HttpStatus.OK)
	public Result getOrder(@PathVariable("orderInfoId") Integer orderInfoId) {
		Result result = null;
		try {
			OrderDto order = orderService.findOrder(orderInfoId);
			result = Result.ok(order);
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 查询订单
	 * 
	 * @param query
	 * @return
	 * @throws Throwable
	 */
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表")
	@PostMapping(value = "order/query")
	@ResponseStatus(HttpStatus.OK)
	public Result findOrders(@RequestBody OrderQuery query) {
		Result result = null;
		try {
			PageHelper<OrderDto> page = new PageHelper<OrderDto>(orderService.findOrders(query));
			result = Result.ok(page);
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "保存订单信息", notes = "保存订单信息")
	@PostMapping(value = "order/{user}")
	public Result saveOrder(@PathVariable("user") String user, @RequestBody(required = true) OrderDto order) {
		Result result = null;
		try {
			orderService.save(user, order);
			result = Result.ok("");
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "提交", notes = "提交")
	@PostMapping(value = "order/submit/{user}")
	public Result submit(@PathVariable("user") String user, @RequestBody(required = true) OrderDto order) {
		Result result = null;
		try {
			orderService.submit(user, order);
			result = Result.ok("");
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "驳回", notes = "驳回")
	@PostMapping(value = "order/{orderInfoId}/reject/{user}")
	public Result reject(@PathVariable("user") String user, @PathVariable(required = true) Integer orderInfoId) {
		Result result = null;
		try {
			orderService.reject(user, orderInfoId);
			result = Result.ok("");
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "变更", notes = "变更")
	@PostMapping(value = "order/{orderInfoId}/upgrade/{user}")
	@ResponseBody
	public Result upgrade(@PathVariable("user") String user, @PathVariable(required = true) Integer orderInfoId) {
		Result result = null;
		try {
			orderService.upgrade(user, orderInfoId);
			result = Result.ok("");
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "复制", notes = "复制")
	@PostMapping(value = "order/{orderInfoId}/copy/{user}")
	@ResponseBody
	public Result copy(@PathVariable(name="user", required = true) String user, @PathVariable(name="orderInfoId", required = true) Integer orderInfoId) {
		Result result = null;
		try {
			OrderDto order = orderService.copy(user, orderInfoId);
			result = Result.ok(order);
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "报价下单", notes = "报价下单")
	@PostMapping(value = "order/{orderInfoId}/transfer/{user}")
	@ResponseBody
	public Result transferToOrder(@PathVariable(name="user", required = true) String user, @PathVariable(name="orderInfoId", required = true) Integer orderInfoId) {
		Result result = null;
		try {
			orderService.transfer(user, orderInfoId);
			result = Result.ok("");
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "提交到BPM", notes = "提交到BPM")
	@PostMapping(value = "order/submitbpm/{user}")
	public Result submitBpm(@PathVariable("user") String user, @RequestBody(required = true) OrderDto order) {
		Result result = null;
		try {
			orderService.submitBpm(user, order);
			result = Result.ok("");
		} catch (Throwable e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	/**
	 * 订单BPM回调接口
	 * <li>sequenceNumber</li>
	 * <li>status</li>
	 * <li>bodyDiscount</li>
	 * <li>unitDiscount</li>
	 * 
	 * @return
	 */
	@ApiOperation(value = "BPM callback", notes = "BPM callback")
	@PostMapping(value = "order/callback")
	@ResponseBody
	public boolean bpmCallback(@RequestBody List<Map> data) {
		try {
			
//			String status = String.valueOf(data.get("status"));
//			String sequenceNumber = String.valueOf(data.get("sequenceNumber"));
//			Double bodyDiscount = Double.valueOf(String.valueOf(data.get("bodyDiscount")));
//			Double unitDiscount = Double.valueOf(String.valueOf(data.get("unitDiscount")));
			System.out.println("=================================1111111111111=============================");
//			this.orderService.updateBpmStatus("admin", sequenceNumber, status, bodyDiscount, unitDiscount);

			return true;
		} catch (Throwable e) {
			logger.error("BPM call back", e);
			return false;
		}
	}

	/**
	 * 根据流水号获取销售订单详情并同步SAP
	 * 
	 * @param sequenceNumber
	 * @return
	 * @throws Throwable
	 */
	@ApiOperation(value = "根据sequenceNumber组装订单并同步SAP", notes = "根据sequenceNumber组装订单并同步SAP")
	@PostMapping(value = "order/{orderInfoId}/sap/{user}")
	@ResponseStatus(HttpStatus.OK)
	public Result sendOrderToSap(@PathVariable("orderInfoId") Integer orderInfoId, @PathVariable("user") String user)
			throws Throwable {
		Result result = null;
		try {
			String res = orderService.sendToSap(user, orderInfoId);
			result = Result.ok(res);
		} catch (Throwable e) {
			logger.error("订单下发SAP失败", e);
			result = Result.error("订单下发SAP失败！" + e.getMessage());
		}
		
		return result;
	}

	/**
	 * 根据流水号获取销售订单详情并同步SAP
	 * 
	 * @param sequenceNumber
	 * @return
	 * @throws Throwable
	 */
	@ApiOperation(value = "修改报价单报价状态", notes = "修改报价单报价状态")
	@PostMapping(value = "order/quote/{user},{orderInfoId},{status}")
	@ResponseStatus(HttpStatus.OK)
	public Result updateQuoteStatus(@PathVariable("orderInfoId") Integer orderInfoId, @PathVariable("status") String status, @PathVariable("user") String user)
			throws Throwable {
		Result result = null;
		try {
			String res = orderService.updateQuoteStatus(user, orderInfoId, status);
			result = Result.ok(res);
		} catch (Throwable e) {
			logger.error("订单下发SAP失败", e);
			result = Result.error("订单下发SAP失败！");
		}
		
		return result;
	}

	/**
	 * 查询订单版本历史
	 * 
	 * @param orderId
	 * @return
	 * @throws Throwable
	 */
	@ApiOperation(value = "根据sequenceNumber查询订单版本历史", notes = "根据sequenceNumber查询订单版本历史")
	@GetMapping(value = "order/{sequenceNumber}/version")
	@ResponseStatus(HttpStatus.OK)
	public Result orderVersions(@PathVariable String sequenceNumber) throws Throwable {
		Result result = null;
		try {
			List<OrderVersion> list = orderService.findOrderVersions(sequenceNumber);
			result = Result.ok(list);
		} catch (Throwable e) {
			logger.error("查询订单版本历史失败", e);
			result = Result.error("查询订单版本历史失败！");
		}
		
		return result;
	}

	@ApiOperation(value = "计算毛利", notes = "计算毛利")
	@PostMapping(value = "order/grossprofit")
	public Result calcGrossProfit(@RequestBody OrderDto order) throws Throwable {
		Result result = null;
		try {
			List<MaterialGroups> list = grossProfitMarginService.calculate(order);
			result = Result.ok(list);
		} catch (Throwable e) {
			logger.error("计算毛利失败", e);
			result = Result.error("计算毛利失败！");
		}
		
		return result;
	}

	@ApiOperation(value = "订单的公共可选项", notes = "所有订单共享的可选项")
	@GetMapping(value = "order/option")
	public Result getOption() throws Throwable {
		Result result = null;
		try {
			OrderOption option = orderService.getOrderOption();
			result = Result.ok(option);
		} catch (Throwable e) {
			logger.error("订单的公共可选择失败", e);
			result = Result.error("订单的公共可选择失败！");
		}

		return result;
	}

}
