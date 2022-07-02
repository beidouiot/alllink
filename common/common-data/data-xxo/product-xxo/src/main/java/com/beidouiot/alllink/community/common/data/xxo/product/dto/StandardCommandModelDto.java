package com.beidouiot.alllink.community.common.data.xxo.product.dto;


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
* @Description TODO
* @author longww
* @date 2022年2月11日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class StandardCommandModelDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8053983261395532423L;

	@NotBlank(message = "指令名称不能为空！")
	private String name;
	
	@NotBlank(message = "指令标识符不能为空！")
	private String code;
	
	@NotNull(message = "调用方式不能为空！")
	private Integer callMode;
	
	private String params;
	
	private String remark;
	
	@NotNull(message = "发布状态不能为空！")
	private Boolean status;
	
	@NotNull(message = "产品类别id不能为空！")
	private Long productTypeId;
}
