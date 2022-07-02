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
public class ProductCommandModelParamDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2801732995182540813L;

	@NotBlank(message = "参数名称不能为空！")
	private String name;
	
	@NotBlank(message = "参数标识符不能为空！")
	private String code;
	
	@NotBlank(message = "数据类型不能为空！")
	private String dataType;
	
	private String dataSpecs;
	
	@NotNull(message = "发布状态不能为空！")
	private Boolean status;
	
	@NotNull(message = "参数方向不能为空！")
	private Integer direction;
	
	@NotNull(message = "产品指令物模型id不能为空！")
	private Long productCommandModelId;
	
	@NotNull(message = "产品id不能为空！")
	private Long productId;
}
