package com.beidouiot.alllink.community.common.data.entity.user.center;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;
import com.beidouiot.alllink.community.common.data.entity.BaseEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * @Description 租户
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.TENANT)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Tenant extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8794836407255420206L;
	
	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.TENANT_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.ADDR, nullable = true, columnDefinition = EntityConstants.TENANT_ADDR_COLUMN_DEFINITION)
	private String addr;
	
	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.TENANT_DESCR_COLUMN_DEFINITION)
	private String descr;
	
	@Column(name = EntityConstants.LINKMAN, nullable = true, columnDefinition = EntityConstants.TENANT_LINKMAN_COLUMN_DEFINITION)
	private String linkman;
	
	@Column(name = EntityConstants.EMAIL, nullable = true, columnDefinition = EntityConstants.TENANT_EMAIL_COLUMN_DEFINITION)
	private String email;
	
	@Column(name = EntityConstants.PHONE, nullable = true, columnDefinition = EntityConstants.PHONE_COLUMN_DEFINITION)
	private String phone;
	
	@Column(name = EntityConstants.ZIP_CODE, nullable = true, columnDefinition = EntityConstants.ZIP_CODE_COLUMN_DEFINITION)
	private String zipCode;
	
	@Column(name = EntityConstants.INDUSTRY_ID, nullable = true, columnDefinition = EntityConstants.INDUSTRY_ID_COLUMN_DEFINITION)
	private Long industryId;
	
	@Type(type = "json")
	@Column(name = EntityConstants.CITY, nullable = true, columnDefinition = EntityConstants.CITY_COLUMN_DEFINITION)
	private String city;
	
	@Column(name = EntityConstants.TENANT_STATUS, nullable = false, columnDefinition = EntityConstants.TENANT_STATUS_COLUMN_DEFINITION)
	private Boolean status;
	
	@Column(name = EntityConstants.SYSTEM_CODE, nullable = true, columnDefinition = EntityConstants.SYSTEM_CODE_COLUMN_DEFINITION)
	private String systemCode;
}
