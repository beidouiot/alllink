package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 *
 * @Description 角色菜单
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleMenuDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4186282003152740541L;

	@NotBlank(message = "角色Id不能为空！")
	private Long roleId;
	
	private String strRoleId;
	
	public String getStrRoleId() {
		strRoleId = roleId == null ? "" : String.valueOf(roleId);
		return strRoleId;
	}
	
	private List<Long> menuIds;
	
	private List<String> strMenuIds;
	
	public List<String> getStrMenuIds() {
		strMenuIds = menuIds == null ? null : menuIds.stream().map(x -> String.valueOf(x)).collect(Collectors.toList());
		return strMenuIds;
	}
	
}
