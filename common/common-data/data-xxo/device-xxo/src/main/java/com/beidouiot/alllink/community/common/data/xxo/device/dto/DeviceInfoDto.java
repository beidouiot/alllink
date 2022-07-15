package com.beidouiot.alllink.community.common.data.xxo.device.dto;

import java.util.Date;

import com.beidouiot.alllink.community.common.base.enums.DeviceOnlineStatus;
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
public class DeviceInfoDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8564330705828570317L;
	
	private String name;
	
	private String nickname;
	
	private String deviceSn;
	
	private String networkingProtocol;
	
	private String equipmentModel;
	
	private String firmwareName;
	
	private String firmwareVersion;
	
	private String hardwareVersion;
	
	private Date lastLinkTime;
	
	private Integer onlineStatus;
	
	private String longitude;
	
	private String latitude;
	
	private String positionDetail;
	
	private Date expiryWarrantyDate;
	
	private String remark;
	
	private String deviceAccessType;
	
	private Long gatewayDeviceId;
	
	private Boolean gatewayFlag;
	
	private Long thirdPlatformId;
	
	private Long tenantId;
	
	private Date activationTime;
	
	private Boolean enableFlag;
	
	private Date lastedUpgradeTime;
	
	private Integer firmwareUpgradeQuantity;
	
	private Long productId;
	
	private String strProductId;
	
	private String strId;
	
	private String strGatewayDeviceId;
	
	private String strThirdPlatformId;
	
	private String strGatewayFlag;
	
	private String strTenantId;
	
	private String strEnableFlag;
	
	private String strOnlineStatus;
	
	public String getStrProductId() {
		strProductId = String.valueOf(productId);
		return strProductId;
	}
	
	public String getStrId() {
		strId = id == null || id == 0 ? "" : String.valueOf(id);
		return strId;
	}
	
	public String getStrGatewayDeviceId() {
		strGatewayDeviceId = gatewayDeviceId == null || gatewayDeviceId == 0 ? "" : String.valueOf(gatewayDeviceId);
		return strGatewayDeviceId;
	}
	
	public String getStrThirdPlatformId() {
		strThirdPlatformId = thirdPlatformId == null || thirdPlatformId == 0 ? "" : String.valueOf(thirdPlatformId);
		return strThirdPlatformId;
	}
	
	public String getStrGatewayFlag() {
		strGatewayFlag = gatewayFlag ? "网关设备" : "网关子设备";
		return strGatewayFlag;
	}
	
	public String getStrTenantId() {
		strTenantId = tenantId == null || tenantId == 0 ? "" : String.valueOf(tenantId);
		return strTenantId;
	}
	
	public String getStrEnableFlag() {
		strEnableFlag = enableFlag ? "启用" : "禁用";
		return strEnableFlag;
	}
	
	public String getStrOnlineStatus() {
		strOnlineStatus = DeviceOnlineStatus.getMsg(onlineStatus);
		return strOnlineStatus;
	}
	

}
