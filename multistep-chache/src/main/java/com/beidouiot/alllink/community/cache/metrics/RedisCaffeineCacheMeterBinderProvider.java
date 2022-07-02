package com.beidouiot.alllink.community.cache.metrics;

import org.springframework.boot.actuate.metrics.cache.CacheMeterBinderProvider;

import com.beidouiot.alllink.community.cache.support.RedisCaffeineCache;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.binder.cache.CaffeineCacheMetrics;
import lombok.NoArgsConstructor;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/

@NoArgsConstructor
public class RedisCaffeineCacheMeterBinderProvider implements CacheMeterBinderProvider<RedisCaffeineCache> {
    

	@Override
	public MeterBinder getMeterBinder(RedisCaffeineCache cache, Iterable<Tag> tags) {
		return new CaffeineCacheMetrics(cache.getCaffeineCache(), cache.getName(), tags);
	}
}
