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
public class DeviceNewDataUpdateDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4745253967551712707L;

	private Long id;
	
	private Long deviceId;

	private String modelCode;

	private Integer modelType;

	private String receivedData;

	private Long tenantId;

	private Long productId;
	
	private String updatedBy;
	
}
