package com.beidouiot.alllink.community.common.data.xxo.device.rpo;

import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Api(tags="查询设备请求信息")
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class DeviceInfoSearchRpo extends SearchBaseRpo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4426626883844175656L;

	@ApiModelProperty(value = "设备名称", dataType = "String", required = false)
	private String name;
	
	@ApiModelProperty(value = "设备昵称", dataType = "String", required = false)
	private String nickname;
	
	@ApiModelProperty(value = "设备SN号", dataType = "String", required = false)
	private String deviceSn;
	
	@ApiModelProperty(value = "设备在线状态", dataType = "Integer", required = false)
	private Integer onlineStatus;

}
