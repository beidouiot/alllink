package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 标准物模型DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class StandardModelUpdateDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2759200760039305097L;

	private Long id;
	
	private String name;
	
	private Integer funcType;
	
	private String dataType;
	
	private Integer accessType;
	
	private Long productTypeId;
	
	private String updatedBy;

}
