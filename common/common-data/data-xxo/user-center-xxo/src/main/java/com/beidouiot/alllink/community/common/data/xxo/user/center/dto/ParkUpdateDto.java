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
 * @Description 园区/小区
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ParkUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6650295497881635353L;
	
	private Long id;
	
	private String name;
	
	private String type;

	private Integer sortNo;
	
	private String descr;
	
	private Long pid;
	
	private Long tenantId;
	
	private Long customerId;
	
	private String updatedBy;

	
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
