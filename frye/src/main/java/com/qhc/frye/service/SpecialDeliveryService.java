package com.qhc.frye.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qhc.frye.dao.SpecialDeliveryRepository;
import com.qhc.frye.domain.SpecialDelivery;

@Service
public class SpecialDeliveryService {

	@Autowired
	private SpecialDeliveryRepository specialDeliveryRepository;



	public SpecialDelivery saveOrUpdate(SpecialDelivery sd) {
		
		return specialDeliveryRepository.save(sd);
	}

	public List<SpecialDelivery> findByOrdersId(Integer kOrdersId) {
		return specialDeliveryRepository.findByKOrderVersionId(kOrdersId);
	}

	public SpecialDelivery findById(Integer applyId) {
		return specialDeliveryRepository.getOne(applyId);
	}
}
