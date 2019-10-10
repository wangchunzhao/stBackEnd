package com.qhc.frye.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.frye.dao.DOrderRepository;
import com.qhc.frye.dao.ItemDetailRepository;
import com.qhc.frye.dao.KOrderInfoRepository;
import com.qhc.frye.dao.OrderFormRepository;
import com.qhc.frye.dao.OrderSupportInforRepository;
import com.qhc.frye.dao.SalesTypeRepository;
import com.qhc.frye.dao.SalesorderVersionRepository;
import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.DSalesType;
import com.qhc.frye.domain.ItemDetails;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.OrderSupportInfo;
import com.qhc.frye.domain.SapSalesGroup;
import com.qhc.frye.rest.controller.entity.AbsOrder;
import com.qhc.frye.rest.controller.entity.SalesOrder;

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
	
	@Autowired
	private OrderFormRepository orderFormRepository;
	
	@Autowired
	private ItemDetailRepository itemDetailRepository;
	
	
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
	public void save(AbsOrder order){
		
		DOrder sDorder = dOrderRepository.saveAndFlush(order.getDorder());
		OrderSupportInfo ori = order.getSupportInforOfOrder();
		if(!order.getContractNumber().trim().isEmpty())
			ori.setOrderId(sDorder.getId());
			supportRepo.saveAndFlush(ori);
		KOrderVersion over = order.getOrderVersion();
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

	
	public List<SapSalesGroup> findGrossProfitBySalesOrder(SalesOrder saleOrder,List<SapSalesGroup> salesGroups) {
		
		List<ItemDetails> details = new ArrayList<ItemDetails>();;
		if(saleOrder.getItemsForm()!=null) {
			details = saleOrder.getItemsForm().getDetailsList();
		}
		//提交类型 3.margin 4.wtw margin
		int submitType = saleOrder.getSubmitType();
		
		//税率
		Double taxRate = saleOrder.getTaxRate();
		
		//物料类型表
		//sapSalesGroups
		
		//毛利表
		BigDecimal sumAmount = BigDecimal.ZERO;//金额
		BigDecimal sumExcludingTaxAmount= BigDecimal.ZERO;//不含税金额
		BigDecimal sumCost= BigDecimal.ZERO;//成本
		BigDecimal sumGrossProfit= BigDecimal.ZERO;//毛利
		Double sumGrossProfitMargin= 0D;//毛利率
		if(details!=null&&details.size()>0) {
			for(SapSalesGroup entity : salesGroups) {
				BigDecimal amount = BigDecimal.ZERO;//金额
				BigDecimal excludingTaxAmount= BigDecimal.ZERO;//不含税金额
				BigDecimal cost= BigDecimal.ZERO;//成本
				BigDecimal grossProfit= BigDecimal.ZERO;//毛利
				Double grossProfitMargin= 0D;//毛利率
				
				for(ItemDetails item : details) {
					if(item.getMaterialCode().equals(entity.getCode())) {
						//总金额
						amount = amount.add(item.getAmount());
						//总金额减去税金 = 不含税金额
						excludingTaxAmount = excludingTaxAmount.subtract(sumAmount.multiply(BigDecimal.valueOf(taxRate)));
						//成本
						if(submitType == 3) {
							cost = cost.add(item.getStandardPrice());
						}
						if(submitType == 4) {
							cost = cost.add(item.getTransfterPrice());
						}
						//毛利
						grossProfit = excludingTaxAmount.subtract(cost);
						//毛利率
						grossProfitMargin = this.CalculateGrossProfit(excludingTaxAmount, cost);
						
						
					}
				}
				
				entity.setAmount(amount);
				entity.setExcludingTaxAmount(excludingTaxAmount);
				entity.setCost(cost);
				entity.setGrossProfit(grossProfit);
				entity.setGrossProfitMargin(grossProfitMargin);
				
				sumAmount = sumAmount.add(amount);
				sumExcludingTaxAmount = sumExcludingTaxAmount.add(excludingTaxAmount);
				sumCost = sumCost.add(cost);
				sumGrossProfit = sumGrossProfit.add(grossProfit);
				sumAmount = sumAmount.add(amount);
			}
			
		}
		
		SapSalesGroup sumssg = new SapSalesGroup();
		sumssg.setAmount(sumAmount);
		sumssg.setExcludingTaxAmount(sumExcludingTaxAmount);
		sumssg.setCost(sumCost);
		sumssg.setGrossProfit(sumGrossProfit);
		if(salesGroups.size()!=0) {
			sumssg.setGrossProfitMargin(sumGrossProfitMargin/salesGroups.size());
		}else {
			sumssg.setGrossProfitMargin(0D);
		}
		sumssg.setCode("sum");
		sumssg.setName("合计");
		
		salesGroups.add(sumssg);
		
		return salesGroups;
	}
	
	
	 public Double CalculateGrossProfit(BigDecimal afterTaxAmount,BigDecimal cost){
		 
	        return (afterTaxAmount.subtract(cost)).divide(afterTaxAmount).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	    }
}
