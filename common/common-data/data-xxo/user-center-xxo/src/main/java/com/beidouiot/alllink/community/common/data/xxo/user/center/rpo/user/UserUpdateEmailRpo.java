package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
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

@Api(tags="修改用户邮箱请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateEmailRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5743273528128390827L;

	@ApiModelProperty(value = "用户Id", dataType = "Long", required = true)
	@NotNull(message = "用户Id不能为空！")
	@CheckNumber(message = "用户Id必须为正整数" )
	private Long id;

	@ApiModelProperty(value = "用户邮箱, length = 100", dataType = "String", required = true)
	@NotBlank(message = "用户邮箱不能为空！")
	@Length(min=1, max=100, message="用户邮箱长度不能超过100个字符")
	@Email(message = "邮箱格式正确！")
	private String email;
}
