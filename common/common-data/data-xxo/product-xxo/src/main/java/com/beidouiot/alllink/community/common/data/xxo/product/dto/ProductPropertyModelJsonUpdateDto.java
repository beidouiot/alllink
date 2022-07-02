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
public class ProductPropertyModelJsonUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8893966971952852566L;

	private Long id;
	
	private String name;
	
	private String dataType;
	
	private String dataSpecs;
	
	private Integer accessType;
	
	private String updatedBy;
	
}
