/**
 * 
 */
package com.qhc.frye.rest.controller.entity.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.qhc.frye.domain.DOrder;
import com.qhc.frye.domain.ItemDetails;
import com.qhc.frye.domain.ItemsForm;
import com.qhc.frye.domain.KAttachment;
import com.qhc.frye.domain.KBiddingPlan;
import com.qhc.frye.domain.KDelieveryAddress;
import com.qhc.frye.domain.KOrderInfo;
import com.qhc.frye.domain.KOrderVersion;
import com.qhc.frye.domain.OrderSupportInfo;

/**
 * @author wang@dxc.com
 *
 */
/**
 * common order type used to present etc..
 */
public class BaseOrder extends AbsOrder{
	/**
	 * 
	 * @return
	 */
	public DOrder toDOrder() {
		DOrder dorder = new DOrder();
		//
		dorder.setSequenceNumber(this.getSequenceNumber());//序列号
		dorder.setOrderTypeCode(this.getOrderType());
		dorder.setOwnerDomainId(this.getSalesCode());
		dorder.setOwnerName(this.getSalesName());
		dorder.setSalesTel(this.getSalesTelnumber());
		dorder.setContractorCode(this.getContracterCode());
		dorder.setContractorName(this.getContracterName());
		dorder.setContractorClassCode(this.getCustomerClazzCode());//customer class
		dorder.setContractorClassName(this.getCustomerClazzName());//customer class name
		dorder.setOfficeCode(this.getUserOfficeCode());
		return dorder;
	}
	/*
	 * 
	 */
	public OrderSupportInfo toSupportInforOfOrder() {
		OrderSupportInfo osi = new OrderSupportInfo();
		osi.setContractNumber(this.getContractNumber());
		osi.setSupportorId(this.getCurrentUser());
		osi.setContractNumber(this.getContractNumber());
		osi.setOperationTime(this.getOptTime());
		return osi;
	}
	/**
	 * 
	 * @return
	 */
	public KOrderInfo toOrderInfo() {
		KOrderInfo temp = new KOrderInfo();
		temp.setLastOperator(this.getCurrentUser());
		temp.setLastOptTime(this.getOptTime());
		temp.setCustomerName(this.getCustomerName());
		//
		temp.setIsReformed(this.getIsReformed());
		temp.setIsConvenientStore(this.getIsConvenientStore());
		if(this.getIsNew()!=0)
			temp.setNew(true);
		else
			temp.setNew(false);
		temp.setTerminalIndustryCode(this.getCustomerClazzCode());
		temp.setTerminalIndustryCodeName(this.getCustomerClazzName());
		temp.setBodyDiscount(this.getBodyDiscount());
		temp.setMainDiscount(this.getMainDiscount());
		temp.setInstallTermCode(this.getInstallCode());
		temp.setInstallTermName(this.getInstallName());
		temp.setReceiveTermCode(this.getConfirmTypeCode());
		temp.setReceiveTermName(this.getConfirmTypeName());
		temp.setContactor1Id(this.getContactor1Id());
		temp.setContactor1Tel(this.getContactor1Tel());
		temp.setContactor2Id(this.getContactor2Id());
		temp.setContactor2Tel(this.getContactor2Tel());
		temp.setContactor3Id(this.getContactor3Id());
		temp.setContactor3Tel(this.getContactor3Tel());
		temp.setFreight(new BigDecimal(this.getFreight()));
		temp.setWarranty(this.getWarrenty());
		temp.setCurrencyCode(this.getCurrency());
		temp.setCurrencyName(this.getCurrencyName());
		temp.setExchange(this.getCurrencyExchange());
		temp.setContractAmount(new BigDecimal(this.getContractValue()));
		temp.setContractRmbAmount(new BigDecimal(this.getContractRMBValue()));
		temp.setSalesType(this.getSaleType());
		temp.setTaxRate(this.getTaxRate());
		temp.setIncotermCode(this.getIncoterm());
		temp.setIncotermName(this.getIncotermName());
		temp.setIncotermContect(this.getIncotermContect());
		temp.setOfficeCode(this.getOfficeCode());
		temp.setOfficeName(this.getOfficeName());
		temp.setGroupCode(this.getGroupCode());
		temp.setGroupName(this.getGroupName());
		temp.setTransferTypeCode(this.getTransferTypeCode());
		temp.setTransferTypeName(this.getTransferTypeName());
		if(this.getIsTerm1()!=0)
			temp.setTerm1(true);
		else
			temp.setTerm1(false);
		if(this.getIsTerm2()!=0)
			temp.setTerm2(true);
		else
			temp.setTerm2(false);
		if(this.getIsTerm2()!=0)
			temp.setTerm2(true);
		else
			temp.setTerm2(false);
		temp.setComments(this.getComments());
		
		return temp;
	}
	/**
	 * 
	 * @param orderInfoId
	 * @return
	 */
	public List<KBiddingPlan> toBiddingPlan(String orderInfoId) {
		List<KBiddingPlan> kbList = new ArrayList<KBiddingPlan>();
		for(BiddingPayment bp:this.getPayments()) {
			KBiddingPlan temp = new KBiddingPlan();
			temp.setCode(bp.getTermCode());
			temp.setName(bp.getTermName());
			temp.setAmount(new BigDecimal(bp.getPercentage()));
			temp.setPayDate(bp.getPayDate());
			temp.setReason(bp.getReason());
			temp.setOrderInfoId(orderInfoId);
			kbList.add(temp);
		}
		return kbList;
	}
	/**
	 * 
	 * @param orderInfoId
	 * @return
	 */
	public List<KDelieveryAddress> toAddress(String orderInfoId){
		List<KDelieveryAddress> kdList = new ArrayList<KDelieveryAddress>();
		for(OrderAddress oa:this.getOrderAddress()) {
			KDelieveryAddress temp = new KDelieveryAddress();
			temp.setProvinceCode(oa.getProvinceCode());
			temp.setProvinceName(oa.getProvinceName());
			temp.setCityCode(oa.getCityCode());
			temp.setCityName(oa.getCityName());
			temp.setDistinctCode(oa.getDistinctCode());
			temp.setDistinctName(oa.getDistinctName());
			temp.setAddress(oa.getAddress());
			temp.setOrderInfoId(orderInfoId);
			kdList.add(temp);
		}
		return kdList;
	}
	/**
	 * 
	 * @param orderInfoId
	 * @return
	 */
	public List<KAttachment> toAttached(String orderInfoId){
		List<KAttachment> kaList = new ArrayList<KAttachment>();
		for(String name:this.getAttachedFileName()) {
			KAttachment ka=new KAttachment();
			ka.setFileName(name);
			ka.setOrderInfo(orderInfoId);
		}
		return kaList;
	}
	/**
	 * 
	 * @param orderInfoId
	 * @return
	 */
	public ItemsForm toForm(String orderInfoId) {
		ItemsForm temp = new ItemsForm();
		temp.setComments(this.getComments());
		temp.setOperator(this.getCurrentUser());
		temp.setType(0);
		temp.setOptTime(this.getOptTime());
		temp.setkOrderInfoId(orderInfoId);
		return temp;
	}
	public List<ItemDetails> toDetails(String formId) {
		List<ItemDetails> idList = new ArrayList<ItemDetails>();
		for(AbsItem item: this.getItems()) {
			ItemDetails temp = new ItemDetails();
			temp.setRowNumber(item.getRowNumber());
			temp.setMaterialCode(item.getMaterialCode());
			temp.setMaterialName(item.getMaterialName());
			if(item.getIsVirtual()==0)
				temp.setVirtual(false);
			else
				temp.setVirtual(true);
			temp.setMaterialAttribute(item.isPurchased());
			temp.setQuantity(item.getQuantity());
			temp.setMeasureUnitCode(item.getUnitCode());
			temp.setAmount(new BigDecimal(item.getRetailPrice()*item.getQuantity()));
			temp.setTransfterPrice(new BigDecimal(item.getTranscationPrice()));
			temp.setStandardPrice(new BigDecimal(item.getStandardPrice()));
			temp.setB2cComments(item.getB2cComments());
			temp.setB2cEstimationAmount(item.getB2CPriceEstimated());
			temp.setB2cEstimationCost(item.getB2CCostOfEstimated());
			temp.setMaterialCode(item.getMaterialCode());
			temp.setMaterialGroupCode(item.getGroupCode());
			temp.setMaterialGroupName(item.getGroupName());
			temp.setDiscount(item.getDiscount());
			temp.setItemCategory(item.getItemCategory());
			temp.setItemRequirementPlan(item.getItemRequirementPlan());
			temp.setVolumeCube(item.getVolumeCube());
			temp.setFreight(new BigDecimal(item.getFeight()));
			temp.setRetailPrice(item.getRetailPrice());
			temp.setDelieveryDate(item.getDeliveryDate());
			temp.setSpecialNeed(item.getSpecialComments());
			//temp.setmo
			//
			temp.setkFormsId(formId);
			idList.add(temp);
		}
		return idList;
	}
}
