package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;


import java.io.Serializable;

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
public class PositionUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1566969151398108635L;

	private Long id;
	
	private String name;

	private String code;
	
	private String descr;
	
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
