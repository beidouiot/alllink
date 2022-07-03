package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="增加角色请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9106465841633484886L;

	@ApiModelProperty(value = "角色名称, length = 20", dataType = "String", required = true)
	@NotBlank(message = "角色名称不能为空！")
	@Length(min=1, max=20, message="角色名称长度不能超过20个字符")
	private String name;
	
	@ApiModelProperty(value = "角色描述, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="角色描述长度不能超过200个字符")
	private String descr;
}
