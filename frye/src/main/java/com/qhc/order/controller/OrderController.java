/**
 * 
 */
package com.qhc.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.OrderOption;
import com.qhc.order.domain.OrderQuery;
import com.qhc.order.domain.OrderVersion;
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

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "根据id查询订单", notes = "根据id查询订单")
	@GetMapping(value = "order/{id}")
	public Result findByKOrderVersionId(@PathVariable Integer id) throws Exception {
		Result result = null;
		try {
			OrderDto order = orderService.findOrder(id);
			result = Result.ok(order);
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	/**
	 * 查询订单
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询订单详情", notes = "查询订单详情")
	@GetMapping(value = "order/detail")
	@ResponseStatus(HttpStatus.OK)
	public Result getOrder(@RequestParam String sequenceNumber, @RequestParam String version) {
		Result result = null;
		try {
			OrderDto order = orderService.findOrder(sequenceNumber, version);
			result = Result.ok(order);
		} catch (Exception e) {
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
	 * @throws Exception
	 */
	@ApiOperation(value = "查询订单列表", notes = "查询订单列表")
	@PostMapping(value = "order/query")
	@ResponseStatus(HttpStatus.OK)
	public Result findOrders(@RequestBody OrderQuery query) {
		Result result = null;
		try {
			PageHelper<OrderDto> page = new PageHelper<OrderDto>(orderService.findOrders(query));
			result = Result.ok(page);
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "提交", notes = "提交")
	@PostMapping(value = "order/{user}/submit")
	public Result submit(@PathVariable("user") String user, @RequestBody(required = true) OrderDto order) {
		Result result = null;
		try {
			orderService.submit(user, order);
			result = Result.ok("");
		} catch (Exception e) {
			e.printStackTrace();
			result = Result.error(e.getMessage());
		}

		return result;
	}

	@ApiOperation(value = "提交到BPM", notes = "提交到BPM")
	@PostMapping(value = "order/{user}/submitbpm")
	public Result submitBpm(@PathVariable("user") String user, @RequestBody(required = true) OrderDto order) {
		Result result = null;
		try {
			orderService.submitBpm(user, order);
			result = Result.ok("");
		} catch (Exception e) {
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
	@PutMapping(value = "order/callback")
	@ResponseBody
	public boolean bpmCallback(@RequestBody Map data) {
		String status = String.valueOf(data.get("status"));
		String sequenceNumber = String.valueOf(data.get("sequenceNumber"));
		Double bodyDiscount = Double.valueOf(String.valueOf(data.get("bodyDiscount")));
		Double unitDiscount = Double.valueOf(String.valueOf(data.get("unitDiscount")));

		this.orderService.updateBpmStatus("admin", sequenceNumber, status, bodyDiscount, unitDiscount);

		return true;
	}

	/**
	 * 根据流水号获取销售订单详情并同步SAP
	 * 
	 * @param sequenceNumber
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据sequenceNumber组装订单并同步SAP", notes = "根据sequenceNumber组装订单并同步SAP")
	@PostMapping(value = "order/sap")
	@ResponseStatus(HttpStatus.OK)
	public String orderCreationForSAP(@RequestParam String sequenceNumber, @RequestParam String version)
			throws Exception {
		return orderService.orderCreationForSAP(sequenceNumber, version);
	}

	/**
	 * 查询订单版本历史
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据sequenceNumber查询订单版本历史", notes = "根据sequenceNumber查询订单版本历史")
	@GetMapping(value = "order/{sequenceNumber}/version")
	@ResponseStatus(HttpStatus.OK)
	public List<OrderVersion> orderVersions(@PathVariable String sequenceNumber) throws Exception {
		return orderService.findOrderVersions(sequenceNumber);
	}

	@ApiOperation(value = "计算毛利", notes = "计算毛利")
	@PostMapping(value = "order/grossprofit")
	public List<MaterialGroups> calcGrossProfit(@RequestBody OrderDto order) throws Exception {
		return orderService.calcGrossProfit(order);
	}

	@ApiOperation(value = "计算毛利", notes = "计算毛利")
	@PostMapping(value = "order/{sequenceNumber}/{version}/grossprofit")
	public List<MaterialGroups> calcGrossProfit(@PathVariable String sequenceNumber, @PathVariable String version)
			throws Exception {
		return orderService.calcGrossProfit(sequenceNumber, version);
	}

	@ApiOperation(value = "订单的公共可选择", notes = "所有订单共享的可选项")
	@GetMapping(value = "order/option")
	public OrderOption getOption() throws Exception {
		OrderOption option = new OrderOption();
		try {
			option = orderService.getOrderOption();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return option;
	}

}
