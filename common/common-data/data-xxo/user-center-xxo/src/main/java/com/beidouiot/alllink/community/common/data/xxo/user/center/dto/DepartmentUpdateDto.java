package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import java.io.Serializable;

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
public class DepartmentUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2242693981006178914L;
	
	private Long id;
	
	private String name;
	
	private String code;
	
	private String descr;
	
	private Integer sortNo;
	
	private String type;
	
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
	
	private String strPid;
	
	public String getStrPid() {
		strPid = pid == null ? "" : String.valueOf(pid);
		return strPid;
	}
}
