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
 * @Description 行业
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class IndustryUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8678765223527030144L;

	private Long id;
	
	private String name;
	
	private String code;
	
	private String description;
	
	private Integer sortNo;
	
	private Long pid;
	
	private String updatedBy;
	
	
	private String strPid;
	
	public String getStrPid() {
		strPid = pid == null ? "" : String.valueOf(pid);
		return strPid;
	}
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}

}
