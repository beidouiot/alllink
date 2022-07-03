package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role;

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

/**
*
* @Description 更新角色
* @author longww
* @date 2021年4月11日
*/
@Api(tags="修改角色请求信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleUpdateRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8125751867051202683L;

	@ApiModelProperty(value = "角色Id", dataType = "Long", required = true)
	@NotNull(message = "角色Id不能为空！")
	@CheckNumber(message = "角色Id必须为正整数" )
	private Long id;
	
	@ApiModelProperty(value = "角色名称, length = 20", dataType = "String", required = true)
	@NotBlank(message = "角色名称不能为空！")
	@Length(min=1, max=20, message="角色名称长度不能超过20个字符")
	private String name;
	
	@ApiModelProperty(value = "角色描述, length = 200", dataType = "String", required = false)
	@Length(min=0, max=200, message="角色描述长度不能超过200个字符")
	private String descr;
}
