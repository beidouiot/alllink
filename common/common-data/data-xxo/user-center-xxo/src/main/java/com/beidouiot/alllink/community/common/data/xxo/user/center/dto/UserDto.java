package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	@NotBlank(message = "用户名不能为空！")
	private String username;

	@NotBlank(message = "用户密码不能为空！")
	private String password;

	@NotBlank(message = "用户邮箱不能为空！")
	private String email;

	@NotBlank(message = "手机号不能为空！")
	private String mobile;

	@NotBlank(message = "姓名不能为空！")
	private String name;

	private String nickname;

	private Integer sex;

	@NotNull(message = "状态不能为空！")
	private Boolean status;

	@NotNull(message = "用户类型不能为空！")
	private String userType;

	private String headPortrait;

	private String weixin;

	private String qqNo;

	private String descr;

	private String code;

	private String identityNo;

	private String identityType;

	private Long tenantId;

	private Long customerId;

	private Long parkId;

	private String systemCode;

	public UserDto(Long id, String username, String email, String mobile, String name, String nickname, Integer sex,
			Boolean status, String userType, String headPortrait, String weixin, String qqNo, String descr, String code,
			String identityNo, String identityType, Long tenantId, Long customerId, Long parkId, String systemCode, String updatedBy, Date updatedDate) {
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
		this.code = code;
		this.identityNo = identityNo;
		this.identityType = identityType;
		this.tenantId = tenantId;
		this.customerId = customerId;
		this.parkId = parkId;
		this.systemCode = systemCode;
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
	
	private String strCustomerId;
	
	public String getStrCustomerId() {
		strCustomerId = customerId == null ? "" : String.valueOf(customerId);
		return strCustomerId;
	}
	
	private String strparkId;
	
	public String getStrParkId() {
		strparkId = parkId == null ? "" : String.valueOf(parkId);
		return strparkId;
	}

}
