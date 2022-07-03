package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;


import javax.validation.constraints.Min;
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
 *
 * @Description 园区/小区
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ParkDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8261264025222339294L;
	
	@NotBlank(message = "园区/小区名称不能为空！")
	private String name;
	
	@NotBlank(message = "节点类型不能为空！")
	private String type;
	
	@NotNull(message = "排序号不能为空！")
	@Min(value = 0, message= "排序号必须是最小值为0的正整数")
	private Integer sortNo;
	
	private String descr;
	
	private Long pid;
	
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
	
	private String strPid;
	
	public String getStrPid() {
		strPid = pid == null ? "" : String.valueOf(pid);
		return strPid;
	}
	
	

}
