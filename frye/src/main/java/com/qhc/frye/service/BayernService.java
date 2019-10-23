/**
 * 
 */
package com.qhc.frye.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.qhc.frye.config.ApplicationConfig;
import com.qhc.frye.service.exception.ExternalServerInternalException;
import com.qhc.frye.service.exception.URLNotFoundException;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 * @param <T>
 *
 */
@Service
public class BayernService<T> {
	
	@Autowired
	ApplicationConfig config;

	private WebClient webClient;

	protected Builder getBuilder() {
		WebClient.Builder webClientBuilder = WebClient.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json").defaultHeader("App-Locale", "chs")
				.defaultHeader(HttpHeaders.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
		webClientBuilder.filter(logRequest());
		return webClientBuilder;
	}

	private ExchangeFilterFunction logRequest() {
		return (clientRequest, next) -> {
			System.out.println("Request: " + clientRequest.method() +" : "+ clientRequest.url());
			clientRequest.headers()
					.forEach((name, values) -> values.forEach(value -> System.out.println("{}={}" + name + value)));
			return next.exchange(clientRequest);
		};
	}

	

}
