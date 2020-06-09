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
}