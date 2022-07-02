package com.beidouiot.alllink.community.cache.support;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import com.beidouiot.alllink.community.cache.properties.CacheProperties;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/

public class RedisCaffeineCacheManager implements CacheManager {
	
	private final Logger LOGGER = LoggerFactory.getLogger(RedisCaffeineCacheManager.class);
    
    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
    
    private CacheProperties cacheProperties;
    
    private RedisTemplate<Object, Object> stringKeyRedisTemplate;

    private boolean dynamic = true;

    private Set<String> cacheNames;
    
    public RedisCaffeineCacheManager(CacheProperties cacheProperties,
            RedisTemplate<Object, Object> stringKeyRedisTemplate) {
        super();
        this.cacheProperties = cacheProperties;
        this.stringKeyRedisTemplate = stringKeyRedisTemplate;
        this.dynamic = cacheProperties.isDynamic();
        this.cacheNames = cacheProperties.getCacheNames();
    }

    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);
        if(cache != null) {
            return cache;
        }
        if(!dynamic && !cacheNames.contains(name)) {
            return cache;
        }
        
        cache = new RedisCaffeineCache(name, stringKeyRedisTemplate, caffeineCache(), cacheProperties);
        Cache oldCache = cacheMap.putIfAbsent(name, cache);
        LOGGER.debug("create cache instance, the cache name is : {}", name);
        return oldCache == null ? cache : oldCache;
    }
    
    public com.github.benmanes.caffeine.cache.Cache<Object, Object> caffeineCache(){
        Caffeine<Object, Object> cacheBuilder = Caffeine.newBuilder();
        if(cacheProperties.getCaffeineProperties().getExpireAfterAccess() > 0) {
            cacheBuilder.expireAfterAccess(cacheProperties.getCaffeineProperties().getExpireAfterAccess(), TimeUnit.MILLISECONDS);
        }
        if(cacheProperties.getCaffeineProperties().getExpireAfterWrite() > 0) {
            cacheBuilder.expireAfterWrite(cacheProperties.getCaffeineProperties().getExpireAfterWrite(), TimeUnit.MILLISECONDS);
        }
        if(cacheProperties.getCaffeineProperties().getInitialCapacity() > 0) {
            cacheBuilder.initialCapacity(cacheProperties.getCaffeineProperties().getInitialCapacity());
        }
        if(cacheProperties.getCaffeineProperties().getMaximumSize() > 0) {
            cacheBuilder.maximumSize(cacheProperties.getCaffeineProperties().getMaximumSize());
        }
        if(cacheProperties.getCaffeineProperties().getRefreshAfterWrite() > 0) {
            cacheBuilder.refreshAfterWrite(cacheProperties.getCaffeineProperties().getRefreshAfterWrite(), TimeUnit.MILLISECONDS);
        }
        return cacheBuilder.build();
    }

    public Collection<String> getCacheNames() {
        return this.cacheNames;
    }
    
    public void clearLocal(String cacheName, Object key) {
        Cache cache = cacheMap.get(cacheName);
        if(cache == null) {
            return ;
        }
        
        RedisCaffeineCache redisCaffeineCache = (RedisCaffeineCache) cache;
        redisCaffeineCache.clearLocal(key);
    }
}
