package com.beidouiot.alllink.community.common.data.entity.user.center;

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
 * @Description 园区
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.PARK)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class Park extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6973343280351091815L;

	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.PARK_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.PARK_TYPE, nullable = false, columnDefinition = EntityConstants.PARK_TYPE_COLUMN_DEFINITION)
	private String type;
	
	@Column(name = EntityConstants.SORT_NO, nullable = true, columnDefinition = EntityConstants.PARK_SORT_NO_COLUMN_DEFINITION)
	private Integer sortNo;
	
	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.PARK_DESCR_COLUMN_DEFINITION)
	private String descr;
	
	@Column(name = EntityConstants.PID, nullable = true, columnDefinition = EntityConstants.PID_COLUMN_DEFINITION)
	private Long pid;
	
	@Column(name = EntityConstants.TENANT_ID, nullable = true, columnDefinition = EntityConstants.TENANT_ID_COLUMN_DEFINITION)
	private Long tenantId;
	
	@Column(name = EntityConstants.CUSTOMER_ID, nullable = true, columnDefinition = EntityConstants.CUSTOMER_ID_COLUMN_DEFINITION)
	private Long customerId;
	
	@Column(name = EntityConstants.SYSTEM_CODE, nullable = true, columnDefinition = EntityConstants.SYSTEM_CODE_COLUMN_DEFINITION)
	private String systemCode;
}
