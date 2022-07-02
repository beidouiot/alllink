package com.beidouiot.alllink.community.common.data.entity.user.center;



import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.beidouiot.alllink.community.common.data.constants.EntityConstants;
import com.beidouiot.alllink.community.common.data.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * @Description 用户
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = EntityConstants.USER)
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@ToString(callSuper=true)
public class User extends BaseEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1019575925696045869L;

	@Column(name = EntityConstants.USERNAME, nullable = false, columnDefinition = EntityConstants.LOGIN_NAME_COLUMN_DEFINITION)
	private String username;
	
	@Column(name = EntityConstants.PASSWORD, nullable = false, columnDefinition = EntityConstants.PASSWORD_COLUMN_DEFINITION)
	private String password;
	
	@Column(name = EntityConstants.EMAIL, nullable = false, columnDefinition = EntityConstants.EMAIL_COLUMN_DEFINITION)
	private String email;
	
	@Column(name = EntityConstants.MOBILE, nullable = false, columnDefinition = EntityConstants.MOBILE_COLUMN_DEFINITION)
	private String mobile;
	
	@Column(name = EntityConstants.NAME, nullable = false, columnDefinition = EntityConstants.NAME_COLUMN_DEFINITION)
	private String name;
	
	@Column(name = EntityConstants.NICKNAME, nullable = true, columnDefinition = EntityConstants.NICKNAME_COLUMN_DEFINITION)
	private String nickname;
	
	@Column(name = EntityConstants.SEX, nullable = true, columnDefinition = EntityConstants.SEX_COLUMN_DEFINITION)
	private Integer sex;
	
	@Column(name = EntityConstants.USER_STATUS, nullable = false, columnDefinition = EntityConstants.USER_STATUS_COLUMN_DEFINITION)
	private Boolean status;
	
	@Column(name = EntityConstants.USER_TYPE, nullable = false, columnDefinition = EntityConstants.USER_TYPE_COLUMN_DEFINITION)
	private String userType;
	
	@Column(name = EntityConstants.HEAD_PORTRAIT, nullable = true, columnDefinition = EntityConstants.HEAD_PORTRAIT_COLUMN_DEFINITION)
	private String headPortrait;
	
	@Column(name = EntityConstants.WEIXIN, nullable = true, columnDefinition = EntityConstants.WEIXIN_COLUMN_DEFINITION)
	private String weixin;
	
	@Column(name = EntityConstants.QQ, nullable = true, columnDefinition = EntityConstants.QQ_COLUMN_DEFINITION)
	private String qqNo;
	
	@Column(name = EntityConstants.DESCR, nullable = true, columnDefinition = EntityConstants.USER_DESCR_COLUMN_DEFINITION)
	private String descr;
	
	@Column(name = EntityConstants.CODE, nullable = true, columnDefinition = EntityConstants.USER_CODE_COLUMN_DEFINITION)
	private String code;
	
	@Column(name = EntityConstants.IDENTITY_NO, nullable = true, columnDefinition = EntityConstants.IDENTITY_NO_COLUMN_DEFINITION)
	private String identityNo;
	
	@Column(name = EntityConstants.IDENTITY_TYPE, nullable = true, columnDefinition = EntityConstants.IDENTITY_TYPE_COLUMN_DEFINITION)
	private String identityType;
	
	@Column(name = EntityConstants.TENANT_ID, nullable = true, columnDefinition = EntityConstants.TENANT_ID_COLUMN_DEFINITION)
	private Long tenantId;
	
	@Column(name = EntityConstants.CUSTOMER_ID, nullable = true, columnDefinition = EntityConstants.CUSTOMER_ID_COLUMN_DEFINITION)
	private Long customerId;
	
	@Column(name = EntityConstants.PARK_ID, nullable = true, columnDefinition = EntityConstants.PARK_ID_COLUMN_DEFINITION)
	private Long parkId;
	
	@Column(name = EntityConstants.SYSTEM_CODE, nullable = true, columnDefinition = EntityConstants.SYSTEM_CODE_COLUMN_DEFINITION)
	private String systemCode;

	/**
     * 权限数据
     */
	@Transient
    private Collection<SimpleGrantedAuthority> authorities;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
