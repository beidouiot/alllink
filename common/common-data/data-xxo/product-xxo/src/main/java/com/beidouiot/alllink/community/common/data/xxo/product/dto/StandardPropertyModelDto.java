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

	private String name;
	
	private String code;
	
	private String dataType;
	
	private String dataSpecs;
	
	private Integer accessType;
	
	private Boolean status;
	
	private Long productTypeId;
	
	private String strId;
	
	public String getStrId() {
		strId = id == null || id == 0 ? "" : String.valueOf(id);
		return strId;
	}
	
	private String strProductTypeId;
	
	public String getStrProductTypeId() {
		strProductTypeId = String.valueOf(productTypeId);
		return strProductTypeId;
	}
	
	
}
