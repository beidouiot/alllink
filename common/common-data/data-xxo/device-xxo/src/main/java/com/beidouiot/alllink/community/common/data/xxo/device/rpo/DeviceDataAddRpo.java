package com.beidouiot.alllink.community.common.data.xxo.device.rpo;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Api(tags="增加设备数据信息")
@Data
@ToString
public class DeviceDataAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1657570950406320412L;

	@ApiModelProperty(value = "设备id", dataType = "String", required = false)
	private String deviceId;
	
	@ApiModelProperty(value = "设备sn", dataType = "String", required = false)
	private String deviceSN;
	
	@ApiModelProperty(value = "设备采集数据", dataType = "String", required = true)
	private Map<String, String> data;
	
}
