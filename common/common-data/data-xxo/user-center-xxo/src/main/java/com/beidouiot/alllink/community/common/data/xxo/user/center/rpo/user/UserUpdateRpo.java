package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="修改用户请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5546381981177544298L;

	@ApiModelProperty(value = "用户Id", dataType = "Long", required = true)
	@NotNull(message = "id不能为空")
	@Min(value = 0)
	@CheckNumber(message = "用户Id为正整数")
	private Long id;

	@ApiModelProperty(value = "租户名称, length = 3~20", dataType = "String", required = true)
	@NotBlank(message = "用户名不能为空！")
	@Length(min=3, max=20, message="租户名称长度不能小于3个字符且不能超过20个字符")
	private String username;
	
	@ApiModelProperty(value = "用户邮箱, length = 100", dataType = "String", required = true)
	@NotBlank(message = "用户邮箱不能为空！")
	@Length(min=1, max=100, message="用户邮箱长度不能超过100个字符")
	@Email(message = "邮箱格式正确！")
	private String email;
	
	@ApiModelProperty(value = "手机号, length = 11", dataType = "String", required = true)
	@NotBlank(message = "手机号不能为空！")
	@Length(min=11, max=11, message="手机号长度必须是11个字符")
	@Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号不合法")
	private String mobile;
	
	@ApiModelProperty(value = "姓名, length = 20", dataType = "String", required = true)
	@NotBlank(message = "姓名不能为空！")
	@Length(min=1, max=20, message="姓名长度不能超过20个字符")
	private String name;
	
	@ApiModelProperty(value = "昵称, length = 50", dataType = "String", required = false)
	@Length(min=0, max=20, message="昵称长度不能超过50个字符")
	private String nickname;
	
	@ApiModelProperty(value = "性别", dataType = "Integer", required = false)
	@Max(value = 150)
	private Integer sex;
	
	@ApiModelProperty(value = "状态", dataType = "Boolean", required = true)
	@NotNull(message = "状态不能为空！")
	private Boolean status;
	
	@ApiModelProperty(value = "用户类型， length = 20", dataType = "String", required = true)
	@NotNull(message = "用户类型不能为空！")
	@Length(min=1, max=20, message="用户类型长度不能超过20个字符")
	private String userType;
	
	@ApiModelProperty(value = "用户头像，头像图片的base64码值， length = 512", dataType = "String", required = false)
	private String headPortrait;
	
	@ApiModelProperty(value = "微信号， length = 50", dataType = "String", required = false)
	@Length(min=0, max=50, message="微信号长度不能超过50个字符")
	private String weixin;
	
	@ApiModelProperty(value = "QQ号， length = 50", dataType = "String", required = false)
	@Length(min=0, max=50, message="QQ号长度不能超过50个字符")
	private String qqNo;
	
	@ApiModelProperty(value = "用户描述， length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="用户描述长度不能超过200个字符")
	private String descr;
	
	@ApiModelProperty(value = "证件号， length = 100", dataType = "String", required = false)
	@Length(min=0, max=100, message="证件号长度不能超过100个字符")
	private String identityNo;
	
	@ApiModelProperty(value = "证件类型，枚举（身份证、军官证、士官证、士兵证、台胞证、护照、绿卡）， length = 100", dataType = "String", required = false)
	@Length(min=0, max=100, message="证件类型长度不能超过100个字符")
	private String identityType;
	
	@ApiModelProperty(value = "所属租户（租户ID）", dataType = "Long", required = false)
	private Long tenantId;

}
