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
* @Description 标准物模型DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class StandardModelDto extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8830826149300335621L;
	
	@NotBlank(message = "属性/事件/命令名称不能为空！")
	private String name;
	
	@NotBlank(message = "属性/事件/命令标识符不能为空！")
	private String code;
	
	@NotNull(message = "模型类型不能为空！")
	private Integer funcType;
	
	@NotBlank(message = "数据类型不能为空！")
	private String dataType;
	
	@NotNull(message = "访问类型不能为空！")
	private Integer accessType;
	
	@NotNull(message = "发布状态不能为空！")
	private Boolean status;
	
	@NotNull(message = "产品类别id不能为空！")
	private Long productTypeId;

}
