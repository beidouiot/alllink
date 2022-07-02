package com.beidouiot.alllink.community.cache.support;

import java.io.Serializable;

import lombok.Data;

/**
*
* @Description TODO
* @author longww
* @date 2021年5月26日
*/
@Data
public class CacheMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1547751720037186181L;

	private String cacheName;
	
	private Object key;

	public CacheMessage(String cacheName, Object key) {
		super();
		this.cacheName = cacheName;
		this.key = key;
	}
}
