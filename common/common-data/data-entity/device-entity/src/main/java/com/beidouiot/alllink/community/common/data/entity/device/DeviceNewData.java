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
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = EntityConstants.DEVICE_NEW_DATA)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper = true)
public class DeviceNewData extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4569085288124874451L;

	@Column(name = EntityConstants.DEVICE_ID, nullable = false, columnDefinition = EntityConstants.DEVICE_ID_COLUMN_DEFINITION)
	private Long deviceId;

	@Column(name = EntityConstants.DEVICE_NEW_DATA_MODEL_CODE, nullable = false, columnDefinition = EntityConstants.DEVICE_NEW_DATA_MODEL_CODE_COLUMN_DEFINITION)
	private String modelCode;

	@Column(name = EntityConstants.DEVICE_NEW_DATA_MODEL_TYPE, nullable = false, columnDefinition = EntityConstants.DEVICE_NEW_DATA_MODEL_TYPE_COLUMN_DEFINITION)
	private Integer modelType;

	@Column(name = EntityConstants.DEVICE_NEW_DATA_RECEIVED_DATA, nullable = false, columnDefinition = EntityConstants.DEVICE_NEW_DATA_RECEIVED_DATA_COLUMN_DEFINITION)
	private String receivedData;

	@Column(name = EntityConstants.TENANT_ID, nullable = false, columnDefinition = EntityConstants.TENANT_ID_COLUMN_DEFINITION)
	private Long tentantId;

	@Column(name = EntityConstants.PRODUCT_ID, nullable = false, columnDefinition = EntityConstants.PRODUCT_ID_COLUMN_DEFINITION)
	private Long productId;
}
