package com.beidouiot.alllink.community.common.data.xxo.device.rpo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Api(tags="增加设备请求信息")
@Data
@ToString
public class DeviceInfoAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2495939032169144387L;
	
	@ApiModelProperty(value = "设备名称", dataType = "String", required = true)
	@NotBlank(message = "设备名称不能为空！")
	private String name;
	
	@ApiModelProperty(value = "设备昵称", dataType = "String", required = false)
	private String nickname;
	
	@ApiModelProperty(value = "设备SN号", dataType = "String", required = true)
	@NotBlank(message = "设备SN号不能为空！")
	private String deviceSn;
	
	@ApiModelProperty(value = "设备组网协议", dataType = "String", required = true)
	@NotBlank(message = "设备组网协议不能为空！")
	private String networkingProtocol;
	
	@ApiModelProperty(value = "设备型号", dataType = "String", required = false)
	private String equipmentModel;

	@ApiModelProperty(value = "固件名称", dataType = "String", required = false)
	private String firmwareName;
	
	@ApiModelProperty(value = "固件版本", dataType = "String", required = false)
	private String firmwareVersion;
	
	@ApiModelProperty(value = "硬件版本", dataType = "String", required = false)
	private String hardwareVersion;
	
	@ApiModelProperty(value = "经度", dataType = "String", required = false)
	private String longitude;
	
	@ApiModelProperty(value = "维度", dataType = "String", required = false)
	private String latitude;
	
	@ApiModelProperty(value = "详细地址", dataType = "String", required = false)
	private String positionDetail;
	
	@ApiModelProperty(value = "过保日期", dataType = "String", required = false)
	private Date expiryWarrantyDate;
	
	@ApiModelProperty(value = "备注", dataType = "String", required = false)
	private String remark;
	
	@ApiModelProperty(value = "设备接入类型", dataType = "String", required = false)
	private String deviceAccessType;
	
	@ApiModelProperty(value = "所属网关Id", dataType = "String", required = false)
	private Long gatewayDeviceId;
	
	@ApiModelProperty(value = "是否网关设备", dataType = "String", required = true)
	private Boolean gatewayFlag;
	
	@ApiModelProperty(value = "所属第三方平台Id", dataType = "String", required = false)
	private Long thirdPlatformId;
	
	@ApiModelProperty(value = "所属租户Id", dataType = "String", required = false)
	private Long tenantId;
	
	@ApiModelProperty(value = "是否启用", dataType = "String", required = true)
	private Boolean enableFlag;
	
	@ApiModelProperty(value = "所属产品Id", dataType = "String", required = true)
	private Long productId;
}
