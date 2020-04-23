package com.qhc.sap.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.qhc.order.domain.sap.SapItemStatus;
import com.qhc.order.domain.sap.SapOrderStatus;
import com.qhc.sap.dao.SapLastUpdatedRepository;
import com.qhc.sap.domain.Bom;
import com.qhc.sap.domain.BomBodyParam;
import com.qhc.sap.domain.BomHeadParam;
import com.qhc.sap.domain.CharacteristicValueDto;
import com.qhc.sap.domain.Clazz;
import com.qhc.sap.domain.ColorDto;
import com.qhc.sap.domain.CurrencyDto;
import com.qhc.sap.domain.CustomerDto;
import com.qhc.sap.domain.DefaultBodyParam;
import com.qhc.sap.domain.DefaultCharacteristicsDto;
import com.qhc.sap.domain.DefaultHeadParam;
import com.qhc.sap.domain.IncotermDto;
import com.qhc.sap.domain.MaterialDto;
import com.qhc.sap.domain.Parameter;
import com.qhc.sap.domain.PaymentPlanDto;
import com.qhc.sap.domain.PriceDto;
import com.qhc.sap.domain.SalesGroup;
import com.qhc.sap.entity.LastUpdated;
import com.qhc.utils.DateUtil;
import com.qhc.utils.HttpUtil;

/**
 * SAP外部接口
 * 
 * @author Walker
 *
 */
@Service
public class SapService {
	private static final Logger logger = LoggerFactory.getLogger(SapService.class);
	
	@Value("${sap.currency.addr}")
	String currencyUrl;
	
	@Value("${sap.incoterm.addr}")
	String incotermUrl;
	
	@Value("${sap.customer.addr}")
	String customerUrl;
	
	@Value("${sap.clazz.addr}")
	String clazzUrl;
	
	@Value("${sap.characteristic.addr}")
	String characteristicUrl;
	
	@Value("${sap.sapOfficeGroup.addr}")
	String sapOfficeGroupUrl;
	
	@Value("${sap.paymentplan.addr}")
	String paymentplanUrl;
	
	@Value("${sap.price.addr}")
	String priceUrl;
	
	@Value("${sap.priceA.addr}")
	String priceAUrl;
	
	@Value("${sap.defaultCharacteristics.addr}")
	String defaultCharacteristicsAUrl;
	
	@Value("${sap.material.addr}")
	String materialUrl;
	
	@Value("${sap.bomExplosion.addr}")
	String bomExplosionUrl;
	
	@Value("${sap.color.addr}")
	String colorUrl;
	
	@Value("${sap.queryOrderStatus.addr}")
	String queryOrderStatusUrl;
	
	
	@Autowired
	private SapLastUpdatedRepository lastUpdate;
	
	public final static long DEFAULT_DATE = 1008005271098L;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	//currency
	public List<CurrencyDto> getCurrencyFromSap() {
		List<CurrencyDto> clist = new ArrayList<CurrencyDto>();
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("KURST");
			parameter1.setValue("M");
			Parameter parameter2 = new Parameter();
			parameter2.setKey("LANGU");
			parameter2.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			parList.add(parameter2);
			String currencyParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(currencyUrl, currencyParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) { 
//				  System.out.println(parseArray.get(i)); 
				  JSONObject obj = (JSONObject)parseArray.get(i); 
				  //
				  if(!obj.getString("tcurr").equals("CNY")|| obj.getString("ktext_f")=="") {
					  System.out.println("数据有误");
					  continue;
				  }else {
					  CurrencyDto currency = new CurrencyDto();
					  currency.setCode(obj.getString("fcurr"));
					  currency.setName(obj.getString("ktext_f"));
					  currency.setRate(StrToDouble(obj.getString("ukurs")));
					  clist.add(currency);
				  }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	//incoterm
	public List<IncotermDto> getIncotermFromSap() {
		List<IncotermDto> ilist = new ArrayList<IncotermDto>();
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LANGU");
			parameter1.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String incotermParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(incotermUrl, incotermParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) { 
				JSONObject obj = (JSONObject)parseArray.get(i); 
				IncotermDto incoterm = new IncotermDto();
				incoterm.setCode(obj.getString("inco1"));
				incoterm.setName(obj.getString("bezei"));
				//全部设置20-出口
				incoterm.setSapSalesTypeCode("20");
				ilist.add(incoterm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ilist;
	}
	//class
	public List<Clazz> getClassesFromSap() {
		List<Clazz> clist = new ArrayList<Clazz>();
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LANGU");
			parameter1.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String clazzParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(clazzUrl, clazzParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) {
				JSONObject obj = (JSONObject)parseArray.get(i); 
				Clazz c1 = new Clazz();
				c1.setCode(obj.getString("class"));
				c1.setName(obj.getString("kschl"));
				clist.add(c1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	//customer
	public List<CustomerDto> getCustomersFromSap() {
		List<CustomerDto> clist = new ArrayList<CustomerDto>();
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("DATUM");
			parameter1.setValue("");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String customerParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(customerUrl, customerParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data_cm");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) {
				 JSONObject obj = (JSONObject)parseArray.get(i);
				 //如果sap_industry_code_code为空，赋默认值
				 String industryCode = ("".equals(obj.getString("bran1")))?"unknow":obj.getString("bran1");
				 
				 if("".equals(obj.getString("kukla")) ) {
					 System.out.println("关键数据不能为空");
					 continue;
				 }else {
					 CustomerDto customer = new CustomerDto();
					 customer.setCode(obj.getString("kunnr"));
					 customer.setName(obj.getString("name1"));
					 customer.setAddress(obj.getString("street"));
					 customer.setChangedDate(new Date());
					 customer.setClazzCode(obj.getString("kukla"));
					 customer.setAffiliationCode(obj.getString("brsch"));
					 customer.setAffiliationName(obj.getString("brtxt"));
					 customer.setIndustryCodeCode(industryCode);
					 clist.add(customer);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	//offices
	public List<SalesGroup> getSalesgroupFromSAP() {
		List<SalesGroup> rl = new ArrayList<SalesGroup>();
		try {
			//接口请求参数
			Parameter parameter2 = new Parameter();
			parameter2.setKey("LANGU");
			parameter2.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter2);
			String sapOfficeGroupParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(sapOfficeGroupUrl, sapOfficeGroupParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
//			Object officeData = parseObject.get("sales_office");
			Object groupData = parseObject.get("sales_group");
			JSONArray groupDataArray = JSONArray.parseArray(groupData.toString());
			for (int i = 0; i < groupDataArray.size();i++) { 
				JSONObject obj = (JSONObject)groupDataArray.get(i);
				SalesGroup sg1 = new SalesGroup();
				sg1.setCode(obj.getString("vkgrp"));
				sg1.setName(obj.getString("vkgrptext"));
				sg1.setOfficeCode(obj.getString("vkbur"));
				sg1.setOfficeName(obj.getString("vkburtext"));
				rl.add(sg1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rl;
	}
	//paymentPlan
	public List<PaymentPlanDto> getPaymentFromSAP(){
		List<PaymentPlanDto> lp = new ArrayList<PaymentPlanDto>();
		try {
			//接口请求参数
			Parameter parameter2 = new Parameter();
			parameter2.setKey("LANGU");
			parameter2.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter2);
			String paymentplanParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(paymentplanUrl, paymentplanParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object plData = parseObject.get("data_bl");
			Object ptData = parseObject.get("data_pt");
			JSONArray plDataArray = JSONArray.parseArray(plData.toString());
			JSONArray ptDataArray = JSONArray.parseArray(ptData.toString());
			for (int i = 0; i < ptDataArray.size();i++) { 
				JSONObject obj = (JSONObject)ptDataArray.get(i);
				if("".equals(obj.getString("text1"))) {
					System.out.println("name不能为空");
					continue;
				}else {
					PaymentPlanDto pm = new PaymentPlanDto();
					pm.setCode(obj.getString("zterm"));
					pm.setName(obj.getString("text1"));
					pm.setPaymentTerm(true);
					lp.add(pm);
				}
			}
			for (int i = 0; i < plDataArray.size();i++) { 
				JSONObject obj = (JSONObject)plDataArray.get(i);
				if("".equals(obj.getString("tebez"))) {
					System.out.println("name不能为空");
					continue;
				}else {
					PaymentPlanDto pm = new PaymentPlanDto();
					pm.setCode(obj.getString("tetbe"));
					pm.setName(obj.getString("tebez"));
					pm.setPaymentTerm(false);
					lp.add(pm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lp;
	}
	//CharacteristicValue
	public List<CharacteristicValueDto> getClassesAndCharacteristicValueFromSap() {
		List<CharacteristicValueDto> clist = new ArrayList<CharacteristicValueDto>();
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LANGU");
			parameter1.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String characteristicParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(characteristicUrl, characteristicParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) {
				JSONObject obj = (JSONObject)parseArray.get(i);
				
				CharacteristicValueDto c1 = new CharacteristicValueDto();
				c1.setCode(obj.getString("atwrt"));
				c1.setName(obj.getString("atwtb"));
				c1.setCharacteristicCode(obj.getString("atnam"));
				c1.setCharacteristicName(obj.getString("atbez"));
				c1.setClazzCode(obj.getString("class")); 
				clist.add(c1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	//materials
	
	public List<MaterialDto> getNewestMaterialsFromSap() {
		List<MaterialDto> mlist = new ArrayList<MaterialDto>();
		try {
			String pingGuLei = "1000,3101,3102,3103,3104,3105,3109,3212,3231,3233,3234,3235,3236,3237,9101,9102,9103";
			String dateParameter = DateUtil.convert2String(this.getLastUpdated(MaterialDto.MATERIAL_CODE), "yyyyMMddHHmmss");

			// 接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LAEDA");
			parameter1.setValue(dateParameter.substring(0, 8));
			Parameter parameter2 = new Parameter();
			parameter2.setKey("UZEIT");
			parameter2.setValue(dateParameter.substring(8, 14));
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			parList.add(parameter2);
			String paymentplanParam = JSONObject.toJSONString(parList);
			// 发送请求获取数据
			String bb = HttpUtil.postbody(materialUrl, paymentplanParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object Data = parseObject.get("data");
			JSONArray DataArray = JSONArray.parseArray(Data.toString());
			for (int i = 0; i < DataArray.size(); i++) {
				JSONObject obj = (JSONObject) DataArray.get(i);
				if ("".equals(obj.getString("meins"))) {
					System.out.println(obj.getString("matnr") + ":计量单位不能为空");
				} else if (pingGuLei.indexOf(obj.getString("bklas")) == -1) {
					Boolean configurable = ("X".equals(obj.getString("kzkfg"))) ? true : false;
					Boolean purchased = ("E".equals(obj.getString("beskz"))) ? true : false;
					String clazzCode = ("".equals(obj.getString("class"))) ? "unconfigurable" : obj.getString("class");

					MaterialDto material = new MaterialDto();
					material.setCode(obj.getString("matnr"));
					material.setDescription(obj.getString("maktx"));
					material.setConfigurable(configurable);
					material.setPurchased(purchased);
					material.setStandardPrice(StrToDouble(obj.getString("verpr")));
					//
					material.setOptTime(DateUtil.convert2Date(obj.getString("laeda") + obj.getString("laetm"), "yyyyMMddHHmmss"));
					material.setUnitCode(obj.getString("meins"));
					material.setGroupCode("9999");
					material.setClazzCode(clazzCode);
					material.setMaterialSize(StrToDouble(obj.getString("volum")));
					material.setMaterialType(obj.getString("mtart"));
					material.setMaterialStatus(obj.getString("mstae"));
					material.setDistributionStatus(obj.getString("mstav"));
					mlist.add(material);
					System.out.println(obj.getString("matnr") + ":评估类不正确");
				} else {
					//
					Boolean configurable = ("X".equals(obj.getString("kzkfg"))) ? true : false;
					Boolean purchased = ("E".equals(obj.getString("beskz"))) ? true : false;
					String clazzCode = ("".equals(obj.getString("class"))) ? "unconfigurable" : obj.getString("class");
					String groupCode = ("".equals(obj.getString("bklas"))) ? "9999" : obj.getString("bklas");

					MaterialDto material = new MaterialDto();

					material.setCode(obj.getString("matnr"));
					material.setDescription(obj.getString("maktx"));
					material.setConfigurable(configurable);
					material.setPurchased(purchased);
					material.setStandardPrice(StrToDouble(obj.getString("verpr")));
					//
					material.setOptTime(DateUtil.convert2Date(obj.getString("laeda") + obj.getString("laetm"), "yyyyMMddHHmmss"));
					material.setUnitCode(obj.getString("meins"));
					material.setGroupCode(groupCode);
					material.setClazzCode(clazzCode);
					material.setMaterialSize(StrToDouble(obj.getString("volum")));
					material.setMaterialType(obj.getString("mtart"));
					material.setMaterialStatus(obj.getString("mstae"));
					material.setDistributionStatus(obj.getString("mstav"));
					mlist.add(material);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mlist;
	}
	//price
	public List<PriceDto> getPriceFromSap(String date,String updateDate) {
		List<PriceDto> ilist = new ArrayList<PriceDto>();
		try {
			//接口请求参数 不带年采价的接口Z_QHC_SD_Q091_SD028
			Parameter parameter1 = new Parameter();
			parameter1.setKey("DATUM");
			parameter1.setValue(date.substring(0, 8));
			Parameter parameter2 = new Parameter();
			Parameter parameter3 = new Parameter();
			if(!"".equals(updateDate)) {
				
				parameter2.setKey("LAEDA");
				parameter2.setValue(updateDate.substring(0, 8));
				
				parameter3.setKey("UZEIT");
				parameter3.setValue(updateDate.substring(8, 14));
			}else {
				parameter2.setKey("LAEDA");
				parameter2.setValue("");
				parameter3.setKey("UZEIT");
				parameter3.setValue("");
			}
			Parameter parameter4 = new Parameter();
			parameter4.setKey("TCODE");
			parameter4.setValue("VK11");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			parList.add(parameter2);
			parList.add(parameter3);
			parList.add(parameter4);
			String priceParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(priceUrl, priceParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) { 
				JSONObject obj = (JSONObject)parseArray.get(i); 
				PriceDto price = new PriceDto();
				price.setPrice(StrToDouble(obj.getString("kbetr")));
				price.setType(obj.getString("kschl"));
				price.setMaterialCode(obj.getString("matnr"));
				price.setLastDate(obj.getString("erdat")+obj.getString("utime"));
				//年采价以外的IndustryCode为空，所以设置默认值
				price.setIndustryCode("unkn");
				ilist.add(price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ilist;
	}
	//priceA
	public List<PriceDto> getPriceAFromSap(String date,String updateDate) {
		List<PriceDto> ilist = new ArrayList<PriceDto>();
		try {
			//带年采价的接口 Z_QHC_SD_Q091_SD028A
			Parameter parameter1 = new Parameter();
			parameter1.setKey("DATUM");
			parameter1.setValue(date.substring(0, 8));//20190101
			Parameter parameter2 = new Parameter();
			Parameter parameter3 = new Parameter();
			if(!"".equals(updateDate)) {
				
				parameter2.setKey("LAEDA");
				parameter2.setValue(updateDate.substring(0, 8));
				parameter3.setKey("UZEIT");
				parameter3.setValue(updateDate.substring(8, 14));
			}else {
				parameter2.setKey("LAEDA");
				parameter2.setValue("");
				parameter3.setKey("UZEIT");
				parameter3.setValue("");
			}
			List<Parameter> parListA = new ArrayList<Parameter>();
			parListA.add(parameter1);
			parListA.add(parameter2);
			parListA.add(parameter3);
			String priceParamA = JSONObject.toJSONString(parListA);
			//发送请求获取数据
			String priceA = HttpUtil.postbody(priceAUrl, priceParamA);
			JSONObject parseObjectA = JSONObject.parseObject(priceA);

			Object priceAdata = parseObjectA.get("data");
			JSONArray parseArrayA = JSONArray.parseArray(priceAdata.toString());
			for (int i = 0; i < parseArrayA.size();i++) { 
				JSONObject objA = (JSONObject)parseArrayA.get(i); 
				PriceDto price = new PriceDto();
				price.setPrice(StrToDouble(objA.getString("kbetr")));
				price.setType(objA.getString("brsch"));
				price.setMaterialCode(objA.getString("matnr"));
				price.setLastDate(objA.getString("erdat")+objA.getString("utime"));
				price.setIndustryCode(objA.getString("kschl"));
				ilist.add(price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ilist;
	}
	//defaultCharacteristic
	public List<DefaultCharacteristicsDto> getDefaultCharacteristics() {
		List<DefaultCharacteristicsDto> list = new ArrayList<DefaultCharacteristicsDto>();
		try {
			//请求参数
			DefaultBodyParam bodyParam = new DefaultBodyParam();
			bodyParam.setSign("");
			bodyParam.setOption("");
			bodyParam.setLow("");
			List paramlist = new ArrayList<>();
			paramlist.add(bodyParam);
			DefaultHeadParam headParam = new DefaultHeadParam();
			headParam.setAedat("");
			headParam.setItMatnr(paramlist);
			String defaultParam = JSONObject.toJSONString(headParam);
			//发送请求获取数据
			String bb = HttpUtil.postbody(defaultCharacteristicsAUrl, defaultParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray dataArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < dataArray.size();i++) { 
				JSONObject obj = (JSONObject)dataArray.get(i);
				
				DefaultCharacteristicsDto dc = new DefaultCharacteristicsDto();
				dc.setMaterialCode(obj.getString("matnr"));
				dc.setClassCode(obj.getString("class"));
				dc.setCharacteristic(obj.getString("atnam"));
				dc.setCharacterDescription(obj.getString("atbez"));
				dc.setCharacterValue(obj.getString("atwrt"));
				dc.setCharacterValueDes(obj.getString("atwtb"));
				list.add(dc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//颜色可选项  分4个
	public List<ColorDto> getColorPrclass(){
		List<ColorDto> list = new ArrayList<ColorDto>();
		try {
			String colorParam= "{}";
			//发送请求获取数据
			String bb = HttpUtil.postbody(colorUrl, colorParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object prclass = parseObject.get("prclass");
			JSONArray prclassArray = JSONArray.parseArray(prclass.toString());
			for (int i = 0; i < prclassArray.size();i++) { 
				JSONObject obj = (JSONObject)prclassArray.get(i);
				
				ColorDto pr = new ColorDto();
				pr.setMaterial(obj.getString("material"));
				pr.setProductClass(obj.getString("product_class"));
				list.add(pr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<ColorDto> getColorCoclass(){
		List<ColorDto> list = new ArrayList<ColorDto>();
		try {
			String colorParam= "{}";
			//发送请求获取数据
			String bb = HttpUtil.postbody(colorUrl, colorParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object coclass = parseObject.get("coclass");
			JSONArray coclasssArray = JSONArray.parseArray(coclass.toString());
			for (int i = 0; i < coclasssArray.size();i++) { 
				JSONObject obj = (JSONObject)coclasssArray.get(i);
				
				ColorDto co = new ColorDto();
				co.setColorClass(obj.getString("color_class"));
				co.setColorCode(obj.getString("color_code"));
				co.setPowderMaterial(obj.getString("powder_material"));
				co.setMaterialDesc(obj.getString("material_dec"));
				list.add(co);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<ColorDto> getColorPaclass(){
		List<ColorDto> list = new ArrayList<ColorDto>();
		try {
			String colorParam= "{}";
			//发送请求获取数据
			String bb = HttpUtil.postbody(colorUrl, colorParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object paclass = parseObject.get("paclass");
			JSONArray paclassArray = JSONArray.parseArray(paclass.toString());
			for (int i = 0; i < paclassArray.size();i++) { 
				JSONObject obj = (JSONObject)paclassArray.get(i);
				
				ColorDto pa = new ColorDto();
				pa.setPaintingClass(obj.getString("painting_class"));
				pa.setClassName(obj.getString("class_name"));
				list.add(pa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<ColorDto> getColorPamapp(){
		List<ColorDto> list = new ArrayList<ColorDto>();
		try {
			String colorParam= "{}";
			//发送请求获取数据
			String bb = HttpUtil.postbody(colorUrl, colorParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object pamapp = parseObject.get("pamapp");
			JSONArray pamappArray = JSONArray.parseArray(pamapp.toString());
			for (int i = 0; i < pamappArray.size();i++) { 
				JSONObject obj = (JSONObject)pamappArray.get(i);
				
				ColorDto pa = new ColorDto();
				pa.setProductClassPam(obj.getString("product_class"));
				pa.setPaintingClassPam(obj.getString("painting_class"));
				pa.setColorClassPam(obj.getString("color_class"));
				pa.setColorCodePam(obj.getString("color_code"));
				list.add(pa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//订单状态查询
	public SapOrderStatus getOrderStatus(String contractNo){
		SapOrderStatus sapOrderStatus = new SapOrderStatus();
		List<SapItemStatus> list = new ArrayList<SapItemStatus>();
		try {
			Parameter parameter1 = new Parameter();
			parameter1.setKey("VBELN");
			parameter1.setValue(contractNo);
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String characteristicParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(queryOrderStatusUrl, characteristicParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object order = parseObject.get("data");
			JSONArray orderArray = JSONArray.parseArray(order.toString());
			for (int i = 0; i < orderArray.size();i++) { 
				JSONObject obj = (JSONObject)orderArray.get(i);
				if(i == 0) {
					// SO number/销售订单编号
					sapOrderStatus.setContractNumber(obj.getString("vbeln"));
					// Block status/冻结状态
					sapOrderStatus.setBlockStatus(obj.getString("spstg"));
					sapOrderStatus.setOverviewStatus(obj.getString("gbstk"));
					sapOrderStatus.setReleaseStatus(obj.getString("user_line"));
				}

				SapItemStatus pa = new SapItemStatus();
				pa.setRowNum(obj.getString("posnr"));
				pa.setMaterialCode(obj.getString("matnr"));
				pa.setQuantity(obj.getDouble("kwmeng"));
				pa.setUnitCode(obj.getString("vrkme"));
				pa.setRejectedCode(obj.getString("abgru"));
				pa.setStatus(obj.getString("gbsta"));
				pa.setPurchaseType(obj.getString("beskz"));
				pa.setGroupCode(obj.getString("bklas"));
				pa.setPlannedOrder(obj.getString("plnum"));
				pa.setProductionOrder(obj.getString("aufnr"));
				pa.setProductionStartdate(obj.getString("erdat"));
				pa.setReceiptDate(obj.getString("bldat"));
				pa.setReceiptQuantity(obj.getDouble("menge"));
				pa.setPlannedIssueQuantity(obj.getDouble("zmenge10"));
				pa.setIssuedQuantity(obj.getDouble("zmenge11"));
				pa.setFirstIssueDate(obj.getDouble("erdat1"));
				pa.setFinallyIssueDate(obj.getDouble("erdat2"));
				list.add(pa);
			}
			sapOrderStatus.setItems(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sapOrderStatus;
	}
	
	
	

	// BOM
	public Map<String, List<Bom>> getBomExplosion(Map<String, String> mapParam) {
		try {
			// 请求参数
			BomHeadParam bomHeadParam = new BomHeadParam();
			bomHeadParam.setMatnr(mapParam.get("bom_code"));
			bomHeadParam.setWerks("0841");
			bomHeadParam.setStlan("1");
			// 去掉map中的物料号，只留特征，循环插入list
			mapParam.remove("bom_code");
			List list = new ArrayList<>();
			for (Map.Entry<String, String> entity : mapParam.entrySet()) {
				BomBodyParam atnam = new BomBodyParam();
				atnam.setAtnam(entity.getKey());
				atnam.setAtwrt(entity.getValue());
				list.add(atnam);
			}
			bomHeadParam.setCharac(list);
			// BOM接口的请求参数
			String bomParam = objectMapper.writeValueAsString(bomHeadParam);

			// 发送请求获取数据
			String jsonResult = HttpUtil.postbody(bomExplosionUrl, bomParam);
			
			Map<String, List<Bom>> map = parseBomResult(jsonResult);
			
			return map;
		} catch (Exception e) {
			logger.error("BOM接口请求", e);
			throw new RuntimeException(e);
		}
	}
	
	public Map<String, List<Bom>> parseBomResult(String jsonResult)
			throws JsonProcessingException, JsonMappingException {
		Map<String, List<Bom>> map = new HashMap<>();
		List<Bom> standard;
		List<Bom> optional;
		objectMapper.getFactory().enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
		JsonNode jsonNode = objectMapper.readTree(jsonResult);
		JsonNode message = jsonNode.get("message");
		if (message == null || !message.asText("").equals("成功")) {
			throw new RuntimeException(String.format("Failed get bom from sap. response: %s", jsonNode));
		}
		// optional
		JsonNode data = jsonNode.get("data");
		// default standard
		JsonNode dataDef = jsonNode.get("data_def");
		
		standard = convert((ArrayNode)dataDef);
		optional = convert((ArrayNode)data);
		
		map.put("optional", optional);
		map.put("standard", standard);
		
		return map;
	}

	/**
	 * convert sap bom to Bom object
	 * 
	 * @param dataArray
	 * @return
	 */
	private List<Bom> convert(ArrayNode dataArray) {
		List<Bom> list = new ArrayList<Bom>();
		for (int i = 0; i < dataArray.size(); i++) {
			JsonNode obj = dataArray.get(i);
			//
			String code = StringUtils.trimToEmpty(obj.get("matnr_stpo").asText());
			String parentCode = StringUtils.trimToEmpty(obj.get("matnr").asText());
			String price = StringUtils.trimToEmpty(obj.get("stprs").asText());
			String quantity = StringUtils.trimToEmpty(obj.get("menge").asText());
			Boolean configurable = StringUtils.isNotEmpty(obj.get("kzkfg").asText()) ? true : false;
			Boolean marked = StringUtils.isNotEmpty(obj.get("mark").asText()) ? true : false;
			//
			Bom bom = new Bom();
			bom.setCode(code);
			bom.setParentCode(parentCode);
			bom.setPrice(NumberUtils.toDouble(price, 0));
			bom.setQuantity(NumberUtils.toDouble(quantity, 0));
			bom.setConfigurable(configurable);
			bom.setMarked(marked);
			
			list.add(bom);
		}
		
		return list;
	}
	
	public static Double StrToDouble(String str) {
		if(str.contains("-")) {
			String bb = "-"+str.substring(0,str.length()-1);
			return Double.valueOf(bb);
		}else {
			return Double.valueOf(str);
		}
	}
	
	public Date getLastUpdated(String code) {
		Optional<LastUpdated> lu = lastUpdate.findById(code);
		if(lu.isPresent()) {
			return lu.get().getLastUpdate();
		}
		Date d = new Date(DEFAULT_DATE);

		return d;
	}
	
	
}
