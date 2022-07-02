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
* @Description TODO
* @author longww
* @date 2022年2月22日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.DEVICE_UPGRADE_INFO)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class DeviceUpgradeInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2273227329157218905L;

	@Column(name = EntityConstants.DEVICE_ID, nullable = false, columnDefinition = EntityConstants.DEVICE_ID_COLUMN_DEFINITION)
	private Long deviceId;
	
	@Column(name = EntityConstants.DEVICE_INFO_DEVICE_SN, nullable = false, columnDefinition = EntityConstants.DEVICE_INFO_DEVICE_SN_COLUMN_DEFINITION)
	private String deviceSn;
	
	@Column(name = EntityConstants.DEVICE_INFO_FIRMWARE_VERSION, nullable = true, columnDefinition = EntityConstants.DEVICE_INFO_FIRMWARE_VERSION_COLUMN_DEFINITION)
	private String firmwareVersion;
	
	@Column(name = EntityConstants.DEVICE_UPGRADE_INFO_UPGRADE_LOGS, nullable = true, columnDefinition = EntityConstants.DEVICE_UPGRADE_INFO_UPGRADE_LOGS_COLUMN_DEFINITION)
	private String upgradeLogs;
	
	@Column(name = EntityConstants.DEVICE_UPGRADE_INFO_UPGRADE_TIME, nullable = false, columnDefinition = EntityConstants.DEVICE_UPGRADE_INFO_UPGRADE_TIME_COLUMN_DEFINITION)
	private Date upgradeTime;
	
	
}
