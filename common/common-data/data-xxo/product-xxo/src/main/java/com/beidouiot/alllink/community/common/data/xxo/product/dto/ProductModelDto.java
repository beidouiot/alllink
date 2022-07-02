package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import javax.validation.constraints.NotNull;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品物模型DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductModelDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5416913221766919485L;
	
	@NotNull(message = "产品id不能为空！")
	private Long productId;

}
