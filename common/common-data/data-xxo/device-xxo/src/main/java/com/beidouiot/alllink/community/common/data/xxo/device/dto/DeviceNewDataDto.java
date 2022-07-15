package com.beidouiot.alllink.community.common.data.xxo.device.dto;


import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceNewDataDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2411714665151371431L;

	private Long deviceId;
	
	private String deviceName;
	
	private String deviceNickname;

	private String modelCode;
	
	private String modelName;

	private Integer modelType;

	private String receivedData;

	private Long tenantId;

	private Long productId;
	
	private String strId;
	
	private String strDeviceId;
	
	private String strTenantId;
	
	private String strProductId;
	
	public String getStrId() {
		strId = id == null || id == 0 ? "" : String.valueOf(id);
		return strId;
	}
	
	public String getStrTenantId() {
		strTenantId = tenantId == null || tenantId == 0 ? "" : String.valueOf(tenantId);
		return strTenantId;
	}
	
	public String getStrProductId() {
		strProductId = String.valueOf(productId);
		return strProductId;
	}
	
	public String getStrDeviceId() {
		strDeviceId = String.valueOf(deviceId);
		return strDeviceId;
	}
	
}
