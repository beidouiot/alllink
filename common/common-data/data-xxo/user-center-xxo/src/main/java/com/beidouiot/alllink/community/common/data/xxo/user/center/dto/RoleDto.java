package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import javax.validation.constraints.NotBlank;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 *
 * @Description 角色
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class RoleDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7983527453108255493L;
	
	@NotBlank(message = "角色名称不能为空！")
	private String name;
	
	private String code;
	
	private String descr;
	
	private Long tenantId;
	
	private Long customerId;
	
	private String systemCode;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}
	
	private String strTenantId;
	
	public String getStrTenantId() {
		strTenantId = tenantId == null ? "" : String.valueOf(tenantId);
		return strTenantId;
	}
	
	private String strCustomerId;
	
	public String getStrCustomerId() {
		strCustomerId = customerId == null ? "" : String.valueOf(customerId);
		return strCustomerId;
	}

}
