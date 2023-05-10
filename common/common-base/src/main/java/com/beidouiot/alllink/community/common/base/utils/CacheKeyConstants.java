package com.beidouiot.alllink.community.common.base.utils;

/**
*
* @Description TODO
* @author longww
* @date 2021年6月13日
*/

public class CacheKeyConstants {
	
	/**
	 * 手机短信验证码缓存
	 */
	public static final String MOBILE_SMS_CODE_CACHE_PREFIX = "mobile_sms_code_cache:user_center:user:sms_code";
	
	public static final String RESOURCE_ROLES_MAP = "AUTH:RESOURCE_ROLES_MAP";
	
	public static final String TOKEN_BLACKLIST_PREFIX = "AUTH:TOKEN:BLACKLIST:";
	
	/**
	 * 注解缓存命名空间 --用户信息缓存
	 */
	public static final String USER_CACHE = "userCache";
	
	/**
	 * 用心信息缓存--手机号key
	 */
	public static final String USER_CENTER_USER_MOBILE_PREFIX = "userCenter:user:mobile:";
	
	/**
	 * 	 * 用心信息缓存--用户名key
	 */
	public static final String USER_CENTER_USER_USERNAME_PREFIX =  "userCenter:user:username:";
	
	public static final String USER_CENTER_USER_PAGE_PREFIX = "userCenter:user:page:";
	
	public static final String USER_CENTER_USER_ALL = "userCenter:user:all";
	

}
