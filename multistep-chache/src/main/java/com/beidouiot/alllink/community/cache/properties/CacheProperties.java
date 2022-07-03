package com.beidouiot.alllink.community.cache.properties;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/
@Data
@ConfigurationProperties(prefix = "spring.cache.multi")
public class CacheProperties {
    
    private Set<String> cacheNames = new HashSet<>();
    
    /** 
     * 是否存储空值，默认true，防止缓存穿透
     */
    private boolean cacheNullValues = true;
    
    /** 
     * 是否动态根据cacheName创建Cache的实现，默认true
     */
    private boolean dynamic = true;
    
    /** 
     * 缓存key的前缀
     */
    private String cachePrefix;
    
    private RedisProperties redisProperties = new RedisProperties();
    
    private CaffeineProperties caffeineProperties =  new CaffeineProperties();
}
