package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import java.io.Serializable;

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
public class TenantUpdateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8912331856795346530L;

	private Long id;
	
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
	
	private String updatedBy;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}
	
}
