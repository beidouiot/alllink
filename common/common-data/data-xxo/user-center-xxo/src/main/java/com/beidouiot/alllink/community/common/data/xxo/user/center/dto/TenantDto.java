package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 *
 * @Description 租户
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class TenantDto extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5201980451841101859L;

	private String name;
	
	private String addr;
	
	private String descr;
	
	private String linkman;
	
	private String email;
	
	private String phone;
	
	private String zipCode;
	
	private Long industryId;
	
	private String city;
	
	private Boolean status;
	
	private String strStatus;
	
	private String systemCode;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}
	
	private String strIndustryId;
	
	public String getStrIndustryId() {
		strIndustryId = strIndustryId == null ? "" : String.valueOf(strIndustryId);
		return strIndustryId;
	}
	
	public String getStrStatus() {
		strStatus = status ? "禁用" : "启用";
		return strStatus;
	}
	
}
