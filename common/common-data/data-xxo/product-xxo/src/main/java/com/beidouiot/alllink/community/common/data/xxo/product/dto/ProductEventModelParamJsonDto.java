package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;

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
public class ProductEventModelParamJsonDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1569190556148860558L;

	private String name;
	
	private String code;
	
	private String dataType;
	
	private String dataSpecs;
	
	private Integer direction;
	
	private Long productEventModelId;
	
}
