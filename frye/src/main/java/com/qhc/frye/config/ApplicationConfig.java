package com.qhc.frye.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang@dxc.com
 *
 */

@Configuration
public class ApplicationConfig {

	@Value("${qhc.bayern.server}")
	String bayernServer;

	@Value("${qhc.bayern.port}")
	int bayernPort;

	@Value("${qhc.bayern.application}")
	String application;

	@Value("${qhc.bayern.protocal}")
	String protocal;

	public final static String SIGN_PROTOCAL = "://";
	public final static String SIGN_SEGAMENT = "/";
	public final static String SIGN_PORT = ":";

	
	public String getBayernServer() {
		return bayernServer;
	}


	public void setBayernServer(String bayernServer) {
		this.bayernServer = bayernServer;
	}


	public int getBayernPort() {
		return bayernPort;
	}


	public void setBayernPort(int bayernPort) {
		this.bayernPort = bayernPort;
	}


	public String getApplication() {
		return application;
	}


	public void setApplication(String application) {
		this.application = application;
	}


	public String getProtocal() {
		return protocal;
	}


	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}


	//
	public String getFryeURL() {

		return this.getProtocal() + SIGN_PROTOCAL + this.getBayernServer() + SIGN_PORT
				+ String.valueOf(this.getBayernPort()) + SIGN_SEGAMENT + this.getApplication() + SIGN_SEGAMENT;
	}

}
