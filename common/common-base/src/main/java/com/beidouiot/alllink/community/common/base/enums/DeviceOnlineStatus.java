package com.beidouiot.alllink.community.common.base.enums;

public enum DeviceOnlineStatus {

	ONLINE(1,"在线"),
	OFFLINE(2,"离线"),
	ACTIVATION(3,"激活"),
	UNACTIVATION(4,"未激活"),
	UNKNOWN(5,"未知");
	
	/**
	 * 代码
	 */
	private int code;
	
	/**
	 * 代码信息
	 */
	private String msg;
	
	DeviceOnlineStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static String getMsg(int code) {
		DeviceOnlineStatus[] msgs = DeviceOnlineStatus.values();
		for (DeviceOnlineStatus deviceOnlineStatus : msgs) {
			if (deviceOnlineStatus.code == code) {
				return deviceOnlineStatus.msg;
			}
		}
		return null;
	}
	
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
