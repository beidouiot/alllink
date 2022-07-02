package com.beidouiot.alllink.community.common.data.entity.product;

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
 *
 * @Description 产品
 * @author longww
 * @date 2021年11月17日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.PRODUCT)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class Product extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6580167628660838156L;

	@Column(name = EntityConstants.CHINESE_NAME, columnDefinition = EntityConstants.PRODUCT_CHINESE_NAME_COLUMN_DEFINITION)
	private String chineseName;
	
	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.PRODUCT_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.PRODUCT_DEVICE_TYPE, nullable = false, columnDefinition = EntityConstants.PRODUCT_DEVICE_TYPE_COLUMN_DEFINITION)
	private Integer productDeviceType;
	
	@Column(name = EntityConstants.PRODUCT_ACCESS_MODE, nullable = true, columnDefinition = EntityConstants.PRODUCT_ACCESS_MODE_COLUMN_DEFINITION)
	private Integer productAccessMode;
	
	@Column(name = EntityConstants.PRODUCT_COMMUNICATION_PROTOCOL, nullable = false, columnDefinition = EntityConstants.PRODUCT_COMMUNICATION_PROTOCOL_COLUMN_DEFINITION)
	private String communicationProtocol;
	
	@Column(name = EntityConstants.PRODUCT_NETWORKING_PROTOCOL, nullable = true, columnDefinition = EntityConstants.PRODUCT_NETWORKING_PROTOCOL_COLUMN_DEFINITION)
	private String networkingProtocol;
	
	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.PRODUCT_DESCR_COLUMN_DEFINITION)
	private String descr;
	
	@Column(name = EntityConstants.PRODUCT_COPY_FLAG, nullable = false, columnDefinition = EntityConstants.PRODUCT_COPY_FLAG_COLUMN_DEFINITION)
	private Boolean copyFlag;
	
	@Column(name = EntityConstants.PRODUCT_TYPE_ID, nullable = false, columnDefinition = EntityConstants.PRODUCT_TYPE_ID_COLUMN_DEFINITION)
	private Long productTypeId;
}
