package com.qhc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author wang@dxc.com
 *
 */

@Configuration
public class ApplicationConfig {

	@Value("${qhc.bayern.url}")
	String bayernUrl;

	public final static String SIGN_PROTOCAL = "://";
	public final static String SIGN_SEGAMENT = "/";
	public final static String SIGN_PORT = ":";

	//
	public String getBayernURL() {
		return this.bayernUrl;
	}

}
