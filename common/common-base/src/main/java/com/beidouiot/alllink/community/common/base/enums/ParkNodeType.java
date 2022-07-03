package com.beidouiot.alllink.community.common.base.enums;

/**
 * 
 *
 * @Description 园区类型
 * @author longww
 * @date 2021年4月11日
 */
public enum ParkNodeType {
	PARK("01", "园区/小区"),
	BUILDING("02", "楼栋"),
	UNIT("03", "单元"),
	FLOOR("04", "楼层"), 
	APARTMENT("05", "房间");
	
	/**
	 * 代码
	 */
	private String code;
	
	/**
	 * 代码信息
	 */
	private String msg;
	
	ParkNodeType(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static String getMsg(String code) {
		ParkNodeType[] msgs = ParkNodeType.values();
		for (ParkNodeType parkNodeType : msgs) {
			if (parkNodeType.code.equals(code)) {
				return parkNodeType.msg;
			}
		}
		return null;
	}
}
