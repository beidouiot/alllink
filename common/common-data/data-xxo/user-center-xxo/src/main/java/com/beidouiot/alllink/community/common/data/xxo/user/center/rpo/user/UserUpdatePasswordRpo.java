package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="修改用户密码请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdatePasswordRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9123205586775085238L;

	@ApiModelProperty(value = "用户Id", dataType = "Long", required = true)
	@NotNull(message = "用户Id不能为空！")
	@CheckNumber(message = "用户Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "用户原密码", dataType = "String", required = true)
	@NotBlank(message = "用户原密码不能为空！")
	@Length(min=6, message="用户密码长度不能小于6个字符")
	private String oldPassword;
	
	@ApiModelProperty(value = "用户新密码", dataType = "String", required = true)
	@NotBlank(message = "用户新密码不能为空！")
	private String newPassword;
}
