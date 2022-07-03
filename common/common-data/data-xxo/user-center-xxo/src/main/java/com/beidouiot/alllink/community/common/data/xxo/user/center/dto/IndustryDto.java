package com.beidouiot.alllink.community.common.data.xxo.user.center.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

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
public class IndustryDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7885998875031916044L;
	
	@NotBlank(message = "行业名称不能为空！")
	private String name;
	
	@NotBlank(message = "行业编号不能为空！")
	private String code;
	
	private String description;
	
	@NotNull(message = "排序号不能为空！")
	@Min(value = 0, message= "排序号必须是最小值为0的正整数")
	private Integer sortNo;
	
	private Long pid;
	
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
