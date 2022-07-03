package com.beidouiot.alllink.community.common.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
*
* @Description TODO
* @author longww
* @date 2021年6月6日
*/
@Component
public class RedisUtil {
	
	private static Integer CACHE_DATE = 10;	//redis 失效时间
	
	@Resource
	private  RedisTemplate<String, Object> redisTemplate;
	
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
    
    public long getTime(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    
   public Object get(String key) {
	   return key == null ? null : redisTemplate.opsForValue().get(key);
   }
   
   public Object get(String key, Object hashKey) {
	   return key == null || hashKey == null ? null : redisTemplate.opsForHash().get(key, hashKey);
   }
   
   public void put(String key, Object hashKey, Object value) {
	   if (key != null && hashKey != null) {
		   redisTemplate.opsForHash().put(key, hashKey, value);
	   }
   }
   
   public void set(String key, Object value) {
	   redisTemplate.opsForValue().set(key, value);
   }
   
   public void set(String key, Object value, long expire, TimeUnit unit) {
	   redisTemplate.opsForValue().set(key, value, expire, unit);
   }
}
