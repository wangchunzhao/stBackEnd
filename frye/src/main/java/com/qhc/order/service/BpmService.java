package com.qhc.order.service;

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
		try {
			logger.info("提交BPM信息: {}", json);
			Object[] objects = client.invoke("StartProcess", new Object[] { json });
			logger.info(new StringBuilder().append("*******").append(objects[0].toString()).toString());
			if ((objects != null) && (objects.length > 0) && (objects[0].toString().equalsIgnoreCase("true")))
				result = true;
		} catch (Exception e) {
			e.printStackTrace();
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
