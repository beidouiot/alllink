package com.beidouiot.alllink.community.cache;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.beidouiot.alllink.community.cache.properties.CacheProperties;
import com.beidouiot.alllink.community.cache.support.CacheMessageListener;
import com.beidouiot.alllink.community.cache.support.RedisCaffeineCacheManager;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties(CacheProperties.class)
public class MultistepCacheAutoConfiguration {
   
	  @Bean
	    @ConditionalOnBean(RedisTemplate.class)
		public RedisCaffeineCacheManager cacheManager(
				CacheProperties cacheConfigProperties,
				RedisTemplate<Object, Object> stringKeyRedisTemplate) {
			return new RedisCaffeineCacheManager(cacheConfigProperties, stringKeyRedisTemplate);
		}
	    
	    @Bean
	    @ConditionalOnMissingBean(name = "stringKeyRedisTemplate")
		public RedisTemplate<Object, Object> stringKeyRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
			RedisTemplate<Object, Object> template = new RedisTemplate<>();
			template.setConnectionFactory(redisConnectionFactory);
			template.setKeySerializer(new StringRedisSerializer());
			template.setHashKeySerializer(new StringRedisSerializer());
			return template;
		}
	    
		@Bean
		public RedisMessageListenerContainer redisMessageListenerContainer(
				CacheProperties cacheConfigProperties,
				RedisTemplate<Object, Object> stringKeyRedisTemplate,
				RedisCaffeineCacheManager redisCaffeineCacheManager) {
			RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
			redisMessageListenerContainer.setConnectionFactory(stringKeyRedisTemplate.getConnectionFactory());
			CacheMessageListener cacheMessageListener = new CacheMessageListener(stringKeyRedisTemplate,
					redisCaffeineCacheManager);
			redisMessageListenerContainer.addMessageListener(cacheMessageListener,
					new ChannelTopic(cacheConfigProperties.getRedisProperties().getTopic()));
			return redisMessageListenerContainer;
		}
}
