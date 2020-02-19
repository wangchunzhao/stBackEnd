package com.qhc.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qhc.order.domain.SpecialDeliveryVo;
import com.qhc.order.entity.SpecialOrderApplication;
import com.qhc.order.mapper.SpecialOrderApplicationMapper;

@Service
public class SpecialDeliveryService {

	@Autowired
	private SpecialOrderApplicationMapper specialOrderApplicationMapper;

	public List<SpecialDeliveryVo> find(Map<String, Object> params) {
		return null;
	}

	@Transactional
	public SpecialOrderApplication saveOrUpdate(SpecialOrderApplication sd) {
		if (sd.getId() == null) {
			specialOrderApplicationMapper.insert(sd);
		} else {
			specialOrderApplicationMapper.update(sd);
		}
		return sd;
	}

	public SpecialOrderApplication findById(Integer applyId) {
		return specialOrderApplicationMapper.findById(applyId);
	}
}
