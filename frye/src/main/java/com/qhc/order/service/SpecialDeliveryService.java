package com.qhc.order.service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.qhc.order.domain.OrderDto;
import com.qhc.order.domain.SpecialDeliveryDto;
import com.qhc.order.entity.SpecialAttachment;
import com.qhc.order.entity.SpecialOrderApplication;
import com.qhc.order.mapper.SpecialOrderApplicationMapper;
import com.qhc.system.entity.User;

@Service
public class SpecialDeliveryService {

	@Autowired
	private SpecialOrderApplicationMapper specialOrderApplicationMapper;
	
	@Autowired
	private OrderService orderService;

	public PageInfo<SpecialDeliveryDto> find(Map<String, Object> params) {
		// 设置分页信息
		int pageNo = params.get("pageNo") == null ? 0 : Integer.parseInt(String.valueOf(params.get("pageNo")));
		int pageSize = params.get("pageSize") == null ? 10000 : Integer.parseInt(String.valueOf(params.get("pageSize")));

		com.github.pagehelper.PageHelper.startPage(pageNo, pageSize);
		List<SpecialDeliveryDto> specials = specialOrderApplicationMapper.findByParams(params);

		return new PageInfo<>(specials);
	}

	@Transactional
	public SpecialDeliveryDto save(String user, SpecialDeliveryDto sd) throws IllegalAccessException, InvocationTargetException {
		SpecialOrderApplication entity = new SpecialOrderApplication();
		sd.setApplyer(user);
		BeanUtils.copyProperties(entity, sd);
		if (sd.getId() == null) {
			specialOrderApplicationMapper.insert(entity);
		} else {
			specialOrderApplicationMapper.update(entity);
		}
		List<SpecialAttachment> attachments = sd.getAttachments();
		return findById(entity.getId());
	}

	public SpecialDeliveryDto findById(Integer applyId) {
		Map<String, Object> params = new HashMap<String, Object>() {
			{
				put("id", applyId);
			}
		};
		PageInfo<SpecialDeliveryDto> list = find(params);
		if (list.getList()  != null && list.getList().size() > 0) {
			return list.getList().get(0);
		} else {
			return null;
		}
	}

	/**
	 * 提交特批发货
	 * @param user
	 * @param sd
	 * @return
	 * @throws Exception 
	 */
	public void submit(String user, SpecialDeliveryDto sd) throws Exception {
		sd = save(user, sd);
		Integer orderInfoId = sd.getOrderInfoId();
		OrderDto order = orderService.findOrder(orderInfoId);
		orderService.submitBpm(user, order);
		sd.setApplyStatus(1);
		this.save(user, sd);
	}
}
