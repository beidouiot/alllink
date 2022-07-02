package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="修改用户头像请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateHeadPortraitRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4317583985653080257L;

	@ApiModelProperty(value = "用户Id", dataType = "Long", required = true)
	@NotNull(message = "用户Id不能为空！")
	@CheckNumber(message = "用户Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "用户头像，头像图片的base64码值， length =< 512", dataType = "String", required = false)
	@NotBlank(message = "用户头像不能为空！")
	private String headPortrait;
}
