/**
 * 
 */
package com.qhc.order.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qhc.order.entity.Order;
import com.qhc.order.entity.ItemDetails;
import com.qhc.order.entity.ItemsForm;
import com.qhc.order.entity.Attachment;
import com.qhc.order.entity.BillingPlan;
import com.qhc.order.entity.Characteristics;
import com.qhc.order.entity.DelieveryAddress;
import com.qhc.order.entity.OrderInfo;
import com.qhc.order.entity.OrderVersion;
import com.qhc.order.entity.OrderSupportInfo;

/**
 * @author wang@dxc.com
 *
 */
public class OrderHelper {
	private OrderDto order;
	public OrderHelper(OrderDto order) {
		this.order = order;
	}
	/**
	 * 
	 * @return
	 */
	public Order toDOrder() {
		Order dorder = new Order();
		//
		dorder.setSequenceNumber(order.getSequenceNumber());//序列号
		dorder.setOrderTypeCode(order.getOrderType());
		dorder.setOwnerDomainId(order.getSalesCode());
		dorder.setOwnerName(order.getSalesName());
		dorder.setSalesTel(order.getSalesTel());
		dorder.setContractorCode(order.getContracterCode());
		dorder.setContractorName(order.getContracterName());
		dorder.setContractorClassCode(order.getCustomerClazzCode());//customer class
		dorder.setContractorClassName(order.getCustomerClazzName());//customer class name
		dorder.setOfficeCode(order.getUserOfficeCode());
		dorder.setCreateTime(new Date());
		return dorder;
	}
	/*
	 * 
	 */
	public OrderSupportInfo toSupportInforOfOrder() {
		OrderSupportInfo osi = new OrderSupportInfo();
		osi.setContractNumber(order.getContractNumber());
		osi.setSupportorId(order.getCurrentUser());
		osi.setContractNumber(order.getContractNumber());
		osi.setOperationTime(order.getOptTime());
		return osi;
	}
	/**
	 * 
	 * @return
	 */
	public OrderInfo toOrderInfo() {
		OrderInfo temp = new OrderInfo();
		temp.setLastOperator(order.getCurrentUser());
		if(order.getOptTime()==null)
			order.setOptTime(new Date());
		temp.setLastOptTime(order.getOptTime());
		temp.setCustomerName(order.getCustomerName());
		//
		temp.setIsReformed(order.getIsReformed());
		temp.setIsConvenientStore(order.getIsConvenientStore());
		if(order.getIsNew()!=0)
			temp.setNew(true);
		else
			temp.setNew(false);
		temp.setTerminalIndustryCode(order.getTerminalType());
		temp.setTerminalIndustryCodeName(order.getTerminalTypeName());
		temp.setBodyDiscount(order.getBodyDiscount());
		temp.setMainDiscount(order.getMainDiscount());
		temp.setInstallTermCode(order.getInstallCode());
		temp.setInstallTermName(order.getInstallName());
		temp.setReceiveTermCode(order.getConfirmTypeCode());
		temp.setReceiveTermName(order.getConfirmTypeName());
		temp.setContactor1Id(order.getContactor1Id());
		temp.setContactor1Tel(order.getContactor1Tel());
		temp.setContactor2Id(order.getContactor2Id());
		temp.setContactor2Tel(order.getContactor2Tel());
		temp.setContactor3Id(order.getContactor3Id());
		temp.setContactor3Tel(order.getContactor3Tel());
		temp.setFreight(new BigDecimal(order.getFreight()));
		temp.setWarranty(order.getWarrenty());
		temp.setCurrencyCode(order.getCurrency());
		temp.setCurrencyName(order.getCurrencyName());
		temp.setExchange(order.getExchange());
		temp.setContractAmount(new BigDecimal(order.getContractValue()));
		temp.setContractRmbAmount(new BigDecimal(order.getContractRmbValue()));
		temp.setSalesType(order.getSaleType());
		temp.setTaxRate(order.getTaxRate());
		temp.setIncotermCode(order.getIncoterm());
		temp.setIncotermName(order.getIncotermName());
		temp.setIncotermContect(order.getIncotermContect());
		temp.setOfficeCode(order.getOfficeCode());
		temp.setOfficeName(order.getOfficeName());
		temp.setGroupCode(order.getGroupCode());
		temp.setGroupName(order.getGroupName());
		temp.setTransferTypeCode(order.getTransferTypeCode());
		temp.setTransferTypeName(order.getTransferTypeName());
		if(order.getIsTerm1()!=0)
			temp.setTerm1(true);
		else
			temp.setTerm1(false);
		if(order.getIsTerm2()!=0)
			temp.setTerm2(true);
		else
			temp.setTerm2(false);
		if(order.getIsTerm2()!=0)
			temp.setTerm2(true);
		else
			temp.setTerm2(false);
		temp.setComments(order.getComments());
		if(order instanceof OrderDto) {
			OrderDto dorder = (OrderDto)order;
			temp.setRecordNumber(dorder.getRecordCode());
		}
		return temp;
	}
	public OrderInfo keepOrderInfo() {
		OrderInfo temp = toOrderInfo();
		return temp;
	}
	/**
	 * 
	 * @param orderInfoId
	 * @return
	 */
	public List<BillingPlan> toBiddingPlan(String orderInfoId) {
		List<BillingPlan> kbList = new ArrayList<BillingPlan>();
		if(order.getPayments()!=null) {
			for(BillingPayment bp:order.getPayments()) {
				BillingPlan temp = new BillingPlan();
				temp.setCode(bp.getTermCode());
				temp.setName(bp.getTermName());
				temp.setAmount(new BigDecimal(bp.getPercentage()));
				temp.setPayDate(bp.getPayDate());
				temp.setReason(bp.getReason());
				temp.setOrderInfoId(orderInfoId);
				kbList.add(temp);
			}
		}
		return kbList;
	}
	/**
	 * 
	 * @param orderInfoId
	 * @return
	 */
	public List<DelieveryAddress> toAddress(String orderInfoId){
		List<DelieveryAddress> kdList = new ArrayList<DelieveryAddress>();
		for(OrderAddress oa:order.getOrderAddress()) {
			DelieveryAddress temp = new DelieveryAddress();
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
	public List<Attachment> toAttached(String orderInfoId){
		List<Attachment> kaList = new ArrayList<Attachment>();
		for(String name:order.getAttachedFileName()) {
			Attachment ka=new Attachment();
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
		temp.setComments(order.getComments());
		temp.setOperator(order.getCurrentUser());
		temp.setType(0);
		temp.setOptTime(order.getOptTime());
		temp.setkOrderInfoId(orderInfoId);
		return temp;
	}
	/**
	 * 
	 * @param formId
	 * @return
	 */
	public List<ItemDetails> toDetails(String formId) {
		List<ItemDetails> idList = new ArrayList<ItemDetails>();
		for(ItemDto item: order.getItems()) {
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
			temp.setB2cEstimationAmount(new BigDecimal(item.getB2cPriceEstimated()));
			temp.setB2cEstimationCost(new BigDecimal(item.getB2cCostOfEstimated()));
			temp.setMaterialCode(item.getMaterialCode());
			temp.setMaterialGroupCode(item.getGroupCode());
			temp.setMaterialGroupName(item.getGroupName());
			temp.setDiscount(item.getDiscount());
			temp.setItemCategory(item.getItemCategory());
			temp.setItemRequirementPlan(item.getItemRequirementPlan());
			temp.setVolumeCube(new BigDecimal(item.getVolumeCube()));
			temp.setFreight(new BigDecimal(item.getFeight()));
			temp.setRetailPrice(new BigDecimal(item.getRetailPrice()));
			temp.setDelieveryDate(item.getDeliveryDate());
			temp.setSpecialNeed(item.getSpecialComments());
			temp.setMosaicImage(item.getMosaicImage());
			temp.setAttachedImage(item.getAttachedImage());
			temp.setRequestBrand(item.getRequestBrand());
			temp.setRequestCircuit(item.getRequestCircult());
			temp.setRequestPackage(item.getRequestPackage());
			temp.setRequestNameplate(item.getRequestNameplate());
			temp.setComments(item.getComments());
			temp.setColorComments(item.getColorComments());
			//
			temp.setkFormsId(formId);
			idList.add(temp);
		}
		return idList;
	}
	/**
	 * 
	 * @return
	 */
	public List<Characteristics> toCharacteristics(List<ItemDetails> details){
		List<Characteristics> kcList = new ArrayList<Characteristics>();
		for(ItemDto item:order.getItems()) {
			for(CharacteristicDto ac :item.getConfigs()) {
				Characteristics temp = new Characteristics();
				temp.setKeyCode(ac.getConfigCode());
				temp.setValueCode(ac.getConfigValueCode());
				
				kcList.add(temp);
			}
		}
		return kcList;
	}
	/**
	 * 
	 * @return
	 */
	public OrderVersion toOrderVersion(boolean b2c,boolean dealer) {
		OrderVersion temp = new OrderVersion();
		String orderStatus = "00";
		String b2cStatus = String.valueOf(0);
		String dealerStatus = String.valueOf(0);
		
		if(b2c)
			b2cStatus=String.valueOf(1);
		if(dealer)
			dealerStatus = String.valueOf(1);
				
		switch(order.getSubmitType()) {
			case 2:
				orderStatus = "01";
				temp.setSubmitDate(order.getOptTime());
				break;
			case 5:
				orderStatus = "02";
				temp.setBpmSubmitDate(order.getOptTime());
				break;
			case 7:
				orderStatus = "10";
				break;
			default:
				temp.setStatus("00");
				break;
		}
		temp.setStatus(orderStatus+b2cStatus+dealerStatus);
		temp.setVersion(order.getCurrentVersion());
		temp.setCreateTime(order.getCreateTime());
		temp.setOptTime(order.getOptTime());
		return temp;
	}
	public OrderVersion keepOrderVersion(OrderVersion old,boolean b2c,boolean dealer) {
		OrderVersion temp = toOrderVersion(b2c,dealer);
		temp.setId(old.getId());
		temp.setOrderId(old.getOrderId());
		temp.setOrderInfoId(old.getOrderInfoId());
		return temp;
	}
	public static ItemDetails itemConversion(final ItemDto  item,final String formId) {
		ItemDetails temp = new ItemDetails();
		temp.setRowNumber(item.getRowNumber());
		temp.setMaterialCode(item.getMaterialCode());
		temp.setMaterialName(item.getMaterialName());
		if (item.getIsVirtual() == 0)
			temp.setVirtual(false);
		else
			temp.setVirtual(true);
		temp.setMaterialAttribute(item.isPurchased());
		temp.setQuantity(item.getQuantity());
		temp.setMeasureUnitCode(item.getUnitCode());
		temp.setMeasureUnitName(item.getUnitName());
		temp.setAmount(new BigDecimal(item.getActuralPrice() * item.getQuantity()));
		temp.setTransfterPrice(new BigDecimal(item.getTranscationPrice()));
		temp.setStandardPrice(new BigDecimal(item.getStandardPrice()));
		temp.setB2cComments(item.getB2cComments());
		temp.setB2cEstimationAmount(new BigDecimal(item.getB2cPriceEstimated()));
		temp.setB2cEstimationCost(new BigDecimal(item.getB2cCostOfEstimated()));
		temp.setMaterialCode(item.getMaterialCode());
		temp.setMaterialGroupCode(item.getGroupCode());
		temp.setMaterialGroupName(item.getGroupName());
		temp.setDiscount(item.getDiscount());
		temp.setItemCategory(item.getItemCategory());
		temp.setItemRequirementPlan(item.getItemRequirementPlan());
		temp.setVolumeCube(new BigDecimal(item.getVolumeCube()));
		temp.setFreight(new BigDecimal(item.getFeight()));
		temp.setRetailPrice(new BigDecimal(item.getRetailPrice()));
		temp.setShipDate(item.getShippDate());
		temp.setSpecialNeed(item.getSpecialComments());
		temp.setMosaicImage(item.getMosaicImage());
		temp.setAttachedImage(item.getAttachedImage());
		temp.setRequestBrand(item.getRequestBrand());
		temp.setRequestCircuit(item.getRequestCircult());
		temp.setRequestPackage(item.getRequestPackage());
		temp.setRequestNameplate(item.getRequestNameplate());
		temp.setComments(item.getComments());
		temp.setColorComments(item.getColorComments());
		temp.setConfigurable(item.isConfigurable());
		temp.setClazzCode(item.getClazzCode());
		//address
		temp.setProvienceCode(item.getProvinceCode());
		temp.setProvienceName(item.getProvinceName());
		temp.setCityCode(item.getCityCode());
		temp.setCityName(item.getCityName());
		temp.setDistrictCode(item.getDistrictCode());
		temp.setDistrictName(item.getDistrictName());
		temp.setAddress(item.getAddress());
		//
		temp.setkFormsId(formId);
		return temp;
	}
	public static Characteristics CharacteristicConversion(final CharacteristicDto character) {
		Characteristics temp = new Characteristics();
		temp.setKeyCode(character.getConfigCode());
		temp.setValueCode(character.getConfigValueCode());
		temp.setIsConfigurable(0);
		return temp;
		
	}
}
