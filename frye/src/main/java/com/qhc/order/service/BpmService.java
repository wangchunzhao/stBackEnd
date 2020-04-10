package com.qhc.order.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BpmService {
	private static Logger logger = LoggerFactory.getLogger(BpmService.class);

	@Value("${bpm.url}")
	private String bpmUrl;

	public boolean callSendProcess(String json) {
		boolean result = false;

		JaxWsDynamicClientFactory dcflient = JaxWsDynamicClientFactory.newInstance();
		Client client = dcflient.createClient(bpmUrl);
		logger.info("提交BPM信息: {}", json);
		Object[] objects = null;
		try {
			objects = client.invoke("StartProcess", new Object[] { json });
		} catch (Exception e) {
			throw new RuntimeException("调用BPM接口失败，错误信息：" + e.getMessage());
		}
		logger.info(new StringBuilder().append("*******").append(objects[0].toString()).toString());
		if ((objects != null) && (objects.length > 0)) {
			String data = StringUtils.trimToEmpty(objects[0].toString());
			if (data.equals("0")) {
				throw new RuntimeException("BPM系统异常");
			} else if (data.equals("1")) {
				result = true;
			} else if (data.equals("2")) {
				throw new RuntimeException("BPM查不到客户经理信息");
			} else if (data.equals("3")) {
				throw new RuntimeException("订单主数据写入失败");
			} else if (data.equals("4")) {
				throw new RuntimeException("明细信息写入失败");
			} else if (data.equals("5")) {
				throw new RuntimeException("流程发起失败");
			} else {
				throw new RuntimeException("未知BPM返回结果-" + data);
			}
		} else {
			throw new RuntimeException("提交BPM返回结果未知");
		}
		return result;
	}

	public boolean callChangOrderProcess(String json) {
		boolean result = false;

		JaxWsDynamicClientFactory dcflient = JaxWsDynamicClientFactory.newInstance();
		Client client = dcflient.createClient(bpmUrl);
		try {
			Object[] objects = client.invoke("ChangOrderProcess", new Object[] { json });
			System.out.println(new StringBuilder().append("*******").append(objects[0].toString()).toString());
			if ((objects != null) && (objects.length > 0) && (objects[0].toString().equalsIgnoreCase("true")))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String callSelectDptAndUser(String json) {
		String result = "";

		JaxWsDynamicClientFactory dcflient = JaxWsDynamicClientFactory.newInstance();
		Client client = dcflient.createClient(bpmUrl);
		try {
			Object[] objects = client.invoke("selectDptAndUser", new Object[] { json });
			System.out.println(new StringBuilder().append("*******").append(objects[0].toString()).toString());
			result = objects[0].toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
