package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品属性模型Json
* @author longww
* @date 2022年1月24日
*/
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductPropertyModelJsonDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4325211697956703286L;

	private String name;
	
	private String code;
	
	private String dataType;
	
	private String dataSpecs;
	
	private Integer accessType;
	
}
