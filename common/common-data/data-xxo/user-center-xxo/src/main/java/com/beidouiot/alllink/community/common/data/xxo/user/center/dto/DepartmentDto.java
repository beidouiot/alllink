package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description TODO
* @author longww
* @date 2021年4月14日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class DepartmentDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8098324140624031736L;

	@NotBlank(message = "组织/部门名称不能为空！")
	private String name;
	
	@NotBlank(message = "组织/部门编号不能为空！")
	private String code;
	
	private String descr;
	
	@NotNull(message = "排序号不能为空")
	private Integer sortNo;
	
	@NotBlank(message = "组织/部门类型不能为空！")
	private String type;
	
	private Long pid;
	
	private Long tenantId;
	
	
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
	
	private String strPid;
	
	public String getStrPid() {
		strPid = pid == null ? "" : String.valueOf(pid);
		return strPid;
	}
}
