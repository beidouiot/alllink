package com.beidouiot.alllink.community.common.data.xxo.device.rpo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Api(tags="修改设备最近接收信息")
@Data
@ToString
public class DeviceNewDataUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8905706222673117566L;

	@ApiModelProperty(value = "Id", dataType = "Long", required = true)
	@NotNull(message = "Id不能为空")
	private Long id;
	
	@ApiModelProperty(value = "所属设备Id", dataType = "Long", required = true)
	@NotNull(message = "设备Id不能为空")
	private Long deviceId;

	@ApiModelProperty(value = "物模型标识", dataType = "String", required = true)
	@NotBlank(message = "标识不能为空")
	private String modelCode;

	@ApiModelProperty(value = "物模型类型", dataType = "Integer", required = true)
	@NotNull(message = "类型不能为空")
	private Integer modelType;

	@ApiModelProperty(value = "物模型数据", dataType = "String", required = true)
	@NotBlank(message = "数据不能为空")
	private String receivedData;

	@ApiModelProperty(value = "所属租户Id", dataType = "Long", required = false)
	private Long tenantId;

	@ApiModelProperty(value = "所属产品Id", dataType = "Long", required = true)
	@NotNull(message = "产品Id不能为空")
	private Long productId;
	
}
