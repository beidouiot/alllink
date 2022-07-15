package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.role;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SearchBaseRpo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 
* @author longww
* @date 2021年4月11日
*/
@Api(tags="查询角色请求信息")
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleSearchRpo extends SearchBaseRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7565593279892055119L;

	@ApiModelProperty(value = "角色名称, length = 20", dataType = "String", required = false)
	@NotBlank(message = "角色名称不能为空！")
	@Length(min=1, max=20, message="角色名称长度不能超过20个字符")
	private String name;
	
	@ApiModelProperty(value = "所属租户Id", dataType = "Long", required = false)
	private Long tenantId;

}
