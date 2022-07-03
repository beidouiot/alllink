package com.beidouiot.alllink.community.cache.properties;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/
@Data
public class RedisProperties {

    /** 
     * 全局过期时间，单位毫秒，默认不过期
     */
    private long defaultExpiration = 0;
    
    /** 
     * 每个cacheName的过期时间，单位毫秒，优先级比defaultExpiration高
     */
    private Map<String, Long> expires = new HashMap<>();
    
    /**
     * 缓存更新时通知其他节点的topic名称
     */
    private String topic = "cache:redis:caffeine:topic";
    
}
