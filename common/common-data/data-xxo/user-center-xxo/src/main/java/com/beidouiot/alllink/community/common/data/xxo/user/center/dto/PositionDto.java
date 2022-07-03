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
* @Description 职位
* @author longww
* @date 2021年5月9日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class PositionDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4164281187600500932L;

	@NotBlank(message = "职位名称不能为空！")
	private String name;
	
	@NotBlank(message = "职位编号不能为空！")
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
