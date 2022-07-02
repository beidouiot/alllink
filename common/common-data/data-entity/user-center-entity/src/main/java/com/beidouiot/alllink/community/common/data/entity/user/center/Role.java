package com.beidouiot.alllink.community.common.data.entity.user.center;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;
import com.beidouiot.alllink.community.common.data.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * @Description 角色
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.ROLE)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class Role extends BaseEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1265765213933721239L;
	
	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.ROLE_NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.CODE, nullable = false, columnDefinition = EntityConstants.ROLE_CODE_COLUMN_DEFINITION)
	private String code;
	
	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.ROLE_DESCR_COLUMN_DEFINITION)
	private String descr;
	
	@Column(name = EntityConstants.TENANT_ID, nullable = true, columnDefinition = EntityConstants.TENANT_ID_COLUMN_DEFINITION)
	private Long tenantId;
	
	@Column(name = EntityConstants.CUSTOMER_ID, nullable = true, columnDefinition = EntityConstants.CUSTOMER_ID_COLUMN_DEFINITION)
	private Long customerId;
	
	@Column(name = EntityConstants.SYSTEM_CODE, nullable = true, columnDefinition = EntityConstants.SYSTEM_CODE_COLUMN_DEFINITION)
	private String systemCode;
	
	@Transient
	private List<Menu> menus = new ArrayList<Menu>();
	
	@Transient
	private List<String> urls = new ArrayList<String>();
	
	

	@Override
	public String getAuthority() {
		return code;
	}

}
