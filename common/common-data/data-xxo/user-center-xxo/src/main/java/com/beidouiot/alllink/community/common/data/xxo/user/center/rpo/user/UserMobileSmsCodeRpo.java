package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="发送手机号短信验证码信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserMobileSmsCodeRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1527526201022361618L;
	
	@ApiModelProperty(value = "手机号, length = 11", dataType = "String", required = true)
	@NotBlank(message = "手机号不能为空！")
	@Length(min=11, max=11, message="手机号长度必须是11个字符")
	@Pattern(regexp = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$", message = "手机号不合法")
	private String mobile;

}
