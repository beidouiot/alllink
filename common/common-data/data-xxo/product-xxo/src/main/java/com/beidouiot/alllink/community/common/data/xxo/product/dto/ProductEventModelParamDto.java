package com.beidouiot.alllink.community.common.data.xxo.product.dto;


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
public class ProductEventModelParamDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6875857573729874327L;

	private String name;
	
	private String code;
	
	private String dataType;
	
	private String dataSpecs;
	
	private Boolean status;
	
	private Integer direction;
	
	private Long productEventModelId;

	private Long productId;
}
