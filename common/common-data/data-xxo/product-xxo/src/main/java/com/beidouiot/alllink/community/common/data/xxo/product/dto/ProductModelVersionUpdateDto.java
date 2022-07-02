package com.beidouiot.alllink.community.common.data.xxo.product.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
*
* @Description 产品物模型历史版本DTO
* @author longww
* @date 2021年12月23日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class ProductModelVersionUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1686226899602219912L;

	private Long id;
	
	private String modelContent;
	
	private Boolean userFlag;
	
	private Integer versionNumber;
	
	private String updatedBy;
}
