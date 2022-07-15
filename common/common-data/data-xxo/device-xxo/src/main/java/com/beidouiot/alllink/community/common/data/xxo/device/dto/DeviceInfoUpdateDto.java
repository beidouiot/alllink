package com.beidouiot.alllink.community.common.data.xxo.device.dto;

import java.io.Serializable;
import java.util.Date;

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
public class DeviceInfoUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7155962368588999832L;

	private Long id;
	
	private String nickname;
	
	private String networkingProtocol;
	
	private String equipmentModel;
	
	private String firmwareName;
	
	private String firmwareVersion;
	
	private String hardwareVersion;
	
	private String longitude;
	
	private String latitude;
	
	private String positionDetail;
	
	private Date expiryWarrantyDate;
	
	private String remark;
	
	private String deviceAccessType;
	
	private Long gatewayDeviceId;
	
	private Boolean gatewayFlag;
	
	private Boolean enableFlag;
	
	private Long productId;

	private String updatedBy;
	
}
