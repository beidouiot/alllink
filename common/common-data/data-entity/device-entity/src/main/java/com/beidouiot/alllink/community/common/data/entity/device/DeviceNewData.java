package com.beidouiot.alllink.community.common.data.entity.device;


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
* @Description 设备最新采集数据信息，每个产品产生一张表，每个设备一条数据
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
public class DeviceNewData extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7249010275234303560L;
	@Column(name = EntityConstants.DEVICE_ID, nullable = false, columnDefinition = EntityConstants.DEVICE_ID_COLUMN_DEFINITION)
	private Long deviceId;
	private String receiveData;
}
