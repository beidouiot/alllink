package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品类别配置行业类别Dto
* @author longww
* @date 2022年2月16日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductTypeIndustryTypeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2417811193880114638L;

	private Long productTypeId;
	
	private List<Long> industryTypeIds;
	
}
