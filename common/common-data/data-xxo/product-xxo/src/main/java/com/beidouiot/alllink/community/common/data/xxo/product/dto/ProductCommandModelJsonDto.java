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
* @Description TODO
* @author longww
* @date 2022年2月17日
*/
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductCommandModelJsonDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2879216550233212082L;

	private String name;
	
	private String code;
	
	private Integer callMode;
	
	private List<ProductCommandModelParamJsonDto> params;
	
	private String remark;
}
