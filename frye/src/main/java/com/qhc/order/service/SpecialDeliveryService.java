package com.qhc.order.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.order.entity.SpecialOrderApplication;
import com.qhc.order.mapper.SpecialOrderApplicationMapper;

@Service
public class SpecialDeliveryService {

	@Autowired
	private SpecialOrderApplicationMapper specialOrderApplicationMapper;



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
