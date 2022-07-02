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
 * @Description 菜单
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class MenuDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6357908987918031788L;

	private String name;
	
	private String code;

	private String addr;
	
	private String icon;
	
	private String type;
	
	private Integer sortNo;
	
	private Integer level;
	
	private Boolean leafFlag;
	
	private Boolean hasButton;
	
	private Long pid;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}
	
	private String strPid;
	
	public String getStrPid() {
		strPid = pid == null ? "" : String.valueOf(pid);
		return strPid;
	}
}
