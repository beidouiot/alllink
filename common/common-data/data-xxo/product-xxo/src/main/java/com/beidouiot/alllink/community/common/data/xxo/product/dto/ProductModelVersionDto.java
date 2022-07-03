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
* @Description 产品物模型历史版本DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductModelVersionDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4329719761349931354L;

	@NotBlank(message = "物模型内容不能为空！")
	private String modelContent;
	
	@NotNull(message = "当前使用标志不能为空！")
	private Boolean userFlag;
	
	@NotNull(message = "版本号不能为空！")
	private Integer versionNumber;
	
	@NotNull(message = "产品id不能为空！")
	private Long productId;
}
