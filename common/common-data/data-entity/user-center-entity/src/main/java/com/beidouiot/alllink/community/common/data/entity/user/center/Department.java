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
* @Description 组织/部门
* @author longww
* @date 2021年4月14日
*/

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.DEPARTMENT)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -220808331783646926L;

	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.DEPARTMENT_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.DEPARTMENT_CODE_COLUMN_DEFINITION)
	private String code;
	
	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.DEPARTMENT_DESCR_COLUMN_DEFINITION)
	private String descr;
	
	@Column(name = EntityConstants.SORT_NO, nullable = false, columnDefinition = EntityConstants.SORT_NO_COLUMN_DEFINITION)
	private Integer sortNo;
	
	@Column(name = EntityConstants.DEPARTMENT_TYPE, nullable = false, columnDefinition = EntityConstants.DEPARTMENT_TYPE_COLUMN_DEFINITION)
	private String type;
	
	@Column(name = EntityConstants.PID, nullable = true, columnDefinition = EntityConstants.PID_COLUMN_DEFINITION)
	private Long pid;
	
	@Column(name = EntityConstants.TENANT_ID, nullable = true, columnDefinition = EntityConstants.TENANT_ID_COLUMN_DEFINITION)
	private Long tenantId;
	
	@Column(name = EntityConstants.CUSTOMER_ID, nullable = true, columnDefinition = EntityConstants.CUSTOMER_ID_COLUMN_DEFINITION)
	private Long customerId;
	
	@Column(name = EntityConstants.SYSTEM_CODE, nullable = true, columnDefinition = EntityConstants.SYSTEM_CODE_COLUMN_DEFINITION)
	private String systemCode;
}
