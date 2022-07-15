package com.beidouiot.alllink.community.common.base.enums;

public enum ProductDeviceTypeEnum {
	GWD(1,"网关设备"),
	GWCD(2,"网关子设备"),
	SD(3,"独立设备");
	
	/**
	 * 代码
	 */
	private int code;
	
	/**
	 * 代码信息
	 */
	private String msg;
	
	ProductDeviceTypeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static String getMsg(int code) {
		ProductDeviceTypeEnum[] msgs = ProductDeviceTypeEnum.values();
		for (ProductDeviceTypeEnum productDeviceTypeEnum : msgs) {
			if (productDeviceTypeEnum.code == code) {
				return productDeviceTypeEnum.msg;
			}
		}
		return null;
	}
	
}
