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
 * @Description 客户Dto
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class CustomerDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4078515191013231799L;
	
	@NotBlank(message = "客户名不能为空！")
	private String name;
	
	private String descr;
	
	private String city;
	
	private String addr;
	
	private String email;
	
	private String phone;
	
	private String zipCode;
	
	private Long tenantId;
	
	private String strTenantId;
	
	public String getStrTenantId() {
		strTenantId = tenantId == null ? "" : String.valueOf(tenantId);
		return strTenantId;
	}
	
	private String systemCode;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}

}
