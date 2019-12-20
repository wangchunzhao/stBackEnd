/**
 * 
 */
package com.qhc.config;

import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * @author wang@dxc.com
 * @param <T>
 *
 */
@Configuration
public class OrderAuditorBean implements AuditorAware<Integer> {

	@Override
	public Optional<Integer> getCurrentAuditor() {
		 return Optional.of(new Random().nextInt(1000));
	}

}
