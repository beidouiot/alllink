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
* @date 2022年1月24日
*/
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductModelJsonDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 91972567108273006L;
	
	private Long productId;
	
	private List<ProductPropertyModelJsonDto> properties;
	
	private List<ProductEventModelJsonDto> events;
	
	private List<ProductCommandModelJsonDto> commands;

}
