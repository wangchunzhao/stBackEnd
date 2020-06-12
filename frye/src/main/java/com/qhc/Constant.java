package com.qhc;

import java.util.HashMap;
import java.util.Map;

public interface Constant {
  // B2C角色
  Integer ROLE_B2C = 3;
  // 工程人员角色
  Integer ROLE_ENGINEER = 4;
  // 支持经理角色
  Integer ROLE_MANAGER = 5;

  Map<String, String> stOrderTypeMap = new HashMap() {
	  {
		  put("1","经销商标准折扣下单");
		  put("2","经销商非标准折扣下单");
		  put("3","直签客户投标报价");
		  put("4","直签客户下定单");
		  put("5","备货");
	  }
  };
  
  Map<String, String> statusMap = new HashMap() {
	  {
		 put("01", "待B2C审批");
		 put("02", "待工程人员审批");
		 put("03", "待支持经理审批");
		 put("04", "提交到BPM");
		 put("05", "BPM审批通过");
		 put("06", "订单变更BPM审批通过");
		 put("09", "已下推SAP");
		 put("10", "Selling Tool驳回");
		 put("11", "BPM驳回");
	  }
  };
  
  Map<String, String> saleTypeMap = new HashMap() {
	  {
		 put("10", "内销");
		 put("20", "出口");
		 put("30", "冷库");
	  }
  };
  
  Map<String, String> itemStatusMap = new HashMap() {
	  {
		 put("00", "草稿");
		 put("10", "下推SAP");
		 put("Z2", "取消");
	  }
  };
  
  Map<String, String> itemCategoryMap = new HashMap() {
	  {
		// ZH0D/ZH01 可配置
		  put("ZHD1","标准");
		  put("ZHD3","免费");
		  put("ZHR3","退货");
		  // ZH0D/ZH02 不可配置
		  put("ZHD2","标准");
		  put("ZHD4","免费");
		  put("ZHR4","退货");
		  // ZH0T/ZH01 可配置
		  put("ZHT1","标准");
		  put("ZHT3","免费");
		  put("ZHR1","退货");
		  // ZH0D/ZH02 可配置
		  put("ZHT2","标准");
		  put("ZHT6","免费");
		  put("ZHR2","退货");
		  // 不可预估费，其他项目收付费
		  put("ZH97","ZH97");
		  put("ZH98","ZH98");
	  }
  };
  
  Map<String, String> itemRequirementPlanMap = new HashMap() {
	  {
		  put("004","物料需求计划");
		  put("001","B2C");
		  put("002","消化");
		  put("003","调发");
		  put("005","替换");
	  }
  };
  
}