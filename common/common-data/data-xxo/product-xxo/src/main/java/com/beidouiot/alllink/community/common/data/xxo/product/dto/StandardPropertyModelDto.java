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
* @date 2022年2月8日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class StandardPropertyModelDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -494401075321570068L;

	@NotBlank(message = "属性名称不能为空！")
	private String name;
	
	@NotBlank(message = "属性标识符不能为空！")
	private String code;
	
	@NotBlank(message = "数据类型不能为空！")
	private String dataType;
	
	private String dataSpecs;
	
	@NotNull(message = "访问类型不能为空！")
	private Integer accessType;
	
	@NotNull(message = "发布状态不能为空！")
	private Boolean status;
	
	@NotNull(message = "产品类别id不能为空！")
	private Long productTypeId;
}
