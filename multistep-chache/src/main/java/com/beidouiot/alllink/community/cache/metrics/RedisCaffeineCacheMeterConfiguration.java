package com.beidouiot.alllink.community.cache.metrics;

import org.springframework.boot.actuate.metrics.cache.CacheMeterBinderProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.binder.MeterBinder;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({MeterBinder.class, CacheMeterBinderProvider.class})
public class RedisCaffeineCacheMeterConfiguration {
	
	@Bean
	public RedisCaffeineCacheMeterBinderProvider redisCaffeineCacheMeterBinderProvider() {
		return new RedisCaffeineCacheMeterBinderProvider();
	}
}
