package com.beidouiot.alllink.community.cache.properties;

import lombok.Data;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/
@Data
public class CaffeineProperties {

    /** 
     * 访问后过期时间，单位毫秒
     */
    private long expireAfterAccess;
    
    /** 
     * 写入后过期时间，单位毫秒
     */
    private long expireAfterWrite;
    
    /** 
     * 写入后刷新时间，单位毫秒
     */
    private long refreshAfterWrite;
    
    /** 
     * 初始化大小
     */
    private int initialCapacity;
    
    /** 
     * 最大缓存对象个数，超过此数量时之前放入的缓存将失效
     */
    private long maximumSize;
}
