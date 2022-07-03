package com.beidouiot.alllink.community.common.base.utils;

/**
 * 
 *
 * @Description 常用常亮
 * @author longww
 * @date 2021年4月11日
 */
public class Constants {
	
	private Constants() {}

	public static final Boolean TRUE = new Boolean(true);
	
	public static final Boolean FALSE = new Boolean(false);
	
	public static final String APP_ID = "appId";
	
	public static final String NONCE_STR = "nonceStr";
	 
	public static final String TIMES = "times";
	
	public static final String SIGNATURE = "signature";
	
	public static final String SUCCESS = "SUCCESS";
	
	public static final String FAIL = "FAIL";
	
	public static final String ACCESS_TOKEN = "accessToken";
	
	public static final String IDEMPOTENT_TOKEN = "idempotentToken";
	
	public static final String AUTHORIZE_TOKEN = "Authorization";
	
	public static final String BEARER = "Bearer ";
	
	public static final String TOKEN = "token ";
	
	public static final String UTF8 = "UTF-8";
	
	public static final String USER = "user";
	
	public static final String AUTHORITY_PREFIX = "ROLE_";

    public static final String AUTHORITY_CLAIM_NAME = "authorities";
	
    public static final String EXPIRES_IN = "expires_in";
    
    public static final String JTI = "jti";
    
    public static final String EXP = "exp";
    
    public static final String ID = "id";
    
    public static final String USER_NAME = "user_name";
    
    public static final String NAME = "name";
    
    /**
     * 登录用户名
     */
    public static final String USERNAME = "username";
    
    public static final String MOBILE = "mobile";
        
    /**
     * 手机短信验证码
     */
    public static final String SMS_CODE = "sms_code";
    
    /**
     * 手机号+短信验证码登录认证证模式
     */
    public static final String SMS = "sms";
    
    /**
     * 用户名(手机号/邮箱)+密码+图形验证码登录认证模式
     */
    public static final String PASSWORD_CODE = "password_code";
    
    /**
     * 微信授权码登录认证模式
     */
    public static final String WEIXIN = "weixin";
    
    /**
     * 微信授权码
     */
    public static final String WEIXIN_CODE = "weixin_code";
}
