package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import java.util.Date;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 *
 * @Description 用户
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class UserDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2378353475167291477L;

	private String username;

	private String password;

	private String email;

	private String mobile;

	private String name;

	private String nickname;

	private Integer sex;

	private Boolean status;

	private String userType;

	private String headPortrait;

	private String weixin;

	private String qqNo;

	private String descr;

	private String identityNo;

	private String identityType;

	private Long tenantId;

	public UserDto(Long id, String username, String email, String mobile, String name, String nickname, Integer sex,
			Boolean status, String userType, String headPortrait, String weixin, String qqNo, String descr, 
			String identityNo, String identityType, Long tenantId, String updatedBy, Date updatedDate) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.status = status;
		this.userType = userType;
		this.headPortrait = headPortrait;
		this.weixin = weixin;
		this.qqNo = qqNo;
		this.descr = descr;
		this.identityNo = identityNo;
		this.identityType = identityType;
		this.tenantId = tenantId;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}
	
	private String strTenantId;
	
	public String getStrTenantId() {
		strTenantId = tenantId == null ? "" : String.valueOf(tenantId);
		return strTenantId;
	}
	
	private String strStatus;
	
	public String getStrStatus() {
		strStatus = status ? "禁用" : "启用";
		return strStatus;
	}

}
