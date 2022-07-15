package com.beidouiot.alllink.community.common.data.entity.device;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;
import com.beidouiot.alllink.community.common.data.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
*
* @Description 设备信息
* @author longww
* @date 2022年2月21日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.DEVICE_INFO)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class DeviceInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6858072990172960726L;
	
	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.DEVICE_INFO_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.DEVICE_INFO_NICKNAME, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_NICKNAME_COLUMN_DEFINITION)
	private String nickname;
	
	@Column(name = EntityConstants.DEVICE_INFO_DEVICE_SN, nullable = false, columnDefinition = EntityConstants.DEVICE_INFO_DEVICE_SN_COLUMN_DEFINITION)
	private String deviceSn;
	
	@Column(name = EntityConstants.DEVICE_INFO_NETWORKING_PROTOCOL, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_NETWORKING_PROTOCOL_COLUMN_DEFINITION)
	private String networkingProtocol;
	
	@Column(name = EntityConstants.DEVICE_INFO_EQUIPMENT_MODEL, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_EQUIPMENT_MODEL_COLUMN_DEFINITION)
	private String equipmentModel;
	
	@Column(name = EntityConstants.DEVICE_INFO_FIRMWARE_NAME, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_FIRMWARE_NAME_COLUMN_DEFINITION)
	private String firmwareName;
	
	@Column(name = EntityConstants.DEVICE_INFO_FIRMWARE_VERSION, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_FIRMWARE_VERSION_COLUMN_DEFINITION)
	private String firmwareVersion;
	
	@Column(name = EntityConstants.DEVICE_INFO_HARDWARE_VERSION, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_HARDWARE_VERSION_COLUMN_DEFINITION)
	private String hardwareVersion;
	
	@Column(name = EntityConstants.DEVICE_INFO_LAST_LINK_TIME, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_LAST_LINK_TIME_COLUMN_DEFINITION)
	private Date lastLinkTime;
	
	@Column(name = EntityConstants.DEVICE_INFO_ONLINE_STATUS, nullable = false, columnDefinition = EntityConstants.DEVICE_INFO_ONLINE_STATUS_COLUMN_DEFINITION)
	private Integer onlineStatus;
	
	@Column(name = EntityConstants.DEVICE_INFO_LONGITUDE, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_LONGITUDE_COLUMN_DEFINITION)
	private String longitude;
	
	@Column(name = EntityConstants.DEVICE_INFO_LATITUDE, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_LATITUDE_COLUMN_DEFINITION)
	private String latitude;
	
	@Column(name = EntityConstants.DEVICE_INFO_POSITION_DETAIL, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_POSITION_DETAIL_COLUMN_DEFINITION)
	private String positionDetail;
	
	@Column(name = EntityConstants.DEVICE_INFO_EXPIRY_WARRANTY_DATE, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_EXPIRY_WARRANTY_DATE_COLUMN_DEFINITION)
	private Date expiryWarrantyDate;
	
	@Column(name = EntityConstants.REMARK, nullable = true, columnDefinition = EntityConstants.REMARK_COLUMN_DEFINITION)
	private String remark;
	
	@Column(name = EntityConstants.DEVICE_INFO_DEVICE_ACCESS_TYPE, nullable = false, columnDefinition = EntityConstants.DEVICE_INFO_DEVICE_ACCESS_TYPE_COLUMN_DEFINITION)
	private String deviceAccessType;
	
	@Column(name = EntityConstants.DEVICE_INFO_GATEWAY_DEVICE_ID, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_GATEWAY_DEVICE_ID_COLUMN_DEFINITION)
	private Long gatewayDeviceId;
	
	@Column(name = EntityConstants.DEVICE_INFO_GATEWAY_FLAG, nullable = false, columnDefinition = EntityConstants.DEVICE_INFO_GATEWAY_FLAG_COLUMN_DEFINITION)
	private Boolean gatewayFlag;
	
	@Column(name = EntityConstants.DEVICE_INFO_THIRD_PLATFORM_ID, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_THIRD_PLATFORM_ID_COLUMN_DEFINITION)
	private Long thirdPlatformId;
	
	@Column(name = EntityConstants.TENANT_ID, nullable = true, columnDefinition = EntityConstants.TENANT_ID_COLUMN_DEFINITION)
	private Long tenantId;
	
	@Column(name = EntityConstants.ACTIVATION_TIME, nullable = true, columnDefinition = EntityConstants.ACTIVATION_TIME_COLUMN_DEFINITION)
	private Date activationTime;
	
	@Column(name = EntityConstants.ENABLE_FLAG, nullable = false, columnDefinition = EntityConstants.ENABLE_FLAG_COLUMN_DEFINITION)
	private Boolean enableFlag;
	
	@Column(name = EntityConstants.DEVICE_INFO_LASTED_UPGRADE_TIME, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_LASTED_UPGRADE_TIME_COLUMN_DEFINITION)
	private Date lastedUpgradeTime;
	
	@Column(name = EntityConstants.DEVICE_INFO_FIRMWARE_UPGRADE_QUANTITY, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_FIRMWARE_UPGRADE_QUANTITY_COLUMN_DEFINITION)
	private Integer firmwareUpgradeQuantity;
	
	@Column(name = EntityConstants.PRODUCT_ID, nullable = false, columnDefinition = EntityConstants.PRODUCT_ID_COLUMN_DEFINITION)
	private Long productId;

}
