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
public class ProductEventModelDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1550345285497014963L;

	private String name;
	
	private String code;
	
	private Integer eventType;
	
	private String params;
	
	private String remark;
	
	private Boolean status;
	
	private Long productId;
	
}
