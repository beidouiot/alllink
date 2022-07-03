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
* @Description 职位
* @author longww
* @date 2021年5月9日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.POSITION)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class Position extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1378813349851932146L;
	
	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.POSITION_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.POSITION_CODE_COLUMN_DEFINITION)
	private String code;
	
	@Column(name = EntityConstants.DESCR, nullable = false, columnDefinition = EntityConstants.POSITION_DESCR_COLUMN_DEFINITION)
	private String descr;
	
	@Column(name = EntityConstants.TENANT_ID, nullable = true, columnDefinition = EntityConstants.TENANT_ID_COLUMN_DEFINITION)
	private Long tenantId;
	
	@Column(name = EntityConstants.CUSTOMER_ID, nullable = true, columnDefinition = EntityConstants.CUSTOMER_ID_COLUMN_DEFINITION)
	private Long customerId;
	
	@Column(name = EntityConstants.SYSTEM_CODE, nullable = true, columnDefinition = EntityConstants.SYSTEM_CODE_COLUMN_DEFINITION)
	private String systemCode;
}
