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
 * @Description 用户
 * @author longww
 * @date 2021年4月11日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class UserUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1207417081343898808L;

	private Long id;
	
	private String strId;

	private String username;
	
	private String email;
	
	private String mobile;
	
	private String name;
	
	private String nickname;
	
	private Integer sex;
	
	private Boolean status;
	
	private String userType;
	
	private String headPortrait;
	
	private String weixin;
	
	private String qqNo;
	
	private String descr;
	
	private String code;
	
	private String identityNo;
	
	private String identityType;
	
	private String updatedBy;
	
	public String getStrId() {
		strId = id == null ? "" : String.valueOf(id);
		return strId;
	}

}
