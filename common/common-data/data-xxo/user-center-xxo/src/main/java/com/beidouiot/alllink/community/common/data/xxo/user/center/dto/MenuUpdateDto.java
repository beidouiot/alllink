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
 * @Description 菜单
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class MenuUpdateDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -560564846600053200L;

	private Long id;

	private String name;

	private String addr;
	
	private String icon;
	
	private String type;
	
	private Integer sortNo;
	
	private String updatedBy;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}

}
