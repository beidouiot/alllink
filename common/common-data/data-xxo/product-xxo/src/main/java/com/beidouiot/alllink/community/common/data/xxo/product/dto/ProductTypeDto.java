package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品类别DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductTypeDto extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6360797844713139638L;
	
	private String name;
	
	private String code;
	
	private String descr;
	
	private String strId;
	
	public String getStrId() {
		this.strId = String.valueOf(id);
		return this.strId;
	}

}
