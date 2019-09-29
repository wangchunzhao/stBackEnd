package com.qhc.frye.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.DOrderRepository;
import com.qhc.frye.dao.KOrderInfoRepository;
import com.qhc.frye.dao.KParentorderVersionRepository;
import com.qhc.frye.dao.OrderSupportInforRepository;
import com.qhc.frye.dao.SalesOrderRepository;
import com.qhc.frye.dao.SalesTypeRepository;
import com.qhc.frye.dao.SalesorderVersionRepository;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.OrderSupportInfo;
import com.qhc.frye.rest.controller.entity.AbsOrder;

@Service
public class OrderService {
	
	@Autowired
	private SalesTypeRepository salesTypeRepo;
	
	@Autowired
	private SalesorderVersionRepository versionRepo;
	
	@Autowired
	private KOrderInfoRepository orderInfoRepo; 
	
	@Autowired
	private DOrderRepository dOrderRepository; 
	
	@Autowired
	private OrderSupportInforRepository supportRepo;
	
	
//	@Autowired
//	private KParentorderVersionRepository parentVerRepo;
	
	/**
	 * 
	 * @return sales type
	 */
	public List<DSalesType> getSalesTypes(){
		return salesTypeRepo.findAll();
	}
	/**
	 * 
	 * @param absOrder
	 */
	public void save(AbsOrder absOrder){
		DOrder sDorder = dOrderRepository.saveAndFlush(absOrder.getDorder());
		OrderSupportInfo ori = absOrder.getSupportInforOfOrder();
		ori.setOrderId(sDorder.getId());
		supportRepo.saveAndFlush(ori);
		KOrderVersion over = absOrder.getOrderVersion();
		over.setkOrdersId(sDorder.getId());
		KOrderVersion kov = versionRepo.saveAndFlush(over);
	}
	/**
	 * 
	 * @param absOrder
	 */
	public void update(AbsOrder absOrder){
		
	}
	
	public DOrder findByKOrderVersionId(String id) {
		
		return dOrderRepository.getOne(id);
	}

}
