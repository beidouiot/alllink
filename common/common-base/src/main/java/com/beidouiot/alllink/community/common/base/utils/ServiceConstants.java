package com.beidouiot.alllink.community.common.base.utils;

/**
 *   
 *
 * @Description 微服务常量
 * @author longww
 * @date 2021年4月11日
 */
public class ServiceConstants {
	
	public static final String OAUTH2_SERVER = "oauth2-auth-server-center";
	public static final String OAUTH2_URI_BASE = "/oauth/";
	
	public static final String USER_CENTER_SERVER = "user-center-server";
	public static final String USER_CENTER_URI_BASE = "/uc/";
	public static final String USER_URI_BASE = USER_CENTER_URI_BASE + "user/";
	public static final String ROLE_URI_BASE = USER_CENTER_URI_BASE + "role/";
	public static final String TENANT_URI_BASE = USER_CENTER_URI_BASE + "tenant/";
	public static final String MENU_URI_BASE = USER_CENTER_URI_BASE + "menu/";
	public static final String PARK_URI_BASE = USER_CENTER_URI_BASE + "park/";
	public static final String INDUSTRY_URI_BASE = USER_CENTER_URI_BASE + "industry/";
	public static final String CUSTOMER_URI_BASE = USER_CENTER_URI_BASE + "customer/";
	public static final String POSITION_URI_BASE = USER_CENTER_URI_BASE + "position/";
	
	
	public static final String PRODUCT_CENTER_SERVER = "product-center-server";
	public static final String PRODUCT_CENTER_URI_BASE = "/pc/";
	public static final String INDUSTRY_TYPE_URI_BASE = PRODUCT_CENTER_URI_BASE + "industryType/";
	public static final String PRODUCT_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/";
	public static final String PRODUCT_TYPE_URI_BASE = PRODUCT_CENTER_URI_BASE + "productType/";
	public static final String STANDARD_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "standard/model/";
	public static final String STANDARD_PROPERTY_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "standard/pm/";
	public static final String STANDARD_PROPERTY_MODEL_PARAM_URI_BASE = PRODUCT_CENTER_URI_BASE + "standard/pmp/";
	public static final String STANDARD_EVENT_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "standard/em/";
	public static final String STANDARD_EVENT_MODEL_PARAM_URI_BASE = PRODUCT_CENTER_URI_BASE + "standard/emp/";
	public static final String STANDARD_COMMAND_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "standard/cm/";
	public static final String STANDARD_COMMAND_MODEL_PARAM_URI_BASE = PRODUCT_CENTER_URI_BASE + "standard/cmp/";
	public static final String PRODUCT_PROPERTY_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/pm/";
	public static final String PRODUCT_PROPERTY_MODEL_PARAM_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/pmp/";
	public static final String PRODUCT_EVENT_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/em/";
	public static final String PRODUCT_EVENT_MODEL_PARAM_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/emp/";
	public static final String PRODUCT_COMMAND_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/cm/";
	public static final String PRODUCT_COMMAND_MODEL_PARAM_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/cmp/";
	public static final String PRODUCT_MODEL_URI_BASE = PRODUCT_CENTER_URI_BASE + "product/model/";
	public static final String DEVICE_SERVER_URI_BASE = "/ds/";
	public static final String DEVICE_INFO_URI_BASE = DEVICE_SERVER_URI_BASE + "deviceInfo/";
	public static final String DEVICE_NEW_DATA_URI_BASE = DEVICE_SERVER_URI_BASE + "deviceNewData/";
	
}
