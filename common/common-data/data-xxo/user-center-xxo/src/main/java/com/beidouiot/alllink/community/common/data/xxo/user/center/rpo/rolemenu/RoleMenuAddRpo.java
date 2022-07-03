package com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.rolemenu;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.validator.CheckNumber;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Api(tags="角色配置用户信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleMenuAddRpo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6297718457034405324L;
	
	@ApiModelProperty(value = "角色Id", dataType = "Long", required = true)
	@NotNull(message = "角色Id不能为空！")
	@Min( value = 0)
	@CheckNumber( message = "角色Id为正整数")
	private Long roleId;
	
	@ApiModelProperty(value = "菜单/功能Id", dataType = "List", required = true)
	private List<Long> menuIds;

}
