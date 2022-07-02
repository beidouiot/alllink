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
* @date 2022年2月8日
*/
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper=true)
public class StandardPropertyModelUpdateDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6922640178999616455L;
	
	private Long id;
	
	private String name;
	
	private String dataType;
	
	private String dataSpecs;
	
	private Integer accessType;
	
	private Boolean status;
	
	private Long productTypeId;
	
	private String updatedBy;
}